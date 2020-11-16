<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>TaskManager</title>
</head>
<body>
<h2>Task Manager</h2>
<div class="parent">
    <div class="child">
        <h3>Tasks</h3>
        <table class="table">

            <c:forEach items="${actual}" var="task">
                <jsp:useBean id="task" type="team.s2f.taskmanager.model.Task"/>
                <tr>
                    <td class="num">${task.id}</td>
                    <td>${task.description}</td>
                    <td class="buttons">
                        <a class="up" href="${pageContext.request.contextPath}/tasks/update?id=${task.id}">Up</a>
                        <a class="del" href="${pageContext.request.contextPath}/tasks/delete?id=${task.id}">Del</a>
                        <a class="done" href="${pageContext.request.contextPath}/tasks/done?id=${task.id}">Done</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="child">
        <h3 class="green">Done</h3>
        <table class="table, green">
            <c:forEach items="${done}" var="task">
                <tr>
                    <td class="num">${task.id}</td>
                    <td>${task.description}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
<div class="add_form">
    <h3>Add new task</h3>
    <jsp:useBean id="task_upd" class="team.s2f.taskmanager.model.Task" scope="request"/>

    <form method="post" action="${pageContext.request.contextPath}/tasks/save">
        <form>
            <label for="desc">New task is:</label>
            <input type="text" id="desc" name="desc" value="${task_upd.description}">
            <button type="submit">Save</button>
        </form>
    </form>
</div>

</body>
</html>

