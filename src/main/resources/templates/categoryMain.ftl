<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div>
        <h2>Категории</h2>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group col-md-6">
                <form method="get" action="/categories" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                           placeholder="Введите название категории">
                    <div class="form-group mt-3">
                        <button type="submit" class="btn btn-outline-secondary">Поиск категории</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <#if isAdmin>
        <div class="container px-4 px-lg-5">
            <div class="info">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(categoryNameError??)?string('is-invalid','')}"
                                   value="<#if category??>${category.categoryName}</#if>"
                                   placeholder="Название категории" aria-label="categoryName"
                                   name="categoryName"
                                   aria-describedby="basic-addon1">
                            <#if categoryNameError??>
                                <div class="invalid-feedback">
                                    ${categoryNameError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Добавить категорию</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>

    <#if !isAdmin>
        <div class="mt-3">
        </div>
    </#if>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list categories as category>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="category/${category.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if category.filename??>
                                    <img src="/img/${category.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${category.categoryName}</strong></span>
                            </div>
                            <#if isAdmin>
                                <div class="container px-4">
                                    <div class="row-flex">
                                        <div class="px-lg-1">
                                            <form action="${category.id}/remove" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Удалить</button>
                                            </form>
                                        </div>
                                        <div class="px-lg-2">
                                            <form action="${category.id}/edit" method="post">
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