<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${bodyType.bodyTypeName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editBodyType" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(bodyTypeNameError??)?string('is-invalid','')}"
                               value="<#if bodyType??>${bodyType.bodyTypeName}</#if>"
                               placeholder="Название кузова" aria-label="bodyTypeName"
                               name="bodyTypeName"
                               aria-describedby="basic-addon1">
                        <#if modelNameError??>
                            <div class="invalid-feedback">
                                ${bodyTypeNameError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                    </div>

                    <input type="hidden" value="${bodyType.id}" name="bodyTypeId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</@c.page>