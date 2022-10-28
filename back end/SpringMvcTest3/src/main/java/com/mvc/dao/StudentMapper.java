package com.mvc.dao;

import com.mvc.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    //全部查询
    List<Student> queryAllStudent();

    //增
    int addStudent(Student student);

    //删
    int deleteStudentById(@Param("id") int id);

    //改
    int updateStudent(Student student);

    //查
    Student queryStudentById(@Param("id") int id);

    //通过姓名查询
    List<Student> queryStudentAny(Student st);
}
