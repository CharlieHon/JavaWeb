<%--JSP文件头--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>韩顺平教育-家居网购</title>
    <%--为了让我们确定页面资源使用一个固定的参考路径，这里设置了base--%>
    <%--<base href="http://localhost:8080/jiaju_mall/">--%>
    <%--JSP脚本表达式--%>
    <base href="<%=request.getContextPath() + "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
<!--    引入jquery，先使用-->
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
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

            // 注册-绑定点击事件
            $("#sub-btn").click(function () {

                // 1. 获取到输入的用户名
                var usernameVal = $("#username").val();
                // 编写正则表达式，进行验证
                var usernamePattern = /^\w{3,10}$/;
                // 验证
                if (!usernamePattern.test(usernameVal)) {
                    // 展示错误提示-属性过滤器
                    $("span[class='errorMsg']").text("用户名格式不对，需要6~10字符");
                    // 阻止表单提交，仅进行验证
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

                // 5. 验证码校验，不能为空
                var codeText =  $("#code").val();
                // 去掉验证码前后的可供
                codeText = $.trim(codeText);
                if (codeText == null || codeText === "") {
                    // 提示
                    $("span.errorMsg").text("验证码不能为空");
                    return false;
                }

                // $("span[class='errorMsg']").text("通过验证！");
                // 完成前端验证后，直接提交表单
                return true;
            })
        })
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo" /></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>会员登录</h4>
                        </a>
                        <a id="register_tab" data-bs-toggle="tab" href="#lg2">
                            <h4>会员注册</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <%--提示错误信息-el表达式--%>
                                    <span style="font-size: 18pt;font-weight: bold;float: right;color: gainsboro">
                                        ${requestScope.msg}
                                    </span>
                                    <form action="memberServlet2" method="post">
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
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <%--<span class="errorMsg"--%>
                                    <%--      style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">--%>
                                    <%--</span>--%>
                                    <span class="errorMsg" style="font-size: 18pt;font-weight: bold;float: right;color: gainsboro">
                                        ${requestScope.msg}
                                    </span>
                                    <!--注册表单-->
                                    <form action="memberServlet2" method="post">
                                        <%--增加隐藏域表示register请求--%>
                                        <input type="hidden" name="action" value="register"/>
                                        <input type="text" id="username" name="username" value="${param.username}" placeholder="用户名"/>
                                        <input type="password" id="password" name="password" value="${param.password}" placeholder="输入密码"/>
                                        <input type="password" id="repwd" name="repassword" value="${param.password}" placeholder="确认密码"/>
                                        <input name="email" id="email" placeholder="电子邮件" value="${param.email}" type="email"/>
                                        <input type="text" id="code" name="code" style="width: 50%" placeholder="验证码"/>
                                            <img id="codeImg" alt="" src="kaptchaServlet" style="width: 120px;height: 50px">
                                            <span style="border-width: 0;color: red; font-size: 15px" type="text" id="recode"></span>
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>会员注册</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a></li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.html">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2021 韩顺平教育~</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>