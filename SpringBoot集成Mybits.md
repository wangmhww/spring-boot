SpringBoot集成Mybits

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

