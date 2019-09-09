$(function(){
	
	$(window).load(function(){
		cframeInit();
		$(window).resize(function(){
			cframeInit();
		});
	});
	
	
})

//初始化页面
function cframeInit(){
	var height = $(document.body).height();
	var conheight = $('#mainIframe',parent.document).parents(".frameMain").find(".con").height();
	if(height < conheight){
		height = conheight;
	}
	$('#mainIframe',parent.document).css("height",height);
}

//商品图片放大
function imgBig(_this){
    _this.className = "imgBig";
    _this.width = "200";
    _this.height = "200";
}

//商品图片放小
function imgSmall(_this){
    _this.className = "imgSmall";
    _this.width = "20";
    _this.height = "20";
}