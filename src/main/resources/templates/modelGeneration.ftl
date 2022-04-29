<#import "parts/common.ftl" as c>

<@c.page>
<h5>${markModel.modelName}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(generationModelNameError??)?string('is-invalid','')}"
                               value="<#if generationModelName??>${modelGeneration.generationModelName}</#if>"
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
                               value="<#if graduationYear??>${modelGeneration.graduationYear}</#if>"
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

                    <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Добавить модель</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list modelGenerations as modelGeneration>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="bodyType/${modelGeneration.id}" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if modelGeneration.filename??>
                                    <img src="/img/${modelGeneration.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${modelGeneration.generationModelName}</strong></span>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${modelGeneration.graduationYear}</strong></span>
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                Нет поколений
            </#list>
        </div>
    </div>
</@c.page>