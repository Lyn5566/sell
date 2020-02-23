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
                            <th>类目id</th>
                            <th>名字</th>
                            <th>type</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list categoryList as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
        </div>
    </div>
</div>
</body>
</html>