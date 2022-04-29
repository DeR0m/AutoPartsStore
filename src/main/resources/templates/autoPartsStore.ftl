<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container px-4 px-lg-5">
        <div class="row gx-4">
            <div class="col-sm-6">
                <a href="#" class="text-decoration-none text-reset">
                    <div class="card py-5 h-100">
                        <div class="card-body text-center">
                            <h4 class="text-uppercase m-0">Корзина</h4>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-sm-6">
                <a href="#" class="text-decoration-none text-reset">
                    <div class="card py-5 h-100">
                        <div class="card-body text-center">
                            <h4 class="text-uppercase m-0">Торговая сеть</h4>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>

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

                    <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Добавить категорию</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list categories as category>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="${category.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if category.filename??>
                                    <img src="/img/${category.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${category.categoryName}</strong></span>
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                Нет категорий
            </#list>
        </div>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data" action="/markCategory">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(markCategoryNameError??)?string('is-invalid','')}"
                               value="<#if markCategory??>${markCategory.markCategoryName}</#if>"
                               placeholder="Название марки автомобиля" aria-label="markCategoryName"
                               name="markCategoryName"
                               aria-describedby="basic-addon1">
                        <#if categoryNameError??>
                            <div class="invalid-feedback">
                                ${markCategoryNameError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input name="fileForMarkCategory" class="form-control form-control-sm" id="formFileSm" type="file">
                    </div>

                    <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Добавить марку автомобиля</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list markCategories as markCategory>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="#" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if markCategory.markFilename??>
                                    <img src="/img/${markCategory.markFilename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${markCategory.markCategoryName}</strong></span>
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                Нет марок автомобилей
            </#list>
        </div>
    </div>


</@c.page>