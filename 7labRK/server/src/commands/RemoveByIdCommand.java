package commands;

import Data.Flat;
import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveByIdCommand extends Command {
    private FlatCollectionManager collectionManager;
    public RemoveByIdCommand(FlatCollectionManager collectionManager, Connection con) {
        super("remove_by_id", "removes an element by their id", CommandAccess.NORMAL, CommandType.ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        BdMain bd = new BdMain();
        try {
            // Parse the provided argument as an integer ID
            int id = Integer.parseInt(getStringArg());
            for (Flat s : collectionManager.getCollection()) {
                // Check if the flat's ID matches the provided ID
                if (s.getId() == id) {
                    // Check if the user executing the command is the owner of the flat
                    if (s.getUserId() == bd.getIdUser(this.getCon(), this.getUser())) {
                        // Remove the flat and its associated house from the database
                        if (bd.removeByIDHouseAndFlat(s, this.getCon())) {
                            return "flat with id #" + id + " has been successfully removed from the collection";
                        } else {
                            return "can't delete the flat"; // Return an error message if deletion fails
                        }
                    } else {
                        return "his not a user of this object"; // Return a message indicating the user mismatch
                    }
                }
            }
        } catch (NumberFormatException | SQLException exception) {
            return "invalid id"; // Return a message indicating an invalid ID or other exceptions
        }
        return answer;
    }
}
