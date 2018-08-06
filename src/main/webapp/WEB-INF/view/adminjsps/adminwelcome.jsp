<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/29
  Time: 20:42
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/chart/zui.chart.min.js"></script>
<h2>新闻发布后台管理系统</h2>
<canvas id="myChart" width="400" height="400"></canvas>
<script>
    // 使用jquery方法获取 2d context 对象
    var ctx = $("#myChart").get(0).getContext("2d");
    // 或者使用 document.getElementById 获取 2d context 对象
    // var ctx = document.getElementById("myChart").getContext("2d");




    // 使用$.zui.Chart构造Chart实例
    var myNewChart = new $.zui.Chart(ctx);
    var data = {
        // labels 数据包含依次在X轴上显示的文本标签
        labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        datasets: [{
            // 数据集名称，会在图例中显示
            label: "红队",

            // 颜色主题，可以是'#fff'、'rgb(255,0,0)'、'rgba(255,0,0,0.85)'、'red' 或 ZUI配色表中的颜色名称
            // 或者指定为 'random' 来使用一个随机的颜色主题
            color: "red",
            // 也可以不指定颜色主题，使用下面的值来分别应用颜色设置，这些值会覆盖color生成的主题颜色设置
            // fillColor: "rgba(220,220,220,0.2)",
            // strokeColor: "rgba(220,220,220,1)",
            // pointColor: "rgba(220,220,220,1)",
            // pointStrokeColor: "#fff",
            // pointHighlightFill: "#fff",
            // pointHighlightStroke: "rgba(220,220,220,1)",

            // 数据集
            data: [65, 59, 80, 81, 56, 55, 40, 44, 55, 70, 30, 40]
        }, {
            label: "绿队",
            color: "green",
            data: [28, 48, 40, 19, 86, 27, 90, 60, 30, 44, 50, 66]
        }]
    };

    var options = {}; // 图表配置项，可以留空来使用默认的配置

    var myLineChart = $("#myLineChart").lineChart(data, options);
</script>