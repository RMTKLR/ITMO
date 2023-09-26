package client;

import connection.Request;
import connection.Response;
import input.InputData;

import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private DatagramSocket datagramSocket;
    private int port;
    private InputData inputData;
    private String user = null;
    private String pswd = null;


    public Client(int port) {
        this.port = port;
        initialize();
        inputData = new InputData(new Scanner(System.in));
    }

    private void initialize() {
        try {
            datagramSocket = new DatagramSocket();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void run() {
        try {
            datagramSocket.setSoTimeout(500);
            boolean isExecutingScript = false;
            while (true) {
                InetAddress IPAddress = InetAddress.getByName("localhost");
                byte[] sendData;
                byte[] receiveData = new byte[4098];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                Request request = forSending(user, pswd);
                if(user != null && request.getCommandName().equals("logout")){
                    System.out.println("User " + user + " logout!!!" );
                    user = null;
                    pswd = null;
                }
                else if(user != null && request.getCommandName().equals("user")){
                    System.out.println("User - " + user);
                }
                else if(user!=null || request.getCommandName().equals("addUser") || request.getCommandName().equals("help") || request.getCommandName().equals("login")) {
                    isExecutingScript = false;  // Add this line
                    if (request.getCommandName().equals("execute_script")) {
                        isExecutingScript = true;
                        System.out.println("Starting script execution");
                        try {
                            InputData inputData1 = new InputData(new Scanner(new File(request.getCommandArgument())));
                            while (inputData1.getScanner().hasNextLine()) {
                                request = inputData1.readCommand(user, pswd);
//                            if (request.getCommandName().equals("add")) {
////                                InputData inputData2 = new InputData(new Scanner(System.in));
////                                request.setFlat(inputData2.readFlat());
//                            }
                                objectOutputStream.writeObject(request);
                                sendData = byteArrayOutputStream.toByteArray();
                                DatagramPacket sendingPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                datagramSocket.send(sendingPacket);

                                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                                datagramSocket.receive(receivePacket);
                                ByteArrayInputStream inputStream = new ByteArrayInputStream(receiveData);
                                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                                Response response = (Response) objectInputStream.readObject();
                                System.out.println(response.getMessage());

                                if(request.getCommandName().equals("login")){
                                    user = response.getUser();
                                    pswd = response.getPswd();
                                }
                            }
                        } catch (NoSuchElementException noSuchElementException) {
                            System.err.println("no such element!!");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                        System.out.println("Finished script execution");

                    }

                    if (request.getCommandName().equals("exit")) break;
                    if (!isExecutingScript) {  // Modify this line
                        objectOutputStream.writeObject(request);
                        sendData = byteArrayOutputStream.toByteArray();
                        DatagramPacket sendingPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        datagramSocket.send(sendingPacket);
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        datagramSocket.receive(receivePacket);
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(receiveData);
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        Response response = (Response) objectInputStream.readObject();
                        System.out.println(response.getMessage());

                        if(request.getCommandName().equals("login")){
                            user = response.getUser();
                            pswd = response.getPswd();
                        }
                    }
                }else{
                    System.out.println("Please Login!!!");
                }
            }
        } catch (SocketTimeoutException socketTimeoutException){
            System.out.println(socketTimeoutException);
            System.out.println("Server is down!!");
        }catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private Request forSending(String user, String pswd) {
        return inputData.readCommand(user, pswd);
    }
}
