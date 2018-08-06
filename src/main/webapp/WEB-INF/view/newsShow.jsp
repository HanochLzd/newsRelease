<%--
  Created by IntelliJ IDEA.
  User: Hanoch
  Date: 2018/7/30
  Time: 20:39
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<style>
    #praiseDown, #praiseUp {
        margin-left: 40px;
        margin-right: 40px;
    }
</style>

<ol class="breadcrumb" style="float: left;margin-bottom: 0;color: rgb(133, 133, 133)">
    <li><i class="icon icon-home"></i><a href="${ctx}/" style="color: rgb(133, 133, 133)">主页</a></li>
    <li><a href="" style="color: rgb(133, 133, 133)">${newsVo.newsThemeName}</a><i class="icon icon-map-marker"></i>
    </li>
</ol>
<article class="article article-condensed">
    <header>
        <h2 style="margin-bottom: 10px">${newsVo.newsTitle}</h2>
        <dl class="dl-inline pull-right" style="margin: 0;padding: 0;font-size: 13px">
            <dt>最后编辑：</dt>
            <dd>${newsVo.newsAuthor}</dd>
            <dt>最后修订：</dt>
            <dd>${newsVo.newsCreateTime}</dd>
            <dt></dt>
            <dd class="pull-right"><span class="label label-success"
                                         style="margin-right: 4px">${newsVo.newsThemeName}</span><span
                    class="label label-info">W3C</span> <span class="label label-danger"><i class="icon-eye-open"></i> 235</span>
            </dd>
        </dl>
    </header>
    <section class="content" style="padding-top: 30px;text-align: left">
        ${newsVo.newsContent}
    </section>
    <footer>
        <div>
            <c:choose>
                <c:when test="${not empty praiseList}">
                    <button id="praiseUp" class="btn btn-primary" type="button" disabled="disabled">
                        <i class="icon icon-chevron-up"></i>
                            ${newsVo.newsUp}</button>

                    <button id="praiseDown" class="btn btn-primary" disabled="disabled"
                            style="background-color: rgb(222, 222, 222);color: rgb(60, 63, 65)">
                        <i class="icon icon-chevron-down"></i>
                            ${newsVo.newsDown}
                    </button>
                </c:when>
                <c:otherwise>
                    <button id="praiseUp" class="btn btn-primary" type="button">
                        <i class="icon icon-chevron-up"></i>
                        <span id="span-up">${newsVo.newsUp}</span></button>

                    <button id="praiseDown" class="btn btn-primary"
                            style="background-color: rgb(222, 222, 222);color: rgb(60, 63, 65)">
                        <i class="icon icon-chevron-down"></i>
                        <span id="span-down">${newsVo.newsDown} </span>
                    </button>
                </c:otherwise>
            </c:choose>
            </section>
        </div>
        <script>

            var praise = $(".btn.btn-primary");
            if (praise[0].disabled === false) {
                if (praise[0].id === "praiseUp") {
                    $("#praiseUp").click(function () {
                        $.ajax({
                            type: "POST",
                            url: '${ctx}/addPraise',
                            dataType: "json",
                            data: {
                                newsId: '${newsVo.newsId}',
                                up:${newsVo.newsUp+1},
                                down:${newsVo.newsDown},
                                type: '1'
                            },
                            success: function (data) {
                                console.log(data);
                                praise[0].disabled = true;
                                praise[1].disabled = true;
                                $("#span-up")[0].innerHTML = ${newsVo.newsUp+1};
                            }
                        });
                    });
                }

                if (praise[1].id === "praiseDown") {
                    $("#praiseDown").click(function () {
                        $.ajax({
                            type: "POST",
                            url: '${ctx}/addPraise',
                            dataType: "json",
                            data: {
                                newsId: '${newsVo.newsId}',
                                up:${newsVo.newsUp},
                                down:${newsVo.newsDown+1},
                                type: '0'
                            },
                            success: function (data) {
                                console.log(data);
                                praise[0].disabled = true;
                                praise[1].disabled = true;
                                $("#span-down")[0].innerHTML = ${newsVo.newsUp+1};
                            }
                        })
                        ;
                    });
                }

            }

            <%--var praise = $("#praise");--%>
            <%--if (praise[0].style.color === "rgb(152, 138, 222)") {--%>
            <%--praise[0].disabled = "disabled";--%>
            <%--}--%>
            <%--$("#praise").click(function () {--%>
            <%--if (praise[0].style.color === "rgb(152, 138, 222)") {--%>
            <%--praise[0].disabled = true;--%>
            <%--} else {--%>
            <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url: '${ctx}/praise',--%>
            <%--dataType: "json",--%>
            <%--data: {--%>
            <%--newsId: '${newsVo.newsId}'--%>
            <%--},--%>
            <%--success: function (data) {--%>
            <%--console.log(data);--%>
            <%--praise[0].disabled = true;--%>
            <%--}--%>
            <%--});--%>
            <%--}--%>
            <%--});--%>

        </script>
    </footer>
</article>
<div class="comment" style="text-align: left;padding: 20px">
    <a href="###" class="avatar">
        <i class="icon-camera-retro icon-2x"></i>
    </a>
    <div class="content">
        <div class="pull-right text-muted">3 个小时前</div>
        <div><a href="###"><strong>张士超</strong></a></div>
        <div class="text">今天玩的真开心！~~~~~~</div>
        <div class="actions">
            <a href="##">回复</a>
        </div>
    </div>
    <div class="comments-list">
        <div class="comment">
            <a href="###" class="avatar">
                <i class="icon-user icon-2x"></i>
            </a>
            <div class="content">
                <div class="pull-right text-muted">2 个小时前</div>
                <div><a href="###"><strong>Catouse</strong></a> <span class="text-muted">回复</span> <a href="###">张士超</a>
                </div>
                <div class="text">你到底把我家钥匙放哪里了...</div>
                <div class="actions">
                    <a href="##">回复</a>
                    <a href="##">编辑</a>
                    <a href="##">删除</a>
                </div>
            </div>
            <div class="comments-list">
                <div class="comment">
                    <a href="###" class="avatar">
                        <i class="icon-yinyang icon-2x"></i>
                    </a>
                    <div class="content">
                        <div class="pull-right text-muted">1 个小时前</div>
                        <div><a href="###"><strong>门口大爷</strong></a> <span class="text-muted">回复</span> <a href="###">Catouse</a>
                        </div>
                        <div class="text">不在我这儿...</div>
                        <div class="actions">
                            <a href="##">回复</a>
                        </div>
                    </div>
                </div>
                <div class="comment">
                    <a href="###" class="avatar">
                        <i class="icon-cube-alt icon-2x"></i>
                    </a>
                    <div class="content">
                        <div class="pull-right text-muted">1 个小时前</div>
                        <div><a href="###"><strong>队长</strong></a> <span class="text-muted">回复</span> <a href="###">Catouse</a>
                        </div>
                        <div class="text">也不在我这儿...</div>
                        <div class="actions">
                            <a href="##">回复</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="comment">
            <a href="###" class="avatar">
                <i class="icon-heart-empty icon-2x"></i>
            </a>
            <div class="content">
                <div class="pull-right text-muted">30 分钟前</div>
                <div><a href="###"><strong>华师大第一美女</strong></a> <span class="text-muted">回复</span> <a href="###">张士超</a>
                </div>
                <div class="text">很开心~~~</div>
                <div class="actions">
                    <a href="##">回复</a>
                </div>
            </div>
        </div>
    </div>
</div>