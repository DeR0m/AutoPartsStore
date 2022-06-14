<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${categoryForMark.categoryForMarkName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editCategoryForMark" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(categoryForMarkNameError??)?string('is-invalid','')}"
                               value="<#if categoryForMark??>${categoryForMark.categoryForMarkName}</#if>"
                               placeholder="Название категории" aria-label="categoryForMarkName"
                               name="categoryForMarkName"
                               aria-describedby="basic-addon1">
                        <#if categoryForMarkNameError??>
                            <div class="invalid-feedback">
                                ${categoryForMarkNameError}
                            </div>
                        </#if>
                    </div>

                    <input type="hidden" value="${categoryForMark.id}" name="categoryForMarkId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</@c.page>