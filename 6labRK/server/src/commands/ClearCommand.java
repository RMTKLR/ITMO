package commands;

import collectionManager.FlatCollectionManager;

public class ClearCommand extends Command{
    private FlatCollectionManager collectionManager;
    public ClearCommand(FlatCollectionManager collectionManager) {
        super("clear", "clears the collection", CommandAccess.NORMAL, CommandType.NON_ARGUMENT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        collectionManager.clear();
        return "collection has been successfully cleared";
    }
}
