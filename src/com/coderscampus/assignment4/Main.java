package com.coderscampus.assignment4;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		
     Students[] students = parseMasterListFile();
      
     if (students == null || students.length == 0) {
         System.out.println("No students found or failed to read the master list.");
         return;
     }

         ArrayList<Students> compsciStudents = new ArrayList<>();
         ArrayList<Students> apmthStudents = new ArrayList<>();
         ArrayList<Students> statStudents = new ArrayList<>();


         for (Students student : students) {
             if (student.getCourse().startsWith("COMPSCI")) {
                 compsciStudents.add(student);
             } else if (student.getCourse().startsWith("APMTH")) {
                 apmthStudents.add(student);
             } else if (student.getCourse().startsWith("STAT")) {
                 statStudents.add(student);
             }
         }


         sortStudentByGrade(compsciStudents);
         sortStudentByGrade(apmthStudents);
         sortStudentByGrade(statStudents);


         writeStudentToCSV("course1.csv", compsciStudents);
         writeStudentToCSV("course2.csv", apmthStudents);
         writeStudentToCSV("course3.csv", statStudents);
     }

     private static Students[] parseMasterListFile() {
         ArrayList<Students> students = new ArrayList<>();
         try (BufferedReader reader = new BufferedReader(new FileReader("src/master_list.txt"))) {
             String header = reader.readLine();

             if (header == null) {
                 System.out.println("Error: The master list file is empty.");
                 return new Students[0];
             }


             String line;
             while ((line = reader.readLine()) != null) {
                 System.out.println("Reading line: " + line); // Debug print

                 if (line.trim().isEmpty()) {
                     continue;
                 }
                 String[] columns = line.split(",", 4);

                 if (columns.length < 4) {
                     System.out.println("Error: Invalid line format: " + line);
                     continue;
                 }
                     try {
                         String id = columns[0].trim();
                         String name = columns[1].trim();
                         String course = columns[2].trim();
                         int grade = Integer.parseInt(columns[3].trim());
                         Students student = new Student(id, name, course, grade);
                         students.add(student);
                     } catch (NumberFormatException e) {
                         System.out.println("Error: Invalid grade format in line: " + line);
                     }
                 }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return students.toArray(new Students[0]);
     }

     private static void sortStudentByGrade (ArrayList < Students > students) {
         students.sort(Comparator.comparingInt(Students::getGrade).reversed());
     }
     private static void writeStudentToCSV (String file, ArrayList < Students > students){
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
             for (Students student : students) {
                 writer.newLine();
                 writer.flush();
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     
	}
}
