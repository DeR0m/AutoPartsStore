<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Список пользователей</h5>
    <table class="table">
        <thead>
        <tr class="text-center">
            <th scope="col">Имя</th>
            <th scope="col">Роль</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr class="text-center">
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}" class="btn btn-dark" role="button">Изменить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>