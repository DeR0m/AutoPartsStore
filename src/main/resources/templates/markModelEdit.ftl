<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<#--    все работает, но надо отредактировать, чтобы было красиво!!!!!!!!!!!!!!!!!!!!-->
<@c.page>
    <h5>${markModel.modelName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editMarkModel" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(modelNameError??)?string('is-invalid','')}"
                               value="<#if markModel??>${markModel.modelName}</#if>"
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
                    <input type="hidden" value="${markModel.id}" name="markModelId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@c.page>