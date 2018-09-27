<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<form action="<c:url value="/subLogin.html"/>" method="post">
    用户名：<input type="text" name="userName"/><br>
    密码：<input type="password" name="password"/><br>
    <input type="submit" value="登录">
</form>
</body>
</html>