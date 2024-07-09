package com.coderscampus.assignment4;

import java.io.*;
import java.util.regex.PatternSyntaxException;

public class Main {
    private static final String FILE_PATH = "src/com/coderscampus/assignment4/student-master-list.csv";
    private static final String FILE_HEADER = "Student ID,Student Name,Course,Grade\n";
    private static final int STUDENT_ARRAY_SIZE = 100;

    public Students[] getStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String currentLine;
            int count = 0;
            Students[] students = new Students[STUDENT_ARRAY_SIZE];
            reader.readLine(); // throw away the first line since it's always the file header, not a Student
            while ((currentLine = reader.readLine()) != null && count < STUDENT_ARRAY_SIZE) {
                students[count++] = parseLine(currentLine);
            }
            return students;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_PATH);
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("File read failed: " + FILE_PATH);
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Students parseLine(String line) {
        try {
            String[] splitLine = line.split(",");
            return new Students(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid format for file: " + line);
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void writeStudentsToFile(Students[] students, String targetFilename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilename))) {
            writer.write(FILE_HEADER);
            for (Students student : students) {
                if (student != null) {
                    writer.write(student.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + targetFilename);
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("File read failed: " + targetFilename);
            System.err.println(e.getMessage());
        }
    }
	
}
