<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${subcategory.subcategoryName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editSubcategory" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(subcategoryNameError??)?string('is-invalid','')}"
                               value="<#if subcategory??>${subcategory.subcategoryName}</#if>"
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

                    <input type="hidden" value="<#if subcategory??>${subcategory.id}</#if>" name="subcategoryId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</@c.page>