package controllers;

import models.Student;
import models.StudentData;
import views.StudentGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    public StudentController(StudentData model, StudentGUI view){
        ArrayList<Student> students = model.getStudents();

        students.forEach( s -> {
            view.addStudent(s);
        });

        view.setAddStudentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = view.getFirstName();
                String lastName = view.getLastName();
                String studentID = view.getStudentID();

                if(firstName.isBlank() || lastName.isBlank() || studentID.isBlank()){
                    view.showError("You must fill out all fields");
                    return;
                }

                Student student = new Student(firstName, lastName, Integer.parseInt(studentID));
                try{
                    model.addStudent(student);
                }catch(SQLException ex){
                    view.showError("Unable to add user");
                    return;
                }

                view.addStudent(student);
                view.clearForm();
            }
        });
        view.setRemoveStudentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = view.getSelectedStudent();

                if(student == null){
                    view.showError("You must select a student to remove!");
                    return;
                }
                model.removeStudent(student);

                view.removeStudent(student);

            }
        });
        view.setButtonClearForm(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearForm();
            }
        });


    }
}
