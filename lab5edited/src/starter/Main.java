package starter;

import Manager.CollectionManager;
import Manager.CommandManager;
import Manager.FileManager;
import xml.CollectionDeserializer;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        FileManager fileManager = new FileManager(args[0]);

        CollectionManager collectionManager = new CollectionManager();

        CollectionDeserializer collectionDeserializer = new CollectionDeserializer(collectionManager);

        String fileContent = fileManager.reader();
        if (!fileContent.equals("")) {
            if(collectionDeserializer.deserialize(fileManager.reader()))
                System.out.println("Data has been successfully loaded.");
        }
        else if(fileContent.equals(""))
            System.out.println("collection is empty!!");
        else System.err.println("can't load the data!!!");

        CommandManager commandManager = new CommandManager(fileManager,collectionManager);

        Runner run = new Runner(commandManager.getCommandMap());
        run.runner();



    }
}
