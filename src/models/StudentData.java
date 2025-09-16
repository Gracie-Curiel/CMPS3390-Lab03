package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private final List<Student> students;
    private String filename;

    public StudentData(){
        filename = "./student.data";
        students = new ArrayList<>();
    }
    // Setters
    public void addStudent(Student student){
        students.add(student);
    }
    public void removeStudent(Student student){
        students.remove(student);
    }

    // Getters
    public List<Student> getStudents(){
        return students;
    }

    public void saveData() throws IOException, ClassCastException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(students);
    }

    public void loadData() throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        List<Student> loaded = (List<Student>) in.readObject();
        students.clear();
        students.addAll(loaded);
    }


}
