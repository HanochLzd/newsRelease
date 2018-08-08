<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/8/8
  Time: 10:25
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<style>
    .input-group {
        margin-top: 10px;
        margin-bottom: 10px;
    }
</style>
<h1 class="header-dividing" style="float: left;margin-top: 0">主题编辑</h1>
<div class="page-header" style="height: 1px"></div>

<form action="${ctx}/admin/themes/add" method="post">
    <div class="input-group" style="width: 365px">
        <span class="input-group-addon">主题编号</span>
        <input name="themeId" type="text" class="form-control" size="20" placeholder="主题编号"
               required>
        <span class="input-group-addon"><i class="icon icon-heart"></i></span>
    </div>

    <div class="input-group" style="width: 250px">
        <span class="input-group-addon">主题名称</span>
        <input name="themeName" type="text" class="form-control" size="20" placeholder="主题名称"
                required>
        <span class="input-group-addon"><i class="icon icon-heart"></i></span>
    </div>

    <div class="input-group" style="width: 300px">
        <span class="input-group-addon">主题描述</span>
        <input name="themeDetail" type="text" class="form-control" size="20" placeholder="主题描述"
               required>
        <span class="input-group-addon"><i class="icon icon-heart"></i></span>
    </div>

    <div class="input-group" style="width: 300px">
        <span class="input-group-addon">主题等级</span>
        <input name="themeLevel" type="text" class="form-control" size="10" placeholder="主题等级"
               required>
        <span class="input-group-addon"><i class="icon icon-heart"></i>（填入1~15的整数）</span>
    </div>

    <div class="input-group">
        <input type="submit" class="btn btn-primary" value=" 添 加 "/>
        <%--<input style="margin-left: 10px" class="btn btn-info " type="reset" value=" 重 置 "/>--%>
    </div>
</form>