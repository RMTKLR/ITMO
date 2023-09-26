package commands;

import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Logincmd extends Command {
    private FlatCollectionManager collectionManager;

    public Logincmd(FlatCollectionManager collectionManager, Connection con) {
        super("login", "login USER", CommandAccess.NORMAL, CommandType.ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() throws SQLException {
        String user = this.getUser();
        String pass = this.getPswd();

        if (user != null && pass != null) {
            // Query the database to retrieve the stored password for the given username
            String selectSQL = "SELECT passwd FROM users WHERE username = ?";
            try (PreparedStatement pstmt = this.getCon().prepareStatement(selectSQL)) {
                pstmt.setString(1, user);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String storedPassword = rs.getString("passwd");
                    if (pass.equals(storedPassword)) {
                        return "login a " + user; // Return a success message upon successful login
                    } else {
                        return "Логин или пароль не правильный!"; // Return a message indicating incorrect password
                    }
                } else {
                    return "Такого юзера нету в системе!"; // Return a message indicating user not found
                }
            } catch (Exception e) {
                return "Ошибка системы!"; // Return a generic system error message
            }
        } else {
            return "Неправильные данные!"; // Return a message indicating incorrect data
        }
    }
}

