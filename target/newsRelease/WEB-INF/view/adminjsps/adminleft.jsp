<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 19:15
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<nav class="menu" data-ride="menu" style="width: 200px">
    <ul id="treeMenu" class="tree tree-menu" data-ride="tree">
        <li><a href="${ctx}/"><i class="icon icon-home"></i>首页</a></li>
        <li id="news">
            <a href="#"><i class="icon icon-newspaper-o"></i>新闻</a>
            <ul>
                <li id="adminnews"><a href="${ctx}/admin/news/queryAll">全部新闻</a></li>
                <li id="newsAdd"><a href="${ctx}/admin/news/addPage">新闻添加</a></li>
                <li id="newsEdit"><a href="#">新闻编辑</a></li>
            </ul>
        </li>
        <li id="themes">
            <a href="#"><i class="icon icon-th-list"></i>新闻类型</a>
            <ul>
                <li><a href="${ctx}/admin/themes/queryAll">全部类型</a></li>
                <li><a href="${ctx}/admin/themes/addPage">类型添加</a></li>
                <li><a href="${ctx}/admin/themes/editPage">类型编辑</a></li>
            </ul>
        </li>
        <li><a href="#"><i class="icon icon-trash"></i>垃圾篓</a></li>
        <li class="open">
            <a href="#"><i class="icon icon-tasks"></i>状态</a>
            <ul>
                <li>
                    <a href="#"><i class="icon icon-circle-blank"></i>已就绪</a>
                    <ul>
                        <li><a href="#">已取消</a></li>
                        <li><a href="#">已关闭</a></li>
                    </ul>
                </li>
                <li><a href="#"><i class="icon icon-play-sign"></i>进行中</a></li>
                <li><a href="#"><i class="icon icon-ok-sign"></i>已完成</a></li>
            </ul>
        </li>
    </ul>
</nav>

<script>
    // 手动通过点击模拟高亮菜单项
    $('#treeMenu').on('click', 'a', function () {
        $('#treeMenu li.active').removeClass('active');
        $(this).closest('li').addClass('active');
    });

    $(function () {
        var pageInfo = '${pageInfo}'.replace(".jsp", "");
        var lis = $("#news").find("li");
        for (var i = 0; i < lis.length; i++) {
            if (pageInfo.indexOf(lis[i].id) > -1) {
                $("#news").addClass("open");
                var str = "#" + pageInfo;
                $(str).addClass("active");
            }
        }
    });
</script>
