package com.coderscampus.assignment4;

import java.io.*;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
        Main main = new Main();
        String masterFile = "master_list.txt";
        Students[] students = File.readStudents(masterFile);
        String[] courses = {"COMPSCI", "APMTH", "STAT"};
        for (int i = 0; i < courses.length; i++) {
            Students[] courseStudents = main.courseOrder(students, courses[i]);
            main.gradeOrder(courseStudents);
            main.writeStudentsToFile(courseStudents,"course" + (i+1) + ".csv");
        }

    }

    public Students[] courseOrder(Students[] students, String course) {
        Students[] orderStudents = new Students[students.length];
        int index = 0;
        for (Students student : students) {
            if (student != null && student.getCourse().contains(course)) {
                orderStudents[index++] = student;

            }
        }
        return orderStudents;
    }

    public void gradeOrder(Students[] students) {
        try {
            Arrays.sort(students, (a, b) -> {
                if (a == null && b == null) return 0;
                if (a == null) return 1;
                if (b == null) return -1;
                return b.getGrade() - a.getGrade();
            });
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeStudentsToFile(Students[] students, String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write("Student ID, Student Name, Student Course, Student Grade\n");
            for (Students student: students) {
                if (student != null) {
                    fw.write(student + "\n");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
