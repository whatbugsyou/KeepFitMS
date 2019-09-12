$(function () {
    //进入页面直接展示所有商品
    findGoods()

    //进入页面获取一级分类
    $.get("http://localhost:8080/selectAllType.do",
        function (res) {
            var content = "";
            content += "<option value='0' selected>全部</option>"
            $.each(res, function (index, ptype) {
                content += "<option value='" + ptype.ptype_id + "'>" + ptype.ptype_name + "</option>"
            });
            $("#provId").html(content);

        }
    ).error(function(xhr,errorText,errorType) { layer.msg(xhr.status+"网络异常请重试")});
});


//查询所有商品信息
function findGoods() {
    //分页
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        $.get("http://localhost:8080/selectGoods.do", {
            "curr": 1,
            "limit": 5
        },
            function (res,status) {

                showGoods(res);
                //总页数大于页码总数
                laypage.render({
                    elem: 'pages',
                    count: res.count,
                    limit: 5,
                    limits: [5, 10, 15, 20],
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    jump: function (obj) {
                        console.log(obj)
                        $.get("http://localhost:8080/selectGoods.do", {
                            "curr": obj.curr,
                            "limit": obj.limit
                        },
                            function (res) {
                                showGoods(res);
                            })
                    }
                });
               
            }
        ).error(function(xhr,errorText,errorType) { layer.msg(xhr.status+"网络异常请重试")});
    });
}



//展示商品并渲染到页面
function showGoods(res) {
    var content = "";
    if (res.count == 0) {
        layer.msg("没有查到结果")
    }
    $.each(res.goods, function (index, goods) {

        content += "<tr><td>" + goods.goods_name + "</td>";
        content += "<td><img src=" + goods.goods_img + " width='20' height='20' onmouseenter='imgBig(this)' onmouseleave='imgSmall(this)'/></td>"  
        if (goods.ptype.ptype_id == 1) {
        content += "<td>" + goods.goods_price + " 元/小时</td>"
        }else{
            content += "<td>" + goods.goods_price + " 元</td>"	
        }
        content += "<td>" + goods.goods_num + "</td>"
        content += "<td>" + goods.ptype.ptype_name + "</td>"
        content += "<td>" + goods.pctype.pctype_name + "</td>"
        if( goods.goods_num!=0){
	        if (goods.goods_status == true) {
	            //获取状态，并判断
	            content += "<td class='goods_status' style='color:green'><i class='layui-icon'>&#xe605;</i></td>"
	            if (goods.ptype.ptype_id == 1) {
	                content += "<td> <button class='layui-btn layui-btn-xs' onclick='use1But("+ JSON.stringify(goods) +")'>使用</button> </td>"
	            } else {            	
	                content += "<td> <button class='layui-btn layui-btn-xs' onclick='useBut("+ JSON.stringify(goods) +")'>使用</button> </td>"
	            }
	        } else {
	            content += "<td class='goods_status' style='color:red'><i class='layui-icon'>&#x1006;</i></td>"
	            if (goods.ptype.ptype_id == 1) {
	                content += "<td> <button class='layui-btn layui-btn-xs layui-btn-disabled' >使用</button> </td>"
	            } else {
	                content += "<td> <button class='layui-btn layui-btn-xs layui-btn-disabled'>使用</button> </td>"
	            }
	        }
    	}else{
    		 content += "<td class='goods_status' style='color:red'><i class='layui-icon'>&#x1006;</i></td>"
 	            if (goods.ptype.ptype_id == 1) {
 	                content += "<td> <button class='layui-btn layui-btn-xs layui-btn-disabled' >使用</button> </td>"
 	            } else {
 	                content += "<td> <button class='layui-btn layui-btn-xs layui-btn-disabled'>使用</button> </td>"
 	            }
    	}
    });
    $("#showAll").html(content); //获取状态，并判断      
}



//使用按钮
function useBut(goods) {
    layui.use('layer', function () {
        var layer = layui.layer;

        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            fixed: false, //不固定
            maxmin: true,
            content: 'goods_use.html',
            success: function (layero, index) {
    			var body = layer.getChildFrame('body', index);
    			var iframeWin = window[layero.find('iframe')[0]['name']];   		
    			iframeWin.useFunction(goods);
    		}           	
        });
    });
}

function use1But(goods) {
    layui.use('layer', function () {
        var layer = layui.layer;
        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            fixed: false, //不固定
            maxmin: true,
            content: 'goods_use1.html',
            success: function (layero, index) {
    			var body = layer.getChildFrame('body', index);
    			var iframeWin = window[layero.find('iframe')[0]['name']];   		
    			iframeWin.use1Function(goods);
    		}           	
        });
    });
}
//解决框架的问题"overflow","hidden"
var setCBodyOverflowINDEX = 0;

function setCBodyOverflow(layero, index, layer) {
    console.log(index)
}