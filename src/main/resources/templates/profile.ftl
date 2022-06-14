<#import "parts/common.ftl" as c>

<@c.page>
    ${message?ifExists}
    <h5> ${username} </h5>
    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button class="btn btn-dark mt-2" type="submit">Сохранить</button>
    </form>

</@c.page>