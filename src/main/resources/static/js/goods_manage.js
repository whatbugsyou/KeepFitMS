 $(function(){
    //进入页面直接展示所有商品
    $.get("http://localhost:8080/KeepFitMS/selectAllGoods.do",
        function (res) {
            $("#showAll").html("");
            $.each(res, function (index, value) { 
                var content = null;
                $("#showAll").append(content);//获取状态，并判断             
                content+="<tr><td>"+value.goods_name+"</td>";
                content+="<td><img src="+value.goods_img+" width='20' height='20' onmouseenter='imgBig(this)' onmouseleave='imgSmall(this)'/></td>"
                content+="<td>"+value.goods_price+"</td>"
                content+="<td>"+value.ptype_name+"</td>"
                content+="<td>"+value.pctype.name+"</td>"
                if(value.goods_status==true){
                    //获取状态，并判断
                    content+="<td class='goods_status'id='status"+value.goods_id+"' onclick='changeStatus("+value.goods_id,value.goods_status+")' style='color:green'>上架</td>"
                }else{
                    content+="<td class='goods_status' id='status"+value.goods_id+"'  onclick='changeStatus("+value.goods_id,value.goods_status+")' style='color:red'>下架</td>"
                }                       
                    content+="<td> <button class='layui-btn layui-btn-xs' onclick='updateBut()'>使用</button> </td>"

            });
        }
    );
   



}); 





//修改按钮
function updateBut(){
    layui.use('layer', function() {
        var layer = layui.layer;
            
        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            fixed: false, //不固定
            maxmin: true,
            content: 'goods_update.html'
        });
    });
    
}
//改变状态
function changeStatus(id,status){
    if(status==true){
        $("#status"+id).css("color","red");
        $("#status"+id).html("下架");
        var status=false;
      
    }else{
        $("#status"+id).css("color","green");
        $("#status"+id).html("上架");
        var status=true;
    }
        $.get("http://localhost:8080/KeepFitMS/selectAllGoods.do",{"id":id,"status":status});   
}
//解决框架的问题"overflow","hidden"
var setCBodyOverflowINDEX = 0; 
function setCBodyOverflow(layero, index, layer){
    console.log(index)
}

