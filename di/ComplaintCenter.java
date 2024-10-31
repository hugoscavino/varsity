package com.hugo.di;

import java.util.ArrayList;
import java.util.List;

public class ComplaintCenter {

    // NOTE: The ComplaintCenter only has a List<Student>
    // It has no idea of Graduate Students or Phd students
    private final List<Student> students;

    // Constructor
    public ComplaintCenter(List<Student> students) {
        this.students = students;

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
