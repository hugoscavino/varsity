package com.hugo.di;

import java.util.ArrayList;
import java.util.List;

public class ComplaintCenter {

    // NOTE: The ComplaintCenter only has a List<Student>
    // It has no idea of Graduate Students or Phd students
    private final List<Student> students;

    // Constructor
    public ComplaintCenter() {
        students = new ArrayList<>();
    }

    // Add all the students to our List<Student>
    public void addStudentWithComplaints(List<Student> students) {

        // start from scratch, clear out the previous list
        this.students.clear();
        // Add this list
        this.students.addAll(students);
    }

    public void printComplaints() {
        for (Student student : students) {
            // Magic happens here!
            // When the application is running the
            // compiler will inspect the Student
            // object and know which complain method to invoke
            student.complain();
        }
    }

}
