package commands;

import Manager.CollectionManager;

public class PrintFieldDescendingHouseCommand implements Command{
    private CollectionManager collectionManager;
    public PrintFieldDescendingHouseCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        collectionManager.print_field_descending_house();
    }

    @Override
    public String getDescription() {
        return "To display descending field house.";
    }
}
