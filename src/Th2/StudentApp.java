package Th2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StudentApp {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            StudentService service = (StudentService) registry.lookup("StudentService");

            // Tạo frame chính
            JFrame mainFrame = new JFrame("Quản lý sinh viên");
            mainFrame.setSize(400, 300);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLayout(new GridBagLayout());

            // Thay đổi màu nền
            mainFrame.getContentPane().setBackground(new Color(240, 240, 240));

            // Tạo panel để chứa các nút
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 hàng, 1 cột, khoảng cách 10

            // Tạo các nút
            JButton searchByNameButton = new JButton("Tìm kiếm theo họ tên");
            JButton searchByGPAButton = new JButton("Tìm kiếm theo GPA");
            JButton updateStudentButton = new JButton("Cập nhật sinh viên");
            JButton exitButton = new JButton("Thoát");

            // Thêm sự kiện cho các nút
            searchByNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SearchByNameApp(service);
                }
            });

            searchByGPAButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SearchByGPAApp(service);
                }
            });

            updateStudentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdateStudentApp(service);
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); // Thoát ứng dụng
                }
            });

            // Thay đổi màu cho các nút
            searchByNameButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
            searchByGPAButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
            updateStudentButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
            exitButton.setBackground(new Color(255, 69, 0)); // Red-Orange

            // Thêm các nút vào panel
            buttonPanel.add(searchByNameButton);
            buttonPanel.add(searchByGPAButton);
            buttonPanel.add(updateStudentButton);
            buttonPanel.add(exitButton);

            // Thêm panel vào frame chính
            mainFrame.add(buttonPanel);

            // Hiển thị frame
            mainFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

