package commands;

import collectionManager.FlatCollectionManager;

import java.sql.Connection;

public class AverageNumberOfRoomsCommand extends Command {
    private FlatCollectionManager collectionManager;
    public AverageNumberOfRoomsCommand(FlatCollectionManager collectionManager, Connection con) {
        super("average_number_of_rooms", "prints average number of rooms", CommandAccess.NORMAL, CommandType.NON_ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        return Integer.toString(collectionManager.averageNumberOfRooms());
    }
}
