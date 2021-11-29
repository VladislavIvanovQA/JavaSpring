<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.11.2021
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% List products = (List) session.getAttribute("products");%>
<body>
<div>
    <c:forEach items="${products}" var="product">
        <c:url var="productUrl" value="/product/ + ${product.id}">
        </c:url>
        <ul>
            <a href="${productUrl}">Product ID: <c:out value="${product.id}"/></a>
            <li>Product TITLE: <c:out value="${product.title}"/></li>
            <li>Product COST: <c:out value="${product.cost}"/></li>
        </ul>
    </c:forEach>
</div>
<div>
    <c:url var="productCreateUrl" value="/product/create">
    </c:url>
    <a href="${productCreateUrl}">Create new product!</a>
</div>
</body>
</html>
