<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${category.categoryName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(subCategoryNameError??)?string('is-invalid','')}"
                               value="<#if subcategoryName??>${subcategory.subcategoryName}</#if>"
                               placeholder="Название подкатегории" aria-label="subcategoryName"
                               name="subcategoryName"
                               aria-describedby="basic-addon1">
                        <#if categoryNameError??>
                            <div class="invalid-feedback">
                                ${subCategoryNameError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                    </div>

                    <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Добавить подкатегорию</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list subcategories as subcategory>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="${subcategory.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if subcategory.filename??>
                                    <img src="/img/${subcategory.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${subcategory.subcategoryName}</strong></span>
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                Нет подкатегории
            </#list>
        </div>
    </div>
</@c.page>