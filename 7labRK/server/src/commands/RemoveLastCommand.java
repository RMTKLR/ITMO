package commands;

import Data.Flat;
import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveLastCommand extends Command {
    private FlatCollectionManager collectionManager;

    public RemoveLastCommand(FlatCollectionManager collectionManager, Connection con) {
        super("remove_last", "removes the last element from the collection", CommandAccess.NORMAL, CommandType.NON_ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        BdMain bd = new BdMain();

        // Check if the collection is empty
        if (collectionManager.getCollection().isEmpty()) {
            answer = "collection is empty";
        } else {
            try {
                // Get the ID of the last flat in the database
                int ind = bd.getFlatNextId(this.getCon()) - 1;

                for (Flat s : collectionManager.getCollection()) {
                    // Check if the flat's ID matches the ID of the last flat
                    if (s.getId() == ind) {
                        // Check if the user executing the command is the owner of the flat
                        if (s.getUserId() == bd.getIdUser(this.getCon(), this.getUser())) {
                            // Remove the last element from the database
                            if (bd.removeByIDHouseAndFlat(s, this.getCon())) {
                                return "last element has been successfully deleted";
                            } else {
                                return "can't delete the last element"; // Return an error message if deletion fails
                            }
                        } else {
                            return "his not a user of this object"; // Return a message indicating the user mismatch
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return answer;
    }
}
