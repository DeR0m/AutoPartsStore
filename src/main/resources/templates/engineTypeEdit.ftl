<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${engineType.engineModel}</h5>

    <div class="container px-4 px-lg-5">
        <div class="info">
            <div class="form-group mt-3">
                <form action="/editEngineType" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <input type="text"
                               class="form-control form-control-sm ${(engineModelError??)?string('is-invalid','')}"
                               value="<#if engineType??>${engineType.engineModel}</#if>"
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
                               value="<#if engineType??>${engineType.engineCapacity}</#if>"
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
                               value="<#if engineType??>${engineType.powerHp}</#if>"
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
                               value="<#if engineType??>${engineType.engineName}</#if>"
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
                               value="<#if engineType??>${engineType.fuelType}</#if>"
                               placeholder="Тип топлива" aria-label="fuelType"
                               name="fuelType"
                               aria-describedby="basic-addon1">
                        <#if fuelTypeError??>
                            <div class="invalid-feedback">
                                ${fuelTypeError}
                            </div>
                        </#if>
                    </div>

                    <input type="hidden" value="${engineType.id}" name="engineTypeId">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Сохранить</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</@c.page>