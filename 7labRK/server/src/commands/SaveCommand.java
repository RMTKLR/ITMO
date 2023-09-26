package commands;

import collectionManager.FlatCollectionManager;
import fileManager.FileManager;
import serializer.CollectionSerializer;

import java.sql.Connection;

public class SaveCommand extends Command{
    private FlatCollectionManager collectionManager;
    public SaveCommand(FlatCollectionManager collectionManager, Connection con) {
        super("save", "saves collection in file", CommandAccess.SERVER, CommandType.NON_ARGUMENT, con);
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute() {
        String answer = "";
        CollectionSerializer serializer = new CollectionSerializer(collectionManager);
        answer = "collection has been successfully saved";
        return answer;
    }
}
