<%--
  Project: 众筹网模板
  User: Administrator
  Date: 10/13/2020
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="UTF-8">
<%@include file="../../include/include-head.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ztree/zTreeStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/my-menu.js"></script>
<body>
<%@include file="../../include/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="../../include/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="menuTree" class="ztree">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/include/modal-menu-add.jsp"%>
<%@ include file="/WEB-INF/include/modal-menu-edit.jsp"%>
<%@ include file="/WEB-INF/include/modal-menu-confirm.jsp"%>
</body>
</html>