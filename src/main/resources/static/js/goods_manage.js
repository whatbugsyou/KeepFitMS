
 $(function(){
	    //进入页面直接展示所有商品
		 findGoods()
		
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

	function findGoods(){
		//分页
		layui.use('laypage', function() {
		 var laypage = layui.laypage;
		 $.get("http://localhost:8080/selectGoods.do",{"curr":1,"limit":10},
			        function(res) {
			    		showGoods(res);
			    		//总页数大于页码总数
			    		laypage.render({
						    elem: 'pages'
						    ,count: res.count
						    ,limit:5
						    ,limits:[5,10,15,20]
						    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
						    ,jump: function(obj){
						      $.get("http://localhost:8080/selectGoods.do",{"curr":obj.curr,"limit":obj.limit},
			    						 function (res) {
			    							showGoods(res);
			    						})
			    						
						    }
						});
			        }
			    );
		});
	}



	//展示商品并渲染到页面
	function showGoods(res){
	    var content = "";    
	    $.each(res.goods, function (index, goods) { 
	        content+="<tr><td>"+goods.goods_name+"</td>";
	        content+="<td><img src="+goods.goods_img+" width='20' height='20' onmouseenter='imgBig(this)' onmouseleave='imgSmall(this)'/></td>"
	        content+="<td>"+goods.goods_price+"</td>"
	        content+="<td>"+goods.goods_num+"</td>"
	        content+="<td>"+goods.ptype.ptype_name+"</td>"
	        content+="<td>"+goods.pctype.pctype_name+"</td>"                   
           
                	 if(goods.goods_status==true){
                         //获取状态，并判断
                         content+="<td class='goods_status' style='width:40px;'> <i class='layui-icon' id='goodsStatus' style='font-size: 22px; color: green;'>&#x1005;</i></td>"
                     }else{
                    	 content+="<td class='goods_status' style='width:40px;'> <i class='layui-icon' id='goodsStatus' style='font-size: 22px; color: red;'>&#x1007;</i></td>"
                     }        
                         content+="<td ><i class='layui-icon layui-btn layui-btn-primary layui-btn-sm' style='font-size: 22px;' onclick='changeStatus("+goods.goods_id+","+goods.goods_status+")'>&#xe642;</i>  " +
                         		"<i class='layui-icon layui-btn layui-btn-primary layui-btn-sm' style='font-size: 22px;' onclick='updateBut("+JSON.stringify(goods)+")'>&#xe716;</i>  </td>"
                       
	    	});
	    $("#showAll").html(content);//获取状态，并判断       
	}








//修改按钮
function updateBut(goods){
    layui.use('layer', function() {
        var layer = layui.layer;
           console.log(goods)
        //iframe层-父子操作
        layer.open({
            type: 2,
            area: ['70%', '70%'],
            fixed: false, //不固定
            maxmin: true,
            content: 'goods_update.html',
            success:function (layero,index) {     
            	  var body = layer.getChildFrame('body', index);
                  var iframeWin = window[layero.find('iframe')[0]['name']]; 
                  body.find('#goods_name').val(goods.goods_name)
                  body.find('#goods_num').val(goods.goods_num)
                  body.find('#goods_price').val(goods.goods_price)
                  body.find('#goods_desc').val(goods.goods_desc)
                  iframeWin.updateFunction(goods);
        }
        });
    });
    
}
//改变状态
function changeStatus(id,status){
	 $.get("http://localhost:8080/changeStatus.do",{"id":id,"status":status});   
	if(status){
	    $("#goodsStatus").css("color","red");
	    $("#goodsStatus").html("&#x1007;");
	    status=false;
	    findGoods();
	}else{
		$("#goodsStatus").css("color","green");
	    $("#goodsStatus").html("&#x1005;");
	    status=true;
	    findGoods();
    }
       
}
//解决框架的问题"overflow","hidden"
var setCBodyOverflowINDEX = 0; 
function setCBodyOverflow(layero, index, layer){
    console.log(index)
}

