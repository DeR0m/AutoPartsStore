<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->

<@c.page>
    <h5>${product.productName}</h5>

    <form action="/editProduct" method="post">
        <input type="text" name="name" value="${product.productName}">
        <input type="text" name="description" value="${product.productDescription}">
        <input type="text" name="amount" value="${product.productAmount}">
        <input type="text" name="price" value="${product.productPrice}">
        <input type="hidden" value="${product.id}" name="productId">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить изменения</button>

    </form>
</@c.page>