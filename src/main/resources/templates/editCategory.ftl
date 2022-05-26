<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
   <h5>${category.categoryName}</h5>

   <form action="/editCategory" method="post">
      <input type="text" name="name" value="${category.categoryName}">

      <input type="hidden" value="${category.id}" name="categoryId">
      <input type="hidden" name="_csrf" value="${_csrf.token}">
      <button type="submit">Сохранить изменения</button>

   </form>
</@c.page>