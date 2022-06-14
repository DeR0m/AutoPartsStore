<#import "parts/common.ftl" as c>

<@c.page>
    <div class="card">
    <div class="text-center p-3">
        <#if productForMark.filename??>
            <img src="/img/${productForMark.filename}" class="card-img-top" style="width: 15rem;">
        </#if>
    </div>
    <div class="card-body text-center">
        <span><strong>Название: ${productForMark.productName}</strong></span>
    </div>
    <div class="card-body text-center">
        <span><strong>Описание: ${productForMark.productDescription}</strong></span>
    </div>
    <div class="card-body text-center">
        <span><strong>Количество на складе: ${productForMark.productAmount}</strong></span>
    </div>
    <div class="card-body text-center">
        <span><strong>Цена: ${productForMark.productPrice}</strong></span>
    </div>

</@c.page>