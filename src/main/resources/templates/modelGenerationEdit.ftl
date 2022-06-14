<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${modelGeneration.generationModelName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editModelGeneration" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(generationModelNameError??)?string('is-invalid','')}"
                               value="<#if modelGeneration??>${modelGeneration.generationModelName}</#if>"
                               placeholder="Название модели" aria-label="generationModelName"
                               name="generationModelName"
                               aria-describedby="basic-addon1">
                        <#if generationModelNameError??>
                            <div class="invalid-feedback">
                                ${generationModelNameError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(graduationYearError??)?string('is-invalid','')}"
                               value="<#if modelGeneration??>${modelGeneration.graduationYear}</#if>"
                               placeholder="Год выпуска" aria-label="graduationYear"
                               name="graduationYear"
                               aria-describedby="basic-addon1">
                        <#if graduationYearError??>
                            <div class="invalid-feedback">
                                ${graduationYearError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group mb-3">
                        <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                    </div>

                    <input type="hidden" value="${modelGeneration.id}" name="modelGenerationId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@c.page>