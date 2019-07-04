package com.zq.dataservice.controller;


import com.zq.dataservice.bean.Students;
import com.zq.dataservice.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/getAllStudents")
    public Map<String,Object> getAllStudents(){
        Map<String,Object> resultMap = new HashMap<>();
        List<Students> allStudents = studentsService.getAllStudents();
        resultMap.put("data",allStudents);
        return resultMap;
    }
}
