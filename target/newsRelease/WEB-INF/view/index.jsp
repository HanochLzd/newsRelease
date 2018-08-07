<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/27
  Time: 11:04
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<html>
<head>
    <meta charset="utf-8">
    <title>新闻中国</title>
    <jsp:include page="include/commonfile.jsp"/>
    <style>
        body {
            background-color: rgb(242, 242, 242);
        }

        #all {
            /*border: 1px solid red;*/
            position: absolute;
            margin-left: 200px;
            height: auto;
        }

        #top {
            /*border: 1px solid black;*/
            width: auto;
            height: 140px;
            /*position: absolute;*/
            float: top;
            left: 100px;
            top: 20px;
        }

        #left {
            /*border: 1px solid black;*/
            float: left;
            width: 250px;
            height: 400px;
            margin-top: 2px;
        }

        #center {
            /*border: 1px solid black;*/
            background-color: white;
            float: left;
            width: 900px;
            height: auto;
            margin-top: 8px;
            margin-left: 2px;
            text-align: center;
            padding-left: 2px;
        }

        a {
            color: black;
        }

        a:hover {
            color: #43B1FC;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="min-height: 23px;margin-bottom: 0">
    <form id="admin-form" class="navbar-form navbar-left" action="${ctx}/adminLogin" method="post">
        <div class="form-group">
            <input type="text" id="username" name="username" class="form-control" placeholder="用户名"
                   style="height: 23px">
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" class="form-control" placeholder="密码"
                   style="height: 23px">
        </div>
        <button type="submit" class="btn btn-default" style="height: 23px;text-align: center;font-size: 10px">登录
        </button>
    </form>
    <%--<i class="icon icon-spin icon-spinner-snake" style="float: right;margin-top: 8px"></i>--%>
    <a href="https://github.com/HanochLzd/newsRelease">
        <i class="icon icon-github icon-2x" style="margin-top: 2px;margin-left:2px;float: right;"></i>
    </a>
    <span id="span-date" style="color: rgb(128, 128, 161);float: right;margin-top: 6px"></span>
</nav>
<div id="all">
    <div id="top">
        <jsp:include page="top.jsp"/>
    </div>

    <div id="left">
        <jsp:include page="left.jsp"/>
    </div>

    <div id="center">
        <c:choose>
            <c:when test="${not empty pageInfo}">
                <jsp:include page="${pageInfo}"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="center.jsp"/>
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
