<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${markCategory.markCategoryName}</h5>

    <form action="/editMark" method="post">
        <input type="text" name="name" value="${markCategory.markCategoryName}">

        <input type="hidden" value="${markCategory.id}" name="markCategoryId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>