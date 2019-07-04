package com.zq.dataservice.service;

import com.zq.dataservice.bean.Students;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {

    public List<Students> getAllStudents(){

        List<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(1,"张琦",31,"黄埔军校","mooc"));
        studentsList.add(new Students(2,"黄渤",31,"烟台","mooc"));
        studentsList.add(new Students(3,"周华健",31,"台湾","mooc"));

        return studentsList;
    }
}
