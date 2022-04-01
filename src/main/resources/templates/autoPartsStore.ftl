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

    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <input type="text" class="form-control form-control-sm ${(categoryNameError??)?string('is-invalid','')}"
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

            <div class="mb-3">
                <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
            </div>


            <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить категорию</button>
            </div>

        </form>
    </div>

<#--    <div class="card-columns">-->
<#--        <#list categories as category>-->
<#--            <div class="card my-3">-->
<#--                <#if category.filename??>-->
<#--                    <img src="/img/${category.filename}" class="card-img-top">-->
<#--                </#if>-->
<#--                <div class="m-2">-->
<#--                    <span>${category.categoryName}</span>-->
<#--                </div>-->

<#--            </div>-->
<#--        <#else>-->
<#--            Нет категорий-->
<#--        </#list>-->
<#--    </div>-->


</@c.page>