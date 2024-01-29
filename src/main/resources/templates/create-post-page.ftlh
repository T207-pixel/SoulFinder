<#import "blocks/template.ftlh" as t>
<@t.template user>

        <#if user.email??>
        <h1 style="text-align: center">Вы можете составить объявление здесь</h1>
        <hr>
        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="true" aria-controls="collapseExample">
            Кликните тут
        </a>
        <div class="collapse mb-3" id="collapseExample" style="">
            <form action="/post/create" method="post" enctype="multipart/form-data">
                <#if errorPost??>
                <div class="form-group">
                    <label for="title">Имя Фамилия Отчество: </label>
                    <input type="text" required class="form-control"
                           id="title" name="title" value="${errorPost.title}">
                </div>
                <div class="form-group">
                    <label for="age">Возраст пропавшего: </label>
                    <div class="input-group mb-3">
                        <input type="number" required class="form-control" id="age" name="age" value="${errorPost.age}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Описание пропавшего: </label>
                    <input type="text" required class="form-control"
                           id="description" name="description" value="${errorPost.description}">
                </div>
                <div class="form-group">
                    <label for="location">Город/Местность исчезновения: </label>
                    <input type="text" required class="form-control"
                           id="location" name="location" value="${errorPost.location}">
                </div>
                <div class="form-group">
                    <label for="coordinates">Возможные координаты Исчезновения: </label>
                    <input type="text" required class="form-control"
                           id="coordinates" name="coordinates" value="${errorPost.coordinates}">
                </div>
                <div class="form-group">
                    <label for="date_of_disappearance">Дата исчезновения: </label>
                    <input type="date" required class="form-control"
                           id="date_of_disappearance" name="date_of_disappearance" value="${errorPost.date_of_disappearance?string("yyyy-MM-dd")}">
                </div>
                <#else>
                <div class="form-group">
                    <label for="title">Имя Фамилия Отчество: </label>
                    <input type="text" required class="form-control"
                           id="title" name="title">
                </div>
                <div class="form-group">
                    <label for="age">Возраст пропавшего: </label>
                    <div class="input-group mb-3">
                        <input type="number" required class="form-control" id="age" name="age">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Описание пропавшего: </label>
                    <input type="text" required class="form-control"
                           id="description" name="description">
                </div>
                <div class="form-group">
                    <label for="location">Город/Местность исчезновения: </label>
                    <input type="text" required class="form-control"
                           id="location" name="location">
                </div>
                <div class="form-group">
                    <label for="coordinates">Возможные координаты Исчезновения: </label>
                    <input type="text" required class="form-control"
                           id="coordinates" name="coordinates">
                </div>
                <div class="form-group">
                    <label for="date_of_disappearance">Дата исчезновения: </label>
                    <input type="date" required class="form-control"
                           id="date_of_disappearance" name="date_of_disappearance">
                </div>
                </#if>
                <div class="form-group">
                    <div class="file">
                        <input required type="file" name="file1" id="file1">
                        <label class="file" for="file1">Первая фотография лица</label>
                    </div>
                </div>
                <div class="file">
                    <div class="custom-file">
                        <input type="file" name="file2" id="file2">
                        <label class="file" for="file2">Вторая фотография человек в полный рост</label>
                    </div>
                </div>
                <div class="file">
                    <div class="custom-file">
                        <input type="file" name="file3" id="file3">
                        <label class="file" for="file3">Третья фотография фото одежды</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="width: 100%" class="btn btn-dark">Создать</button>
            </form>
        </div>
        </#if>

<#if errors??>
<div class="alert alert-danger" role="alert">
    <ul>
        <#list errors as error>
        <li style="color: red">${error.defaultMessage}</li>
    </#list>
    </ul>
</div>
</#if>

<#if overFlow??>
<div class="alert alert-danger" role="alert">
    ${overFlow}
</div>
</#if>

</@t.template>



<!--<!DOCTYPE html>-->
<!--<html lang="en">-->
<!--<head>-->
<!--    <meta charset="UTF-8">-->
<!--    <title>SoulFinder</title>-->
<!--</head>-->
<!--<body>-->
<!--<h1>SoulFinder</h1>-->

<!--<#if user.email??>-->
<!--<hr>-->
<!--    <h3>Создать объявление</h3>-->
<!--    <form action="/post/create" method="post" enctype="multipart/form-data">-->
<!--        <#if errorPost??>-->
<!--            Имя Фамилия Отчество: <input type="text" name="title" value="${errorPost.title}"/><br><br>-->
<!--            Возраст пропавшего: <input type="number" name="age" value="${errorPost.age}"/><br><br>-->
<!--            Описание пропавшего: <input type="text" name="description" value="${errorPost.description}"/><br><br>-->
<!--            Дата исчезновения: <input type="date" name="date_of_disappearance" value="${errorPost.date_of_disappearance?string("yyyy-MM-dd")}"/><br><br>-->
<!--            Город/Местность исчезновения: <input type="text" name="location" value="${errorPost.location}"/><br><br>-->
<!--        <#else>-->
<!--            Имя Фамилия Отчество: <input type="text" name="title"/><br><br>-->
<!--            Возраст пропавшего: <input type="number" name="age"/><br><br>-->
<!--            Описание пропавшего: <input type="text" name="description"/><br><br>-->
<!--            Дата исчезновения: <input type="date" name="date_of_disappearance"/><br><br>-->
<!--            Город/Местность исчезновения: <input type="text" name="location"/><br><br>-->
<!--            Фотография лица: <input type="file" name="file1"/><br><br>-->
<!--            Фотография в рост: <input type="file" name="file2"/><br><br>-->
<!--            Фотография одежды пропавшего: <input type="file" name="file3"/><br><br>-->
<!--        </#if>-->
<!--        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<!--        <input type="submit" value="Добавить объявление" />-->
<!--    </form>-->
<!--</#if>-->

<!--<#if errors??>-->
<!--<ul>-->
<!--    <#list errors as error>-->
<!--        <li style="color: red">${error.defaultMessage}</li>-->
<!--    </#list>-->
<!--</ul>-->
<!--</#if>-->

<!--</body>-->
<!--</html>-->