package Th2;

import java.io.Serializable;

public class Student implements Serializable {
    private int idSV; // Mã sinh viên
    private String maSV; // Mã sinh viên
    private String hoTen; // Họ tên sinh viên
    private int namSinh; // Năm sinh
    private String queQuan; // Quê quán
    private float GPA; // Điểm trung bình

    // Constructor
    public Student(int idSV, String maSV, String hoTen, int namSinh, String queQuan, float GPA) {
        this.idSV = idSV;
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
        this.GPA = GPA;
    }

    // Getter và Setter
    public int getIdSV() {
        return idSV;
    }

    public void setIdSV(int idSV) {
        this.idSV = idSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }
}
