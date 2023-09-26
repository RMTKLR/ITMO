package commands;

import Data.Flat;
import Data.House;
import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddCommand extends Command {
    private FlatCollectionManager collectionManager;
    public AddCommand(FlatCollectionManager collectionManager, Connection con) {
        super("add", "adds new element to the collection", CommandAccess.NORMAL, CommandType.ARGUMENT_WITH_FLAT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() throws SQLException {
        try {
            // Retrieve the Flat object from the command arguments
            Flat f = this.getFlat();

            // Create an instance of BdMain to interact with the database
            BdMain bd = new BdMain();

            // Generate unique IDs for the new flat, house, and user
            int idHouse = bd.getHouseNextId(this.getCon());
            int idFlat = bd.getFlatNextId(this.getCon());
            int idUser = bd.getIdUser(this.getCon(), this.getUser());

            // Set the generated IDs for the Flat object
            f.getHouse().setId(idHouse);
            f.setId(idFlat);
            f.setUserId(idUser);

            // Save the new house record to the database
            bd.savehouse(f, this.getCon());

            // Save the new flat record to the database
            bd.saveToMainTable(f, this.getCon());

            return "Успешно добавлено!"; // Return a success message
        } catch (Exception e) {
            return "Что-то пошло не так!"; // Return an error message
        }
    }
}
