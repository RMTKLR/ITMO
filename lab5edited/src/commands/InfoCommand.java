package commands;

import Manager.CollectionManager;

public class InfoCommand implements Command{
    private  CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        collectionManager.info();
    }

    @Override
    public String getDescription() {
        return "To display information about the collection.";
    }
}
