package Main;
import CollectionManager.FlatCollectionManager;
import FileManager.*;
import input.*;
import XML.CollectionDeserializer;

public class Main {
    // common line argument ; String[]args
    public static void main(String[] args) {
        FileManager fileManager = new FileManager(args[0]);
        FlatCollectionManager collectionManager = new FlatCollectionManager();
        CollectionDeserializer collectionDeserializer = new CollectionDeserializer(collectionManager);
        if(collectionDeserializer.deserialize(fileManager.read()))
            System.out.println("data successfully loaded");
        else System.out.println("can't load data");
       Input input = new Input(fileManager,collectionManager);
       input.getInput();
    }
}