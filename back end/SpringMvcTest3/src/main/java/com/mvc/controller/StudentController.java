package com.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mvc.pojo.Student;
import com.mvc.service.StudentService;
import com.mvc.utils.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentService studentService;

    //查询全部学生，并返回一个学生展示页
    @RequestMapping(value = "/allstudent",produces="text/html; charset=UTF-8")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8081")
    public String list(Model model) {

//        List<Map<String,Object>> studentList= studentService.queryAllStudent();
        List studentList= studentService.queryAllStudent();
        List<Student> studentList1=new ArrayList<Student>();
//      List studentList= studentService.queryAllStudent();
        String jsonString= JSON.toJSONString(studentList);
        model.addAttribute("studentList",studentList1);
//        System.out.println(jsonString);
        return jsonString;
//    return "allstudent";
    }
    //跳转到新增页面
    @RequestMapping("/toaddstudent")
    public String toAddpaper(){
        return "addstudent";
    }

    //添加学生
    @RequestMapping ("/addstudent")
    @CrossOrigin(origins = "http://localhost:8081")
    public int addstudent(Student student,HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject jsonObject = JsonReader.receivePost(request);
        String sex = jsonObject.getString("sex");
        String age = jsonObject.getString("age");
        String name = jsonObject.getString("name");
//
//        System.out.println(sex + age + name + "aaaa");

        Student st = new Student();
        st.setAge(Integer.parseInt(age));
        st.setSex(sex);
        st.setName(name);
        int add = studentService.addStudent(st);
//        List<Map<String,Object>> studentList= studentService.queryAllStudent();
//        String jsonString= JSON.toJSONString(studentList);
        return add;
//        return null;
    }
    //删除
    @RequestMapping("/deletestudent")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8081")
    public JSONObject deleteStudent(HttpServletRequest request) throws IOException {

        JSONObject jsonObject=JsonReader.receivePost(request);
        String id=jsonObject.getString("deleteid");
        System.out.println(id+"aaaaaaa");
        int delete= studentService.deleteStudentById(Integer.parseInt(id));
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("states",id);
        return jsonObject1;
//        return null;
    }
    //跳到修改修页面
    @RequestMapping("/toupdatestudent")
    public String toupdatestudent(int id,Model model){
        Student student=studentService.queryStudentById(id);
        model.addAttribute("qstudent",student);
        return "updatestudent";
    }

    //改
    @RequestMapping("/updatestudent")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8081")
    public int updatestudent(Student student,HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject jsonObject = JsonReader.receivePost(request);
        String id = jsonObject.getString("updateid");
        String sex = jsonObject.getString("sex");
        String age = jsonObject.getString("age");
        String name = jsonObject.getString("name");
        Student st = new Student();
        st.setId(Integer.parseInt(id));
        st.setAge(Integer.parseInt(age));
        st.setSex(sex);
        st.setName(name);
        int update=studentService.updateStudent(st);
        return update;
//    return null;
    }


    //查询学生
    @RequestMapping( value = "/querystudent",produces="text/html; charset=UTF-8")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8081")
    public String queryStudentAny(String queryStudentName,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        JSONObject jsonObject = JsonReader.receivePost(request);
        Student st=new Student();
        if (!jsonObject.get("name").equals("")) {
            String name = jsonObject.getString("name");
            st.setName("%" + name + "%");
        }
        if (!jsonObject.get("sex").equals("") && jsonObject.containsKey("sex")) {
            String sex = jsonObject.getString("sex");
            st.setSex(sex);
        } else {
            st.setSex(null);
        }

        if (!jsonObject.get("age").equals("") && jsonObject.containsKey("age")) {
            String age = jsonObject.getString("age");
            st.setAge(Integer.valueOf(age));
        } else {
            st.setAge(-1);
        }
        List<Map<String, Object>> list =  studentService.queryStudentAny(st);
        System.out.println(list);
        String jsonString= JSON.toJSONString(list);
        return jsonString;
    }

}
