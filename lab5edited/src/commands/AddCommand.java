package commands;

import Manager.CollectionManager;
import Manager.InputDataManager;

public class AddCommand implements Command{
    private CollectionManager collectionManager;
    private InputDataManager inputDataManager;

    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.inputDataManager = new InputDataManager(collectionManager);
    }

    @Override
    public void execute(String argument) {
       collectionManager.add(inputDataManager.readFlat());
    }



    @Override
    public String getDescription() {
        return "To add a new element to the collection.";
    }
}
