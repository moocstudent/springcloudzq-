package com.zq.dataservice.controller;

import com.zq.dataservice.bean.Index;
import com.zq.dataservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/getCodes")
    public List<Index> get(){
        return indexService.fetchIndexesFromThirdPart();
    }
}
