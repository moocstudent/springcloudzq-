package com.zq.dataservice.util;

import cn.hutool.core.convert.Convert;
import com.zq.dataservice.bean.Index;
import com.zq.dataservice.bean.IndexData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapListUtil {

    public static List<Index> map2Index(List<Map> temp) {
        List<Index> indexes = new ArrayList<>();
        for (Map map : temp) {
            String code = map.get("code").toString();
            String name = map.get("name").toString();
            Index index= new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }

        return indexes;
    }

    public static List<IndexData> map2IndexData(List<Map> temp){
        List<IndexData> indexDatas = new ArrayList<>();
        for(Map map : temp){
            String date = map.get("date").toString();
            float closePoint = Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData();

            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDatas.add(indexData);
        }
        return indexDatas;
    }
}
