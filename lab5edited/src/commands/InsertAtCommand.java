package commands;

import Manager.CollectionManager;
import Manager.InputDataManager;

import java.awt.font.NumericShaper;

public class InsertAtCommand implements Command{
    private CollectionManager collectionManager;
    public InsertAtCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        try {
            long index = Long.parseLong(argument);
            collectionManager.insert_at(index, new InputDataManager(collectionManager).readFlat());
        }catch (NumberFormatException e){
            System.err.println("invalid input!!! \ninsertAt index");
        }
    }

    @Override
    public String getDescription() {
        return "To add a new element at a given position";
    }
}
