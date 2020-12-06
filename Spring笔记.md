
###### <img src="C:\Users\wangm\Desktop\1111.jpg" alt="img" style="zoom:200%;" />

**单例Bean**：整个系统只有一份，非懒加载的单例Bean一般Spring启动的时候创建 ，懒加载的单例Bean之后再第一次使用时创建，单例Bean一般用ConCurrentHashMap用来存储，懒加载一般在循环依赖中使用。可以在类上添加@Lazy表示懒加载

**原型Bean**：没有在启动的时候创建，一般在使用的时候创建。可以在类上添加@Scope(“prototype”)表示一个Bean位原型Bean

Spring启动时首先会扫描将类将在成BeanDefiniton--->根据BeanDefinition生成非懒加载的单例Bean

**BeanDefinition**用于存储类的描述，一般包含Bean的类型（作用域）、类名称、是否懒加载，是否依赖某个Bean、初始化方法、销毁方法等。

**自动注入的两种方式**：bytype ,byname。 一般时先bytype再byname。如果bytype找到多个Bean，则再根据byname查找，bytype查找不到则证明不存在Bean。

**..Aware接口：**为了给Bean的特定的属性赋值，因此会给具体的类实现..Aware接口，例如BeanNameAware接口会在Bean实例化之后会给Bean的BeanName属性设置具体的BeanName。

**BeanPostProcessor**:Bean后置处理器



## **Bean的生命周期**

**BeanFactory** 生成Bean的工厂类

**FactoryBean **是一个特殊的Bean ，也是一个小的BeanFactory

```
@Component
public class LubanFactoryBean implements FactoryBean {
   @Override
   public Object getObject() throws Exception {
      User user = new User();
      System.out.println(user);
      return user;
   }

   @Override
   public Class<?> getObjectType() {
      return User.class;
   }
}
```

使用lubanFactoryBean可以获取User实例，如果获取LubanFactoryBean的Bean实例则需要使用&lubanFactoryBean获取

![img](https://cdn.nlark.com/yuque/0/2020/png/365147/1602587965056-87dd226a-0989-42ab-bfab-3fb4868fd4ac.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_20%2Ctext_6bKB54-t5a2m6Zmi5Ye65ZOB%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10)

类的扫描

### **Bean的生成过程**

### 1. 生成BeanDefinition

1、Spring启动的时候会扫描，会先调用

```
Resource[] resources = getResourcePatternResolver().getResources(packageSearchPath);
```

拿到所指定的包路径下的所有文件资源（***.class文件）

然后便利每个Resource，为每个Resource生成一个MetadataReader对象，这个对象拥有三个功能：

	1. 获取对应的Resource资源
 	2. 获取Resource对应的class的元数据信息，包括类的名字，是不是一个接口、是不是一个注解、是不是抽象类、有没有父类、父类的名字，所实现的所有接口的名字，内部类的类名等等，
 	3. 获取Resource对应class上的注解信息，当前类有哪些注释，当前有哪些方法上有注解。

再生成MetadataReader对象时，会利用ASM技术解析class文件，得到类的元数据集信息和注解信息，这个过程中也会利用ClassLoader会加载注解类（**ClassUtils.getDefaultClassLoader()所获得的类加载器**）但不会加载主类。



有了MetadataReader对象，就相当于有了当前类的所有信息，但是当前类并没有加载，也是可以理解的，真正在用到这个类的时候才加载



然后利用MetadatReader对象生成一个ScannedGenericBeanDefinition对象，**注意 此时生成的BeanDefinition对象中的beanClass存储的是当前类的名称，而不是class对象。**（beanClass类型属于Object，它既可以存储类的名字，也可以存储class对象）

### 2. 合并BeanDefinition

如果某个BeanDefiniton存在父BeanDefinition，那么需要合并。

扫描到的BeanDefinition为GenericBeanDefinition类型的，内部多了parentName属性，表示该BeanDefinition的父BeanDefinition，

### 3. 加载类

有了BeanDefinition之后，后续就会基于BeanDefinition去创建Bean，而创建Bean就必须实例化对象，而实例化就必须先加载当前BeanDefinition所对应的class，在AbstractAutowireCapableBeanFactory类的createBean()方法中，一开始就会调用：

```java
Class<?> resolvedClass = resolveBeanClass(mbd, beanName);
```

这行代码就是去加载类，该方法是这么实现的：

```java
if (mbd.hasBeanClass()) {
    return mbd.getBeanClass();
}
if (System.getSecurityManager() != null) {
    return AccessController.doPrivileged((PrivilegedExceptionAction<Class<?>>) () ->
        doResolveBeanClass(mbd, typesToMatch), getAccessControlContext());
    }
else {
    return doResolveBeanClass(mbd, typesToMatch);
}
```

```
public boolean hasBeanClass() {
    return (this.beanClass instanceof Class);
}
```

如果beanClass属性的类型是Class，那么就直接返回，如果不是，则会根据类名进行加载（doResolveBeanClass方法所做的事情）



会利用BeanFactory所设置的类加载器来加载类，如果没有设置，则默认使用**ClassUtils.getDefaultClassLoader()**所返回的类加载器来加载。



#### **ClassUtils.getDefaultClassLoader()**

1. 优先获取当前线程中的ClassLoader

2. 如果为空，则获取加载ClassUtils类的类加载器（正常情况下，就是AppClassLoader，但是如果是在Tomcat中运行，那么则会是Tomcat中为每个应用所创建的WebappClassLoader）

3. 如果为空，那么则是bootstrap类加载器加载的ClassUtils类，那则获取系统类加载器进行加载

   

### 4. 实例化前

允许第三方可以不按照Spring的正常流程来创建一个Bean，可以利用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation方法来提前返回一个Bean对象，直接结束Bean的生命周期

### 5. 推断构造方法

单独讲

### 6. 实例化

构造方法反射得到一个实例

### 7. BeanDefinition的后置处理

```
for (BeanPostProcessor bp : getBeanPostProcessors()) {
    if (bp instanceof MergedBeanDefinitionPostProcessor) {
        MergedBeanDefinitionPostProcessor bdp = (MergedBeanDefinitionPostProcessor) bp;
        bdp.postProcessMergedBeanDefinition(mbd, beanType, beanName);
    }
}
```

这里可以处理BeanDefinition，但是此时实例对象已经生成好了，所以修改beanClass已经没用了，但是可以修改PropertyValues，比如：

```
@Component
public class LubanMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        if (beanName.equals("userService")) {
            beanDefinition.setBeanClass(User.class); // 没用
            beanDefinition.getPropertyValues().add("name","xxx");
        }
    }
}
```

### 8. 填充属性

  1、获取BeanDefinition的自动注入方式

​	通过@Component、@Service、 @Controller这三种定义Bean的方式BeanDefinition的自动注入防止为NO

只用通过@Bean(autowire= Autowire.BY_NAME) 或者XML的方式定义的BeanDefinition才能走下面代码。

​		![image-20201123225527120](C:\Users\wangm\AppData\Roaming\Typora\typora-user-images\image-20201123225527120.png)

在autoWareByName方法内部先是调用了unsatisfiedNonSimpleProperties方法，该方法先解析了类文件，根据类文件找到所有的set方法，并截取set方法找到对应的bean的beanName，添加到数组中并返回。然后再更具beanName找到对应的bean添加到pvs中，最后再最后更具pvs的值给属性赋值

![image-20201123230840181](C:\Users\wangm\AppData\Roaming\Typora\typora-user-images\image-20201123230840181.png)

再autowireByType内先是调用了unsatisfiedNonSimpleProperties方法，该方法解析了类文件，根据类文件找到所有的set方法，再根据set方法的到对应的属性描述PropertyDescriptor，根据PropertyDescriptor得到set方法的参数信息，最后在更具参数信息找到对应的依赖描述，再调用resolveDependency方法解析依赖描述得到对应的bean，并添加到pvs中，最后再最后更具pvs的值给属性赋值

![image-20201123233922967](C:\Users\wangm\AppData\Roaming\Typora\typora-user-images\image-20201123233922967.png)

2.通过配置方式进行自动注入

### 9. 执行Aware

1. ((BeanNameAware) bean).setBeanName(beanName);
2. ((BeanClassLoaderAware) bean).setBeanClassLoader(bcl);
3. ((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.**this**);
4. ((ApplicationContextAware) bean).setApplicationContext(ApplicationContext applicationContext);
5. EnvironmentAware
6. EmbeddedValueResolverAware
7. ResourceLoaderAware
8. ApplicationEventPublisherAware
9. MessageSourceAware



### 10. 初始化前

```
for (BeanPostProcessor processor : getBeanPostProcessors()) {
    Object current = processor.postProcessBeforeInitialization(result, beanName);
    if (current == null) {
        return result;
    }
    result = current;
}
```

### 11. 初始化

程序员指定特定的初始化方法的方式

1. 实现InitializingBean接口并重写afterPropertiesSet方法;

2. 客户通过BeanDefinition后置处理设置初始化方法

3. 通过@PostConstruct注解添加到方法上  表示该方法为初始化方法

   

### 12. 初始化后

```
for (BeanPostProcessor processor : getBeanPostProcessors()) {
    Object current = processor.postProcessAfterInitialization(result, beanName);
    if (current == null) {
        return result;
    }
    result = current;
}
```



Bean生命周期的整个过程

```
实例化前 ---InstantiationAwareBeanPostProcessor中的postProcessBeforeInstantiation方法
实例化
合并BeanDefinition ---MergedBeanDefinitionPostProcessors中的applyMergedBeanDefinitionPostProcessors  找@autoWire和@Value注入点
实例化后 ---InstantiationAwareBeanPostProcessor中的postProcessAfterInstantiation方法
填充属性 byType byName（通过Xml方式注入）
填充属性后-- InstantiationAwareBeanPostProcessor中的postProcessProperties方法执行自动注入
执行Aware
初始化前 ---BeanPostProcessor中的postProcessAfterInstantiation方法
初始化
初始化后  ---BeanPostProcessor中的postProcessAfterInitialization方法
```



## Bean的销毁过程

### 1. 容器关闭

### 2. 发布ContextClosedEvent事件

### 3. 调用LifecycleProcessor的onClose方法

### 4. 销毁单例Bean

1. 找出所有DisposableBean(实现了DisposableBean接口的Bean)
2. 遍历每个DisposableBean
3. 找出依赖了当前DisposableBean的其他Bean，将这些Bean从单例池中移除掉
4. 调用DisposableBean的destroy()方法
5. 找到当前DisposableBean所包含的inner beans，将这些Bean从单例池中移除掉 (inner bean参考https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-inner-beans)

这里涉及到一个设计模式：**适配器模式**



在销毁时，Spring会找出实现了DisposableBean接口的Bean。



但是我们在定义一个Bean时，如果这个Bean实现了DisposableBean接口，或者实现了AutoCloseable接口，或者在BeanDefinition中指定了destroyMethodName，那么这个Bean都属于“DisposableBean”，这些Bean在容器关闭时都要调用相应的销毁方法。



所以，这里就需要进行适配，将实现了DisposableBean接口、或者AutoCloseable接口等适配成实现了DisposableBean接口，所以就用到了DisposableBeanAdapter。



会把实现了AutoCloseable接口的类封装成DisposableBeanAdapter，而DisposableBeanAdapter实现了DisposableBean接口。



## Spring中有几种依赖注入？

### 手动注入

​			set注入

```
	<bean id="userService" class="com.luban.service.UserService" />
	<bean id="orderService" class="com.luban.service.OrderService" autowire="constructor">
	   <property name="userService" ref="userService"/>
   </bean>
```

​			构造器注入

```
	<bean id="userService" class="com.luban.service.UserService" />
	<bean id="orderService" class="com.luban.service.OrderService" autowire="constructor">
      <constructor-arg name="userService" ref="userService"></constructor-arg>
   </bean>
```

###  自动注入

​			1.xml里面的注入

​					set注入 当使用byType或byName时 当重写了构造器时，必须重写默认构造器

```
   <bean id="userService" class="com.luban.service.UserService" />
   <bean id="orderService" class="com.luban.service.OrderService" autowire="byName">
   </bean>
```

​					构造器注入

```
    <bean id="userService" class="com.luban.service.UserService" />
    <bean id="orderService" class="com.luban.service.OrderService" 					autowire="constructor">
   </bean>
```

​			2.@Autoware

​					1、属性上

​					2、set方法上

​					3、构造器上

@Autoware的自动注入过程



## @Value

一般使用@Value注解填充application.properties属性的值，也可以使用该注解注入依赖，这样就可以直接ByName进行注入

例如

```
@Component
public class OrderService {
   @Value("#{userService}")
   private UserService userService;

   public void setUserService(UserService userService) {
      this.userService = userService;
   }
   public void test(){
      System.out.println(this.userService);
   }
}
```



BeanDefinition的创建

```
BeanDefinitionRegistryPostProcessor
```

















