<!DOCTYPE html>
<html>
<head>
    <title>SoulFinder</title>
</head>
<body>
<h1>SoulFinder</h1><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"><br><br>
    Эл. почта: <input type="email" name="email"><br><br>
    Номер телефона: <input type="text" name="phoneNumber"><br><br>
    Пароль: <input type="password" name="password"><br><br>
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