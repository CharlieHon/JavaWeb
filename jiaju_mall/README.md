# 家具网购项目

## 分层思想

- ![软件开发流程/阶段](img.png)

> **分层模式**：分层的目的是为了解耦，降低代码的耦合度，有利于项目的维护和升级

- ![JavaEE经典的三层架构](img_1.png)
- 浏览器(`http://ip:port/工程路径/资源`)
  1. 浏览器返回的数据，进行解析，并展示数据
  2. 关联的技术(html,css,js,jquery,vue...)
- 服务器
  - `web层/视图层/view`
    1. 接收用户请求
    2. 调用service层，完成业务处理
    3. 返回响应数据
    4. 可能做重定向/请求转发
    - 技术多元=>MVC测试
      1. html,css,js,jquery,vue/其它架构
      2. servlet-在服务器端解析
      3. **springmvc**-在服务器端解析
  - `service层/业务层`
    1. 完成各种业务处理，提供多业务API方法
    - 技术单纯
      1. Java技术
      2. **Spring**
  - `DAO层/数据层/数据持久层`
    1. 完成对数据库的操作
    2. 经典的就是`C(create)R(read/retrieve)U(update)D(delete)`
    - 技术多元化
      - JDBC,DBUtils(数据库连接池),jdbcTemplate
      - MyBatis,MyBatis-Plus...

| 分层       | 对应包                                                | 说明               |
|----------|----------------------------------------------------|------------------|
| web层     | `com.charlie.furns.web/servlet/controller/handler` | 接受用户请求，调用service |
| service层 | `com.charlie.furns.service`                        | service接口包       |
|          | `com.charlie.furns.service.impl`                   | service接口实现类     |
| dao持久层   | `com.charlie.furns.dao`                            | dao接口包           |
|          | `com.charlie.furns.dao.impl`                       | dao接口实现类         |
| 实体bean对象 | `com.charlie.furns.pojo/entity/domain/bean`        | JavaBean类        |
| 工具类      | `com.charlie.furns.utils`                          | 工具类              |
| 测试包      | `com.charlie.furns.test`                           | 完成对dao/service测试 |

- ![项目具体分层(不同包)方案](img_2.png)

### MVC

1. 什么是MVC
   - MVC全称：Model模型、View视图、Controller控制器
   - MVC最早出现在JavaEE三层中的Web层，可以有效知道Web层的代码如何有效分离，单独工作
   - **View视图：只负责数据和界面的显示**，不接受任何与显示数据无关的代码，便于程序员和美工分工合作(Vue/JSP/Thymeleaf/Html)
   - **Controller控制器：只负责接收请求，调用业务层的代码处理请求，然后派发页面，是一个调度者的角色(servlet)**
   - **Model模型：将与业务逻辑相关的数据封装为具体的JavaBean类，其中不参杂任何与数据处理相关的代码(JavaBean/Domain/Pojo)**
2. MVC是一种思想
   - **MVC的思想是将软件代码拆分成为组件，单独开发，组合使用(目的还是为了解耦合)，也有很多落地的框架比如SpringMVC**
3. MVC示意图
   - ![MVC示意图](img_3.png)
4. ![img_4.png](img_4.png)
5. ![分层思想](img_5.png)

## 实现功能01-创建项目，正确运行静态页面

把前端人员给的静态页面拷贝到web路径下
- ![img_6.png](img_6.png)

## 实现功能02-会员注册前端JS校验

| 需求分析                      | 思路分析                    |
|---------------------------|-------------------------|
| ![img_7.png](img_7.png)   | ![img_9.png](img_9.png) |
| ![img_8.png](img_8.png)   |                         |

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>韩顺平教育-家居网购</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="../../assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="../../assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="../../assets/css/style.min.css"/>
<!--    引入jquery，先使用-->
    <script type="text/javascript" src="../../script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () { // 页面加载完毕后执行function
            // 绑定点击事件
            $("#sub-btn").click(function () {

                // 1. 获取到输入的用户名
                var usernameVal = $("#username").val();
                // 编写正则表达式，进行验证
                var usernamePattern = /^\w{6,10}$/;
                // 验证
                if (!usernamePattern.test(usernameVal)) {
                    // 展示错误提示-属性过滤器
                    $("span[class='errorMsg']").text("用户名格式不对，需要6~10字符");
                    return false;   // 不提交，返回false
                }

                // 2. 完成对密码的校验
                var passwordVal = $("#password").val();
                var passwordPattern = /^\w{6,10}$/;
                if (!passwordPattern.test(passwordVal)) {
                    // 展示密码错误提示-基本过滤器
                    $("span.errorMsg").text("密码格式不对，需要6~10字符");
                    return false;
                }

                // 3. 两次密码相同验证
                // 得到第二次输入密码
                var repwdVal = $("#repwd").val();
                if (repwdVal !== passwordVal) {
                    $("span.errorMsg").text("输入的两次密码不同！");
                    return false;
                }

                // 4. 验证邮箱
                // 得到邮箱
                var emailVal = $("#email").val();
                // 说明：在java中正则表达式的转义是 \\，在js中正则表达式转义是 \
                var emailPattern = /^[\w-]+@([a-zA-Z]+\.)+[a-zA-Z]+$/;
                if (!emailPattern.test(emailVal)) {
                    $("span.errorMsg").text("电子邮件格式有误！");
                    return false;
                }

                $("span[class='errorMsg']").text("通过验证！");
                return false;
            })
        })
    </script>
</head>

<body>
<!--内容过多而省略...-->
</body>
</html>
```

## 实现功能03-会员注册后端

- ![会员注册后端](img_10.png)
- ![会员注册程序框架图](img_11.png)
- 分析：从前端注册发出请求到后端处理
- 实现：从底层数据库到上层调用，上层依赖下层

### 1. 创建数据库和表

- ![img_12.png](img_12.png)

```mysql
# 创建家具网购需要的数据库和表
-- 创建 home_furnishing 数据库
CREATE DATABASE IF NOT EXISTS home_furnishing;
USE home_furnishing;
-- 创建会员表
CREATE TABLE member (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(32) NOT NULL UNIQUE,
	`password` VARCHAR(32) NOT NULL,
	`email` VARCHAR(64)
);
-- 测试数据
INSERT INTO member(username, `password`, `email`) VALUES
	('admin', md5('admin'), 'charlie@tju.edu.cn');
SELECT * FROM member;
```

### 2. 创建entity

```java
package com.charlie.furns.entity;

public class Member {
    // 在定义JavaBean属性时，一定要与表字段名一致
    private Integer id;
    private String username;
    private String password;
    private String email;

    public Member() {
    }

    public Member(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "id=" + id + ", username=" + username +  ", email=" + email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### 3. 数据库工具类

```java
package com.charlie.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编写getConnection方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //关闭连接, 强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

```java
package com.charlie.furns.dao;


import com.charlie.furns.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 开发BasicDAO , 是其他DAO的父类
 */
public class BasicDAO<T> { //泛型指定具体类型

    private QueryRunner qr =  new QueryRunner();

    //开发通用的dml方法, 针对任意的表
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection, sql, parameters);
            return  update;
        } catch (SQLException e) {
           throw  new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    /**
     * 返回多个对象(即查询的结果是多行), 针对任意表
     * @param sql sql 语句，可以有 ?
     * @param clazz 传入一个类的Class对象 比如 Actor.class
     * @param parameters 传入 ? 的具体的值，可以是多个
     * @return 根据Actor.class 返回对应的 ArrayList 集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);

        } catch (SQLException e) {
            throw  new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //查询单行结果 的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return  qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw  new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //查询单行单列的方法,即返回单值的方法
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return  qr.query(connection, sql, new ScalarHandler(), parameters);
        } catch (SQLException e) {
            throw  new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }
}
```

### 4. DAO

- [BasicDAO<T>](src/com/charlie/furns/dao/BasicDAO.java)

```java
package com.charlie.furns.dao;

import com.charlie.furns.entity.Member;

public interface MemberDAO {    // 需要自己分析，需要哪些方法
    // 提供一个通过用户名返回对应的Member
    public Member queryMemberByUsername(String username);
    // 提供一个保存Member对象到数据库/member表
    public int saveMember(Member member);
}
```

```java
package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.MemberDAO;
import com.charlie.furns.entity.Member;

public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {

    /**
     * 通过用户名返回对应的Member
     * @param username 用户名
     * @return 对应的Member，如果没有该Member则返回null
     */
    @Override
    public Member queryMemberByUsername(String username) {
        // 提示：现在sqlyog测试，然后再拿到程序中
        String sql = "select * from member where username=?";
        return querySingle(sql, Member.class, username);
    }

    /**
     * 保存一个会员
     * @param member 传入的Member对象
     * @return 返回-1失败，返回其它的数字就是受影响的行数
     */
    @Override
    public int saveMember(Member member) {
        String sql = "INSERT INTO member(username, `password`, `email`)" +
                " VALUES (?, MD5(?), ?);";
        return update(sql, member.getUsername(), member.getPassword(), member.getEmail());
    }
}
```

### 5. service层

- [MemberService](src/com/charlie/furns/service/MemberService.java)
- [MemberServiceImpl](src/com/charlie/furns/service/impl/MemberServiceImpl.java)

> 小技巧：查看某个方法的具体实现类，快捷键 `ctrl+alt+b`
> 
> `ctrl+b`只可以定位到编译类型的方法

### 6. web层

- ![img_13.png](img_13.png)

> 在web层配置文件路径时和普通项目不同，web项目的工作目录在out，而src时JavaSE application上使用的路径。
> 
> web项目中需要使用到类加载器来获取

```
JDBCUtilsByDruid.java
// 因为是web项目，工作目录在out，文件的加载需要使用类加载器
// 找到我们的工作目录
properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
// properties.load(new FileInputStream("src\\druid.properties"));
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    // 定义一个属性MemberService
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("RegisterServlet 被调用...");
        // 接收用户注册信息->一定要看前端代码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        // 判断用户名是否可用
        if (!memberService.isExistsUsername(username)) {
            // 用户名不存在-注册
            Member member = new Member(null, username, password, email);
            if (memberService.registerMember(member)) { // 注册成功
                // 请求转发
                req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req, resp);
            } else {    // 注册失败
                req.getRequestDispatcher("/views/member/register_fail.jsp").forward(req, resp);
            }
        } else {
            // 用户名不可用
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

## 实现功能04-会员登录

- ![会员登录需求](img_14.png)
- ![会员登录程序框架图](img_15.png)

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果在登录界面，用户没有输入内容就直接提交，后台接收到的就是空串 ""
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.login(member) == null) {
            // 页面转发-登录失败
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

## 实现功能05-登录错误提示，表单回显

- ![需求分析](img_16.png)
- ![登录错误提示程序框架图](img_17.png)

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果在登录界面，用户没有输入内容就直接提交，后台接收到的就是空串 ""
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.login(member) == null) {
            // 把登录错误信息，放入到request域
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误");
            // 页面转发-登录失败
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        }
    }
}
```

```html
<div class="login-register-form">
    <%--提示错误信息-el表达式--%>
    <span style="font-size: 18pt;font-weight: bold;float: right;color: gainsboro">
        <!--回显返回的信息-->
        ${requestScope.msg}
    </span>
    <form action="loginServlet" method="post">
        <!--将用户名输入框内容设置为req返回数据的username-->
        <input type="text" name="username" value="${requestScope.username}" placeholder="Username"/>
        <input type="password" name="password" placeholder="Password"/>
        <div class="button-box">
            <div class="login-toggle-btn">
                <input type="checkbox"/>
                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                <a href="#">Forgot Password?</a>
            </div>
            <button type="submit"><span>Login</span></button>
        </div>
    </form>
</div>
```

## 实现功能06-web层servlet减肥

方案1-if-else
- ![问题分析](img_18.png)
- ![解决思路](img_19.png)
- [方案1-if-else](web/views/member/login2.jsp)

```html
<form action="memberServlet" method="post">
    <%--增加隐藏域表示login请求--%>
    <input type="hidden" name="action" value="login"/>
    <input type="text" name="username" value="${requestScope.username}" placeholder="Username"/>
    <input type="password" name="password" placeholder="Password"/>
    <div class="button-box">
        <div class="login-toggle-btn">
            <input type="checkbox"/>
            <a class="flote-none" href="javascript:void(0)">Remember me</a>
            <a href="#">Forgot Password?</a>
        </div>
        <button type="submit"><span>Login</span></button>
    </div>
</form>
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该servlet处理和Member相关的请求
 */
public class MemberServlet extends HttpServlet {

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取到action
        String action = request.getParameter("action");
        // 判断，调用不同的方法
        if ("login".equals(action)) {
            login(request, response);
        } else if ("register".equals(action)) {
            register(request, response);
        } else {
            response.getWriter().write("请求参数action有误！");
        }
    }

    /**
     * 处理会员注册
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("RegisterServlet 被调用...");
        // 接收用户注册信息->一定要看前端代码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        // 判断用户名是否可用
        if (!memberService.isExistsUsername(username)) {
            // 用户名不存在-注册
            Member member = new Member(null, username, password, email);
            if (memberService.registerMember(member)) { // 注册成功
                // 请求转发
                req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req, resp);
            } else {    // 注册失败
                req.getRequestDispatcher("/views/member/register_fail.jsp").forward(req, resp);
            }
        } else {
            // 用户名不可用
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        }
    }

    /**
     * 处理会员登录
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果在登录界面，用户没有输入内容就直接提交，后台接收到的就是空串 ""
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.login(member) == null) {
            // 把登录错误信息，放入到request域
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误");
            // 页面转发-登录失败
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        }
    }
}
```

方案2-反射+模板设计模式+动态绑定
- ![方案2思路](img_20.png)
- ![img_22.png](img_22.png)
- ![img_21.png](img_21.png)

```java
package com.charlie.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * BasicServlet不需要在web.xml上配置
 */
public abstract class BasicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取到action
        // 提示：如果使用了模板模式+反射+动态绑定，要满足action的value和方法名一致
        String action = req.getParameter("action");
        /* 使用反射，获取当前对象的方法
        1. this 就是请求的Servlet
        2. declareMethod方法对象就是当前请求的servlet对应的action名字的方法
            该方法对象declaredMethod 是变化的，根据用户的请求变化
        3. 使用模板模式+反射+动态绑定机制===>实现简化多个if-else分支操作
         */
        // this: com.charlie.furns.web.MemberServlet2@2554be6
        try {
            Method declaredMethod =
                    this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // declaredMethod: protected void com.charlie.furns.web.MemberServlet2.login
            // 使用方法对象，进行反射调用
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该servlet处理和Member相关的请求
 */
public class MemberServlet2 extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    /**
     * 处理会员注册
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("RegisterServlet 被调用...");
        // 接收用户注册信息->一定要看前端代码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        // 判断用户名是否可用
        if (!memberService.isExistsUsername(username)) {
            // 用户名不存在-注册
            Member member = new Member(null, username, password, email);
            if (memberService.registerMember(member)) { // 注册成功
                // 请求转发
                req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req, resp);
            } else {    // 注册失败
                req.getRequestDispatcher("/views/member/register_fail.jsp").forward(req, resp);
            }
        } else {
            // 用户名不可用
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        }
    }

    /**
     * 处理会员登录
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果在登录界面，用户没有输入内容就直接提交，后台接收到的就是空串 ""
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.login(member) == null) {
            // 把登录错误信息，放入到request域
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误");
            // 页面转发-登录失败
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        }
    }
}
```

## 实现功能07-后台管理-显示家具

| ![img_23.png](img_23.png) | ![img_24.png](img_24.png) |
|---------------------------|---------------------------|

- ![程序框架图](img_25.png)

```mysql
### 设计furn表，家具表 需求-文档-界面
-- 有时会看到 id int(11) 其中11表示显示的宽度，配合zerofill，存放的数据范围是和int一致的
--	int(6) 123 ---> 000123
--	int的范围是-2^32~2^32-1 最大长度是11位(带上符号)
--	int(11)->有符号
--	int(10)->无符号
-- 对于数据库中的图片，可以存放其所在地址的url
DROP TABLE IF EXISTS furn;
CREATE TABLE IF NOT EXISTS furn (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,	# id
	`name` VARCHAR(64) NOT NULL,			# 家具名
	`maker` VARCHAR(64) NOT NULL,			# 制造商
	price DECIMAL(11, 2) NOT NULL,			# 价格，定点数
	`sales` INT UNSIGNED NOT NULL,			# 销量
	`stock` INT UNSIGNED NOT NULL,			# 库存
	`img_path` VARCHAR(256) NOT NULL		# 存放图片的路径
)CHARSET utf8 ENGINE INNODB;
-- 增加测试数据
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`)
	VALUES(NULL, '北欧风格小桌子', '熊猫家居', 180, 666, 7, 'assets/images/product-image/6.jpg');
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`)
	VALUES(NULL, '简约风格小椅子', '熊猫家居', 180, 666, 7, 'assets/images/product-image/4.jpg');
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`)
	VALUES(NULL, '典雅风格小台灯', '蚂蚁家居', 180, 666, 7, 'assets/images/product-image/14.jpg');
SELECT * FROM furn;
```

> 设计的FurnServlet继承BasicServlet，要调用到FurnServlet的list方法，需要在浏览器地址栏输入
> `jiaju_mall/manage/furnServlet?action=list`

```html
<tbody>
<%--取出furns集合，循环显示--%>
<c:forEach items="${requestScope.furns}" var="furn">
<tr>
    <td class="product-thumbnail">
        <a href="#"><img class="img-responsive ml-3" src=${furn.imgPath} alt=""/></a>
    </td>
    <td class="product-name"><a href="#">${furn.name}</a></td>
    <td class="product-name"><a href="#">${furn.maker}</a></td>
    <td class="product-price-cart"><span class="amount">${furn.price}</span></td>
    <td class="product-quantity">
        ${furn.sales}
    </td>
    <td class="product-quantity">
        ${furn.stock}
    </td>
    <td class="product-remove">
        <a href="#"><i class="icon-pencil"></i></a>
        <a href="#"><i class="icon-close"></i></a>
    </td>
</tr>
</c:forEach>
</tbody>
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    /**
     * 使用前面的模板设计模式+反射+动态绑定来调用list方法
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过在地址栏输入 manage/furnServlet?action=list 能够调用该方法
        // System.out.println("FurnServlet 的list方法被调用...");
        List<Furn> furns = furnService.queryFurns();
        // 把furn集合放入到req域
        req.setAttribute("furns", furns);
        // 请求转发
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }
}
```

## 实现功能08-后台管理-添加家具

- ![img_26.png](img_26.png)
- ![img_27.png](img_27.png)

> 1. 解决中文乱码问题：在 `BasicServlet` 的 `doPos()` 方法中设置 `req.setCharacterEncoding("utf-8");`
> 2. 解决表单重复提交问题：当使用请求转发时，浏览器地址栏会停留在第一个servlet，如果刷新页面，会再次发出请求并提交数据，
> 所以在支付页面等情况下，不要使用请求转发，否则会造成重复支付。
> 3. 数据正确性校验：添加家具时提交的数据可能有问题，可以分别在前端和后端进行数据校验

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();
    
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取家具信息
        String name = req.getParameter("name");
        String maker = req.getParameter("maker");
        String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
        // 图片路径imgPath使用默认即可
        String imgPath = "assets/images/product-image/16.jpg";

        /*
        后端数据校验
        1. 逐个进行校验
        try {
            BigDecimal price = new BigDecimal(price);
        } catch (Exception e) {
            System.out.println("家具价格格式有误~");
            req.setAttribute("msg", "家具价格格式有误~");
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }
        2. 在 new Furn() 处校验一次性验证
        3. SprintMVC有个专门用于数据校验的规则/框架 JSP303 Hibernate Validator
         */

        Furn furn = null;
        try {
            furn = new Furn(null, name, maker, new BigDecimal(price), Integer.parseInt(sales), Integer.parseInt(stock), imgPath);
        } catch (Exception e) {
            System.out.println("添加家具信息有误...");
            req.setAttribute("msg", "添加的家具数据有误，请仔细检验~");
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }

        // 添加家具
        furnService.addFurn(furn);

        // 请求转发到家具显示页面，即需要重新走一遍furnServlet的list方法
        // 因为这里使用请求转发，当用户刷新页面时会重新发出一次添加请求，就会造成数据重复提交，解决方法->使用请求重定向
        // req.getRequestDispatcher("/manage/furnServlet?action=list").forward(req, resp);

        // 因为重定向实际是让浏览器重新发送请求，所以回送的url是一个完整的url
        String url = req.getContextPath() + "/manage/furnServlet?action=list";
        resp.sendRedirect(url);
        //System.out.println("url=" + url);   // url=/jiaju_mall/manage/furnServlet?action=list
    }
}
```

> 使用 `BeanUtils` 自动封装javabean

1. `BeanUtils`工具类，可以一次性地把所有请求的参数注入到JavaBean中
2. 经常用于把Map中的值注入到JavaBean中，或者是对象属性的拷贝操作
3. 需要导入jar包 `commons-beanutils-1.8.0.jar` 和 `commons-logging-1.1.1.jar`

- 老韩小技巧：Debug时选中某个方法，右键->`Evaluate Expression` 能够得到表达式的值
- ![img_28.png](img_28.png)

```java
package com.charlie.furns.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

public class DataUtils {
    // 将使用BeanUtils工具自动封装javabean的方法放到静态方法中使用
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将上述自动封装的方法封装到utils中
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());

        // 添加家具
        furnService.addFurn(furn);

        // 请求转发到家具显示页面，即需要重新走一遍furnServlet的list方法
        // 因为这里使用请求转发，当用户刷新页面时会重新发出一次添加请求，就会造成数据重复提交，解决方法->使用请求重定向
        // req.getRequestDispatcher("/manage/furnServlet?action=list").forward(req, resp);

        // 因为重定向实际是让浏览器重新发送请求，所以回送的url是一个完整的url
        String url = req.getContextPath() + "/manage/furnServlet?action=list";
        resp.sendRedirect(url);
        //System.out.println("url=" + url);   // url=/jiaju_mall/manage/furnServlet?action=list
    }
}
```

- ![img_29.png](img_29.png)

## 实现功能09-后台管理-删除家具

- ![需求分析](img_30.png)
- ![思路分析](img_31.png)

| DAO层                      | Service层                   | Servlet层                   |
|---------------------------|----------------------------|----------------------------|
| ![img_32.png](img_32.png) | ![img_34.png](img_34.png)  | ![img_37.png](img_37.png)  |
| ![img_33.png](img_33.png) | ![img_35.png](img_35.png)  | ![img_36.png](img_36.png)  |

## 实现功能10-后台管理-修改家具

- ![需求分析](img_38.png)
- ![需求分析](img_39.png)
- ![思路分析](img_40.png)

```html
<form action="manage/furnServlet" method="post">
    <%--因为是post方法，所以需要使用到隐藏域id和action--%>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${requestScope.furn.id}">
            <a href="#"><img class="img-responsive ml-3" src="assets/images/product-image/default.jpg" alt=""/></a>
        <td class="product-name"><input name="name" style="width: 60%" type="text" value="${requestScope.furn.name}"/></td>
        <td class="product-name"><input name="maker" style="width: 90%" type="text" value="${requestScope.furn.maker}"/></td>
        <td class="product-price-cart"><input name="price" style="width: 90%" type="text" value="${requestScope.furn.price}"/></td>
            <input name="sales" style="width: 90%" type="text" value="${requestScope.furn.sales}"/>
            <input name="stock" style="width: 90%" type="text" value="${requestScope.furn.stock}"/>
            <input type="submit" style="width: 90%;background-color: silver;border: silver;border-radius: 20%;" value="修改家居"/>
</form>
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    // 处理回显家具信息的请求
    protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        // 将furn放入到req域中
        req.setAttribute("furn", furn);
        // 请求转发
        req.getRequestDispatcher("/views/manage/furn_update.jsp").forward(req, resp);
    }

    // 处理修改家具信息的请求
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        // 修改家具信息
        furnService.updateFurn(furn);
        // 重定向
        String url = req.getContextPath() + "/manage/furnServlet?action=list";
        resp.sendRedirect(url);
    }
}
```

## 实现功能11-后台分页(分页显示家具)

- ![需求分析](img_41.png)
- ![思路分析](img_42.png)

> 数据模型：设计一个Page的JavaBean，包含要显示的页数和该页内的数据。设置为泛型类型，方便扩展。
> 对于Page中不同的属性，其中能从数据库DB中直接获取的放大DAO中处理，从前端获取的放到service中处理

```java
package com.charlie.furns.entity;

import java.util.List;

/**
 * Page是一个JavaBean，是一个分页的数据模型，包含了分页的各种信息
 * T表示泛型，因为将来分页模型对应的数据类型是不确定的
 */
public class Page<T> {

    // 因为每页显示多少条记录，在其它地方有可能使用
    // ctrl+shift+u => 切换变量名大小写
    public static final Integer PAGE_SIZE = 3;

    // 表示显示当前页[显示第几页]
    private Integer pageNo;     // 前端页面获取
    // 表示每页显示几条记录
    private Integer pageSize = PAGE_SIZE;
    // 表示共有多少页
    private Integer pageTotalCount; // 通过计算而来
    // 表示的是共有多少条记录
    private Integer totalRow;   // 可以从数据库获得 -> DAO
    // 返回当前页要显示的数据
    private List<T> items;      // 从数据库中获取 -> DAO
    // 分页导航的字符串
    private String url;
    // getter and setter 方法省略
}
```

```java
package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.FurnDAO;
import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;

import java.util.List;

public class FurnDAOImpl extends BasicDAO<Furn> implements FurnDAO {

    @Override
    public int getTotalRow() {
        String sql = "select count(*) from furn";
        //return (Integer) queryScalar(sql);    // Long cannot be cast to Integer
        return ((Number) queryScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {
        String sql = "SELECT `id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path` imgPath from `furn` limit ?, ?";
        return queryMulti(sql, Furn.class, begin, pageSize);
    }
}
```

```java
package com.charlie.furns.service.impl;

import com.charlie.furns.dao.FurnDAO;
import com.charlie.furns.dao.impl.FurnDAOImpl;
import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
import com.charlie.furns.service.FurnService;

import java.util.List;

public class FurnServiceImpl implements FurnService {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public Page<Furn> page(int pageNo, int pageSize) {
        // 先创建一个page对象，然后根据实际情况填充属性
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);                     // 要显示第几页
        page.setPageSize(pageSize);                 // 每页显示的数据量
        int totalRow = furnDAO.getTotalRow();
        page.setTotalRow(totalRow);                 // 一共有多少行数据记录

        // pageTotalCount 要显示的页数
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageNo != 0) {
            pageTotalCount += 1;
        }
        page.setPageTotalCount(pageTotalCount);     // 一共可以显示多少页

        // begin = (第几页 - 1) * 每页显示的数据
        int begin = (pageNo - 1) * pageSize;
        page.setItems(furnDAO.getPageItems(begin, pageSize));
        // url分页导航，后续实现
        return page;
    }
}
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    // 处理分页显示请求
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用service方法，获取Page对象
        Page<Furn> page = furnService.page(pageNo, pageSize);
        // 将page放入到req域
        req.setAttribute("page", page);
        // 请求转发到furn_manage.jsp页面
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }
}
```

## 实现功能12-后台分页(分页导航)

- ![需求分析](img_43.png)

```html
<!--  Pagination Area Start -->
<div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
    <ul>
        <%--首页--%>
        <li><a href="manage/furnServlet?action=page&pageNo=1">首页</a></li>
        <%--上一页:如果当前页大于1，就显示上一页--%>
        <c:if test="${requestScope.page.pageNo > 1}">
            <li><a href="manage/furnServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上页</a></li>
        </c:if>

        <%--显示所有的分页数，先易后难
        先确定开始页数 begin 第1页
        再确定结束页数 end 末页
        问题：如果页数很多，如何处理？ => 通过算法最多显示5页
        --%>
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${requestScope.page.pageTotalCount}"/>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <%--如果i是当前页，就使用class="active"修饰--%>
            <c:if test="${i == requestScope.page.pageNo}">
                <li><a class="active" href="manage/furnServlet?action=page&pageNo=${i}">${i}</a></li>
            </c:if>
            <c:if test="${i != requestScope.page.pageNo}">
                <li><a href="manage/furnServlet?action=page&pageNo=${i}">${i}</a></li>
            </c:if>
        </c:forEach>

        <%--下一页--%>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
            <li><a href="manage/furnServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下页</a></li>
        </c:if>
        <%--末页--%>
        <li><a href="manage/furnServlet?action=page&pageNo=${requestScope.page.pageTotalCount}">末页</a></li>
        <li><a>共 ${requestScope.page.pageTotalCount} 页</a></li>
        <li><a>共 ${requestScope.page.totalRow} 记录</a></li>
    </ul>
</div>
<!--  Pagination Area End -->
```

- 如下处理修改/删除后返回对应的分页上
- ![img_44.png](img_44.png)

> 注意:
> - 在jsp中使用 `${requestScope.pageNo}` 获取的是 `Request`域中的数据,即通过 `req.setAttribute("pageNo", pageNo)` 添加的域数据
> - 而通过 `${param.pageNo}` 可以获取请求参数的值,如通过请求 `furnServlet?action=page&pageNo=1`

```
// 处理回显家具信息的请求
protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = DataUtils.parseInt(req.getParameter("id"), 0);
    Furn furn = furnService.queryFurnById(id);
    // 将furn放入到req域中
    req.setAttribute("furn", furn);

    // 1. 将从请求中获取的参数信息pageNo保存到req的域中,则可以在jsp中使用 ${requestScope.pageNo} 获取域数据
    // 2. 如果是请求带来的参数如 pageNo=1,而且通过请求转发到下一个页面,
    //      在下一个页面可以通过 ${param.pageNo} 获取,此时不需要设置域数据,直接请求转发req即可
    //req.setAttribute("pageNo", req.getParameter("pageNo"));
    //req.setAttribute("pageSize", req.getParameter("pageSize"));

    // 请求转发
    req.getRequestDispatcher("/views/manage/furn_update.jsp").forward(req, resp);
}
```

## 实现功能13-首页分页

- ![img_45.png](img_45.png)
- ![img_46.png](img_46.png)

```html
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%--家具项目默认进入的index页面只负责将请求直接转发给处理对应业务的servlet
类似网站的入口
--%>
<jsp:forward page="/customerFurnServlet?action=page&pageNo=1"></jsp:forward>
```

## 实现功能14-首页搜索

- ![需求分析](img_47.png)
- ![程序思路](img_48.png)
- ![img_49.png](img_49.png)

点击分页条保持上次搜索记录的两种实现方法
1. 使用 `${param.name}` 获取请求转发的搜索参数
2. 通过设置返回page的属性url保存name

```html
<!--web/index.jsp-->
<jsp:forward page="/customerFurnServlet?action=pageByName&pageNo=1"></jsp:forward>

<!--web/views/customer/index.jsp-->
<div class="dropdown_search">
    <form class="action-form" action="customerFurnServlet">
        <input type="hidden" name="action" value="pageByName">
        <input class="form-control" name="name" value="${param.name}" placeholder="请输入家具名搜索" type="text">
        <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
    </form>
</div>
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerFurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    // 处理首页搜索请求
    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        /*
        1. 如果参数有name，但是没有值，则接收的是 ""
        2. 如果name参数都没有，接收到的是 null
         */
        if (name == null) {
            name = "";
        }
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), 4);
        Page<Furn> page = furnService.pageByName(name, pageNo, pageSize);
        req.setAttribute("page", page);
        
        StringBuilder url =new StringBuilder("customerFurnServlet?action=pageByName");
        if (!"".equals(name)) {
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());

        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }
}
```

```html
<!--  Pagination Area Start -->
<div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
    <ul>
        <%--首页--%>
        <%--上一页:如果当前页大于1，就显示上一页--%>
        <c:if test="${requestScope.page.pageNo > 1}">
                <li><a href="customerFurnServlet?action=pageByName&pageNo=1&name=${param.name}">首页</a></li>
                <li><a href="customerFurnServlet?action=pageByName&pageNo=${requestScope.page.pageNo-1}&name=${param.name}">上页</a></li>
        </c:if>

        <%--显示所有的分页数，先易后难
        先确定开始页数 begin 第1页
        再确定结束页数 end 末页
        问题：如果页数很多，如何处理？ => 通过算法最多显示5页
        --%>
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${requestScope.page.pageTotalCount}"/>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <%--如果i是当前页，就使用class="active"修饰--%>
            <c:if test="${i == requestScope.page.pageNo}">
                <%--<li><a class="active" href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${param.name}">${i}</a></li>--%>
                <li><a class="active" href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
            </c:if>
            <c:if test="${i != requestScope.page.pageNo}">
                <%--<li><a href="customerFurnServlet?action=pageByName&pageNo=${i}&name=${param.name}">${i}</a></li>--%>
                <li><a href="${requestScope.page.url}&pageNo=${i}">${i}</a></li>
            </c:if>
        </c:forEach>

        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
            <li><a href="customerFurnServlet?action=pageByName&pageNo=${requestScope.page.pageNo+1}&name=${param.name}">下页</a></li>
            <li><a href="customerFurnServlet?action=pageByName&pageNo=${requestScope.page.pageTotalCount}&name=${param.name}">末页</a></li>
        </c:if>
        <li><a>共 ${requestScope.page.pageTotalCount} 页</a></li>
        <li><a>共 ${requestScope.page.totalRow} 记录</a></li>
    </ul>
</div>
<!--  Pagination Area End -->
```

> 首页搜索中遇到的奇怪问题：
> 1. 图片src='#'会请求首页(web/index.jsp)，进而再去请求 `customerFrunServlet`

```html
<%--img src=# 会去请求当前页url，加上base标签参考即localhost:8080/jiaju_mall/#--%>
<%--<img src="#" alt="">--%>
```

## 实现功能15-显示登录名

- ![需求分析](img_50.png)
- ![程序思路](img_51.png)

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 该servlet处理和Member相关的请求
 */
public class MemberServlet2 extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();
    
    // 处理会员登录
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果在登录界面，用户没有输入内容就直接提交，后台接收到的就是空串 ""
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.login(member) == null) {
            // 把登录错误信息，放入到request域
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误");
            // 页面转发-登录失败
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        } else {
            // 将得到的member对象放入到session
            req.getSession().setAttribute("member", member);
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        }
    }
}
```

```html
<!-- web/views/customer/index.jsp -->
<%--根据用户的登录状态，显示不同的菜单
思路：根据session中有误member对象，来判断
    ${empty xxx} 如xxx为null，返回true，否则返回false
--%>
<c:if test="${empty sessionScope.member}">
    <div class="header-bottom-set dropdown">
        <a href="views/member/login2.jsp">登录|注册</a>
    </div>
</c:if>
<c:if test="${not empty sessionScope.member}">
    <div class="header-bottom-set dropdown">
        <a>欢迎：${sessionScope.member.username}</a>
    </div>
    <div class="header-bottom-set dropdown">
        <a href="#">订单管理</a>
    </div>
    <div class="header-bottom-set dropdown">
        <a href="#">安全退出</a>
    </div>
</c:if>
<!-- Single Wedge End -->
```

## 实现功能16-注销功能

- ![需求分析](img_52.png)
- ![程序思路](img_53.png)

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 该servlet处理和Member相关的请求
 */
public class MemberServlet2 extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    // 注销登录
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁当前用户的session
        req.getSession().invalidate();
        // 重定向到网站首页->刷新首页
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
```

```html
<div class="header-bottom-set dropdown">
    <a href="memberServlet?action=logout">安全退出</a>
</div>
```

## 实现功能17-注册验证码

- ![需求分析](img_54.png)
- ![程序框架](img_55.png)
- ![谷歌的验证码jar包](img_60.png)
- ![img_56.png](img_56.png)
- ![Kaptcha常量](img_57.png)

> 表单重复提交情况：
> 1. 提交完表单，服务器使用请求转发进行页面跳转。用户刷新 `F5`，会发起最后一次请求，造成表单重复提交问题。解决：使用重定向
> 2. 用户正常提交，由于网络延迟等原因，未收到服务器的响应，这时用户着急多点了几次提交操作，也会造成表单重复提交。解决：验证码
> 3. 用户正常提交，由于服务器也没有延迟，但是提交完成后，用户回退浏览器，重新提交，也会造成表单重复提交。解决：验证码

```java
package com.google.code.kaptcha.servlet;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KaptchaServlet extends HttpServlet implements Servlet {
    private Properties props = new Properties();
    private Producer kaptchaProducer = null;
    private String sessionKeyValue = null;
    private String sessionKeyDateValue = null;
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setDateHeader("Expires", 0L);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");
        String capText = this.kaptchaProducer.createText();
        // this.sessionKeyValue: KAPTCHA_SESSION_KEY
        req.getSession().setAttribute(this.sessionKeyValue, capText);
        // this.sessionKeyDataValue: KAPTCHA_SESSION_DATE
        req.getSession().setAttribute(this.sessionKeyDateValue, new Date());
        BufferedImage bi = this.kaptchaProducer.createImage(capText);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);
    }
}
```

```html
<!--给验证码图片绑定一个点击事件，使得每点击一次都可以刷新图片-->
<script type="text/javascript">
    $(function () { // 页面加载完毕后执行function

        /*
        模拟一个点击事件，选中注册
        决定是显示登录还是注册的tab "" 不能少
        如果注册失败，显示注册tab，而不是默认的登录tab
         */
        if ("${requestScope.active}" === "register") {
            $("#register_tab")[0].click();  // 模拟点击
        }
        
        // 对验证码图片进行处理，绑定一个点击事件，可以获取新的验证码
        $("#codeImg").click(function () {
            // 有的浏览器在url没有变化的时候，图片不会发出新的请求
            // 为了防止不请求不刷新，可以携带一个变化的参数
            // this.src = "http://localhost:8080//jiaju_mall/kaptchaServlet?d=" + new Date();
            this.src = "<%=request.getContextPath()%>/kaptchaServlet?d=" + new Date();
        })
        
        // 前端验证码校验，不能为空
        var codeText = $("#code").val();    // 获取验证码输入框输入的值
        codeText = $.trim(codeText);        // 去除字符串前后的空格
        if (codeText == null || codeText === "") {
            $("span.errorMsg").val("验证码不能为空");
            return false;
        }
    })
</script>
<!--将验证码图片的src设置为KaptchaServlet即可-->
<img id="codeImg" alt="" src="kaptchaServlet" style="width: 120px;height: 50px">
```

```java
package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class MemberServlet2 extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    // 处理会员注册
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("RegisterServlet 被调用...");
        // 接收用户注册信息->一定要看前端代码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // 获取用户提交的验证码
        String code = req.getParameter("code");
        // 从session中获取到KaptchaServlet创建的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 立即删除session中的验证码，防止该验证码被重复使用
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 如果token不为null，并且和用户提交的验证码一致，就继续
        if (token != null && token.equalsIgnoreCase(code)) {
            // 判断用户名是否可用
            if (!memberService.isExistsUsername(username)) {
                // 用户名不存在-注册
                Member member = new Member(null, username, password, email);
                if (memberService.registerMember(member)) { // 注册成功
                    // 请求转发
                    req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req, resp);
                } else {    // 注册失败
                    req.getRequestDispatcher("/views/member/register_fail.jsp").forward(req, resp);
                }
            } else {
                // 用户名不可用
                req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
            }
        } else {    // 验证码不正确
            // 待会一个信息，要求显示到注册选项页
            req.setAttribute("active", "register");
            req.setAttribute("msg", "验证码有误~");
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        }
    }
}
```

## 实现功能18-添加家具到购物车

- ![img_58.png](img_58.png)
- ![img_59.png](img_59.png)
- ![session结构](img_61.png)

```html
<!--以下是添加到购物车的按键-->
<button title="Add To Cart" class="add-to-cart" furnId="${furn.id}">Add To Cart</button>
<!--以下是对添加按键的处理-->
<script type="text/javascript">
    $(function () {
        // 给 add to cart 按键绑定事件 jquery
        $(".add-to-cart").click(function () {
            // 获取到点击的furn的-id
            var furnId = $(this).attr("furnId");
            // 发出一个请求添加家具 => ajax
            location.href = "cartServlet?action=addItem&id=" + furnId;
        })
    })
</script>
<!--servlet处理...-->
<!--购物车图标显示商品总数目-->
<a href="#offcanvas-cart"
   class="header-action-btn header-action-btn-cart offcanvas-toggle pr-0">
    <i class="icon-handbag"> 购物车</i>
    <%--${sessionScope.cart.totalCount}的本质是调用cart的getTotalCount()方法
    因此，可以不在cart中添加属性totalCount，而是直接写方法
    --%>
    <span class="header-action-num">${sessionScope.cart.totalCount}</span>
</a>
```

```java
package com.charlie.furns.web;

public class CartServlet extends BasicServlet {

    // 增加一个属性
    private FurnService furnService = new FurnServiceImpl();

    // 增加一个添加家具数据到购物车的方法
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先得到添加的家具id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 获取到id对应的furn对象
        Furn furn = furnService.queryFurnById(id);

        // TODO: 判断家具是否为空。以下先把正常的逻辑走完，再处理异常情况

        // 根据furn构建cartItem
        CartItem item = new CartItem(furn.getId(), furn.getName(), 1, furn.getPrice(), furn.getPrice());
        // 从session中获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) { // 说明当前用户的session中没有cart
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        // 将cartItem加入到cart对象
        cart.addItem(item);
        System.out.println("cart=" + cart);

        // 添加完毕后，需要返回到 /* 添加家具的页面 */
        // 请求头中的字段 Referer 保存着发送请求的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
```

## 实现功能19-显示购物车

- ![需求分析](img_62.png)
- ![思路分析](img_63.png)
- ![img_64.png](img_64.png)

> 注意：
> 
> 1. sessionScope.cart.items 取出的是 private Map<Integer, CartItem> items = new HashMap<>();
> 2. 所以通过foreach取出的每一个对象是 HashMap<Integer, CartItem> 的 k-v
> 3. var 取出的是 entry
> 4. 所以要取出cartItem对象，要通过 entry.value
> 
> `BigDecimal`的add方法返回一个新值，而调用者的值不发生改变，因此需要将add的返回结果重新赋给。
> 如 `cartTotalPrice = cartTotalPrice.add(items.get(id).getTotalPrice());`

```html
<%--
1. 通过分析发现 offcanvas-toggle 会在 main.js 中做处理，阻止超链接本身的调转
2. 所以可以将该 class 先去掉，恢复超链接的本身机制
--%>
<a href="views/cart/cart.jsp"
   class="header-action-btn header-action-btn-cart pr-0">
    <i class="icon-handbag">购物车</i>
    <%--${sessionScope.cart.totalCount}的本质是调用cart的getTotalCount()方法--%>
    <span class="header-action-num">${sessionScope.cart.totalCount}</span>
</a>
<a href="#offcanvas-mobile-menu"
   class="header-action-btn header-action-btn-menu offcanvas-toggle d-lg-none">
    <i class="icon-menu"></i>
</a>

<!--显示购物车中的商品-->
<tbody>
<%--显示购物车项，进行循环的地方
1. sessionScope.cart.items 取出的是 private Map<Integer, CartItem> items = new HashMap<>();
2. 所以通过foreach取出的每一个对象是 HashMap<Integer, CartItem> 的 k-v
3. var 取出的是 entry
4. 所以要取出cartItem对象，要通过 entry.value
--%>
<c:if test="${not empty sessionScope.cart.items}">
    <c:forEach items="${sessionScope.cart.items}" var="entry">
        <tr>
            <td class="product-thumbnail">
                <a href=""><img class="img-responsive ml-3" src="assets/images/product-image/1.jpg" alt=""/></a>
            </td>
            <td class="product-name"><a href="#">${entry.value.name}</a></td>
            <td class="product-price-cart"><span class="amount">$${entry.value.price}</span></td>
            <td class="product-quantity">
                <div class="cart-plus-minus">
                    <input class="cart-plus-minus-box" type="text" name="qtybutton" value="${entry.value.count}"/>
                </div>
            </td>
            <td class="product-subtotal">$${entry.value.totalPrice}</td>
            <td class="product-remove">
                <a href=""><i class="icon-close"></i></a>
            </td>
        </tr>
    </c:forEach>
</c:if>
</tbody>

<!--显示商品的总数和总价-->
<h4>共${sessionScope.cart.totalCount}件商品 总价${sessionScope.cart.cartTotalPrice}元</h4>
```

```java
package com.charlie.furns.entity;

// Cart就是购物车，可以存放多个CartItem对象
public class Cart {
    // 家具id -> 购物车项
    private Map<Integer, CartItem> items = new HashMap<>();

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // 返回购物车中所有商品的总价
    public BigDecimal getCartTotalPrice() {
        BigDecimal cartTotalPrice = new BigDecimal(0);
        // 遍历items
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            // 注意：一定要把add后的值重新赋给 cartTotalPrice，这样才能累加
            cartTotalPrice = cartTotalPrice.add(items.get(id).getTotalPrice());
        }
        return cartTotalPrice;
    }
}
```

## 实现功能20-修改/删除/清空购物车

- ![需求分析](img_65.png)
- ![思路分析](img_66.png)

```html
<!--对应的js代码-->
<script type="text/javascript">
    $(function () {
        /*
        - 这里是通过在全部文件中查找 cart-plus-minus 后在 main.js 中找到的处理商品数量+/-的操作
        - 将代码粘贴到cart.jsp中增加自己对商品数量+-的操作，同时将原main.js中代码注释掉，否则会点1次，而操作2次
         */
        var CartPlusMinus = $(".cart-plus-minus");
        CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
        CartPlusMinus.append('<div class="inc qtybutton">+</div>');
        $(".qtybutton").on("click", function () {
            var $button = $(this);
            var oldValue = $button.parent().find("input").val();
            if ($button.text() === "+") {
                var newVal = parseFloat(oldValue) + 1;
            } else {
                // Don't allow decrementing below zero
                if (oldValue > 1) {
                    var newVal = parseFloat(oldValue) - 1;
                } else {
                    newVal = 1;
                }
            }
            $button.parent().find("input").val(newVal);
            // 这里参考了上一行的写法，发现它能够获取 input标签中的新值，所以有了如下获取 cartItemId属性的值
            var cartItemId = $button.parent().find("input").attr("cartItemId");
            // 在这里发出修改购物车的请求
            location.href = "cartServlet?action=updateCount&count=" + newVal + "&id=" + cartItemId;
        });

        // 对删除某项或清空购物车的提示
        $("i.icon-close").click(function () {
            var cartItemName = $(this).parent().parent().parent().find("td:eq(1)").text();
            return confirm("请确认是否删除【 " + cartItemName + " 】")
        })
        $("#clearCart").click(function () {
            return confirm("请确认是否清空购物车？");
        })
    })
</script>

<!--显示购物车清单-->
<c:if test="${not empty sessionScope.cart.items}">
    <c:forEach items="${sessionScope.cart.items}" var="entry">
        <tr>
            <td class="product-thumbnail">
                <a href=""><img class="img-responsive ml-3"
                                src="assets/images/product-image/1.jpg" alt=""/></a>
            </td>
            <td class="product-name"><a href="#">${entry.value.name}</a></td>
            <td class="product-price-cart"><span class="amount">$${entry.value.price}</span>
            </td>
            <td class="product-quantity">
                <div class="cart-plus-minus">
                    <input cartItemId="${entry.key}" class="cart-plus-minus-box" type="text"
                           name="qtybutton" value="${entry.value.count}"/>
                </div>
            </td>
            <td class="product-subtotal">$${entry.value.totalPrice}</td>
            <td class="product-remove">
                <a href="cartServlet?action=deleteItem&id=${entry.key}"><i
                        class="icon-close"></i></a>
            </td>
        </tr>
    </c:forEach>
</c:if>

<!--清空购物车-->
<div class="cart-clear">
    <button>继 续 购 物</button>
    <a id="clearCart" href="cartServlet?action=clear">清 空 购 物 车</a>
</div>
```

```java
package com.charlie.furns.web;

public class CartServlet extends BasicServlet {

    // 增加一个属性
    private FurnService furnService = new FurnServiceImpl();

    // 更新某个CartItem的数量，即更新购物车
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        int count = DataUtils.parseInt(req.getParameter("count"), 1);
        // 获取session中的购物车cart
        Cart cart =  (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
        }
        // 回到请求更新购物车的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    // 根据id，删除购物车中的某项家具
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    // 清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
```

## 实现功能21-生成订单

- ![需求分析](img_67.png)
- ![思路分析](img_68.png)

```mysql
-- 订单表 order
-- 参考界面来写订单表
-- 每个字段都都是用 not null 来约束
-- 字段类型的设计，应当和相关的表的字段相对应
-- 外键是否必要？
-- 1.需要[可以从db层保证数据的一致性]——(以前) FOREIGN KEY(`member_id`) REFERENCES `member`(`id`)	
-- 2.不需要[外键对效率有影响，应当从程序的业务保证一致性]——(现在/推荐)	
CREATE TABLE IF NOT EXISTS `order`(
	id VARCHAR(64) PRIMARY KEY,		-- 订单号
	`create_time` DATETIME NOT NULL,	-- 订单生成事件
	price DECIMAL(11,2) NOT NULL,		-- 订单金额
	`status` TINYINT NOT NULL,		-- 状态0未发货，1已发货，2已结账
	`member_id` INT UNSIGNED NOT NULL	-- 该订单属于某个用户/会员
)CHARSET utf8 ENGINE INNODB;

# 创建订单项
CREATE TABLE IF NOT EXISTS `order_item`(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,	-- 订单项的id
    `name` VARCHAR(64) NOT NULL,			-- 家具名
    `price` DECIMAL(12, 2) NOT NULL,		-- 单价
    `count` INT UNSIGNED NOT NULL,			-- 数量
    `total_price` DECIMAL(11, 2) NOT NULL,		-- 订单项的总价
    `order_id` VARCHAR(64) NOT NULL		-- 关联的订单
)CHARSET utf8 ENGINE INNODB;
```

```java
// OrderDAOImpl实现将订单order保存到数据库表order
package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.OrderDAO;
import com.charlie.furns.entity.Order;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order`(`id`, `create_time`, `price`, `status`, `member_id`) " +
                "VALUES(?, ?, ?, ?, ?);";
        return update(sql, order.getId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getMemberId());
    }
}
```

```java
// OrderServiceImpl，saveOrder接收传来的购物车cart和会员id，保存数据到order表
package com.charlie.furns.service.impl;

public class OrderServiceImpl implements OrderService {

    // 这里体现javaee分层的优点，在service层通过组合多个dao的方法，完成某个业务
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public String saveOrder(Cart cart, int memberId) {
        // 这里的业务逻辑相对复杂
        // 完成的任务是将 cart 购物车中的数据，以order和orderItem形式保存到db
        /*
        TODO 因为生成订单会操作多表，因此会涉及到多表事务问题 ThreadLocal+Mysql事务机制+过滤器
        关于事务的处理，考虑的点比较多，后面专门处理
         */
        // 1. 通过cart对象，构建对应的Order对象
        // 先生成一个UUID，表示当前的订单号，订单号是唯一的
        String orderId = System.currentTimeMillis() + "" + memberId;
        Order order = new Order(orderId, new Date(), cart.getCartTotalPrice(), 0, memberId);
        // 保存order到数据表
        orderDAO.saveOrder(order);

        // 2. 通过cart对象，遍历出cartItem，保存到orderItem

        // 1> 通过keySet遍历cart
        //Map<Integer, CartItem> items = cart.getItems();
        //Set<Integer> keys = items.keySet();
        //for (Integer id : keys) {
        //    CartItem cartItem = items.get(id);
        //    // 保存
        //    orderItemDAO.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getPrice(),
        //            cartItem.getCount(), cartItem.getTotalPrice(), orderId));
        //    // 更新一下furn表的sales的销量，stock存量
        //    // 1) 获取到furn对象
        //    Fur n furn = furnDAO.queryFurnById(id);
        //    // 2) 更新一下这个furn的sales销量，stock库存
        //    furn.setSales(furn.getSales() + cartItem.getCount());
        //    furn.setStock(furn.getStock() - cartItem.getCount());
        //    // 3) 更新到数据库的furn表
        //    furnDAO.updateFurn(furn);
        //}

        // 2> 通过entrySet的方式遍历cart
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            // 保存
            orderItemDAO.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getPrice(),
                    cartItem.getCount(), cartItem.getTotalPrice(), orderId));
            // 更新一下furn表的sales的销量，stock存量
            // 1) 获取到furn对象
            Furn furn = furnDAO.queryFurnById(entry.getKey());
            // 2) 更新一下这个furn的sales销量，stock库存
            furn.setSales(furn.getSales() + cartItem.getCount());
            furn.setStock(furn.getStock() - cartItem.getCount());
            // 3) 更新到数据库的furn表
            furnDAO.updateFurn(furn);
        }

        // 清空购物车
        cart.clear();
        return orderId;
    }
}
```

```java
package com.charlie.furns.web;

public class OrderServlet extends BasicServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 业务代码
        Member member = (Member) req.getSession().getAttribute("member");
        if (member == null) {
            // 说明该用户没有登录，将其转发到登录页面
            //resp.sendRedirect(req.getContextPath() + "/views/member/login2.jsp");
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
            return; // 直接返回
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart || cart.getTotalCount() == 0) {
            //resp.sendRedirect(req.getContextPath() + "/index.jsp");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return; // 直接返回
        }
        // 到此，购物车不为空，且已经登录
        Integer memberId = member.getId();
        String orderId = orderService.saveOrder(cart, memberId);
        // 返回的订单，交给前端显示
        req.getSession().setAttribute("orderId", orderId);
        // 使用重定向方式，请求到 checkout.jsp
        resp.sendRedirect(req.getContextPath() + "/views/order/checkout.jsp");
    }
}
```

## 实现功能22-订单管理

### 课后作业01-添加购物车按钮动态处理

- ![需求分析](img_69.png)

```html
<%--添加家具到购物车按钮，这里加上校验：当家具库存为0时，前台显示“暂时缺货”
后台也加上校验，只有在库存>0时，才能添加到购物车
--%>
<c:if test="${furn.stock > 0}">
    <button title="Add To Cart" class="add-to-cart" furnId="${furn.id}">Add To Cart</button>
</c:if>
<c:if test="${furn.stock == 0}">
    <button title="Add To Cart" class="add-to-cart" furnId="${furn.id}">【暂时缺货】</button>
</c:if>
```

### 课后作业02-订单管理

- ![需求分析](img_70.png)

```java
package com.charlie.furns.dao.impl;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {

    @Override
    public List<Order> queryOrderByMemberId(int memberId) {
        String sql = "SELECT `id`, `create_time` AS `createTime`, `price`, `status`, `member_id` AS `memberId` " +
                "FROM `order` WHERE `member_id`=?";
        return queryMulti(sql, Order.class, memberId);
    }

    @Override
    public BigDecimal totalCount(String orderId) {
        String sql = "SELECT SUM(`count`) FROM `order_item` WHERE `order_id`=?";
        return (BigDecimal) queryScalar(sql, orderId);
    }

    @Override
    public BigDecimal totalPrice(String orderId) {
        String sql = "SELECT SUM(`total_price`) FROM `order_item` WHERE `order_id`=?";
        return (BigDecimal) queryScalar(sql, orderId);
    }
}
```

```java
package com.charlie.furns.web;

public class OrderServlet extends BasicServlet {

    private OrderService orderService = new OrderServiceImpl();

    // 订单管理，显示出用户的所有订单
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int memberId = DataUtils.parseInt(req.getParameter("memberId"), 0);
        List<Order> orders = orderService.showOrders(memberId);
        req.getSession().setAttribute("orders", orders);
        req.getRequestDispatcher("/views/order/order.jsp").forward(req, resp);
    }

    // 订单详情，显示该订单的所有订单项
    protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderItemsByOrderId(orderId);
        req.getSession().setAttribute("orderItems", orderItems);
        req.getSession().setAttribute("totalPrice", orderService.totalPrice(orderId));
        req.getSession().setAttribute("totalCount", orderService.totalCount(orderId));
        req.getRequestDispatcher("/views/order/order_detail.jsp").forward(req, resp);
    }
}
```

## 实现功能23-过滤权限验证

- ![需求分析](img_71.png)
- ![思路分析](img_72.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
    <!--过滤器一般配置在最上面-->
    <filter>
        <filter-name>AuthFilter</filter-name>
        <filter-class>com.charlie.furns.filter.AuthFilter</filter-class>
        <init-param>
            <!--被拦截的目录中，需要放行的资源
            这里配置后，还需要在过滤器中处理-->
            <param-name>excludedUrls</param-name>
            <param-value>/views/manage/manage_login.jsp, /views/member/login2.jsp</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>AuthFilter</filter-name>
        <!--这里配置要进行验证的url
            1. 在filter-mapping中url配置要拦截/过滤的url
            2. 对于不需要拦截/过滤的url，就不配置
            3. 对于要拦截的目录的某些要放行的资源，在配置参数中指定放行
        -->
        <!--配置要拦截的web资源-->
        <url-pattern>/views/manage/*</url-pattern>
        <url-pattern>/views/cart/*</url-pattern>
        <url-pattern>/views/member/*</url-pattern>
        <url-pattern>/views/order/*</url-pattern>
        <!--配置要拦截的servlet-->
        <url-pattern>/cartServlet</url-pattern>
        <url-pattern>/manage/furnServlet</url-pattern>
        <url-pattern>/orderServlet</url-pattern>
    </filter-mapping>
</web-app>
```

```java
package com.charlie.furns.filter;

import com.charlie.furns.entity.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 这是用于权限验证的过滤器，对指定的url进行检验
 * 如果登录过，就放行；如果没有登录，就返回到登录页面
 */
public class AuthFilter implements Filter {

    // 把要排除的url放入到excludedUrls
    private List<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取到配置的 excludedUrls
        String strExcludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] splitUrl = strExcludedUrls.split(",");
        // 将 String[] 转成 List<String>
        excludedUrls = Arrays.asList(splitUrl);
        //System.out.println("excludedUrls=" + excludedUrls); // [/views/manage/manage_login.jsp,  /views/member/login2.jsp]
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        // 等到用户请求的url
        String url = req.getServletPath();
        //System.out.println("url=" + url);   // url=/views/member/login2.jsp

        // 判断是否需要验证，如果请求的url不在排除范围，则走过滤的逻辑
        if (!excludedUrls.contains(url)) {
            // 得到session中的member对象
            Member member = (Member) req.getSession().getAttribute("member");
            if (null == member) {   // 如果成立，说明未登录
                // 请求转发到登录页面，请求转发不走过滤器！
                req.getRequestDispatcher("/views/member/login2.jsp").forward(servletRequest, servletResponse);
                // 如果这里使用重定向，就会导致无限请求...
                // 直接返回，后面的语句不再执行
                return;
            }
        }
        // 到这里说明用户已登录 或者 在排除范围内，则继续访问资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
```

## 实现功能24-事务管理

- ![需求分析](img_74.png)
- ![思路分析](img_73.png)
- ![程序思路](img_75.png)

```java
package com.charlie.furns.utils;

// 基于druid数据库连接池的工具类
// 为了进行事务管理，增加ThreadLocal
public class JDBCUtilsByDruid {

    private static DataSource ds;
    // 定义属性ThreadLocal。这里存放一个Connection
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            // 因为是web项目，工作目录在out，文件的加载需要使用类加载器
            // 找到我们的工作目录
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
//            properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 从ThreadLocal获取Connection，从而保证在同一个线程中，获取的是同一个Connection
    public static Connection getConnection() {
        Connection connection = threadLocalConn.get();
        if (connection == null) {   // 说明当前的threadLocalConn没有连接
            // 就从数据库连接池中，取出一个连接放入
            try {
                connection = ds.getConnection();
                // 将连接设置为手动提交，即取消自动提交，默认每执行一次数据库操作都会自动提交，
                // 取消自动提交保证数据统一管理
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 将连接与线程关联
            threadLocalConn.set(connection);
        }
        return connection;
    }

    //原方法
    //public static Connection getConnection() throws SQLException {
    //    return ds.getConnection();
    //}

    // 提交事务(成功)
    public static void commit() {
        Connection connection = threadLocalConn.get();
        if (connection != null) {   // 确保该链接非空/有效
            try {
                connection.commit();    // 事务提交
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close(); // 关闭连接
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // 注意：
        // 1. 当提交后，需要把connection从threadLocalConn中清除掉
        // 2. 否则会造成threadLocalConn长时间持有该连接，会影响效率
        // 3. 也因为Tomcat底层使用的线程池技术
        threadLocalConn.remove();
    }

    // 事务回滚(失败)，所谓回滚是撤销和某个connection关联的操作
    public static void rollback() {
        Connection connection = threadLocalConn.get();
        if (connection != null) {   // 保证当前连接有效
            try {
                connection.rollback();  // 回滚
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close(); // 关闭
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        threadLocalConn.remove();
    }

    //关闭连接, 强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

- 删除[BasicDAO](src/com/charlie/furns/dao/BasicDAO.java)中的finally关闭连接池连接操作

```java
package com.charlie.furns.web;

public class OrderServlet extends BasicServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 业务代码
        Member member = (Member) req.getSession().getAttribute("member");
        if (member == null) {
            // 说明该用户没有登录，将其转发到登录页面
            //resp.sendRedirect(req.getContextPath() + "/views/member/login2.jsp");
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
            return; // 直接返回
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart || cart.isEmpty()) {
            //resp.sendRedirect(req.getContextPath() + "/index.jsp");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return; // 直接返回
        }

        /* 分析：
        1. 如果只是希望对orderService.saveOrder方法进行事务控制
        2. 可以不使用过滤器，直接在这里进行提交和回滚即可
        3. 这里做了演示，后面将其提到filter中，控制更多类似的事务管理
         */
        //String orderId = null;
        //try {
        //    orderId = orderService.saveOrder(cart, member.getId());
        //    JDBCUtilsByDruid.commit();      // 提交事务
        //} catch (Exception e) {
        //    JDBCUtilsByDruid.rollback();    // 出现异常，回滚事务
        //    throw new RuntimeException(e);
        //}

        String orderId = orderService.saveOrder(cart, member.getId());
        // 返回的订单，交给前端显示
        req.getSession().setAttribute("orderId", orderId);
        // 使用重定向方式，请求到 checkout.jsp
        resp.sendRedirect(req.getContextPath() + "/views/order/checkout.jsp");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
    <!--配置管理事务的过滤器，在权限验证后，即先进行权限验证-->
    <filter>
        <filter-name>TransactionFilter</filter-name>
        <filter-class>com.charlie.furns.filter.TransactionFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>TransactionFilter</filter-name>
        <!--这里对所有请求都做处理，后续可以根据需求再调整-->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

```java
package com.charlie.furns.filter;

// 管理事务的过滤器
public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtilsByDruid.commit();      // 统一提交
        } catch (Exception e) { // 出现异常
            JDBCUtilsByDruid.rollback();    // 回滚事务
            e.printStackTrace();
        }
    }
}
```

- ![异常机制参与业务逻辑](img_76.png)

## 完成功能25-统一错误提示页面

- ![需求分析](img_77.png)
- ![思路分析](img_78.png)
- ![img_79.png](img_79.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
    <!--404:NOT FOUND 错误提示页面-->
    <error-page>
        <error-code>404</error-code>
        <location>/views/error/404.jsp</location>
    </error-page>
    <!--500:服务器内部错误 错误提示页面-->
    <error-page>
        <error-code>500</error-code>
        <location>/views/error/500.jsp</location>
    </error-page>
</web-app>
```

## 实现功能26-Ajax检验注册名

- ![需求分析](img_80.png)
- ![思路分析](img_81.png)

```java
package com.charlie.furns.web;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 该servlet处理和Member相关的请求
 */
public class MemberServlet2 extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    // 验证某个用户名是否已经存在
    protected void isExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取用户名
        String username = req.getParameter("username");
        // 2. 调用service
        boolean isExist = memberService.isExistsUsername(username);
        /* 3. 思路
        1) 如何返回json格式(根据前端需求来写)
        2) {"isExist": false};
         */
        // 4. 先使用最简单的拼接 -> 后续改进
        //String resultJson = "{\"isExist\": " + isExist + "}";
        // => 将要返回的数据=>map=>json
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isExist", isExist);
        String resultJson = new Gson().toJson(resultMap);
        // 5. 返回
        // 这里前端是ajax发出的请求，返回的数据也是交给ajax处理的
        // 不需要设置resp.setContextType("text/json")，这是让浏览器处理
        resp.getWriter().write(resultJson);
    }

    // 验证码校验
    protected void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        // 从session中获取到KaptchaServlet创建的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        Map<String, Object> codeMap = new HashMap<>();
        if (token != null && token.equalsIgnoreCase(code)) {
            codeMap.put("code", true);
        } else {
            codeMap.put("code", false);
        }
        resp.getWriter().write(new Gson().toJson(codeMap));
    }
}
```

```html
<script type="text/javascript">
    $(function () { // 页面加载完毕后执行function

        // 给用户名输入框绑定一个blur
        $("#username").blur(function () {     // 失去焦点后触发
            // 获取输入的用户名
            var username = $(this).val();     // 或者 this.value
            // 发出ajax请求
            $.getJSON(
                "memberServlet2",
                // "action=isExistUsername&username=" + username,  // 发送的数据可以以字符串的形式，也可以以json的格式
                {   // 相当于发送的ajax请求，携带的数据是通过json对象放入
                  "action": "isExistUsername",
                  "username": username,
                  "date": new Date()
                },
                function (data) {   // data是请求成功后返回的数据
                    // console.log("data=", data);
                    if (data.isExist) {
                        $("span.errorMsg").text("用户名已存在~");
                    } else {
                        $("span.errorMsg").text("用户名可以使用！");
                    }
                }
            )
        })

        // 验证码ajax判断
        $("#code").blur(function () {
            var codeVal = this.value;
            $.getJSON(
                "memberServlet2",
                {
                    "action": "captcha",
                    "code": codeVal,
                    "date": new Date()
                },
                function (data) {
                    // console.log("data=", data);
                    if(data.code) {
                        $("#recode").text("验证码正确！");
                    } else {
                        $("#recode").text("验证码有误~");
                    }
                }
            )
        })
    )
</script>
```

## 实现功能27-Ajax添加购物车

- ![需求分析](img_82.png)
- ![思路分析](img_83.png)

```java
package com.charlie.furns.web;

public class CartServlet extends BasicServlet {

    // 增加一个属性
    private FurnService furnService = new FurnServiceImpl();

    // 处理Ajax发出的添加商品到购物车的请求
    protected void addItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        int stock = furn.getStock();
        if (stock == 0) {
            return;
        }
        CartItem cartItem = new CartItem(furn.getId(), furn.getName(), 1, furn.getPrice(), furn.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 添加完毕后，返回json数据给前端，前端得到json数据后进行局部刷新即可
        // 1. 规定json格式 {"cartTotalCount": 3}
        // 2. 创建map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("cartTotalCount", cart.getTotalCount());
        // 3. 转成json并返回
        resp.getWriter().write(new Gson().toJson(resultMap));
    }
}
```

```html
<script type="text/javascript">
    $(function () {
        // 给 add to cart 按键绑定事件 jquery
        $(".add-to-cart").click(function () {
            // 获取到点击的furn的-id
            var furnId = $(this).attr("furnId");
            // 发出一个请求添加家具 => ajax
            // location.href = "cartServlet?action=addItem&id=" + furnId;

            // 这里使用jquery发出ajax请求，得到数据进行局部刷新，解决刷新页面的效率低的问题
            $.getJSON(
                "cartServlet",
                {   // 这里通过json方式，传入ajax请求要携带的数据
                    "action": "addItemByAjax",
                    "id": furnId,
                    "date": new Date()
                },
                function (data) {
                    // console.log("data=", data); // cartTotalCount: 12
                    // 更新购物车商品数量
                    $("span.header-action-num").text(data.cartTotalCount);
                }
            )
        })
    })
</script>
```

> 出现问题：在未登录时，点击"add to cart"不会经过过滤器跳转到会员登录页面！
> 1. 主要是因为服务器得到的是ajax发送过来的request请求，也就是说这个请求不是浏览器请求，而是ajax请求。
>   所以，servlet对request进行请求转发/重定向都不能影响浏览器的跳转
> 2. 解决方案：如果要想实现跳转，可以返回`url`，在浏览器执行`window.location(url);`

- ![ajax请求](img_84.png)

```java
package com.charlie.furns.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    // 判断请求是否为Ajax请求，根据请求头字段X-Requested-With判断
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
```

```java
package com.charlie.furns.filter;

/**
 * 这是用于权限验证的过滤器，对指定的url进行检验
 * 如果登录过，就放行；如果没有登录，就返回到登录页面
 */
public class AuthFilter implements Filter {

    // 把要排除的url放入到excludedUrls
    private List<String> excludedUrls;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        // 等到用户请求的url
        String url = req.getServletPath();
        //System.out.println("url=" + url);   // url=/views/member/login2.jsp

        // 判断是否需要验证，如果请求的url不在排除范围，则走过滤的逻辑
        if (!excludedUrls.contains(url)) {
            // 得到session中的member对象
            Member member = (Member) req.getSession().getAttribute("member");
            if (null == member) {   // 如果成立，说明未登录
                // 判断是否为Ajax请求
                if (!WebUtils.isAjaxRequest(req)) { // 不是Ajax请求
                    // 请求转发到登录页面，请求转发不走过滤器！
                    req.getRequestDispatcher("/views/member/login2.jsp").forward(servletRequest, servletResponse);
                    // 如果这里使用重定向，就会导致无限请求...
                } else {    // 是Ajax请求
                    // 返回一个url，以json格式返回
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("url", "views/member/login2.jsp");
                    servletResponse.getWriter().write(new Gson().toJson(resultMap));
                }
                // 直接返回，后面的语句不再执行
                return;
            }
        }
        // 到这里说明用户已登录 或者 在排除范围内，则继续访问资源
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
```

```html
$(".add-to-cart").click(function () {
    // 获取到点击的furn的-id
    var furnId = $(this).attr("furnId");
    // 发出一个请求添加家具 => ajax
    // location.href = "cartServlet?action=addItem&id=" + furnId;

    // 这里使用jquery发出ajax请求，得到数据进行局部刷新，解决刷新页面的效率低的问题
    $.getJSON(
        "cartServlet",
        {   // 这里通过json方式，传入ajax请求要携带的数据
            "action": "addItemByAjax",
            "id": furnId,
            "date": new Date()
        },
        function (data) {
            // console.log("data=", data); // cartTotalCount: 12
            if (data.url === undefined) {   // 用户已经登录，没有返回跳转到登录页面的url
                // 更新购物车商品数量
                $("span.header-action-num").text(data.cartTotalCount);
            } else {
                // 到这里说明当前服务器返回url，需要跳转到登录页面
                location.href = data.url;
            }
        }
    )
})
```
