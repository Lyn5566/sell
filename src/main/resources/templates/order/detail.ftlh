<html>
<#include "../common/header.ftlh">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftlh">
    <div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-4 column">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr><th>订单id</th>
                        <th>订单总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td>${orderDTO.orderId}</td>
                        <td>${orderDTO.getOrderAmount()}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <#--订单详情表数据-->
            <div class="col-md-12 column">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>商品id</th>
                        <th>商品名称</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>总额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTO.getOrderDetailList() as productInfo>
                        <tr class="info">
                            <td>${productInfo.getProductId()}</td>
                            <td>${productInfo.getProductName()}</td>
                            <td>${productInfo.getProductPrice()}</td>
                            <td>${productInfo.getProductQuantity()}</td>
                            <td>${productInfo.getProductPrice()*productInfo.getProductQuantity()}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-md-12 column">
                <#if orderDTO.getOrderStatusEnum().getCode() == 0>
                    <button type="button" class="btn btn-primary btn-default" id="finishBtn">完结订单</button>
                    <button type="button" class="btn btn-primary btn-danger" id="cancelBtn">取消订单</button>
                </#if>
            </div>
        </div>
    </div>
    </div>
</div>

<script>
    $("#finishBtn").click(function () {
        if (!confirm('确定要完结订单吗')) {
            return;
        }
        location.href = '/sell/seller/order/finish?orderId=${orderDTO.orderId}';
    });
    $("#cancelBtn").click(function () {
        if (!confirm('确定要取消订单吗')) {
            return;
        }
        location.href = '/sell/seller/order/cancel?orderId=${orderDTO.orderId}';
    })
</script>
</body>
</html>