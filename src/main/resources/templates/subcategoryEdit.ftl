<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${subcategory.subcategoryName}</h5>

    <form action="/editSubcategory" method="post">
        <input type="text" name="name" value="${subcategory.subcategoryName}">

        <input type="hidden" value="${subcategory.id}" name="subcategoryId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>