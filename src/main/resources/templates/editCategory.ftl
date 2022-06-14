<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${category.categoryName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editCategory" method="post" enctype="multipart/form-data">
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

                    <input type="hidden" value="<#if category??>${category.id}</#if>" name="categoryId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">

                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@c.page>