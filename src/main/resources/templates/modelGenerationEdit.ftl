<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${markCategory.markCategoryName}</h5>

    <form action="/editModelGeneration" method="post">
        <input type="text" name="name" value="${modelGeneration.generationModelName}">
        <input type="text" name="name" value="${modelGeneration.graduationYear}">

        <input type="hidden" value="${modelGeneration.id}" name="modelGenerationId">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>