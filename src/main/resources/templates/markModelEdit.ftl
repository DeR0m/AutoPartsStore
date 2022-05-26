<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${markModel.modelName}</h5>

    <form action="/editMarkModel" method="post">
        <input type="text" name="name" value="${markModel.modelName}">

        <input type="hidden" value="${markModel.id}" name="markModelId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>