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

    //	http://127.0.0.1:8001/getCodes
    @GetMapping("/getCodes")
    public List<Index> get(){
        return indexService.fetchIndexesFromThirdPart();
    }
    //	http://127.0.0.1:8001/freshCodes
    @GetMapping("/freshCodes")
    public String fresh() throws Exception {
        indexService.fresh();
        return "fresh codes successfully";
    }
    //	http://127.0.0.1:8001/removeCodes
    @GetMapping("/removeCodes")
    public String remove(){
        indexService.remove();
        return "remove codes successfully";
    }

}
