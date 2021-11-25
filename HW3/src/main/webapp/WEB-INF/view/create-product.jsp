<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.11.2021
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
  <form:form action="create" modelAttribute="product">
      <form:hidden path="id" value="${product.id}"/>
      Title: <form:input path="title"/>
      <br>
      Cost: <form:input path="cost"/>
      <br>
      <input type="submit" value="Create">
  </form:form>
  </body>
</html>
