package server;

import Data.Flat;
import bd.BdConnection;
import bd.BdMain;
import collectionManager.FlatCollectionManager;
import commands.CommandManager;
import connection.Request;
import connection.Response;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private DatagramSocket datagramSocket;
    private CommandManager commandManager;
    private FlatCollectionManager collectionManager;
    BdConnection bd = new BdConnection();
    Connection cnt = bd.getConnection();
    BdMain bdCntr = new BdMain();
    public Server(int port, FlatCollectionManager collectionManager) throws SQLException {
        initialize(port);
        this.collectionManager = collectionManager;
        commandManager = new CommandManager(collectionManager, cnt);
        ArrayDeque<Flat> setT = bdCntr.getAllData(cnt);
        this.collectionManager.setCollection(setT);
    }

    private void initialize(int port) {
        try {
            this.datagramSocket = new DatagramSocket(port);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Start the server and handle incoming requests
    public void run() {
        ExecutorService requestExecutor = Executors.newFixedThreadPool(5); // Fixed thread pool for handling requests
        ExecutorService responseExecutor = Executors.newCachedThreadPool(); // Cached thread pool for sending responses

        while (true) {
            try {
                datagramSocket.setSoTimeout(20000000);
                byte[] receive = new byte[4098];
                System.out.println("Waiting for a client to be connected...!!!");
                DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
                datagramSocket.receive(receivePacket);
                System.out.println("Client with IP #" + receivePacket.getAddress() + " has sent data to the server");

                // Handle each request in a separate thread
                requestExecutor.execute(() -> {
                    try {
                        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(receive);
                        ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                        Request request = (Request) inputStream.readObject();
                        System.out.println(request.getCommandName() + " has been received from the client");

                        ArrayDeque<Flat> setT = bdCntr.getAllData(cnt);
                        this.collectionManager.setCollection(setT);

                        Response response = commandManager.runCommand(request, cnt);

                        // Send response in a separate thread
                        responseExecutor.execute(() -> {
                            try {
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                                objectOutputStream.writeObject(response);
                                System.out.println("Sent to [" + receivePacket.getAddress() + "]\n" + response.getMessage());
                                byte[] send = new byte[4098];
                                send = outputStream.toByteArray();
                                InetAddress IP = receivePacket.getAddress();
                                int port = receivePacket.getPort();
                                DatagramPacket sendPacket = new DatagramPacket(send, send.length, IP, port);
                                datagramSocket.send(sendPacket);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (SocketTimeoutException socketTimeoutException) {
                System.out.println("Server is down");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
