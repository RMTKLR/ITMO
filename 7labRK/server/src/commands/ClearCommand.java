package commands;

import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;

public class ClearCommand extends Command {
    private FlatCollectionManager collectionManager;

    public ClearCommand(FlatCollectionManager collectionManager, Connection con) {
        super("clear", "clears the collection", CommandAccess.NORMAL, CommandType.NON_ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        // Create an instance of BdMain to interact with the database
        BdMain bd = new BdMain();
        try {
            // Clear the collection for the user specified in the command
            bd.clearByUserID(bd.getIdUser(this.getCon(), this.getUser()), this.getCon());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error BD!"; // Return an error message in case of database-related issues
        }
        return "collection has been successfully cleared"; // Return a success message
    }
}
