<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/8/1
  Time: 9:58
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/jqcloud/jqcloud.css"/>
<script src="${ctx}/jqcloud/jqcloud-1.0.3.min.js"></script>
<h1 class="header-dividing" style="float: left;margin-top: 0">新闻主题</h1>
<div class="page-header" style="height: 1px"></div>
<div id="example" style="width: 550px; height: 350px;"></div>
<table class="table table-bordered" id="tableDataGridExample">
    <thead>
    <tr>
        <th>主题编号</th>
        <th>主题名称</th>
        <th>主题描述</th>
        <th>主题优先级</th>
        <th>主题创建时间</th>
        <th>操作</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="theme" items="${themes}">
        <tr>
            <td>${theme.themeId}</td>
            <td>${theme.themeName}</td>
            <td>${theme.themeDetail}</td>
            <td>${theme.themeLevel}</td>
            <td>${theme.themeCreateTime}</td>
            <td><a href="${ctx}/admin/themes/editPage?themeId=${theme.themeId}">编辑</a>|<a
                    href="${ctx}/admin/themes/delete?themeId=${theme.themeId}" onclick="return confirm('请确认删除');">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    var word_array = [];
    <c:forEach var="theme" items="${themes}">
    word_array.push({
        text: '${theme.themeName}',
        weight: ${theme.themeLevel},
        html: {
            title: '${theme.themeDetail}'
        }
    });
    </c:forEach>

    $(function () {
        // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
        $("#example").jQCloud(word_array);
    });
</script>
