$(function(){
    //进入页面直接展示所有商品
    $.get("http://localhost:8080/selectAllGoods.do",
        function (res) {
            var content = "";    
            $.each(res, function (index, goods) { 
                content+="<tr><td>"+goods.goods_name+"</td>";
                content+="<td><img src="+goods.goods_img+" width='20' height='20' onmouseenter='imgBig(this)' onmouseleave='imgSmall(this)'/></td>"
                content+="<td>"+goods.goods_price+"</td>"
                content+="<td>"+goods.ptype.ptype_name+"</td>"
                content+="<td>"+goods.pctype.pctype_name+"</td>"
                if(goods.goods_status==true){
                    //获取状态，并判断
                    content+="<td class='goods_status' style='color:green'>上架</td>"
                }else{
                    content+="<td class='goods_status' style='color:red'>下架</td>"
                }        
                if(goods.ptype.ptype_name=1){
                    content+="<td> <button class='layui-btn layui-btn-xs' onclick='use1But()'>使用</button> </td>"
                }else{
                    content+="<td> <button class='layui-btn layui-btn-xs' onclick='useBut()'>使用</button> </td>"
                }
            });
            $("#showAll").html(content);//获取状态，并判断       
        }
    );
   
    //进入页面获取一级分类
   $.get("http://localhost:8080/selectAllType.do",
        function (res) {
            var content = "";
            content+="<option value='0' selected>全部</option>"
            $.each(res, function (index, ptype) {    
                content+="<option value='"+ptype.ptype_id+"'>"+ptype.ptype_name+"</option>"
            });
            $("#provId").html(content);     
        }
    ); 
}); 




//使用按钮
function useBut(){
    layui.use('layer', function() {
        var layer = layui.layer;
            
        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            fixed: false, //不固定
            maxmin: true,
            content: 'goods_use.html'
        });
    });	
}
function use1But(){
    layui.use('layer', function() {
        var layer = layui.layer;		
        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            fixed: false, //不固定
            maxmin: true,
            content: 'goods_use1.html'
        });
    });	
}
//解决框架的问题"overflow","hidden"
var setCBodyOverflowINDEX = 0; 
function setCBodyOverflow(layero, index, layer){
    console.log(index)
}

