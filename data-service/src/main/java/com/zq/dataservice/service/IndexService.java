package com.zq.dataservice.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zq.dataservice.bean.Index;
import com.zq.dataservice.util.MapListUtil;
import com.zq.dataservice.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {

    private List<Index> indexes;
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    public List<Index> fresh(){
        indexes = fetchIndexesFromThirdPart();
        IndexService indexService = SpringContextUtil.getBean(IndexService.class);
        indexService.remove();
        return indexService.store();
    }
    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    public List<Index> fetchIndexesFromThirdPart(){
        List<Map> tempList = restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json", List.class);
//        System.out.println("tempList:"+tempList);
        return MapListUtil.map2Index(tempList);
    }
    @CacheEvict(allEntries = true) //清空缓存
    public void remove(){
    }

    @Cacheable(key = "'all_codes'")
    public List<Index> store(){
        return indexes;
    }

    @Cacheable(key = "'all_codes'")
    public List<Index> get(){
        return CollUtil.toList();
    }

    public List<Index> thirdPartNotConnected(){
        Index index = new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
    }


}
