<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${product.productName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editProduct" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(productNameError??)?string('is-invalid','')}"
                               value="<#if product??>${product.productName}</#if>"
                               placeholder="Название товара" aria-label="productName"
                               name="productName"
                               aria-describedby="basic-addon1">
                        <#if productNameError??>
                            <div class="invalid-feedback">
                                ${productNameError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(productDescriptionError??)?string('is-invalid','')}"
                               value="<#if product??>${product.productDescription}</#if>"
                               placeholder="Описание товара" aria-label="productDescription"
                               name="productDescription"
                               aria-describedby="basic-addon1">
                        <#if productDescriptionError??>
                            <div class="invalid-feedback">
                                ${productDescriptionError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(productAmountError??)?string('is-invalid','')}"
                               value="<#if product??>${product.productAmount}</#if>"
                               placeholder="Количество" aria-label="productAmount"
                               name="productAmount"
                               aria-describedby="basic-addon1">
                        <#if productAmountError??>
                            <div class="invalid-feedback">
                                ${productAmountError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(productPriceError??)?string('is-invalid','')}"
                               value="<#if product??>${product.productPrice}</#if>"
                               placeholder="Цена товара" aria-label="productPrice"
                               name="productPrice"
                               aria-describedby="basic-addon1">
                        <#if productPriceError??>
                            <div class="invalid-feedback">
                                ${productPriceError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                    </div>

                    <input type="hidden" value="${product.id}" name="productId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</@c.page>