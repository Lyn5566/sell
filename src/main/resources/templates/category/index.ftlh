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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <input type="text" name="categoryId" value="${(category.categoryId)!''}" hidden>
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" name="categoryName" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input type="text" name="categoryType" class="form-control" value="${(category.categoryType)!''}"/>
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