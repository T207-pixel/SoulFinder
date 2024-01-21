<!DOCTYPE html>
<html>
<head>
    <title>SoulFinder</title>
</head>
<body>
<h1>SoulFinder</h1><hr>
<h3>Контактная информация пользователя ${user.name}</h3>
<b>Если увидите пропавшего ченловека данного пользователя, обращайтесь по этим контактам:</b><br>
<b>Номер телефона: </b> ${user.phoneNumber}<br>
<b>Email: </b> ${user.email}<br>

<h4>Объявления пользователя ${user.name}</h4>

<#list posts as post>
    <div>
        <p><b>${post.title}</b> | <a href="/post/${post.id}">Подробнее...</a> </p>
    </div>
    <#else>
    <h3>Пропавших нет</h3>
</#list>

<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>