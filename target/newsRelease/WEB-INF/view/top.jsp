<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/28
  Time: 13:45
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    /* wrap */
    #lunbo-box {
        position: relative;
        width: 355px;
        height: 135px;
        float: left;
        margin: 0;
        overflow: hidden;
    }

    /* image */
    #lunbo-box .list-image {
        position: absolute;
        /*width: 355px;*/
        height: 135px;
    }

    #box .list-image li {
        width: 355px;
        height: 135px;
    }

    #lunbo-box .list-image li img {
        height: 135px;
    }

    /*#lunbo-box .list-number li.current {*/
        /*opacity: 1;*/
        /*filter: alpha(opacity=100);*/
    /*}*/
</style>

<a href="${pageContext.request.contextPath}/">
    <img style="float: left; margin-top: 12px;"
         src="${pageContext.request.contextPath}/image/xh_logo.png"
         alt="新闻网"
         srcset=""/>
</a>

<div style="float: left;width: 650px">
    <img style="height: 135px;margin-left: 10px;margin-top: 2px"
         src="${pageContext.request.contextPath}/image/20180808144129.jpg" alt="TITLE"
         srcset="">
</div>
<%--<img style="height: 130px; margin-top: 2px" src="${pageContext.request.contextPath}/image/end_ad.jpg" alt="AD"--%>
<%--srcset="">--%>
<div id="lunbo-box">
    <ul class="list-image" style="list-style: none;margin:0;border: 0;padding-top: 5px;">
        <li><img
                src="${pageContext.request.contextPath}/image/20180808144744.png" alt="AD" srcset=""></li>
        <li><img
                src="${pageContext.request.contextPath}/image/cjgs.jpg" alt="AD" srcset=""></li>
        <li><img
                src="${pageContext.request.contextPath}/image/myStore_1978.jpg" alt="AD" srcset=""></li>
        <li><img
                src="${pageContext.request.contextPath}/image/shggzcf_go.jpg" alt="AD" srcset=""></li>
        <li><img
                src="${pageContext.request.contextPath}/image/jubao_www_new.jpg" alt="AD" srcset=""></li>
    </ul>
</div>
<script>
    const oBox = document.getElementById("lunbo-box");
    const oUl = oBox.getElementsByTagName("ul")[0];
    const oImage = oUl.getElementsByTagName("li");
    //根据图片 li 的个数生成同样数量的 按钮 li
    let eleArr = [];
    for (let i = 0; i < oImage.length; i++) {
        eleArr.push("<li>" + (i + 1) + "</li>");
    }
    // 创建节点元素并且添加节点元素至 html 里面
    var oEle = document.createElement("ul");
    oEle.className = "list-number";
    oEle.innerHTML = eleArr.join("");
    // oBox.appendChild(oEle);
    // 手动点击，触发轮播的触发函数
    const oNumber = oEle.getElementsByTagName("li");
    for (var i = 0; i < oNumber.length; i++) {
        oNumber[i].index = i;
        oNumber[i].onmouseover = function () {
            Show(this.index);
        }
    }

    // 轮播触发，并且内部调用移动效果函数
    function Show(index) {
        for (let i = 0; i < oNumber.length; i++) {
            oNumber[i].className = "";
        }
        oNumber[index].className = "current";
        Move(index);
    }

    // 移动效果函数
    let timer = null;

    function Move(index) {
        clearInterval(timer);
        let target = -index * 138;//图片一次滚动的高度
        timer = setInterval(function () {
            let speed = (target - oUl.offsetTop) / 10;
            // 速度为 目标位置 - 当前位置
            speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
            oUl.offsetTop === target ? clearInterval(timer) : oUl.style.top = oUl.offsetTop + speed + "px";
            // 没有到达目标，则不停止。
        }, 30);
    }

    // 添加自动轮播函数
    let nowIndex = 0;
    let autoTimer = null;
    oBox.onmouseout = function () {
        auto();
    }
    oBox.onmouseover = function () {
        clearInterval(autoTimer);
    }

    function auto() {
        autoTimer = setInterval(() => {
            if (nowIndex < oImage.length - 1) {
                nowIndex++;
            } else {
                nowIndex = 0;
            }
            Show(nowIndex);
        }, 2000);
    }

    auto();
    // 起始的时候默认开始
</script>