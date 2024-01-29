<#import "blocks/template.ftlh" as t>
<@t.template user>

<div class="container mt-4">
    <h4 class="mb-4">Подробная информация о пропавшем человеке</h4>

    <div class="row">
        <#list images as img>
        <div class="col-lg-4 col-md-6 mb-4">
            <img src="/images/${img.id}" class="img-fluid rounded" />
        </div>
    </#list>
    </div>

    <dl class="row mt-4">
    <dt class="col-sm-3">Имя Фамилия Отчество:</dt>
    <dd class="col-sm-9">${post.title}</dd>

    <dt class="col-sm-3">Возраст пропавшего:</dt>
    <dd class="col-sm-9">${post.age}</dd>

    <dt class="col-sm-3">Описание пропавшего:</dt>
    <dd class="col-sm-9">${post.description}</dd>

    <dt class="col-sm-3">Дата исчезновения:</dt>
    <dd class="col-sm-9">${post.date_of_disappearance}</dd>

    <dt class="col-sm-3">Город/Местность исчезновения:</dt>
    <dd class="col-sm-9">${post.location}</dd>

    <dt class="col-sm-3">Дата создания поста:</dt>
    <dd class="col-sm-9">${post.dateOfCreated?date("yyyy-MM-dd")}</dd>

    <dt class="col-sm-3">Человек, создавший данную запись:</dt>
    <dd class="col-sm-9"><a href="/user/${post.user.id}">${post.user.name}</a></dd>
    </dl>

    <div id="map-test" class="map"></div>
    <script
        src="https://api-maps.yandex.ru/2.1/?apikey=${yandexMapsApiKey}&lang=ru_RU"
        type="text/javascript"
    ></script>
    <script id="myScript" arg1="${coordinates[0]}" arg2="${coordinates[1]}" src="/static/js/script.js"></script>

    <#if user.email??>
        <form action="/post/${post.id}/take_part" method="post" class="mt-4">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Принять участие</button>
        </form>
    </#if>

    <#if users?? && users?size != 0>
    <div class="mt-2 dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
            Список участников
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <#list users as user>
            <li><a class="dropdown-item" href="/user/${user.id}">${user.name}</a></li>
        </#list>
        </ul>
    </div>
    <#else>
    <p class="mt-4">Участников не обнаружено</p>
    </#if>
</div>

</@t.template>