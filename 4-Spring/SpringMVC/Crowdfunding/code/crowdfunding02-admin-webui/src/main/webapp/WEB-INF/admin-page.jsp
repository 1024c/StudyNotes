<%--
  Project: 众筹网模板
  User: tawe
  Date: 2020/10/4
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="UTF-8">
<%@include file="include/include-head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<%@include file="include/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="include/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="admin/page.html" method="post" class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件" value="${param.keyword}">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <a href="admin/to/add/page.html" type="button"
                       class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增
                    </a>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td colspan="2">未查询到相关数据</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${mystatus.count}</td>
                                        <td><input type="checkbox"/></td>
                                        <td>${admin.loginAcct}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-xs">
                                                <i class="glyphicon glyphicon-check"></i>
                                            </button>
                                            <a href="admin/to/edit/page.html?adminId=${admin.id}&keyword=${param.keyword}&pageNum=${pageInfo.pageNum}&pageSize=${pageInfo.pageSize}"
                                               class="btn btn-primary btn-xs">
                                                <i class="glyphicon glyphicon-pencil"></i>
                                            </a>
                                            <a href="admin/page/remove/${admin.id}.html?keyword=${param.keyword}&pageNum=${pageInfo.pageNum}&pageSize=${pageInfo.pageSize}"
                                               class="btn btn-danger btn-xs">
                                                <i class="glyphicon glyphicon-remove"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <c:if test="${pageInfo.isFirstPage}">
                                            <li class="disabled"><a href="javascript:void(0)">首页</a></li>
                                            <li class="disabled"><a href="javascript:void(0)">上一页</a></li>
                                        </c:if>
                                        <c:if test="${!pageInfo.isFirstPage}">
                                            <li><a href="admin/page.html?pageNum=1&keyword=${param.keyword}">首页</a></li>
                                            <li>
                                                <a href="admin/page.html?pageNum=${pageInfo.prePage}&keyword=${param.keyword}">上一页</a>
                                            </li>
                                        </c:if>
                                        <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
                                            <c:if test="${pageNum == pageInfo.pageNum}">
                                                <li class="active"><a href="javascript:void(0)">${pageNum}<span
                                                        class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${pageNum != pageInfo.pageNum}">
                                                <li>
                                                    <a href="admin/page.html?pageNum=${pageNum}&keyword=${param.keyword}">${pageNum}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${pageInfo.isLastPage}">
                                            <li class="disabled"><a href="javascript:void(0)">下一页</a></li>
                                            <li class="disabled"><a href="javascript:void(0)">末页</a></li>
                                        </c:if>
                                        <c:if test="${!pageInfo.isLastPage}">
                                            <li>
                                                <a href="admin/page.html?pageNum=${pageInfo.nextPage}&keyword=${param.keyword}">下一页</a>
                                            </li>
                                            <li>
                                                <a href="admin/page.html?pageNum=${pageInfo.pages}&keyword=${param.keyword}">末页</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>