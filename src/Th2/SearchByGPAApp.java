package Th2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchByGPAApp extends JFrame {
    private StudentService studentService;
    private JTextField minGPAField, maxGPAField;
    private JTextArea resultArea;

    public SearchByGPAApp(StudentService studentService) {
        this.studentService = studentService;

        setTitle("Tìm kiếm theo khoảng GPA");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new JLabel("GPA Min:"));
        minGPAField = new JTextField(5);
        panel.add(minGPAField);

        panel.add(new JLabel("GPA Max:"));
        maxGPAField = new JTextField(5);
        panel.add(maxGPAField);

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
            String minGPA = minGPAField.getText();
            String maxGPA = maxGPAField.getText();
            resultArea.setText("");

            try {
                if (!minGPA.isEmpty() && !maxGPA.isEmpty()) {
                    float min = Float.parseFloat(minGPA);
                    float max = Float.parseFloat(maxGPA);
                    List<Student> gpaStudents = studentService.searchByGPA(min, max);
                    for (Student student : gpaStudents) {
                        resultArea.append(student.getHoTen() + "\n");
                    }
                }
            } catch (Exception ex) {
                resultArea.append("Lỗi: " + ex.getMessage());
            }
        }
    }
}
