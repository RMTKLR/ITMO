package commands;

import Manager.CollectionManager;
import Manager.InputDataManager;

public class AddIfMaxCommand implements Command {
    private CollectionManager collectionManager;
    public AddIfMaxCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        if (collectionManager.addIfMax(new InputDataManager(collectionManager).readFlat())) System.out.println("Flat that has max number of rooms has been added successfully.");
        else System.out.println("Flat hasn't added successfully because couldn't find max room.");
    }

    @Override
    public String getDescription() {
        return "To add element to collection if it is max.";
    }
}
