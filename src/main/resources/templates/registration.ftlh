<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Регистрация</h1>
<hr>

<form action="/registration" method="post">
    <#if invalidUser??>
        <div class="form-group">
            <label for="name">Имя пользователя</label>
            <input type="text" class="form-control" required id="name" name="name" value="${invalidUser.name}">
        </div>
        <div class="form-group">
            <label for="email">Эл. почта: </label>
            <input type="email" class="form-control" required id="email" name="email" value="${invalidUser.email}">
        </div>
        <div class="form-group">
            <label for="phoneNumber">Номер телефона: </label>
            <input type="text" class="form-control" required id="phoneNumber" name="phoneNumber" value="${invalidUser.phoneNumber}">
        </div>
        <div class="form-group">
            <label for="password">Пароль: </label>
            <input type="password" class="form-control" required id="password" name="password" value="${invalidUser.password}">
        </div>
    <#else>
    <div class="form-group">
        <label for="name">Имя пользователя</label>
        <input type="text" class="form-control" required id="name" name="name">
    </div>
    <div class="form-group">
        <label for="email">Эл. почта: </label>
        <input type="email" class="form-control" required id="email" name="email">
    </div>
    <div class="form-group">
        <label for="phoneNumber">Номер телефона: </label>
        <input type="text" class="form-control" required id="phoneNumber" name="phoneNumber">
    </div>
    <div class="form-group">
        <label for="password">Пароль: </label>
        <input type="password" class="form-control" required id="password" name="password">
    </div>
</#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
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

</@t.template>