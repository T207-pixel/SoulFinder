<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SoulFinder</title>
</head>
<body>
<h1>SoulFinder</h1><hr>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <form action="/openProfile" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Профиль"/>
    </form>
    <form action="/post/create" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Создать объявление"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Панель админиистратора</a>
    </#if>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<hr>
<h4>Список пропавших людей</h4>

<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>

<#list posts as post>
    <div>
        <p><b>${post.title}</b> | <a href="/post/${post.id}">Подробнее...</a> </p>
        <#assign img = post.images[0]>
        <img src="/images/${img.id}" height="50px"/><br><br>
    </div>
    <#else>
    <h3>Искать некого</h3>
</#list>

<!--<#if user.email??>-->
<!--<hr>-->
<!--<h3>Создать объявление</h3>-->
<!--<form action="/post/create" method="post" enctype="multipart/form-data">-->
<!--    Имя Фамилия Отчество: <input type="text" name="title"/><br><br>-->
<!--    Возраст пропавшего: <input type="number" name="age"/><br><br>-->
<!--    Описание пропавшего: <input type="text" name="description"/><br><br>-->
<!--    Дата исчезновения: <input type="date" name="date_of_disappearance"/><br><br>-->
<!--    Город/Местность исчезновения: <input type="text" name="location"/><br><br>-->
<!--    Фотография лица: <input type="file" name="file1"/><br><br>-->
<!--    Фотография в рост: <input type="file" name="file2"/><br><br>-->
<!--    Фотография одежды пропавшего: <input type="file" name="file3"/><br><br>-->
<!--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<!--    <input type="submit" value="Добавить объявление" />-->
<!--</form>-->
<!--</#if>-->

<#if errors??>
    <ul>
    <#list errors as error>
        <li style="color: red">${error.defaultMessage}</li>
    </#list>
    </ul>
</#if>

</body>
</html>