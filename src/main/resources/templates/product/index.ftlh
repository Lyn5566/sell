<html>
<#include "../common/header.ftlh">
<body>
<div class="toggled" id="wrapper">
    <!--侧边栏-->
    <#include "../common/nav.ftlh">
    <!--主体内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-8 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <input type="text" name="productId" value="${(productInfo.productId)!''}" hidden>
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" name="productName" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="text" name="productPrice" class="form-control" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input type="text" name="productDescription" class="form-control" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img src="${(productInfo.productIcon)!''}">
                            <input type="text" name="productIcon" class="form-control" value="${(productInfo.productIcon)!''}">
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType">
                                <#list categoryList as category>
                                   <option value="${category.categoryType}"
                                           <#if (productInfo.categoryType)??&&productInfo.categoryType == category.categoryType>
                                           selected
                                           </#if>
                                   >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>