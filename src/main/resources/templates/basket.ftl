<#import "parts/common.ftl" as c>

<@c.page>
    <h3>${user.username}</h3>
    <ul class="list-group">
        <#list products as product>
            <li class="list-group-item">
                <a href="/category/product/${product.id}"> ${product.productName}</a>
            </li>
        </#list>

    </ul>
</@c.page>