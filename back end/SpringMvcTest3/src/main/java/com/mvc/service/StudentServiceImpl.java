package com.mvc.service;

import com.mvc.dao.StudentMapper;
import com.mvc.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
//    public List<Map<String, Object>> queryAllStudent() {
        public List queryAllStudent() {
        List<Student> studentList=studentMapper.queryAllStudent();
//        Map<String, Object> map = null;
//        List<Map<String, Object>> list = new ArrayList();
//        for (Student student : studentList) {
//            map = new HashMap();
//            map.put("id", student.getId());
//            map.put("name", student.getName());
//            map.put("sex", student.getSex());
//            map.put("age", student.getAge());
//            list.add(map);
//        }
        return studentMapper.queryAllStudent() ;
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public int deleteStudentById(int id) {
        return studentMapper.deleteStudentById(id);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public Student queryStudentById(int id) {
        return studentMapper.queryStudentById(id);
    }

    @Override
    public List<Map<String, Object>> queryStudentAny(Student st) {

        List<Student> ls = studentMapper.queryStudentAny(st);
        Map<String, Object> map = null;
        List<Map<String, Object>> list = new ArrayList();
        for (Student student : ls) {
            map = new HashMap();
            map.put("id", student.getId());
            map.put("name", student.getName());
            map.put("sex", student.getSex());
            map.put("age", student.getAge());
            list.add(map);
        }
        System.out.println(list.toString());
        return list;

    }
}
