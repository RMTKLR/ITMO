package commands;

import Manager.CollectionManager;

public class AverageOfNumberOfRoomsCommand implements Command {
    private CollectionManager collectionManager;
    public AverageOfNumberOfRoomsCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        collectionManager.average_of_number_of_rooms();
    }

    @Override
    public String getDescription() {
        return "To display the average of number of rooms.";
    }
}
