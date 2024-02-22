package commands;

import Manager.CollectionManager;

public class RemoveByIdCommand implements Command{
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        int id = Integer.parseInt(argument);
        collectionManager.removeById(id);
    }

    @Override
    public String getDescription() {
        return "To remove an element by its id.";
    }
}
