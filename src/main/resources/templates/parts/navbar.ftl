<#include "security.ftl">
<#import "login.ftl" as l>

<div class="wrapper">
    <header class="header">
        <div class="header__content">
            <div class="header-top">
                <div class="container px-4 px-lg-5" style="display: flex;">
                    <div class="logo__container">
                        <a href="/" class="header__logo">Магазин автозапчастей</a>
                    </div>
                    <div class="top-navigation">
                        <ul class="top-navigation__row">
                            <li class="top-navigation__link pt-2">
                                ${name}
                            </li>
                            <li class="top-navigation__link">
                                <@l.logout />
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="header-bottom">
                <div class="container px-4 px-lg-5">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav mr-auto pt-2 pb-2">
                                <li class="bottom-navigation__link">
                                    <a class="bottom-navigation__link" href="/">Главная</a>
                                </li>
                                <li class="bottom-navigation__link">
                                    <a class="bottom-navigation__link" href="/categories">Категории</a>
                                </li>
                                <li class="bottom-navigation__link">
                                    <a class="bottom-navigation__link" href="/markCategories">Марки автомобилей</a>
                                </li>
                                <li class="bottom-navigation__link">
                                    <a class="bottom-navigation__link" href="/user/basket/">Корзина</a>
                                </li>
                                <#if user??>
                                <li class="bottom-navigation__link">
                                    <a class="bottom-navigation__link" href="/user/profile">Профиль</a>
                                </li>
                                </#if>
                                <#if isAdmin>
                                <li class="bottom-navigation__link">
                                    <a class="bottom-navigation__link" href="/user">Пользователи</a>
                                </li>
                                </#if>

                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
</div>