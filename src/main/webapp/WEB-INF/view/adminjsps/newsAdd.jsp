<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 13:44
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<script src="${ctx}/kindeditor/kindeditor.min.js"></script>

<style>
    .input-group {
        margin-top: 10px;
        margin-bottom: 10px;
    }
</style>

<h1 class="header-dividing" style="float: left;margin-top: 0">新闻发布</h1>
<div class="page-header" style="height: 1px"></div>

<form action="${ctx}/admin/news/add" method="post">
    <div class="input-group" style="width: 365px">
        <span class="input-group-addon">新闻编号</span>
        <input name="newsId" type="text" class="form-control" size="20" placeholder="自动生成哦！^_^" disabled>
        <span class="input-group-addon"><i class="icon icon-heart"></i></span>
    </div>

    <div class="input-group" style="width: 200px">
        <span class="input-group-addon">新闻主题</span>
        <select class="form-control" name="themeId">
            <c:forEach items="${themes}" var="theme">
                <option value="${theme.themeId}">${theme.themeName}</option>
            </c:forEach>
        </select>
    </div>

    <div class="input-group" style="width: 300px">
        <span class="input-group-addon">新闻编辑(责任)</span>
        <input name="newsAuthor" type="text" class="form-control" size="20" placeholder="新闻编辑" required>
        <span class="input-group-addon"><i class="icon icon-heart"></i></span>
    </div>

    <div class="input-group" style="width: 600px">
        <span class="input-group-addon">新闻标题</span>
        <input name="newsTitle" type="text" class="form-control" size="20" placeholder="新闻标题" required>
        <span class="input-group-addon"><i class="icon icon-heart"></i></span>
    </div>

    <div class="input-group">
        <textarea id="contentSimple" name="newsContent" class="form-control kindeditorSimple" title="新闻正文"
                  style="height:400px;width: 1100px">在此编辑文章正文...</textarea>
    </div>

    <div class="input-group" style="width: 200px">
        <span class="input-group-addon"><i class="icon icon-thumbs-o-up"></i></span>
        <input type="text" class="form-control" size="1px" value="0" disabled>
        <span class="input-group-addon"><i class="icon icon-thumbs-o-down"></i></span>
        <input type="text" class="form-control" size="1px" value="0" disabled>
    </div>
    <div class="input-group">
        <input type="submit" class="btn btn-primary" value=" 提 交 "/>
    </div>
</form>
<script>
    KindEditor.create('textarea.kindeditorSimple', {
        basePath: '${ctx}/kindeditor',
        bodyClass: 'article-content',
        resizeType: 1,
        allowPreviewEmoticons: false,
        allowImageUpload: true,
        items: [
            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist', '|', 'emoticons', 'image', 'link'
        ]
    });
</script>