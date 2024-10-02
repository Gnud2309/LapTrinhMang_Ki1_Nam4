package Th2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StudentServer {
    public static void main(String[] args) {
        try {
            StudentService service = new StudentServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("StudentService", service);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
