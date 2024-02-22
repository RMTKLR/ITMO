package commands;

import Manager.CollectionManager;
import Manager.FileManager;
import com.thoughtworks.xstream.mapper.Mapper;
import xml.CollectionSerializer;

import java.io.File;

public class SaveCommand implements Command{
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private CollectionSerializer collectionSerializer;
    public File file;
    public SaveCommand(FileManager fileManager, CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        collectionSerializer = new CollectionSerializer(collectionManager);
    }
    @Override
    public void execute(String argument) {
        try {
            if(!file.exists()) throw new NullPointerException("there is no any file to save!!!");
        }catch (NullPointerException e) {
            e.getMessage();
        }
        fileManager.write(collectionSerializer.xmlSerialize());
        System.out.println("Data has been saved successfully to the file.");
    }

    @Override
    public String getDescription() {
        return "To save data to the file.";
    }
}
