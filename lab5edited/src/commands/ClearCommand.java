package commands;

import Manager.CollectionManager;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        if (collectionManager.getCollection().isEmpty()) System.out.println("The collection is already empty!!!");
        else {
            collectionManager.clear();
            System.out.println("\n\t\t\t\t\t\t\tCollection has been successfully cleared!!!");
        }
    }

    @Override
    public String getDescription() {
        return "To clear the collection.";
    }
}
