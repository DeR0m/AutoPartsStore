<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->

<@c.page>
    <h5>${productForMark.productName}</h5>

    <form action="/editProductForMark" method="post">
        <input type="text" name="name" value="${productForMark.productName}">
        <input type="text" name="description" value="${productForMark.productDescription}">
        <input type="text" name="amount" value="${productForMark.productAmount}">
        <input type="text" name="price" value="${productForMark.productPrice}">
        <input type="hidden" value="${productForMark.id}" name="productForMarkId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>