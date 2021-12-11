package com.example.proiect_real;


import com.example.proiect_real.models.ClassModel;
import com.example.proiect_real.models.StudentModel;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    Faker faker = new Faker();
    List<StudentModel> students = new ArrayList<>();
    List<ClassModel> classes = new ArrayList<>();

    public List<StudentModel> getStudents() {
        String fname, lname, pname;
        boolean sex;
        StudentModel student;
        for (int i = 0; i < 10; i++) {
            fname = faker.name().firstName();
            lname = faker.name().lastName();
            pname = faker.name().fullName();
            sex = faker.random().nextBoolean();

            student = new StudentModel(
                    fname,
                    lname,
                    "19 august",
                    faker.number().randomDouble(4, 1, 2),
                    faker.number().numberBetween(30, 100),
                    fname + lname + "@gmail.com",
                    "stejarului 75",
                    pname,
                    pname + "@gmail.com",
                    "0784126338",
                    sex
            );
            students.add(student);
        }

        System.out.println(students);

        return students;
    }

    public List<ClassModel> getClasses(){

        String teacherName;
        ClassModel classModel;
        for(int i=0;i<7;i++){
            teacherName = faker.name().fullName();
            classModel = new ClassModel(
                    faker.name().title(),
                    teacherName,
                    teacherName+"@gmail.com"
            );
            classes.add(classModel);
        }
        return classes;
    }


}