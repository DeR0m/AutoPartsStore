<#import "parts/common.ftl" as c>

<@c.page>

    <div class="card">
    <div class="text-center p-3">
        <#if product.filename??>
            <img src="/img/${product.filename}" class="card-img-top" style="width: 15rem;">
        </#if>
    </div>
    <div class="card-body text-center">
        <span><strong>Название: ${product.productName}</strong></span>
    </div>
    <div class="card-body text-center">
        <span><strong>Описание: ${product.productDescription}</strong></span>
    </div>
    <div class="card-body text-center">
        <span><strong>Количество на складе: ${product.productAmount}</strong></span>
    </div>
    <div class="card-body text-center">
        <span><strong>Цена: ${product.productPrice}</strong></span>
    </div>

</@c.page>