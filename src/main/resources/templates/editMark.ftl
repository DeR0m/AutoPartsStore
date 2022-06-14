<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${markCategory.markCategoryName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editMark" method="post" enctype="multipart/form-data" action="/markCategory">
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
                        <input name="fileForMarkCategory" class="form-control form-control-sm" id="formFileSm"
                               type="file">
                    </div>

                    <input type="hidden" value="${markCategory.id}" name="markCategoryId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</@c.page>