<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="pb-4">
        <h2>${markCategory.markCategoryName} ${markModel.modelName} ${modelGeneration.generationModelName}</h2>
    </div>

    <#if isAdmin>
        <div class="container px-4 px-lg-5">
            <div class="info">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(bodyTypeNameError??)?string('is-invalid','')}"
                                   value="<#if bodyTypeName??>${bodyType.bodyTypeName}</#if>"
                                   placeholder="Название кузова" aria-label="bodyTypeName"
                                   name="bodyTypeName"
                                   aria-describedby="basic-addon1">
                            <#if modelNameError??>
                                <div class="invalid-feedback">
                                    ${bodyTypeNameError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Добавить тип кузова</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </#if>
    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list bodyTypes as bodyType>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="engineType/${bodyType.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if bodyType.filename??>
                                    <img src="/img/${bodyType.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${bodyType.bodyTypeName}</strong></span>
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
                                            <form action="#" method="post">
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
                Нет выбора кузова
            </#list>
        </div>
    </div>
</@c.page>