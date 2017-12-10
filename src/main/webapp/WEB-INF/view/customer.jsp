<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>客户管理</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://unpkg.com/nprogress@0.2.0/nprogress.css"/>

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/nprogress@0.2.0/nprogress.js"></script>

</head>
<body>
<div class="container">

    <h1 class="text-center">客户例表</h1>
    <table class="table">
        <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th>操作</th>
        </tr>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.contact}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td>
                    <a class="btn btn-primary" href="${BASE}/customer_edit?id=${customer.id}">编辑</a>
                    <a class="btn btn-danger" href="${BASE}/customer_delete?id=${customer.id}">删除</a>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>
</body>
</html>
