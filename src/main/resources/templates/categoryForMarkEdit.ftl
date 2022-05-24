<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${categoryForMark.markCategoryName}</h5>

    <form action="/editCategoryForMark" method="post">
        <input type="text" name="name" value="${categoryForMark.categoryForMarkName}">

        <input type="hidden" value="${categoryForMark.id}" name="categoryForMarkId">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>