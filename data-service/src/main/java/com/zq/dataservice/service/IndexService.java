package com.zq.dataservice.service;

import com.zq.dataservice.bean.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IndexService {
    private List<Index> indexes;
    @Autowired
    private RestTemplate restTemplate;

    public List<Index> fetchIndexesFromThirdPart(){
        List tempList = restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json", List.class);
        System.out.println("tempList:"+tempList);
        return tempList;
    }


}
