package Th1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class TCPClient {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 160, 25);
        frame.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        frame.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 160, 25);
        frame.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        frame.add(loginButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 110, 250, 80);
        frame.add(resultArea);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                try (Socket socket = new Socket("localhost", 1234);
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

                    output.writeObject(username);
                    output.writeObject(password);

                    String response = (String) input.readObject();
                    if ("SUCCESS".equals(response)) {
                        List<String> users = (List<String>) input.readObject();
                        resultArea.setText("Login successful!\nUsers:\n" + String.join("\n", users));
                    } else {
                        resultArea.setText("Login failed!");
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.setVisible(true);
    }
}
