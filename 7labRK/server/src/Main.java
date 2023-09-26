import Data.Flat;
import bd.BdConnection;
import bd.BdMain;
import collectionManager.FlatCollectionManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import server.Server;

import java.sql.Connection;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        try {
            FlatCollectionManager collectionManager = new FlatCollectionManager();
            Server server = new Server(1234, collectionManager);

            server.run();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.err.println("Server Error!");
        }
    }
}
