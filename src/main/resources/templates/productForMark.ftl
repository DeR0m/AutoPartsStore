<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="pb-4">
        <h2>${markCategory.markCategoryName} ${markModel.modelName} ${modelGeneration.generationModelName} ${bodyType.bodyTypeName} ${engineType.engineModel} ${categoryForMark.categoryForMarkName} ${subcategory.subcategoryName}</h2>
    </div>

    <#if isAdmin>
        <div class="container px-4 px-lg-5">
            <div class="info">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(productNameError??)?string('is-invalid','')}"
                                   value="<#if productName??>${productForMark.productName}</#if>"
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
                                   value="<#if productDescription??>${productForMark.productDescription}</#if>"
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
                                   value="<#if productAmount??>${productForMark.productAmount}</#if>"
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
                                   value="<#if productPrice??>${productForMark.productPrice}</#if>"
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
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Добавить товар</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list productForMarks as productForMark>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="#" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if productForMark.filename??>
                                    <img src="/img/${productForMark.filename}" class="card-img-top"
                                         style="width: 5rem;">
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
                                <span><strong>Цена: ${productForMark.productPrice}</strong></span>
                            </div>
                            <div class="container px-4">
                                <div class="row-flex">
                                    <div class="px-lg-2">
                                        <form action="${subcategory.id}/${productForMark.id}/addBasket" method="post">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <button class="btn btn-dark mt-2 mb-2" type="submit">Добавить в корзину
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <#if isAdmin>
                                <div class="container px-4">
                                    <div class="row-flex">
                                        <div class="px-lg-1">
                                            <form action="#" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Удалить</button>
                                            </form>
                                        </div>
                                        <div class="px-lg-2">
                                            <form action="${productForMark.id}/productForMarkEdit" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Редактировать
                                                </button>
                                            </form>
                                        </div>
                                        <div class="px-lg-2">
                                            <form action="${productForMark.id}" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Добавить в корзину
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </div>
                    </a>
                </div>
            <#else>
                Нет товара
            </#list>
        </div>
    </div>
</@c.page>