<%--
  Project: 众筹网模板
  User: tawe
  Date: 2020/10/4
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="UTF-8">
<%@include file="/WEB-INF/include/include-head.jsp" %>

<body>
<%@include file="/WEB-INF/include/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <p class="help-block label label-danger">${pageContext.exception.message}</p>
                <div class="panel-body">
                    <form action="admin/page/edit/${admin.id}.html" method="post" role="form">
                        <input type="hidden" id="id" name="id" value="${admin.id}"/>
                        <input type="hidden" id="keyword" name="keyword" value="${param.keyword}"/>
                        <input type="hidden" id="pageNum" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" id="pageSize" name="pageSize" value="${param.pageSize}"/>
                        <div class="form-group">
                            <label for="loginAcct">登陆账号</label>
                            <input type="text" class="form-control" id="loginAcct" name="loginAcct" value="${admin.loginAcct}"
                                   placeholder="请输入登陆账号">
                        </div>
                        <%--不支持修改密码操作--%>
                        <%--<div class="form-group">--%>
                        <%--    <label for="userPswd">登陆密码</label>--%>
                        <%--    <input type="password" class="form-control" id="userPswd" name="userPswd"--%>
                        <%--           placeholder="请输入登陆密码">--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="userName">用户名称</label>
                            <input type="text" class="form-control" id="userName" name="userName" value="${admin.userName}"
                                   placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" value="${admin.email}"
                                   placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改
                        </button>
                        <a href="admin/to/edit/page.html?adminId=${admin.id}" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>