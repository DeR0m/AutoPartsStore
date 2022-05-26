<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${bodyType.bodyTypeName}</h5>

    <form action="/editBodyType" method="post">
        <input type="text" name="name" value="${bodyType.bodyTypeName}">

        <input type="hidden" value="${bodyType.id}" name="bodyTypeId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>