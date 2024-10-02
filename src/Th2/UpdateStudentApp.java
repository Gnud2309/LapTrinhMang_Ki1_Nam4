package Th2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentApp extends JFrame {
    private StudentService studentService;
    private JTextField updateIDField, updateNameField, updateGPAField, updateYearField, updateAddressField;
    private JTextArea resultArea;

    public UpdateStudentApp(StudentService studentService) {
        this.studentService = studentService;

        setTitle("Cập nhật sinh viên");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("ID sinh viên:"));
        updateIDField = new JTextField();
        panel.add(updateIDField);

        panel.add(new JLabel("Tên sinh viên:"));
        updateNameField = new JTextField();
        panel.add(updateNameField);

        panel.add(new JLabel("Năm sinh:"));
        updateYearField = new JTextField();
        panel.add(updateYearField);

        panel.add(new JLabel("Địa chỉ:"));
        updateAddressField = new JTextField();
        panel.add(updateAddressField);

        panel.add(new JLabel("GPA:"));
        updateGPAField = new JTextField();
        panel.add(updateGPAField);

        JButton updateButton = new JButton("Cập nhật");
        updateButton.addActionListener(new UpdateAction());
        panel.add(updateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    class UpdateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(updateIDField.getText());
                String name = updateNameField.getText();
                int year = Integer.parseInt(updateYearField.getText());
                String address = updateAddressField.getText();
                float gpa = Float.parseFloat(updateGPAField.getText());
                Student updatedStudent = new Student(id, "SV" + id, name, year, address, gpa);
                studentService.updateStudent(updatedStudent);
                resultArea.append("Thông tin sinh viên đã được cập nhật.\n");
            } catch (Exception ex) {
                resultArea.append("Lỗi: " + ex.getMessage());
            }
        }
    }
}
