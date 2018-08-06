<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 13:51
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/res/css/global.css">
</head>
<body>
<h1 style="margin-left: 182px"><b>新闻发布平台后台管理</b></h1>
<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li class="layui-this" style="color: rgb(67, 177, 252);border-bottom: 2px solid rgb(67, 177, 252);">登入
                </li>
                <li><a href="reg.html">注册</a></li>
            </ul>
            <span style="color: red">${message}</span>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post" action="${ctx}/adminLogin">
                            <div class="layui-form-item">
                                <label for="L_name" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_name" name="username" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="password" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" lay-submit
                                        style="background-color: rgb(67, 177, 252)">立即登录
                                </button>
                                <span style="padding-left:20px;">
                                    <a href="forget.html">忘记密码？</a>
                                </span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="fly-footer">
    <p> 2018 &copy; <a href="http://www.layui.com/"
                       target="_blank">layui.com 出品</a></p>
</div>

<script src="${ctx}/res/layui/layui.js"></script>


</body>
</html>
