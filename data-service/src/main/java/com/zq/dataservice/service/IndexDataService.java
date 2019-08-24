package com.zq.dataservice.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zq.dataservice.bean.Index;
import com.zq.dataservice.bean.IndexData;
import com.zq.dataservice.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {

    private Map<String,List<IndexData>> indexDatas = new HashMap<>();
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    public List<IndexData> fresh(String code){
        List<IndexData> indexData = fetchIndexesFromThirdPart(code);
        indexDatas.put(code,indexData);
        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }
    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    @CachePut(key = "'indexData-code-'+#p0")
    public List<IndexData> fetchIndexesFromThirdPart(String code){
        List<IndexData> tempList = restTemplate.getForObject("http://127.0.0.1:8090/indexes/"+code+".json", List.class);
        System.out.println("tempList:"+tempList);
        return tempList;
    }
    @CacheEvict(key = "'indexData-code-'+#p0") //清空缓存
    public void remove(String code){
    }

    @CachePut(key = "'indexData-code-'+#p0") //p0:param0 第一个参数
    public List<IndexData> store(String code){
        return indexDatas.get(code);
    }

    @Cacheable(key = "'indexData-code-'+#p0")
    public List<IndexData> get(String code){
        //进来就说明没有走缓存
        return fetchIndexesFromThirdPart(code);
//        return CollUtil.toList();
    }

    public List<IndexData> thirdPartNotConnected(String code){
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("n/a");
        return CollectionUtil.toList(indexData);
    }


}
