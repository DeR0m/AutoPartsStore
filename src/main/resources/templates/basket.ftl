<#import "parts/common.ftl" as c>

<@c.page>
    <h3>${user.username}</h3>
    <ul class="list-group">
        <#list products as product>
            <#list productForMarks as productForMark>
                <li class="list-group-item">
                    <a href="product/${product.id}"> ${product.productName}</a>
                </li>
                <li class="list-group-item">
                    <a href="productForMark/${productForMark.id}"> ${productForMark.productName}</a>
                </li>
            </#list>
        </#list>

    </ul>
</@c.page>