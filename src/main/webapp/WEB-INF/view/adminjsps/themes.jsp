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
