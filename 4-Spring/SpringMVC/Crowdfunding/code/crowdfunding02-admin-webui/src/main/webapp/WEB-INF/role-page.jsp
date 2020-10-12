<%--
  Project: 众筹网模板
  User: Administrator
  Date: 10/9/2020
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="UTF-8">
<%@include file="/WEB-INF/include/include-head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my-role.js"></script>

<body>
<%@include file="/WEB-INF/include/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <label for="keyword" class="input-group-addon">查询条件</label>
                                <input id="keyword" name="keyword" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件" value="${param.keyword}">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <%--HTML 绑定 onclick 事件--%>
                    <%--<button id="showAddModalBtn" type="button" class="btn btn-primary" style="float:right;" onclick="$('#addModal').modal('show');"><i class="glyphicon glyphicon-plus" ></i> 新增</button>--%>
                    <%--js 中绑定 click 事件--%>
                    <button id="showAddModalBtn" type="button" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus" ></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty pageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">未查询到相关数据</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty pageInfo.list}">
                                <c:forEach items="${pageInfo.list}" var="role" varStatus="myStatus">
                                    <tr>
                                        <td id="roleId" name="roleId" style="display:none;">${role.id}</td>
                                        <td>${myStatus.count}</td>
                                        <td><input type="checkbox"/></td>
                                        <td name="roleName">${role.roleName}</td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-xs">
                                                <i class="glyphicon glyphicon-check"></i>
                                            </button>
                                            <button id="edit${role.id}" class="btn btn-primary btn-xs">
                                                <i class="glyphicon glyphicon-pencil"></i>
                                            </button>
                                            <button id="remove${role.id}" class="btn btn-danger btn-xs">
                                                <i class="glyphicon glyphicon-remove"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <%--设置当前页面参数,用于页码的跳转--%>
                            <c:set var="currentPage" value="role"/>
                            <%@include file="/WEB-INF/include/include-tfoot.jsp" %>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/include/modal-role-add.jsp"%>
</body>
</html>