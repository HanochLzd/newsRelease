<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 19:15
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<h1 class="header-dividing" style="float: left;margin-top: 0">全部新闻(总计 ${totalNewsCount}条)</h1>
<div class="page-header" style="height: 1px"></div>

<div class="input-group" style="width: 400px;margin-bottom: 10px">
    <span class="input-group-addon"><i class="icon icon-search"></i></span>
    <span class="input-group-addon fix-border fix-padding" style="width: 100px">
         <select class="form-control" name="type" id="type">
            <option value="theme">主题</option>
            <option value="title">标题</option>
            <option value="author">编辑</option>
        </select>
    </span>
    <input type="text" class="form-control" size="15" style="min-height: 33px" id="searchContent">
    <span class="input-group-btn">
    <button id="search-button" class="btn btn-default" type="button">搜索</button>
  </span>
</div>

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
    <tbody id="allNews">
    <c:forEach var="news" items="${newsVoList}">
        <tr>
            <td>${news.newsId}</td>
            <td>${news.newsThemeName}</td>
            <td>${news.newsAuthor}</td>
            <td>${news.newsTitle}</td>
            <td>${news.newsUp}</td>
            <td>${news.newsDown}</td>
            <td>${news.newsCreateTime}</td>
            <td><a href="${ctx}/admin/news/editPage?newsId=${news.newsId}">
                <button class="btn btn-info " type="button">编辑</button>
            </a><a
                    href="${ctx}/admin/news/delete?newsId=${news.newsId}" onclick="return confirm('请确认删除');">
                <button class="btn btn-warning " type="button">删除</button>
            </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    $("#search-button").click(function () {
        let type = $("#type option:selected").val();
        let searchContent = $("#searchContent").val();
        // alert("[" + type + "," + searchContent + "]");
        if (searchContent === "" || searchContent == null) {
            location.reload();
        }
        $.ajax({
            type: "POST",
            url: '${ctx}/admin/news/queryByExample',
            dataType: "json",
            data: {
                type: type,
                searchContent: searchContent
            },
            success: function (data) {
                $("#allNews")[0].innerHTML = "";
                for (let i = 0; i < data.length; i++) {
                    $("#allNews")[0].innerHTML += "<tr>" +
                        "            <td>" + data[i].newsId + "</td>" +
                        "            <td>" + data[i].newsThemeName + "</td>" +
                        "            <td>" + data[i].newsAuthor + "</td>" +
                        "            <td>" + data[i].newsTitle + "</td>" +
                        "            <td>" + data[i].newsUp + "</td>" +
                        "            <td>" + data[i].newsDown + "</td>" +
                        "            <td>" + data[i].newsCreateTime + "</td>" +
                        "            <td><a href=\"${ctx}/admin/news/editPage?newsId=" + data[i].newsId + "\">" +
                        "                <button class=\"btn btn-info \" type=\"button\">编辑</button>" +
                        "            </a><a\n" +
                        "                    href=\"${ctx}/admin/news/delete?newsId=" + data[i].newsId + "\" onclick=\"return confirm('请确认删除');\">" +
                        "                <button class=\"btn btn-warning \" type=\"button\">删除</button>" +
                        "            </a></td>\n" +
                        "        </tr>";
                }
            },
            error: function () {
                $("#allNews")[0].innerHTML = "";
            }
        });
    })
</script>