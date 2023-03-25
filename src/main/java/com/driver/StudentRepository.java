package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    HashMap <String,Student> studentMap = new HashMap<>();

    HashMap <String,Teacher> teacherMap = new HashMap<>();

    HashMap <String,String> teacherStudentMap = new HashMap<>();
    public void addStudent(Student student) {
        String key = student.getName();
        studentMap.put(key,student);
    }


    public void addTeacher(Teacher teacher) {
        String key = teacher.getName();
        teacherMap.put(key,teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        teacherStudentMap.put(student,teacher);
    }


    public Student getStudentName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentList = new ArrayList<>();
        for(String tempStudent : teacherStudentMap.keySet()){
            if(teacherStudentMap.get(tempStudent).equals(teacher)){
                studentList.add(tempStudent);
            }
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(String student : studentMap.keySet()){
            studentList.add(student);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
        for(Map.Entry<String,String> entry : teacherStudentMap.entrySet()) {
            if(entry.getValue().equals(teacher)){

                String studentName = entry.getKey();

                studentMap.remove(studentName);
                teacherStudentMap.remove(studentName);
            }
        }
    }

    public void deleteAllTeachers() {

        for(String teacherName : teacherMap.keySet()){
            teacherMap.remove(teacherName);

            for(Map.Entry<String,String> entry : teacherStudentMap.entrySet()){
                if(entry.getValue().equals(teacherName)){
                    String studentName = entry.getKey();

                    studentMap.remove(studentName);
                    teacherStudentMap.remove(studentName);
                }
            }
        }
    }
}
