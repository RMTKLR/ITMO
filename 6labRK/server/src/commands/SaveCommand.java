package commands;

import collectionManager.FlatCollectionManager;
import fileManager.FileManager;
import serializer.CollectionSerializer;

public class SaveCommand extends Command{
    private FlatCollectionManager collectionManager;
    private FileManager fileManager;
    public SaveCommand(FlatCollectionManager collectionManager,FileManager fileManager) {
        super("save", "saves collection in file", CommandAccess.SERVER, CommandType.NON_ARGUMENT);
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }
    @Override
    public String execute() {
        String answer = "";
        CollectionSerializer serializer = new CollectionSerializer(collectionManager);
        if(fileManager.write(serializer.serialize()))
            answer = "collection has been successfully saved";
        else answer = " can't save the collection";
        return answer;
    }
}
