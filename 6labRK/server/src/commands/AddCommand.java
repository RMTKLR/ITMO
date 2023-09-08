package commands;

import collectionManager.FlatCollectionManager;

public class AddCommand extends Command{
    private FlatCollectionManager collectionManager;
    public AddCommand(FlatCollectionManager collectionManager) {
        super("add", "adds new element to the collection ", CommandAccess.NORMAL, CommandType.ARGUMENT_WITH_FLAT);
        this.collectionManager =collectionManager;
    }

    @Override
    public String execute() {
        return collectionManager.add(getFlat());
    }
}
