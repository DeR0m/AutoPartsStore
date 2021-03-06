<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row ">
            <label class="col-sm-2 col-form-label">Имя пользователя :</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="User name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row mt-2">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row mt-2">
                <label class="col-sm-2 col-form-label">Повторите пароль:</label>
                <div class="col-sm-6">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Retype password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>


        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration" class="text-button">Зарегистрироваться</a></#if>
        <button class="btn btn-outline-secondary mt-2" type="submit"><#if isRegisterForm>Зарегестрироваться<#else>Войти</#if></button>
    </form>
</#macro>

<#macro logout>
    <#include "security.ftl" >
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-outline-secondary" type="submit"><#if user??>Выйти<#else>Войти</#if></button>
    </form>
</#macro>