package commands;

import Data.Flat;
import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.function.BiFunction;

public class UpdateCommand extends Command {
    private FlatCollectionManager collectionManager;

    public UpdateCommand(FlatCollectionManager collectionManager, Connection con) {
        super("update", "update an element in the collection with the given id", CommandAccess.NORMAL, CommandType.BOTH_ARGUMENT_AND_ARGUMENT_FLAT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() throws SQLException {
        String dataCSV = "";
        String allRes = "";
        boolean b = false;
        BdMain bd = new BdMain();

        String username = this.getUser();
        String passwd = this.getPswd();
        int id = Integer.parseInt(getStringArg());

        int usernameId = bd.getIdUser(this.getCon(), username);

        // Check if an element with the given ID exists in the collection
        if (collectionManager.getCollection().stream().filter(p -> p.getId() == id).count() >= 1) {
            // Check if the user has permission to update the element
            if (collectionManager.getCollection().stream().filter(p -> p.getId() == id && p.getUserId() == usernameId).count() == 1) {
                b = true;
            }
        } else {
            return "Такой элемент не существует!"; // Return a message indicating that the element doesn't exist
        }

        if (b) {
            Integer grAsmin = null;
            for (Flat i : collectionManager.getCollection()) {
                if (i.getId() == id && i.getUserId() == usernameId) {
                    grAsmin = i.getHouse().getId();
                }
            }

            // Print the house ID for debugging purposes
            System.out.println(grAsmin);

            try {
                Flat stdTmp = this.getFlat();

                // SQL statement to update the flat information
                String sql = "UPDATE flat SET name = ?, x = ?, y = ?, area = ?, numberofrooms = ?, numberofbathrooms = ?, new = ?, transport = ? WHERE id = ? AND userId = ?";
                PreparedStatement preparedStatement = this.getCon().prepareStatement(sql);

                preparedStatement.setString(1, stdTmp.getName());
                preparedStatement.setLong(2, stdTmp.getCoordinates().getX());
                preparedStatement.setFloat(3, stdTmp.getCoordinates().getY());
                preparedStatement.setFloat(4, stdTmp.getArea());
                preparedStatement.setInt(5, stdTmp.getNumberOfRooms());
                preparedStatement.setLong(6, stdTmp.getNumberOfBathrooms());
                preparedStatement.setBoolean(7, stdTmp.isNew());
                preparedStatement.setObject(8, stdTmp.getTransport(), Types.OTHER);

                preparedStatement.setInt(9, id);
                preparedStatement.setInt(10, usernameId);

                // SQL statement to update the house information
                String sqlPerson = "UPDATE house SET name = ?, year = ?, numberOfFloors = ? WHERE id = ?";
                PreparedStatement preparedStatement2 = this.getCon().prepareStatement(sqlPerson);
                preparedStatement2.setString(1, stdTmp.getHouse().getName());
                preparedStatement2.setLong(2, stdTmp.getHouse().getYear());
                preparedStatement2.setLong(3, stdTmp.getHouse().getNumberOfFloors());

                preparedStatement2.setInt(4, grAsmin);

                int affectedRows = preparedStatement.executeUpdate();
                int affectedRows2 = preparedStatement2.executeUpdate();

                if (affectedRows > 0 && affectedRows2 > 0) {
                    System.out.println("Записи успешно обновлены!"); // Print a success message for debugging purposes
                } else {
                    System.out.println("Обновление не было произведено."); // Print an error message for debugging purposes
                }

                allRes = allRes + "Объект успешно изменено!";
                return allRes; // Return a success message
            } catch (Exception e) {
                e.printStackTrace();
                return "Ошибка изменения!"; // Return an error message if an exception occurs
            }
        } else {
            return "У вас нет доступа!"; // Return a message indicating that the user doesn't have permission
        }
    }
}
