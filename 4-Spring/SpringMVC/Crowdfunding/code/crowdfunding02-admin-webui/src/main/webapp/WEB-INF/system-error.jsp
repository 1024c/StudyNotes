<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/24/2020
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<%@include file="/WEB-INF/include/include-head.jsp" %>
<script type="text/javascript">
    $(function () {
        $("button").click(function () {
            // 调用 back() 方法类似于点击浏览器后退的按钮
            window.history.back();
        });
    });
</script>
<body>
<%@include file="/WEB-INF/include/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container" style="text-align: center">
                <h3>系统信息页面</h3>
                <h4>${exception.message}</h4>
                <button style="width: 300px; margin: 0 auto 0 auto;" class="btn btn-lg btn-success btn-block">返回上一页</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
