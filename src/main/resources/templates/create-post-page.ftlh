<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SoulFinder</title>
</head>
<body>
<h1>SoulFinder</h1><hr>

<#if user.email??>
<hr>
    <h3>Создать объявление</h3>
    <form action="/post/create" method="post" enctype="multipart/form-data">
        Имя Фамилия Отчество: <input type="text" name="title"/><br><br>
        Возраст пропавшего: <input type="number" name="age"/><br><br>
        Описание пропавшего: <input type="text" name="description"/><br><br>
        Дата исчезновения: <input type="date" name="date_of_disappearance"/><br><br>
        Город/Местность исчезновения: <input type="text" name="location"/><br><br>
        Фотография лица: <input type="file" name="file1"/><br><br>
        Фотография в рост: <input type="file" name="file2"/><br><br>
        Фотография одежды пропавшего: <input type="file" name="file3"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить объявление" />
    </form>
</#if>

<#if errors??>
<ul>
    <#list errors as error>
        <li style="color: red">${error.defaultMessage}</li>
    </#list>
</ul>
</#if>

</body>
</html>