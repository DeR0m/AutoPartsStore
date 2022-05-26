<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="pb-4">
        <h2>${markCategory.markCategoryName}</h2>
    </div>

    <#if isAdmin>
        <div class="container px-4 px-lg-5">
            <div class="info">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(modelNameError??)?string('is-invalid','')}"
                                   value="<#if modelName??>${markModel.modelName}</#if>"
                                   placeholder="Название модели" aria-label="modelName"
                                   name="modelName"
                                   aria-describedby="basic-addon1">
                            <#if modelNameError??>
                                <div class="invalid-feedback">
                                    ${modelNameError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Добавить модель</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list markModels as markModel>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="generation/${markModel.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if markModel.filename??>
                                    <img src="/img/${markModel.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${markModel.modelName}</strong></span>
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
                                            <form action="${markModel.id}/editMarkModel" method="post">
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
                Нет моделей
            </#list>
        </div>
    </div>
</@c.page>