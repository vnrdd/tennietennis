package org.rud.tennis.network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Server {

    private DatagramSocket serverSocket;
    private static InetAddress IPAddress;
    private static int port;

    private String message;
    private ArrayList<String> users;
    private ArrayList<Table> tables;
    private HashMap<String, Integer> timetable;
    private String player1;
    private String player2;


    public Server(String _port) {
        message = "";
        try {
            serverSocket = new DatagramSocket(Integer.parseInt(_port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connected");
        users = new ArrayList<String>();
        timetable = new HashMap<>();
        tables = new ArrayList<Table>();
        tables.add(new Table());

    }

    public static void main(String[] args) throws Exception {
        final Server server = new Server(args[0]);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    try {
                        server.serverSocket.receive(receivePacket);
                    } catch (Exception e) {
                        break;
                    }

                    server.message = new String(receivePacket.getData());
                    if (!server.message.equals("")) {
                        // System.out.println(server.message);
                        if (server.message.indexOf(" ") != -1){
                            server.player1 = server.message.split(" ")[0];
                            String message = "@" + server.message.split(" ")[1];
                            if (server.users.size() > 0) {
                                int eq = 0;
                                for (int i = 0; i < server.users.size(); i++) {
                                    if (server.users.get(i).contains(server.player1)) {
                                        eq = 1;
                                        break;
                                    }
                                }
                                if (eq == 0) {
                                    server.users.add(server.player1);
                                }
                            }
                        }
                    }

                    IPAddress = receivePacket.getAddress();
                    port = receivePacket.getPort();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (!server.message.equals("@quit")) {
                    byte[] sendData = "Table1&Table2&Table3".getBytes();

                    if (server.message.contains("Y")){
                        sendData = server.message.getBytes();
                    }
                    if(server.message.contains("@f")){
                        String name = server.message.split("@")[0];
                    }
                    if(server.message.contains("@s")){
                        String name = server.message.split("@")[0];
                        server.message = "@" + server.message.split("@")[1];
                    }

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    try {
                        server.serverSocket.send(sendPacket);
                    } catch (Exception e) {
                    }
                }

                server.serverSocket.close();
            }
        }.start();
    }
}