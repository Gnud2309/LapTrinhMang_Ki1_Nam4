package Th2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class SearchByNameApp extends JFrame {
    private StudentService studentService;
    private JTextField searchNameField;
    private JTextArea resultArea;

    public SearchByNameApp(StudentService studentService) {
        this.studentService = studentService;

        setTitle("Tìm kiếm theo họ tên");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new JLabel("Nhập họ tên:"));
        searchNameField = new JTextField(15);
        panel.add(searchNameField);

        JButton searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new SearchAction());
        panel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    class SearchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = searchNameField.getText();
            resultArea.setText("");

            try {
                List<Student> foundStudents = studentService.searchByName(name);
                for (Student student : foundStudents) {
                    resultArea.append(student.getHoTen() + "\n");
                }
            } catch (Exception ex) {
                resultArea.append("Lỗi: " + ex.getMessage());
            }
        }
    }
}
