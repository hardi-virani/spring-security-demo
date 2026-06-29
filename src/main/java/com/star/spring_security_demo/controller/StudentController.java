package com.star.spring_security_demo.controller;

import com.star.spring_security_demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "hardi", "Java"),
            new Student(2, "mann", "MLOps")
    ));

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {

        return (CsrfToken) request.getAttribute("_csrf"); // _csrf came from : localhost:8080/logout -> insepect -> elements tab -> extend form -> input name = "_csrf".

    }



    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {

        students.add(student);

    }

}
