<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="pb-4">
        <h2>${markCategory.markCategoryName} ${markModel.modelName} ${modelGeneration.generationModelName} ${bodyType.bodyTypeName} ${engineType.engineModel} ${categoryForMark.categoryForMarkName}</h2>
    </div>

    <#if isAdmin>
        <div class="container px-4 px-lg-5">
            <div class="info">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(subcategoryNameError??)?string('is-invalid','')}"
                                   value="<#if subcategoryName??>${subcategory.subcategoryName}</#if>"
                                   placeholder="Название категории" aria-label="subcategoryName"
                                   name="subcategoryName"
                                   aria-describedby="basic-addon1">
                            <#if subcategoryNameError??>
                                <div class="invalid-feedback">
                                    ${subcategoryNameError}
                                </div>
                            </#if>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Добавить подкатегорию</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list subcategories as subcategory>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="productForMark/${subcategory.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="card-body text-center">
                                <span><strong>${subcategory.subcategoryName}</strong></span>
                            </div>
                            <#if isAdmin>
                                <div class="container px-4">
                                    <div class="row-flex">
                                        <div class="px-lg-1">
                                            <form action="${subcategory.id}/removeSubcategory" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Удалить</button>
                                            </form>
                                        </div>
                                        <div class="px-lg-2">
                                            <form action="${subcategory.id}/editSubcategory" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Редактировать
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
                Нет категорий
            </#list>
        </div>
    </div>
</@c.page>