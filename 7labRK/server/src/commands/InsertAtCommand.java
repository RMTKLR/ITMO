package commands;

import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertAtCommand extends Command {
    private FlatCollectionManager collectionManager;

    public InsertAtCommand(FlatCollectionManager collectionManager, Connection con) {
        super("insert_at", "insert an element in the given index", CommandAccess.NORMAL, CommandType.BOTH_ARGUMENT_AND_ARGUMENT_FLAT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        BdMain bd = new BdMain();
        try {
            // Check if the user associated with the flat matches the user executing the command
            if (bd.getIdUser(this.getCon(), this.getUser()) == this.getFlat().getUserId()) {
                int index = Integer.parseInt(getStringArg());
                answer = " given flat has been successfully inserted in index #" + index;
            } else {
                return "his not a user of this object"; // Return a message indicating the user mismatch
            }
        } catch (NumberFormatException | SQLException exception) {
            answer = "invalid index"; // Return a message indicating an invalid index or other exceptions
        }
        return answer;
    }
}
