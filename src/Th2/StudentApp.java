package Th2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class StudentApp {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            StudentService service = (StudentService) registry.lookup("StudentService");

            JFrame mainFrame = new JFrame("Quản lý sinh viên");
            mainFrame.setSize(400, 300);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

            // Tìm kiếm theo họ tên
            JButton searchByNameButton = new JButton("Tìm kiếm theo họ tên");
            searchByNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SearchByNameApp(service);
                }
            });
            mainFrame.add(searchByNameButton);

            // Tìm kiếm theo GPA
            JButton searchByGPAButton = new JButton("Tìm kiếm theo GPA.");
            searchByGPAButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SearchByGPAApp(service);
                }
            });
            mainFrame.add(searchByGPAButton);

            // Cập nhật sinh viên
            JButton updateStudentButton = new JButton("Cập nhật sinh viên.");
            updateStudentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdateStudentApp(service);
                }
            });
            mainFrame.add(updateStudentButton);

            mainFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

