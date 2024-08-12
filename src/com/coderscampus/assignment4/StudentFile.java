package com.coderscampus.assignment4;

import java.io.*;

public class StudentFile {
			
			public static Students[] readStudents(String Master_File) {
		        Students[] students = new Students[100];
		      
		        try (BufferedReader br = new BufferedReader(new FileReader(Master_File))) {
		            String line;
		            br.readLine();
		            int index = 0;

		            while ((line = br.readLine()) != null) {
		                String[] data = line.split(",");
		                String id = data[0];
		                String name = data[1];
		                String course = data[2];
		                int grade = Integer.parseInt(data[3]);
		                students[index++] = new Students(id, name, course, grade);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();

		        }
		        return students;
		    }
	}