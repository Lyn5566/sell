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
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.getContent() as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td>${productInfo.productIcon}</td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <td>
                                    <#if productInfo.getProductStatusEnum().getCode() == 0>
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                        <#else >
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${productInfoPage.getSize()}">上一页</a></li>
                        </#if>
                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if currentPage ==index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${index}&size=${productInfoPage.getSize()}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#--                <#else>-->
                        <#--                    <#if orderDTOPage.getTotalPages() gt 5>-->
                        <#--                        <#list currentPage..orderDTOPage.getTotalPages() as index>-->
                        <#--                            <#if currentPage ==index>-->
                        <#--                                <li class="disabled"><a href="#">${index}</a></li>-->
                        <#--                                <#else>-->
                        <#--                                <li>-->
                        <#--                                    <a href="/sell/seller/order/list?page=${index}&size=${orderDTOPage.getSize()}">${index}</a>-->
                        <#--                                </li>-->
                        <#--                            </#if>-->
                        <#--                        </#list>-->
                        <#--                    </#if>-->
                        <#if currentPage gte productInfoPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage+1}&size=${productInfoPage.getSize()}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>