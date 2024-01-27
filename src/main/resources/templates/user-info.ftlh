<#import "blocks/template.ftlh" as t>
<@t.template user>


<div class="container mt-4">
<h3 class="mb-4">Контактная информация пользователя ${user.name}</h3>
<p><b>Если увидите пропавшего ченловека данного пользователя, обращайтесь по этим контактам:</b><br>
    <b>Номер телефона: </b> ${user.phoneNumber}<br>
    <b>Email: </b> ${user.email}<br>

    <h4 class="mt-4">Объявления пользователя</h4>
    <#list posts as post>
    <div class="card mb-3">
        <div class="card-body">
            <p><b>${post.title}</b> | <a href="/post/${post.id}" class="stretched-link">Подробнее...</a> </p>
        </div>
    </div>
    <#else>
        <p class="mt-4">Пропавших нет</p>
    </#list>

    <h3 class="mt-4">Участвует в поисковых операциях</h3>
    <#list postsParticipant as post>
    <div class="card mb-3">
        <div class="card-body">
            <p><b>${post.title}</b> | <a href="/post/${post.id}" class="stretched-link">Подробнее...</a> </p>
        </div>
    </div>
    <#else>
    <p class="mt-4">Пока ни в одной</p>
    </#list>

    <#if errorMessage??>
    <div class="alert alert-danger mt-4" role="alert">
        ${errorMessage}
    </div>
</#if>
</div>


</@t.template>