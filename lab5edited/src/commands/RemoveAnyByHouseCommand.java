package commands;

import Manager.CollectionManager;

public class RemoveAnyByHouseCommand implements Command{
    private CollectionManager collectionManager;
    public RemoveAnyByHouseCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {


    }

    @Override
    public String getDescription() {
        return "To remove an element by their house Name.";
    }
}
