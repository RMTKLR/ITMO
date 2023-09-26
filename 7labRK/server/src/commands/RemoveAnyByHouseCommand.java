package commands;

import Data.Flat;
import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveAnyByHouseCommand extends Command {
    private FlatCollectionManager collectionManager;

    public RemoveAnyByHouseCommand(FlatCollectionManager collectionManager, Connection con) {
        super("remove_any_by_house", "removes an element from the collection whose house field is the same as the given one", CommandAccess.NORMAL, CommandType.ARGUMENT_WITH_HOUSE, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() throws SQLException {
        String answer = "";
        BdMain bd = new BdMain();

        // Check if the collection is empty
        if (collectionManager.getCollection().isEmpty()) {
            answer = "collection is empty";
        } else {
            for (Flat f : collectionManager.getCollection()) {
                // Check if the house name of the flat matches the provided house name
                if (f.getHouse().getName().equals(this.getHouse().getName())) {
                    // Check if the user executing the command is the owner of the flat
                    if (f.getUserId() == bd.getIdUser(this.getCon(), this.getUser())) {
                        int indF = f.getId();
                        int inH = f.getHouse().getId();
                        try {
                            // Remove the flat and its associated house from the database
                            bd.removeByIDHouseAndFlat(f, this.getCon());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            answer = "BD ERROR!";
                        }
                        answer = "omission has been executed successfully";
                    } else {
                        return "his not a user of this object"; // Return a message indicating the user mismatch
                    }
                }
            }
        }
        return answer;
    }
}
