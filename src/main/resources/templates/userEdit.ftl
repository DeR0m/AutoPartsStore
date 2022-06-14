<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Пользователи</h5>

    <div class="form-group mt-3">
        <form action="/user" method="post">
            <div class="form-group mb-3">
                <input class="form-control form-control-sm" type="text" name="username" value="${user.username}">
            </div>
            <#list roles as role>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"
                           name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                    <label class="form-check-label" for="flexCheckDefault">
                        ${role}
                    </label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-dark mt-2" type="submit">Сохранить</button>

        </form>
    </div>
</@c.page>