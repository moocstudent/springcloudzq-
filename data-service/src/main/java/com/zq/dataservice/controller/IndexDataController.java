package com.zq.dataservice.controller;

import com.zq.dataservice.bean.IndexData;
import com.zq.dataservice.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexDataController {
    @Autowired
    private IndexDataService indexDataService;

    //	http://127.0.0.1:8001/getIndexData/000300
    @GetMapping("/getIndexData/{code}")
    public List<IndexData> get(@PathVariable("code")String code)throws Exception{
        return indexDataService.get(code);
    }
    //	http://127.0.0.1:8001/freshIndexData/000300
    @GetMapping("/freshIndexData/{code}")
    public String fresh(@PathVariable("code")String code) throws Exception {
        indexDataService.fresh(code);
        return "fresh index data successfully";
    }
    //	http://127.0.0.1:8001/removeCodes
    @GetMapping("/removeIndexData/{code}")
    public String remove(@PathVariable("code") String code){
        indexDataService.remove(code);
        return "remove index data successfully";
    }

}
