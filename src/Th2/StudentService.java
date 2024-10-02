package Th2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {
    List<Student> searchByName(String name) throws RemoteException; // Find naem
    List<Student> searchByGPA(float minGPA, float maxGPA) throws RemoteException; // Find GPA trong [min,max]
    void updateStudent(Student student) throws RemoteException; // Update thông tin sinh viên
    List<Student> getAllStudents() throws RemoteException; // GetAll sinh viên
}
