package com.mvc.service;

import com.mvc.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    //全部查询
    List<Map<String, Object>> queryAllStudent();

    //增
    int addStudent(Student student);

    //删
    int deleteStudentById(int id);

    //改
    int updateStudent(Student student);
    //查
    Student queryStudentById(int id);

    //通过姓名查询
    List<Map<String,Object>> queryStudentAny(Student st);
}
