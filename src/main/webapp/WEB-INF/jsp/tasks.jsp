<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>

<%--<c:forEach items="${meals}" var="meal">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
    <tr data-mealExcess="${meal.excess}">
        <td>
                &lt;%&ndash;${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}&ndash;%&gt;
                &lt;%&ndash;<%=TimeUtil.toString(meal.getDateTime())%>&ndash;%&gt;
                &lt;%&ndash;${fn:replace(meal.dateTime, 'T', ' ')}&ndash;%&gt;
                ${fn:formatDateTime(meal.dateTime)}
        </td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
        <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
    </tr>
</c:forEach>--%>

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
                        <a class="up" href="/">Up</a>
                        <a class="del" href="/">Del</a>
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
    <form action="post">
        <form>
            <label for="desc">New task is:</label>
            <input type="text" id="desc" name="desc">
            <button type="submit">Save</button>
        </form>
    </form>
</div>

</body>
</html>

