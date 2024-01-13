<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
    <%--引入jquery--%>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () { // 页面加载完毕后，执行 function(){}
            // $("i.icon-close").click(function () {
            //     var del = confirm("请确认是否删除？");
            //     return del; // 确认：true 取消：false
            // });

            // 绑定点击事件
            $("a.deleteCss").click(function () {
                // 获取到要删除的家具名
                // this表示当前点击的a标签的dom对象 -> jquery对象 $(this)
                // 通过下面前端代码可以看出，家具名字对应的元素与该标签的相对关系
                // this父标签的父标签下的第二个子标签的text内容
                var furnName = $(this).parent().parent().find("td:eq(1)").text();
                /*
                1. confirm方法会弹出一个确认窗口
                2. 点击确认返回 true
                3. 点击取消返回 false
                 */
                return confirm("请确认是否删除【" + furnName + "】");
            })
        })
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <form class="action-form" action="#">
                                    <input class="form-control" placeholder="Enter your search key" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="#">后台管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="views/manage/furn_add.jsp?pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">添加家具</a>
                        </div>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  End -->
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
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
<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">家居后台管理</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>商家</th>
                                <th>价格</th>
                                <th>销量</th>
                                <th>库存</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--取出furns集合，循环显示 getItems()--%>
                            <c:forEach items="${requestScope.page.items}" var="furn">
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
                                            <%--修改家具信息--%>
                                        <a class="updateCss" href="manage/furnServlet?action=showFurn&id=${furn.id}&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}"><i class="icon-pencil"></i></a>
                                            <%--删除家具信息--%>
                                        <a class="deleteCss" href="manage/furnServlet?action=del&id=${furn.id}&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}"><i
                                                class="icon-close"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
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
    </div>
</div>
<!-- Cart Area End -->

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
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a>
                                        </li>
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