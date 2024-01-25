<!DOCTYPE html>
<html>
<head>
    <title>SoulFinder</title>
</head>
<body>
<h1>SoulFinder</h1><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    <#if invalidUser??>
        Имя пользователя: <input type="text" name="name" value="${invalidUser.name}"><br><br>
        Эл. почта: <input type="email" name="email" value="${invalidUser.email}"><br><br>
        Номер телефона: <input type="text" name="phoneNumber" value="${invalidUser.phoneNumber}"><br><br>
        Пароль: <input type="password" name="password" value="${invalidUser.password}"><br><br>
    <#else>
        Имя пользователя: <input type="text" name="name"><br><br>
        Эл. почта: <input type="email" name="email"><br><br>
        Номер телефона: <input type="text" name="phoneNumber"><br><br>
        Пароль: <input type="password" name="password"><br><br>
</#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errors??>
    <ul>
        <#list errors as error>
        <li style="color: red">${error.defaultMessage}</li>
    </#list>
    </ul>
</#if>
<#if errorMessage??>
    <li style="color: red">${errorMessage}</li>
</#if>
</body>
</html>