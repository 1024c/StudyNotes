<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 9/29/2020
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<%--    ${requestScope}--%>
<%--    ${requestScope.admin}--%>
    ${sessionScope.admin.getUserName()}
</body>
</html>
