<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/8/7
  Time: 11:01
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<style>
    #pageSelect {
        padding-left: 0;
    }

    .box > ul {
        margin-top: 0;
    }
</style>

<ol class="breadcrumb" style="float: left;margin-bottom: 0;color: rgb(133, 133, 133)">
    <li><i class="icon icon-home"></i><a href="${ctx}/" style="color: rgb(133, 133, 133)">主页</a></li>
    <li>${themeName}<i style="color: rgb(133, 133, 133)" class="icon icon-map-marker"></i>
    </li>
</ol>

<div id="div-news-title"
     style=" padding: 10px 20px 20px;width: 900px;margin-top: 30px;background-color: white">
    <div class="list-group" style="text-align: left">
        <ul class="ul-title" id="ul-title">
            <c:forEach var="news" items="${newsList}">
                <li class="list-group-item" style="list-style: initial;font-size: 14px"><a
                        href="${ctx}/showNews?newsId=${news.newsId}">${news.newsTitle}</a><span
                        style="float: right">${news.newsCreateTime}</span></li>
            </c:forEach>
        </ul>
    </div>
    <div style="height: 50px;width: 800px;margin-left: 60px">
        <div class="box" id="box" style="float: left"></div>
    </div>
    <script>
        (function ($, window, document) {
            // 定义构造函数
            function Paging(el, options) {
                this.el = el;
                this.options = {
                    pageNo: options.initPageNo || 1, // 初始页码
                    totalPages: options.totalPages || 1, //总页数
                    totalCount: options.totalCount || '', // 条目总数
                    slideSpeed: options.slideSpeed || 0, // 缓动速度
                    jump: options.jump || false, // 支持跳转
                    callback: options.callback || function () {
                    } // 回调函数
                };
                this.init();
            }

            // 给实例对象添加公共属性和方法
            Paging.prototype = {
                constructor: Paging,
                init: function () {
                    this.createDom();
                    this.bindEvents();
                },
                createDom: function () {
                    var that = this,
                        ulDom = '',
                        jumpDom = '',
                        content = '',
                        liWidth = 60, // li的宽度
                        totalPages = that.options.totalPages, // 总页数
                        wrapLength = 0;
                    totalPages > 5 ? wrapLength = 5 * liWidth : wrapLength = totalPages * liWidth;
                    for (var i = 1; i <= that.options.totalPages; i++) {
                        i != 1 ? ulDom += '<li>' + i + '</li>' : ulDom += '<li class="sel-page">' + i + '</li>';
                    }
                    that.options.jump ? jumpDom = '<input type="text" placeholder="1" class="jump-text" id="jumpText"><button type="button" class="jump-button" id="jumpBtn">跳转</button>' : jumpDom = '';
                    content = '<button type="button" id="firstPage" class="turnPage first-page">首页</button>' +
                        '<button class="turnPage" id="prePage">上一页</button>' +
                        '<div class="pageWrap" style="width:' + wrapLength + 'px">' +
                        '<ul id="pageSelect" style="transition:all ' + that.options.slideSpeed + 'ms">' +
                        ulDom +
                        '</ul></div>' +
                        '<button class="turnPage" id="nextPage">下一页</button>' +
                        '<button type="button" id="lastPage" class="last-page">尾页</button>' +
                        jumpDom +
                        '<p class="total-pages">共&nbsp;' +
                        that.options.totalPages +
                        '&nbsp;页</p>' +
                        '<p class="total-count">' +
                        that.options.totalCount +
                        '</p>';
                    that.el.html(content);
                },
                bindEvents: function () {
                    var that = this,
                        pageSelect = $('#pageSelect'), // ul
                        lis = pageSelect.children(), // li的集合
                        liWidth = lis[0].offsetWidth, // li的宽度
                        totalPages = that.options.totalPages, // 总页数
                        pageIndex = that.options.pageNo, // 当前选择的页码
                        distance = 0, // ul移动距离
                        prePage = $('#prePage'),
                        nextPage = $('#nextPage'),
                        firstPage = $('#firstPage'),
                        lastPage = $('#lastPage'),
                        jumpBtn = $('#jumpBtn'),
                        jumpText = $('#jumpText');

                    prePage.on('click', function () {
                        pageIndex--;
                        if (pageIndex < 1) pageIndex = 1;
                        handles(pageIndex);
                    })

                    nextPage.on('click', function () {
                        pageIndex++;
                        if (pageIndex > lis.length) pageIndex = lis.length;
                        handles(pageIndex);
                    })

                    firstPage.on('click', function () {
                        pageIndex = 1;
                        handles(pageIndex);
                    })

                    lastPage.on('click', function () {
                        pageIndex = totalPages;
                        handles(pageIndex);
                    })

                    jumpBtn.on('click', function () {
                        var jumpNum = parseInt(jumpText.val().replace(/\D/g, ''));
                        if (jumpNum && jumpNum >= 1 && jumpNum <= totalPages) {
                            pageIndex = jumpNum;
                            handles(pageIndex);
                            jumpText.val(jumpNum);
                        }
                    })

                    lis.on('click', function () {
                        pageIndex = $(this).index() + 1;
                        handles(pageIndex);
                    })

                    function handles(pageIndex) {
                        lis.removeClass('sel-page').eq(pageIndex - 1).addClass('sel-page');
                        // if (totalPages <= 5) {
                        //     that.options.callback(pageIndex);
                        //     return false;
                        // }
                        if (pageIndex >= 3 && pageIndex <= totalPages - 2) distance = (pageIndex - 3) * liWidth;
                        if (pageIndex == 2 || pageIndex == 1) distance = 0;
                        if (pageIndex > totalPages - 2) distance = (totalPages - 5) * liWidth;
                        pageSelect.css('transform', 'translateX(' + (-distance) + 'px)');
                        pageIndex == 1 ? firstPage.attr('disabled', true) : firstPage.attr('disabled', false);
                        pageIndex == 1 ? prePage.attr('disabled', true) : prePage.attr('disabled', false);
                        pageIndex == totalPages ? lastPage.attr('disabled', true) : lastPage.attr('disabled', false);
                        pageIndex == totalPages ? nextPage.attr('disabled', true) : nextPage.attr('disabled', false);
                        that.options.callback(pageIndex);
                    }

                    handles(that.options.pageNo); // 初始化页码位置
                }
            }
            $.fn.paging = function (options) {
                return new Paging($(this), options);
            }
        })(jQuery, window, document);

        // function showNewsByGroup(themeId) {
        //     alert(themeId);
        // }

        $('#box').paging({
            initPageNo: 1, // 初始页码
            totalPages: ${totalPageCount}, //总页数
            // totalCount: '合计' + setTotalCount + '条数据', // 条目总数
            slideSpeed: 600, // 缓动速度。单位毫秒
            jump: true, //是否支持跳转
            callback: function (page) { // 回调函数
                $.ajax({
                    type:
                        "POST",
                    url:
                        '${ctx}/newsPage',
                    dataType:
                        "json",
                    data:
                        {
                            pageNo: page,
                            newsType: ${themeId}
                        },
                    success: function (data) {
                        $("#ul-title")[0].innerHTML = "";
                        for (var i = 0; i < data.length; i++) {
                            if (i < 5 && page === 1) {
                                $("#ul-title")[0].innerHTML += "<li class=\"list-group-item\" style=\"list-style: initial;font-size: 14px\"><a\n" +
                                    "href=\"${ctx}/showNews?newsId=" + data[i].newsId + "\"><b>" + data[i].newsTitle + "</b></a><span\n" +
                                    "style=\"float: right\">" + data[i].newsCreateTime + "</span></li>";
                            } else {
                                $("#ul-title")[0].innerHTML += "<li class=\"list-group-item\" style=\"list-style: initial;font-size: 14px\"><a\n" +
                                    "href=\"${ctx}/showNews?newsId=" + data[i].newsId + "\">" + data[i].newsTitle + "</a><span\n" +
                                    "style=\"float: right\">" + data[i].newsCreateTime + "</span></li>";
                            }
                        }
                    },
                    error: function () {
                        console.log("error!");
                    }
                })
                ;
            }
        })


    </script>
</div>

