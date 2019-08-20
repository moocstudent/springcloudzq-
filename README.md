# springcloudzq

注册数据微服务 <br>

+ 趋势投资融合
启动顺序:
1. eureka-server启动
2. 模拟第三方数据端启动 - third-part-index-data-proj
3. 数据控制层 data-service 启动 (index-gather-store-service)
4. 查看 localhost:8001/getCodes (通过调取第三方的数据API获取数据)
5. todo: 配置config-server 将 IndexService的链接 加入配置文件中.
即:
```java
List<Map> temp= restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json",List.class);
```

1. 改善了PortUtil <br>

