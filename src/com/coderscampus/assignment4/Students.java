package com.coderscampus.assignment4;

public class Students {
    private String studentId;
    private String studentName;
    private String studentCourse;
    private int studentGrade;

    public Students(String id, String name, String course, int grade) {

        this.studentId = id;
       this.studentName = name;
        this.studentCourse = course;
        this.studentGrade = grade;
    }

    public String getId() {
        return studentId;
    }

    public void setId(String id) {
        this.studentId = id;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public String getCourse() {
        return studentCourse;
    }

    public void setCourse(String course) {
        this.studentCourse = course;
    }

    public int getGrade() {
        return studentGrade;
    }

    public void setGrade(int grade) {
        this.studentGrade = grade;
    }

    @Override
    public String toString() {
        return studentId + "," + studentName + " , " + studentCourse + " , " + studentGrade;

    }
}
