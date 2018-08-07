<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/28
  Time: 13:48
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<style>
    .li-title {
        list-style: initial;
        width: auto;
        font-family: 'Microsoft Yahei', sans-serif;
        margin-bottom: 5px;
        font-size: 14px;
    }

    #ul-title_1, #ul-title_2 {
        margin-top: 0;
    }
</style>
<div class="panel panel-warning" style="margin-bottom: 10px;margin-top: 5px;">
    <div class="panel-heading" style="background-color: #43B1FC;color: white">
        <c:forEach items="${newsMap}" var="entry" begin="0" end="0">
            <c:set var="theme1" value="${entry.key}"/>
            <b>${theme1}</b>
        </c:forEach>
    </div>
    <div class="panel-body">
        <ul id="ul-title_1">
            <c:forEach items="${newsMap[theme1]}" var="news" begin="0" end="5">
                <li class="li-title"><a href="${ctx}/showNews?newsId=${news.newsId}">${news.newsTitle}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="panel panel-info">
    <div class="panel-heading">
        <c:forEach items="${newsMap}" var="entry" begin="1" end="1">
            <c:set var="theme2" value="${entry.key}"/>
            <b>${theme2}</b>
        </c:forEach>
    </div>
    <div class="panel-body">
        <ul id="ul-title_2">
            <c:forEach items="${newsMap[theme2]}" var="news" begin="0" end="7">
                <li class="li-title"><a href="${ctx}/showNews?newsId=${news.newsId}">${news.newsTitle}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
