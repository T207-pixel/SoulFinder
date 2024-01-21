<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SoulFinder</title>
</head>
<body>
<h1>SoulFinder</h1><hr>
<h4>Подробная информация о пропавшем человеке</h4>

<#list images as img>
    <img src="/images/${img.id}" height="333px"/><br><br>
</#list>

<b>Имя Фамилия Отчество:: </b>${post.title}<br>
<b>Возраст пропавшего: </b>${post.age}<br>
<b>Описание пропавшего: </b>${post.description}<br>
<b>Дата исчезновения: </b>${post.date_of_disappearance}<br>
<b>Город/Местность исчезновения: </b>${post.location}<br>
<b>Дата создания поста: </b>${post.dateOfCreated}<br>
<b>Человек, создавший данную запись: </b><a href="/user/${post.user.id}">${post.user.name}</a><br>

<form action="/post/${post.id}/take_part" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Принять участие"/>
</form>

<#if users?? && users?size != 0>
<ol>
        <#list users as user>
        <li><a href="/user/${user.id}">${user.name} </a> </li>
    </#list>
</ol>
    <#else>
    <p>Участников не обнаружено</p>
</#if>

</body>
</html>