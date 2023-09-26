package commands;

import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUserCmd extends Command {
    private FlatCollectionManager collectionManager;

    public AddUserCmd(FlatCollectionManager collectionManager, Connection con) {
        super("addUser", "adds new USER", CommandAccess.NORMAL, CommandType.ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() throws SQLException {
        String user = this.getUser();
        String pass = this.getPswd();

        if (user != null && pass != null) {
            // Check if the username already exists in the database
            String checkSQL = "SELECT 1 FROM users WHERE username = ?";
            try (PreparedStatement pstmtt = this.getCon().prepareStatement(checkSQL)) {
                pstmtt.setString(1, user);
                ResultSet rs = pstmtt.executeQuery();
                if (rs.next()) {
                    return "Не возможно создать аккаунт! Такой пользователь уже существует!";
                } else {
                    // Insert a new user record into the database
                    String insertSQL = "INSERT INTO users (username, passwd) VALUES (?, ?)";
                    try (PreparedStatement pstmt = this.getCon().prepareStatement(insertSQL)) {
                        pstmt.setString(1, user);
                        pstmt.setString(2, pass);

                        int affectedRows = pstmt.executeUpdate();
                        if (affectedRows > 0) {
                            return "Добавлен новый Юзер: " + user;
                        } else {
                            return "Не возможно создать аккаунт!";
                        }
                    }
                }
            }
        } else {
            return "Неправильные данные!"; // Return a message indicating incorrect data
        }
    }
}
