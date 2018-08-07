<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 19:15
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<h1 class="header-dividing" style="float: left;margin-top: 0">全部新闻</h1>
<div class="page-header" style="height: 1px"></div>
<table class="table table-bordered" id="tableDataGridExample">
    <thead>
    <tr>
        <th>新闻编号</th>
        <th>新闻主题</th>
        <th>新闻作者(责任)</th>
        <th>标题</th>
        <th>赞</th>
        <th>踩</th>
        <th>发布时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="news" items="${newsVoList}">
        <tr>
            <td>${news.newsId}</td>
            <td>${news.newsThemeName}</td>
            <td>${news.newsAuthor}</td>
            <td>${news.newsTitle}</td>
            <td>${news.newsUp}</td>
            <td>${news.newsDown}</td>
            <td>${news.newsCreateTime}</td>
            <td><a href="${ctx}/admin/news/editPage?newsId=${news.newsId}">编辑</a>|<a
                    href="${ctx}/admin/news/delete?newsId=${news.newsId}" onclick="return confirm('请确认删除');">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>