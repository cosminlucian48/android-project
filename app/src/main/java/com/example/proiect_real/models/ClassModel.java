package com.example.proiect_real.models;

import java.io.Serializable;

public class ClassModel  implements Serializable {
    private String className;
    private String classTeacher;
    private String teacherMail;

    public ClassModel(String className, String classTeacher, String teacherMail) {
        this.className = className;
        this.classTeacher = classTeacher;
        this.teacherMail = teacherMail;
    }

    public String getClassName() {
        return className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public String getTeacherMail() {
        return teacherMail;
    }
}
