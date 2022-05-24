<#import "parts/common.ftl" as c>

<@c.page>
    ${message?ifExists}
    ${username}
    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button class="btn btn-primary mb-2" type="submit">Save</button>
    </form>

</@c.page>