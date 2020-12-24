package org.rud.tennis.network;

import java.io.*;
import java.net.*;
import java.util.Arrays;

import java.net.*;

public class Client {
    public String message;
    public String receivedMessage;

    private String name;
    private DatagramSocket clientSocket;
    private InetAddress ipAddress;

    private boolean nameSent = false;
    private boolean runningStatus = true;
    private final int port;


    public Client(String[] args) {
        port = Integer.parseInt(args[1]);
        message = "";
        name = args[2];
        try {
            clientSocket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void send(String data) {
        message = data;
    }

    public void load(final String[] args) throws Exception {
        new Thread() {
            @Override
            public void run() {
                while (runningStatus) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    try {
                        clientSocket.receive(receivePacket);
                    } catch (Exception e) {
                        break;
                    }

                    receivedMessage = new String(receivePacket.getData());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (runningStatus) {
                    try {
                        ipAddress = InetAddress.getByName(args[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (message.equals(""))
                        continue;

                    byte[] sendData = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress,
                            port);

                    try {
                        clientSocket.send(sendPacket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    message = "";
                }
                clientSocket.close();
            }
        }.start();
    }
}