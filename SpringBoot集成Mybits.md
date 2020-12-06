# SpringBoot集成Mybits

通过Idea创建好项目

1、添加Mybatis、jdbc以及mysql依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

2、设置数据源地址

```
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.url=jdbc:mysql://47.110.71.90:3306/test
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

3、设置mybatis配置

```
# 配置mapper扫描路径
mybatis.mapper-locations=classpath:dao/*.xml
# 开启log日志
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
# 开启驼峰命名
mybatis.configuration.map-underscore-to-camel-case=true
# 设置类转换扫描包
mybatis.type-handlers-package=com.recruit.wm.handler
```

4、在启动类上设置mapper扫描，用户扫描DAO

```
@MapperScan("com.recruit.wm.dao")
public class RecruitStarterApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

}
```

5、开发mapper接口

```
@Mapper
public interface UserDao {
    public int insert(User User);

    public User selectById(String id);
}
```



# 引入druid数据库连接池



1、引入druid依赖

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
```

2.在application.properties中配置druid相关

```
spring.datasource.druid.enable=true
#\u521D\u59CB\u5316\u8FDE\u63A5\u6C60\u6570\u91CF
spring.datasource.druid.initial-size=2
#\u6700\u5C0F\u8FDE\u63A5\u6C60\u6570\u91CF
spring.datasource.druid.min-idle=2
#\u6700\u5927\u8FDE\u63A5\u6C60\u6570\u91CF
spring.datasource.druid.max-active=10
#\u83B7\u53D6\u8FDE\u63A5\u65F6\u6700\u5927\u7B49\u5F85\u65F6\u95F4\uFF0C\u5355\u4F4D\u6BEB\u79D2
spring.datasource.druid.max-wait=60000
#\u914D\u7F6E\u95F4\u9694\u591A\u4E45\u8FDB\u884C\u4E00\u6B21\u68C0\u67E5\uFF0C\u68C0\u6D4B\u9700\u8981\u5173\u95ED\u7684\u63A7\u7EBF\u8FDE\u63A5\uFF0C\u5355\u4F4D\u6BEB\u79D2\u3002
spring.datasource.druid.time-between-eviction-runs-millis=60000
#\u9500\u6BC1\u7EBF\u7A0B\u65F6\u68C0\u6D4B\u5F53\u524D\u8FDE\u63A5\u7684\u6700\u540E\u6D3B\u52A8\u65F6\u95F4\u548C\u5F53\u524D\u65F6\u95F4\u5DEE\u5927\u4E8E\u8BE5\u503C\u65F6\uFF0C\u5173\u95ED\u5F53\u524D\u8FDE\u63A5
spring.datasource.druid.min-evictable-idle-time-millis=300000
```





# 引入Swagger相关依赖

1、引入相关依赖

```
<!-- swagger -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.4.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.4.0</version>
</dependency>
<!-- knife4j Swagger文档界面 -->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.2</version>
    <exclusions>
        <exclusion>
            <artifactId>swagger-models</artifactId>
            <groupId>io.swagger</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

2、配置Swagger相关Bean，通过apiInfo添加界面标题、描述 ，

```
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name="swagger.enable", havingValue = "true")
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("recruit-user")
                .select()
     // 指定API扫描路径           .apis(RequestHandlerSelectors.basePackage("com.recruit.wm.controller")).paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Swagger文档")
                .version("1.1.0")
                .description("描述")
                .build();
    }
}
```

3、启动类中实现WebMvcConfigurer接口，并重写addResourceHandler(ResourceHandlerRegistry registry) 方法

```
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
}
```

4、编写Controller

```
@Api("用户配置类")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public IUserService userService;

    @PostMapping("/insert")
    @ApiOperation("新增用户接口")
    
    public int insert(@RequestBody User user){
        return userService.insert(user);
    }

    @GetMapping(value = "/selectById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value="获取用户", notes="根据指定用户Id获取用户信息",httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户Id", required = true,paramType = "path")
    public User selectById(@PathVariable("id") String id){
        return userService.selectById(id);
    }
}
```

5、通过http://127.0.0.1:8080/doc.html访问
