
### 订单系统
业务的开展往往是围绕订单来展开，根据用户的行为逐个设计表结构

```sql
    /**
     * 购物车
     */
    create table order_cart (
        `spuId`,
        `skuId`,
        `userId`    comment '用户id',
        `shopId`    comment '店铺id',
        `productName`   comment '商品名称',
        `productNum`    comment '商品数量',
        `productCost`   comment '商品价格',
        `statue`    comment '状态'
    )
    /**
     * 订单表
     */
    create table order_info (
        `orderId`       comment '订单编号(雪花算法)',
        `itemCount`     comment '商品项数量',
        `userId`        comment '用户id',
        `shopId`        comment '店铺id',
        `createTime`    comment '下单时间',
        `payType`       comment '支付方式(可用数字表示，如1：支付宝，2：微信，3：银行卡...)',
        `payTime`       comment '支付时间',
        `outTradeNo`    comment 'outTradeNo 支付宝订单号',
        `deliveryType`  comment '配送方式',
        `deliveryTime`  comment '期望配送日期',
        `productCost`   comment '商品总额',
        `deliveryCost`  comment '运费',
        `payment`       comment '实际付款',
        `orderStatue`   comment '订单状态（这里的状态可根据实际项目来定，可以定10,20,30..这样如果中间缺少一个状态可以添加进去）'
    )
    /**
     * 订单商品表
     * 购物车里面有多个店铺的商品，那么应该分别为这些店铺生成对应的订单
     * 平台可以进行合并支付，但是订单还是要归店铺的
     * order_info与order_product是1对多的关系，一个订单可能有多个商品
     */
    create table order_product (
        `orderId`       comment '',
        `spuId`         comment '',
        `skuId`         comment '',
        `shopId`        comment '',
        `productName`   comment '',
        `productNum`    comment '',
        `productCost`   comment ''
    )
    /**
     * 物流表
     */
    create table order_logistics (
        `orderId`           comment '',
        `logisticsId`       comment '',
        `logisticsCode`     comment '',
        `trackingNo`        comment '',
        `sendTime`          comment '',
        `receiveTime`       comment ''
    )
    /**
     * 物流跟踪表
     */
    create table order_logistics_flow (
        `orderId`       comment '',
        `logisticsId`   comment '',
        `trackingNo`    comment '',
        `remark`        comment '第三方接口返回的信息'
    )
    /**
     * 支付记录表
     */
    create table order_pay_history (
        `orderId`       comment '',
        `payType`       comment '',
        `orderCost`     comment '',
        `payment`       comment '',
        `pay_json`      comment '第三方支付参数信息',
        `remark`        comment '备注'
    )
    /**
     * 订单评论表
     */
     create table order_product_comment (
        `spuId`         comment '',
        `skuId`         comment '',
        `commentUserId` comment '评论用户id',
        `nickName`      comment '昵称',
        `thumbsNum`     comment '点赞数',
        `comment`       comment '',
        `score`         comment '',
        `commentTime`   comment '',
        `product_info_json`     comment '评论的商品信息',
        `image_json`    comment '评论图片'
    )
    /**
     * 发票信息表
     */
    create table order_invoice ()
```




