package commands;

import Manager.CollectionManager;
import Manager.InputDataManager;

public class UpdateByAnIdCommand implements Command {
    private CollectionManager collectionManager;
    public UpdateByAnIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        try {
            int id = Integer.parseInt(argument);
            collectionManager.updateByAnId(id, new InputDataManager(collectionManager).readFlat());
        }catch (NumberFormatException e){
            System.out.println("invalid argument");
        }
    }

    @Override
    public String getDescription() {
        return "To update the element by its id.";
    }


}
