<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<jsp:useBean id="product" scope="request" type="ru.gb.model.Product"/>
<div>
    <ul>
        <li>Product ID: <c:out value="${product.id}"/></li>
        <li>Product TITLE: <c:out value="${product.title}"/></li>
        <li>Product COST: <c:out value="${product.cost}"/></li>
    </ul>
</div>
<c:url var="deleteUrl" value="/product/delete">
    <c:param name="id" value="${product.id}"/>
</c:url>
<c:url var="editUrl" value="/product/edit">
    <c:param name="id" value="${product.id}"/>
</c:url>
<div>
    <a href="${deleteUrl}">Delete!</a>
    <a href="${editUrl}">Edit</a>
</div>
</body>
</html>
