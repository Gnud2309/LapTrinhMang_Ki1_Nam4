package Th1;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    public static void main(String[] args) {
        int port = 1234; // Cổng kết nối
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket).start(); // Xử lý mỗi kết nối trên luồng mới
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            String username = (String) input.readObject();
            String password = (String) input.readObject();

            if (authenticate(username, password)) {
                List<String> users = getUsers();
                output.writeObject("SUCCESS");
                output.writeObject(users);
            } else {
                output.writeObject("FAILURE");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate(String username, String password) {
        // Kết nối đến cơ sở dữ liệu MySQL
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:4306/users_db", "root", "")) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<String> getUsers() {
        List<String> userList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:4306/users_db", "root", "")) {
            String sql = "SELECT username FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                userList.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
