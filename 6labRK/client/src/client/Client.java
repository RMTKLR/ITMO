package client;

import connection.Request;
import connection.Response;
import input.InputData;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Client {
    private DatagramSocket datagramSocket;
    private int port;
    private InputData inputData;

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
            while (true) {
                InetAddress IPAddress = InetAddress.getByName("localhost");
                byte[] sendData;
                byte[] receiveData = new byte[4098];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                Request request = forSending();
                if(request.getCommandName().equals("execute_script")){
                    try {
                        InputData inputData1 = new InputData(new Scanner(new File(request.getCommandArgument())));
                        while(inputData1.getScanner().hasNextLine()){
                            request = inputData1.readCommand();
                            objectOutputStream.writeObject(request);
                            sendData = byteArrayOutputStream.toByteArray();
                            DatagramPacket sendingPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
                            datagramSocket.send(sendingPacket);
                            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            datagramSocket.receive(receivePacket);
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(receiveData);
                            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                            Response response = (Response) objectInputStream.readObject();
                            System.out.println(response.getMessage());
                        }
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                if(request.getCommandName().equals("exit")) break;
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
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Request forSending() {
        System.out.println("Enter a command (help to get help) : ");
        return inputData.readCommand();
    }
}
