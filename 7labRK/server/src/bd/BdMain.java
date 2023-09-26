package bd;

import Data.Coordinates;
import Data.Flat;
import Data.House;
import Data.Transport;

import java.time.LocalDateTime;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class BdMain {
    // Method to retrieve all flat data from the database
    public ArrayDeque<Flat> getAllData(Connection cnt) throws SQLException {
        ArrayDeque<Flat> dt = new ArrayDeque<>();
        BdConnection bd = new BdConnection();

        // Execute a SQL query to retrieve flat and house information
        ResultSet resultSet = bd.executeQuery(cnt, "SELECT * FROM flat m JOIN house p ON m.houseid = p.id");

        // Loop through the result set and create Flat objects
        while (resultSet.next()){
            // Retrieve flat information from the result set
            int flatId = resultSet.getInt(1);
            String flatName = resultSet.getString(2);
            long x = resultSet.getLong(3);
            float y = resultSet.getFloat(4);
            Timestamp creationDateTimestamp = resultSet.getTimestamp(5);
            LocalDateTime creationDate = creationDateTimestamp.toLocalDateTime();
            Float area = resultSet.getFloat(6);
            int numberOfRooms = resultSet.getInt(7);
            Long numberOfBathrooms = resultSet.getLong(8);
            boolean isNew = resultSet.getBoolean(9);
            Transport transport = Transport.valueOf(resultSet.getString(10));
            int userId = resultSet.getInt(12);

            // Retrieve house information from the result set
            int houseId = resultSet.getInt(13);
            String houseName = resultSet.getString(14);
            Long year = resultSet.getLong(15);
            Long numberOfFloors = resultSet.getLong(16);

            // Create a Flat object and add it to the deque
            Flat ft = new Flat(flatId, flatName, new Coordinates(x, y), creationDate, area, numberOfRooms, numberOfBathrooms, isNew, transport, new House(houseId, houseName, year, numberOfFloors), userId);
            dt.add(ft);
        }

        return dt;
    }

    // Method to save a house to the database
    public int savehouse(Flat mySet, Connection cnt) throws SQLException {
        Flat i = mySet;
        int nextId = getHouseNextId(cnt);

        // SQL query to insert a new house record
        String insertPersonSQL = "INSERT INTO House (name, year, numberoffloors) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = cnt.prepareStatement(insertPersonSQL)) {
            pstmt.setString(1, i.getHouse().getName());
            pstmt.setLong(2, i.getHouse().getYear());
            pstmt.setLong(3, i.getHouse().getNumberOfFloors());

            pstmt.executeUpdate();
        }
        return nextId;
    }

    // Method to save a flat to the main table in the database
    public boolean saveToMainTable(Flat mySet, Connection cnt) {
        Flat i = mySet;
        String insertMainTableSQL = "INSERT INTO Flat (name, x, y, creationDate, area, numberOfRooms, numberOfBathrooms, \"new\", transport, houseid, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = cnt.prepareStatement(insertMainTableSQL)) {
            pstmt.setString(1, i.getName());
            pstmt.setLong(2, i.getCoordinates().getX());
            pstmt.setFloat(3, i.getCoordinates().getY());
            pstmt.setTimestamp(4, Timestamp.valueOf(i.getCreationDate()));
            pstmt.setFloat(5, i.getArea());
            pstmt.setInt(6, i.getNumberOfRooms());
            pstmt.setLong(7, i.getNumberOfBathrooms());
            pstmt.setBoolean(8, i.isNew());
            pstmt.setObject(9, i.getTransport().toString(), Types.OTHER);
            pstmt.setInt(10, i.getHouse().getId());
            pstmt.setInt(11, i.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    // Method to get the next house ID from a sequence
    public int getHouseNextId(Connection conn) throws SQLException {
        String sql = "SELECT last_value FROM house_id_seq";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching next ID: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;  // Return -1 or throw an exception to indicate an error
    }

    // Method to get the next flat ID from a sequence
    public int getFlatNextId(Connection conn) throws SQLException {
        String sql = "SELECT last_value FROM flat_id_seq";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching next ID: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;  // Return -1 or throw an exception to indicate an error
    }

    // Method to get the ID of a user by their username
    public int getIdUser(Connection conn, String name) throws SQLException {
        String selectSQL = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    // Method to remove a flat and its associated house by ID
    public boolean removeByIDHouseAndFlat(Flat flat, Connection conn) throws SQLException {
        int id = flat.getId();
        int houseId = flat.getHouse().getId();
        try {
            // Delete the flat record
            String deleteSQL = "DELETE FROM flat WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, flat.getId());
                int affectedRows = pstmt.executeUpdate();
            }
            // Delete the associated house record
            String deleteSQLl = "DELETE FROM house WHERE id = ?";
            try (PreparedStatement pstmtt = conn.prepareStatement(deleteSQLl)) {
                pstmtt.setInt(1, flat.getHouse().getId());
                int affectedRowss = pstmtt.executeUpdate();
                if (affectedRowss > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }catch (Exception e){
            System.out.println("BD ERROR!" + e.getMessage());
            return false;
        }
    }

    // Method to remove a flat by ID and user ID
    public boolean removeByID(Flat flat, Connection conn) throws SQLException {
        int id = flat.getId();
        int houseId = flat.getHouse().getId();
        String deleteSQL = "DELETE FROM flat WHERE id = ? and userid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.setInt(1, flat.getUserId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                String deleteSQLl = "DELETE FROM house WHERE id = ?";
                try (PreparedStatement pstmtt = conn.prepareStatement(deleteSQLl)) {
                    pstmtt.setInt(1, houseId);
                    int affectedRowss = pstmtt.executeUpdate();
                    if (affectedRowss > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }

    // Method to clear all records associated with a user ID
    public boolean clearByUserID(int id, Connection conn) throws SQLException {
        ArrayDeque<Flat> dt = new ArrayDeque<>();
        BdConnection bd = new BdConnection();

        // Retrieve house IDs associated with the user
        String sql = "SELECT houseid FROM flat m JOIN house p ON m.houseid = p.id and m.userid = " + id + ";";
        LinkedList<Integer> s = new LinkedList<>();
        ResultSet resultSet = bd.executeQuery(conn, sql);
        while (resultSet.next()) {
            int houseId = resultSet.getInt(1);
            s.add(houseId);
        }
        // Delete flat records associated with the user
        String deleteSQLL = "DELETE FROM flat WHERE userid = ?";
        try (PreparedStatement pstmtt = conn.prepareStatement(deleteSQLL)) {
            pstmtt.setInt(1, id);
            int affectedRowss = pstmtt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("BD ERROR");
            return false;
        }

        // Delete house records associated with the user
        for(Integer houseId: s) {
            String deleteSQL = "DELETE FROM house WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, houseId);
                int affectedRows = pstmt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("BD ERROR!");
                return false;
            }
        }
        return true;
    }
}
