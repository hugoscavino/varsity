package com.hugo.di;

import java.util.ArrayList;
import java.util.List;

public class SchoolApplication {
    public static void main(String[] args) {
        // Create a ComplaintCenter
        List<Student> studentsWithComplaints = new ArrayList<>();
        // get all the student with complaints

        // Create a Graduate Student
        Student graduateStudent = new GraduateStudent();

        // Create a PhD Student
        Student phdStudent = new Phd();

        // add the students to the List<Student>
        studentsWithComplaints.add(graduateStudent);
        studentsWithComplaints.add(phdStudent);

        // Add the list of <Student> to the ComplaintCenter
        ComplaintCenter complaintCenter = new ComplaintCenter(studentsWithComplaints);

        /*
         Note that each type of student will print/invoke its OWN IMPLEMENTATION
         The compiler at runtime determines which type it has
         despite only being declared as a List<Student>
         Had we declared a List<Phd> the ComplaintCenter could only
         accept PHD student complaints. This way the ComplaintCenter
         can accept Student and any new type of Student that may be designed later
        */
        complaintCenter.printComplaints();

        System.out.println("--------------------------- PART TIME STUDENTS ADDED ---------------");

        // If the future we want to add parttime students
        // add it to the list
        studentsWithComplaints.add(new PartTimeStudent());
        // Add the list again
        complaintCenter = new ComplaintCenter(studentsWithComplaints);
        // print out the complaints
        complaintCenter.printComplaints();
    }
}
