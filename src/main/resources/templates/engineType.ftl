<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="pb-4">
        <h2>${markCategory.markCategoryName} ${markModel.modelName} ${modelGeneration.generationModelName} ${bodyType.bodyTypeName}</h2>
    </div>

    <#if isAdmin>
        <div class="container px-4 px-lg-5">
            <div class="info">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(engineModelError??)?string('is-invalid','')}"
                                   value="<#if engineModel??>${engineType.engineModel}</#if>"
                                   placeholder="Название модели" aria-label="engineModel"
                                   name="engineModel"
                                   aria-describedby="basic-addon1">
                            <#if engineModelError??>
                                <div class="invalid-feedback">
                                    ${engineModelError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(engineCapacityError??)?string('is-invalid','')}"
                                   value="<#if engineCapacity??>${engineType.engineCapacity}</#if>"
                                   placeholder="Объем двигателя" aria-label="engineCapacity"
                                   name="engineCapacity"
                                   aria-describedby="basic-addon1">
                            <#if engineCapacityError??>
                                <div class="invalid-feedback">
                                    ${engineCapacityError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(powerHpError??)?string('is-invalid','')}"
                                   value="<#if powerHp??>${engineType.powerHp}</#if>"
                                   placeholder="Мощность двигателя" aria-label="powerHp"
                                   name="powerHp"
                                   aria-describedby="basic-addon1">
                            <#if powerHpError??>
                                <div class="invalid-feedback">
                                    ${powerHpError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(engineNameError??)?string('is-invalid','')}"
                                   value="<#if engineName??>${engineType.engineName}</#if>"
                                   placeholder="Название двигателя" aria-label="engineName"
                                   name="engineName"
                                   aria-describedby="basic-addon1">
                            <#if engineNameError??>
                                <div class="invalid-feedback">
                                    ${engineNameError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group mb-3">
                            <input type="text"
                                   class="form-control form-control-sm ${(fuelTypeError??)?string('is-invalid','')}"
                                   value="<#if fuelType??>${engineType.fuelType}</#if>"
                                   placeholder="Тип топлива" aria-label="fuelType"
                                   name="fuelType"
                                   aria-describedby="basic-addon1">
                            <#if fuelTypeError??>
                                <div class="invalid-feedback">
                                    ${fuelTypeError}
                                </div>
                            </#if>
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-secondary">Добавить тип двигателя</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </#if>

    <div class="container px-4 px-lg-5">
        <div class="d-flex justify-content-evenly p-2">
            <span><strong>Модель</strong></span>
            <span><strong>Объем</strong></span>
            <span><strong>Мощность</strong></span>
            <span><strong>Двигатель</strong></span>
            <span><strong>Тип топлива</strong></span>
        </div>

        <div class="row row-flex gx-1 gy-2">

            <#list engineTypes as engineType>
                <div class="">
                    <a href="categoryForMark/${engineType.id}" class="text-decoration-none text-reset">

                        <div class="card">
                            <div class="d-flex justify-content-evenly p-3">
                                <span><strong>${engineType.engineModel}</strong></span>
                                <span><strong>${engineType.engineCapacity}</strong></span>
                                <span><strong>${engineType.powerHp}</strong></span>
                                <span><strong>${engineType.engineName}</strong></span>
                                <span><strong>${engineType.fuelType}</strong></span>
                            </div>
                            <#if isAdmin>
                                <div class="container px-4">
                                    <div class="row-flex">
                                        <div class="px-lg-1">
                                            <form action="#" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Удалить</button>
                                            </form>
                                        </div>
                                        <div class="px-lg-2">
                                            <form action="${engineType.id}/editEngineType" method="get">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <button class="btn btn-dark mt-2 mb-2" type="submit">Редактировать
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </div>
                    </a>
                </div>
            <#else>
                Нет выбора двигателя
            </#list>
        </div>
    </div>
</@c.page>