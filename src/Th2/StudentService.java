package Th2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {
    List<Student> searchByName(String name) throws RemoteException; // Tìm sinh viên theo họ tên
    List<Student> searchByGPA(float minGPA, float maxGPA) throws RemoteException; // Tìm sinh viên theo khoảng GPA
    void updateStudent(Student student) throws RemoteException; // Cập nhật thông tin sinh viên
    List<Student> getAllStudents() throws RemoteException; // Lấy tất cả sinh viên
}
