<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Авторизация</h1>
<hr>
<form action="/login " method="post">
    <div class="form-group">
        <label for="email">Электронная почта</label>
        <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="email" name="username">
        <div id="email" class="invalid-feedback">
            Неверныя электронная почта
        </div>
    </div>
    <div class="form-group">
        <label for="password">Пароль</label>
        <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
        <div id="password" class="invalid-feedback">
            Неверный пароль
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
</form>
<hr>
    <h2>Нет аккаунта SOULFINDER? <a href="/registration">Зарегистрируйся!</a></h2>
</@t.template>












<!--<!DOCTYPE html>-->
<!--<html>-->
<!--<head>-->
<!--    <title>SoulFinder</title>-->
<!--</head>-->
<!--<body>-->
<!--<h1>SoulFinder</h1><hr>-->
<!--<h4>Авторизация</h4>-->
<!--<form action="/login" method="post">-->
<!--    Эл. почта: <input type="email" name="username"><br><br>-->
<!--    Пароль: <input type="password" name="password"><br><br>-->
<!--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<!--    <input type="submit" value="Вход"/>-->
<!--</form>-->
<!--<#if error??>-->
<!--    <div style="color: red;">${error}</div>-->
<!--</#if>-->
<!--<a href="/registration">Зарегистрироваться</a>-->
<!--</body>-->
<!--</html>-->