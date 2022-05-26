<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->

<@c.page>
    <h5>${engineType.engineModel}</h5>

    <form action="/editEngineType" method="post">
        <input type="text" name="name" value="${engineType.engineModel}">
        <input type="text" name="capacity" value="${engineType.engineCapacity}">
        <input type="text" name="powerHp" value="${engineType.powerHp}">
        <input type="text" name="engineName" value="${engineType.engineName}">
        <input type="text" name="fuelType" value="${engineType.fuelType}">
        <input type="hidden" value="${engineType.id}" name="engineTypeId">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>