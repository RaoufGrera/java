package Classes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class MembershipClient extends JFrame {
    private JTextField nameField, addressField, numberField;
    private JTextArea outputArea;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public MembershipClient() {
        setTitle("Health Club Membership System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Number:"));
        numberField = new JTextField();
        inputPanel.add(numberField);
        add(inputPanel, BorderLayout.NORTH);

        // Output Panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Member");
        JButton removeButton = new JButton("Remove Member");
        JButton listButton = new JButton("List Members");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(listButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Connect to the server
        connectToServer();

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                if (!name.isEmpty() && !address.isEmpty()) {
                    sendRequest("ADD " + name + " " + address);
                } else {
                    outputArea.append("Error: Name and address cannot be empty.\n");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = numberField.getText();
                if (!number.isEmpty()) {
                    sendRequest("REMOVE " + number);
                } else {
                    outputArea.append("Error: Membership number cannot be empty.\n");
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequest("LIST");
            }
        });
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 12345);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            outputArea.append("Connected to server.\n");
        } catch (IOException e) {
            outputArea.append("Error: Could not connect to server.\n");
            e.printStackTrace();
        }
    }

    private void sendRequest(String request) {
        try {
            oos.writeObject(request);
            oos.flush();
            String response = (String) ois.readObject();
            outputArea.append(response + "\n");
        } catch (IOException | ClassNotFoundException e) {
            outputArea.append("Error: Failed to send/receive data.\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MembershipClient client = new MembershipClient();
            client.setVisible(true);
        });
    }
}