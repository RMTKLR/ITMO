import collectionManager.FlatCollectionManager;
import deserializer.CollectionDeserializer;

import fileManager.FileManager;
import server.Server;
public class Main {
    public static void main(String[] args) {
        try{
            FileManager fileManager = new FileManager();
            fileManager.setPath(args[0]);
            FlatCollectionManager collectionManager = new FlatCollectionManager();
            if(!fileManager.read().equals("")) {
                CollectionDeserializer deserializer = new CollectionDeserializer();
                collectionManager.setCollection(deserializer.deserialize(fileManager.read()));
                collectionManager.setIds(deserializer.getIds());
            } else System.out.println("file was empty");
                Server server = new Server(1234, collectionManager,fileManager);
                server.run();
        }catch (IndexOutOfBoundsException exception){
            System.err.println("you have forget to pass the path by command line argument");
        }
    }
}