package Th2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl extends UnicastRemoteObject implements StudentService {
    private Connection connection;

    public StudentServiceImpl() throws RemoteException {
        super();
        // Kết nối đến CSDL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:4306/school", "root", ""); // Thay đổi thông tin đăng nhập
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> searchByName(String name) throws RemoteException {
        List<Student> result = new ArrayList<>();
        String query = "SELECT * FROM students WHERE hoTen LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Student(
                        rs.getInt("idSV"),
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getInt("namSinh"),
                        rs.getString("queQuan"),
                        rs.getFloat("GPA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Student> searchByGPA(float minGPA, float maxGPA) throws RemoteException {
        List<Student> result = new ArrayList<>();
        String query = "SELECT * FROM students WHERE GPA BETWEEN ? AND ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setFloat(1, minGPA);
            pstmt.setFloat(2, maxGPA);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Student(
                        rs.getInt("idSV"),
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getInt("namSinh"),
                        rs.getString("queQuan"),
                        rs.getFloat("GPA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateStudent(Student student) throws RemoteException {
        String query = "UPDATE students SET maSV = ?, hoTen = ?, namSinh = ?, queQuan = ?, GPA = ? WHERE idSV = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, student.getMaSV());
            pstmt.setString(2, student.getHoTen());
            pstmt.setInt(3, student.getNamSinh());
            pstmt.setString(4, student.getQueQuan());
            pstmt.setFloat(5, student.getGPA());
            pstmt.setInt(6, student.getIdSV());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() throws RemoteException {
        List<Student> result = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result.add(new Student(
                        rs.getInt("idSV"),
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getInt("namSinh"),
                        rs.getString("queQuan"),
                        rs.getFloat("GPA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
