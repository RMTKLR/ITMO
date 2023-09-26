package commands;

import collectionManager.FlatCollectionManager;

import java.sql.Connection;

public class InfoCommand extends Command{
    private FlatCollectionManager collectionManager;
    public InfoCommand(FlatCollectionManager collectionManager, Connection con) {
        super("info", "shows collection 's info", CommandAccess.NORMAL, CommandType.NON_ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        return "ArrayList of Flats size : "+collectionManager.getCollection().size()+" creation date : "+ collectionManager.getLocalDateTime();
    }
}
