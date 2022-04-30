<#import "parts/common.ftl" as c>

<@c.page>
<h5>${bodyType.bodyTypeName}</h5>

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

                    <div class="form-group mb-3">
                        <input name="file" class="form-control form-control-sm" id="formFileSm" type="file">
                    </div>

                    <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-secondary">Добавить тип двигателя</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="container px-4 px-lg-5">
        <div class="row row-flex gx-1 gy-2">
            <#list engineTypes as engineType>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                    <a href="#" class="text-decoration-none text-reset">
                        <div class="card">
                            <div class="text-center p-3">
                                <#if engineType.filename??>
                                    <img src="/img/${engineType.filename}" class="card-img-top" style="width: 5rem;">
                                </#if>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${engineType.engineModel}</strong></span>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${engineType.engineCapacity}</strong></span>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${engineType.powerHp}</strong></span>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${engineType.engineName}</strong></span>
                            </div>
                            <div class="card-body text-center">
                                <span><strong>${engineType.fuelType}</strong></span>
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                Нет выбора двигателя
            </#list>
        </div>
    </div>
</@c.page>