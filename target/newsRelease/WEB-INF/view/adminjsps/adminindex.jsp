<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 19:18
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<html>
<head>
    <title>新闻发布后台管理</title>
    <jsp:include page="admincommonfile.jsp"/>
    <style>
        html body {
            margin-top: 0;
            background-color: rgb(242, 242, 242);
        }

        #all {
            /*border: 1px solid red;*/
            position: absolute;
            margin-left: 70px;
            height: auto;
        }

        /*#top {*/
        /*border: 1px solid black;*/
        /*width: auto;*/
        /*height: 140px;*/
        /*!*position: absolute;*!*/
        /*float: top;*/
        /*left: 100px;*/
        /*top: 20px;*/
        /*}*/

        #left {
            /*border: 1px solid black;*/
            float: left;
            width: 250px;
            height: 800px;
            margin-top: 2px;
            padding: 20px;
        }

        #center {
            /*border: 1px solid black;*/
            background-color: rgb(242, 242, 242);
            float: left;
            width: 1160px;
            height: auto;
            margin-top: 2px;
            margin-left: 2px;
            text-align: center;
            /*padding-left: 2px;*/
            padding: 20px;
        }

        #span-date {
            color: rgb(128, 128, 161);
            float: right;
            margin-top: 6px
        }

        #span-welcome {
            color: rgb(128, 128, 161);
            float: left;
            margin-top: 6px
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="min-height: 26px;margin-bottom: 0">

    <c:choose>
        <c:when test="${not empty sessionScope.admin}">
            <span id="span-welcome"><b>欢迎使用新闻管理系统！</b>当前用户：${admin.userName}</span>
        </c:when>
        <c:otherwise>
            <form class="navbar-form navbar-left" action="${ctx}/adminLogin" method="post">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="用户名" style="height: 23px">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" style="height: 23px">
                </div>
                <button type="submit" class="btn btn-default" style="height: 23px;text-align: center;font-size: 10px">登录
                </button>
            </form>
        </c:otherwise>
    </c:choose>
    <%--<i class="icon icon-spin icon-spinner-snake" style="float: right;margin-top: 8px"></i>--%>
    <a href="https://github.com/">
        <i class="icon icon-github icon-2x" style="margin-top: 2px;margin-left:2px;float: right;"></i>
    </a>
    <span id="span-date"></span>
</nav>
<div id="all">
    <div id="top">
        <%--<jsp:include page="top.jsp"/>--%>
    </div>

    <div id="left">
        <jsp:include page="adminleft.jsp"/>
    </div>

    <div id="center">
        <c:choose>
            <c:when test="${not empty pageInfo}">
                <jsp:include page="${pageInfo}"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="adminwelcome.jsp"/>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<script>
    $(function () {
        var spandate = $("#span-date");
        spandate[0].innerHTML = new Date();
    });
</script>
</body>
</html>
