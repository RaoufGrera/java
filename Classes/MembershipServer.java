package Classes;

import Classes.MemberMaintainer;
import Exaptions.InvalidMemberNumberException;

import java.io.*;
import java.net.*;

public class MembershipServer {
    private static MemberMaintainer memberMaintainer = new MemberMaintainer();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running and waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle client request in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            while (true) {
                // Read the client's request
                String request = (String) ois.readObject();
                System.out.println("Received request: " + request);

                // Process the request
                String response = processRequest(request);

                // Send the response back to the client
                oos.writeObject(response);
                oos.flush();
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String processRequest(String request) {
        if (request.startsWith("+")) {
            // Add member
            int sepIndex = request.indexOf('+', 1);
            if (sepIndex < 2) {
                return "Invalid add command: " + request;
            }
            String name = request.substring(1, sepIndex);
            String address = request.substring(sepIndex + 1);
            memberMaintainer.addMember(name, address);
            return "Member added: " + name + ", " + address;
        } else if (request.startsWith("-")) {
            // Remove member
            try {
                int number = Integer.parseInt(request.substring(1));
                memberMaintainer.removeMember(number);
                return "Member removed: " + number;
            } catch (NumberFormatException e) {
                return "Invalid remove command: " + request;
            } catch (InvalidMemberNumberException e) {
                return "Error: " + e.getMessage();
            }
        } else if (request.equalsIgnoreCase("LIST")) {
            // List members
            return memberMaintainer.listMembers();
        } else {
            return "Error: Unknown command";
        }
    }
}