package server;

import collectionManager.FlatCollectionManager;
import commands.CommandManager;
import commands.SaveCommand;
import connection.Request;
import connection.Response;
import fileManager.FileManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private DatagramSocket datagramSocket;
    private CommandManager commandManager;
    private FileManager fileManager;
    private FlatCollectionManager collectionManager;
    public Server(int port, FlatCollectionManager collectionManager, FileManager fileManager){
        initialize(port);
        this.collectionManager = collectionManager;
        commandManager = new CommandManager(collectionManager);
        this.fileManager = fileManager;
    }
    private void initialize(int port){
        try {
            this.datagramSocket = new DatagramSocket(port);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public void run(){
        while(true){
            try{
                datagramSocket.setSoTimeout(100000);
                byte[] receive = new byte[4098];
                byte[] send = new byte[4098];
                System.out.println("Waiting for a client to be connected...!!!");
                DatagramPacket receivePacket = new DatagramPacket(receive,receive.length);
                datagramSocket . receive(receivePacket);
                System.out.println("Client with Ip #"+receivePacket.getAddress()+" has sent data to the server");
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(receive);
                ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                Request request = (Request) inputStream.readObject();
                System.out.println(request.getCommandName()+" has been received from server");
                Response response = commandManager.runCommand(request);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(response);
                System.out.println("Sent to ["+receivePacket.getAddress()+"]\n"+response.getMessage());
                send = outputStream.toByteArray();
                InetAddress IP = receivePacket.getAddress();
                int port = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(send, send.length,IP,port );
                datagramSocket.send(sendPacket);
            }catch (Exception exception){
                SaveCommand saveCommand = new SaveCommand(collectionManager,fileManager) ;
                System.out.println(saveCommand.execute());
                exception.printStackTrace();
            }
           SaveCommand saveCommand = new SaveCommand(collectionManager,fileManager) ;
            System.out.println(saveCommand.execute());
        }
    }
}
