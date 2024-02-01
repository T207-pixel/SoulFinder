<#import "blocks/template.ftlh" as t>
<@t.template user>

<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>

    <div class="mx-2 px-2 btn-group" role="group">
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="btn btn-dark  mr-2" value="Выйти"/>
        </form>
        <form action="/openProfile" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="btn btn-dark  mr-2" value="Профиль"/>
        </form>
        <form action="/post/create" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="btn btn-dark  mr-2" value="Создать объявление"/>
        </form>
    </div>

    <#if user.isAdmin()>
        <a href="/admin" class="btn btn-primary">Панель админиистратора</a>
    </#if>
</#if>
<hr>
<h4 class="display-4 text-center">Список пропавших людей</h4>

<form action="/" method="get" class="form-inline m-3">
    <label for="title" class="fs-3 mr-2">Поиск по названию объявления:</label>
    <input type="text" id="title" name="title" class="form-control mr-2 w-50">
    <input type="submit" value="Поиск" class="btn btn-dark">
</form>

<div class="row row-cols-1 row-cols-md-3">
    <#list posts as post>
    <#if post.isPostCheckedByAdmin || user.isAdmin()>
    <a href="/post/${post.id}" class="product-link">
        <div class="card text-white bg-dark my-3 product m-3">
            <#assign img = post.images[0]>
            <img src="/images/${img.id}"
                 class="card-img-top">
            <div class="card-body">
                <h5 class="card-title">${post.title}</h5>
            </div>
            <div class="card-footer">
                ${post.location}
            </div>
            <#if !post.isPostCheckedByAdmin && user.isAdmin()>
                <div class="alert alert-warning" role="alert">
                    Проверить контент
                </div>
            </#if>
        </div>
    </a>
    </#if>
    <#else>
    <h1 style="text-align: center">Не найдено</h1>
</#list>
</div>

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

</@t.template>