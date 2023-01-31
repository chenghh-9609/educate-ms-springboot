# java.lang.classnotfoundexception com.mysql.jdbc.driver 

配置mysql driver
`pom.xml`:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.31</version>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```
`src/main/resources/application.properties`配置mysql数据源:
```java
server.port=5555
spring.datasource.url=jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

# Cannot invoke "org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.queryForMap()" because "jdbcTemplate" is null
JdbcTemplate需要注入：
```java
@Autowired
private JdbcTemplate jdbcTemplate;
```

跨域配置：controller中加上`@CrossOrigin`标记

# HTTP Status 400 - Required Integer parameter "id" is not presented
1. https://stackoverflow.com/questions/32299733/http-status-400-required-integer-parameter-id-is-not-present
   解决方法：`@RequestParam(value = "id", required=false)`
2. 请求参数写错了...
   1. get获取请求参数用 `@PathVariable("id")`(放在路径中，如：localhost:5555/getuser/1)或者`@RequestParam(value = "id")`(放在路径中，如：localhost:5555/getuser/?id=1)
   2. post获取请求参数用`@RequestBody Map<String, Object> params`可以解析requestbody里的json对象

# Field dao in com.example.demo.controller.StudentController required a bean of type 'com.example.demo.dao.StudentDAO' that could not be found. 
https://blog.csdn.net/Julycaka/article/details/80622754
1. StudentDAOImpl.java没有添加@Repository注解
2. 启动类DemoApplication.java没有放在外层包，而是放在了controller、model等其他包内
3. 配置了mybatis，但没有指定扫描的包。
   1. 启动类加注释:@MapperScan(basePackages = { "mapper所在的包路径" }, sqlSessionFactoryRef = "sqlSessionFactory")，表示扫描xx.xx.mapper包下的所有mapper。
   2. 直接在你生成出来的xxxMapper.java类上加@Mapper标签。