package commands;

import Manager.CollectionManager;

public class RemoveLastCommand implements Command{
    private CollectionManager collectionManager;
    public RemoveLastCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        collectionManager.removeLast();
    }

    @Override
    public String getDescription() {
        return "To remove the last element.";
    }
}
