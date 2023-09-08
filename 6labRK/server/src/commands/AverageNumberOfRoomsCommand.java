package commands;

import collectionManager.FlatCollectionManager;

public class AverageNumberOfRoomsCommand extends Command {
    private FlatCollectionManager collectionManager;
    public AverageNumberOfRoomsCommand(FlatCollectionManager collectionManager) {
        super("average_number_of_rooms", "prints average number of rooms", CommandAccess.NORMAL, CommandType.NON_ARGUMENT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        return Integer.toString(collectionManager.averageNumberOfRooms());
    }
}
