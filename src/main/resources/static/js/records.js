$(function () {
	findRecords();
	echartNum();
	init();
	$(window).resize(function () {
		init();
	});



})

//初始化页面
function init() {
	var win_h = $(window).height();
	var win_w = $(window).width();

	$(".login_main").height(win_h);
	$(".login").css("margin-top", "-" + $(".login").height() / 2 + "px");
}


//echartsTest
function echartsMoney(id, data, title) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(id));


	// 指定图表的配置项和数据
	var option = {
		//鼠标移入提示框设置
		tooltip: {
			trigger: 'axis'
		},
		xAxis: {
			type: 'category',
			data: title
		},
		yAxis: {
			type: 'value',
			axisLabel: {
				formatter: function (value) {
					return value + '元';
				}
			}
		},
		grid: [{
			show: true,
			backgroundColor: "#FFF",
			left: 50,
			right: 10,
			top: 10,
			bottom: 60
		}],
		series: [{
			data: data,
			type: 'line',
			lineStyle: {
				normal: {
					color: '#FFD205',
					width: 5
				}
			},
			smooth: true
		}]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}

//订单总量
function echartsOrderNum(id, data, title) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(id));


	// 指定图表的配置项和数据
	var option = {
		//鼠标移入提示框设置
		tooltip: {
			trigger: 'axis'
		},
		xAxis: {
			type: 'category',
			data: title
		},
		yAxis: {
			type: 'value'
		},
		grid: [{
			show: true,
			backgroundColor: "#FFF",
			left: 40,
			right: 10,
			top: 10,
			bottom: 60
		}],
		series: [{
			data: data,
			type: 'line',
			lineStyle: {
				normal: {
					color: '#FFD205',
					width: 5
				}
			},
			smooth: true
		}]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}


//图标数据
function echartNum(title, data, data1) {

	/*	var data = [10,20];*/
	/*var title = ['今天', '昨天', '1月15日', '1月16日', '1月17日', '1月18日', '1月19日'];*/
	echartsOrderNum("echartsOrderNum", data, title);

	/*var data1 = [1120000, 15268423, 8729600, 1012500, 13220000, 1012500, 11320000];*/
	/*var title = records.records_time;*/
	echartsMoney("echartsMoney", data1, title);
}
//获取 出售记录
function findRecords() {
	layui.use('laypage', function () {
		var laypage = layui.laypage;
		$.get("http://localhost:8080/findRecords.do", {
			"curr": 1,
			"limit": 5
		},
			function (res) {
				//渲染开始一次页面，查出list总数
				showRecords(res);
				//总页数大于页码总数
				laypage.render({
					elem: 'pages',
					count: res.count,
					limit: 5,
					limits: [5, 10, 15, 20],
					layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
					jump: function (obj) {
						$.get("http://localhost:8080/findRecords.do", {
							"curr": obj.curr,
							"limit": obj.limit
						},
							function (res) {
								//进入回调函数，再次调用渲染页面
								showRecords(res);
								showDataByTime(res);
							})
					}
				});
			}
		);
		$.get("http://localhost:8080/findAllRecords.do",
			function (res) {
				var content = ""
				content += "<div class='layui-col-md1 br'>";
				content += "<div class='layui-col-md6'>";
				content += "<div class='title'><span></span><i class='layui-icon ' style='font-size: 40px; color: black;'>&#xe62a;</i></div>";
				content += "<div class='nums'><font></font><span></span></div>";
				content += "</div>";
				content += "</div>";
				content += "<div class='layui-col-md2 br'>";
				content += "<div class='layui-col-md6'>";
				content += "<div class='title'>销售总量</div>";
				content += "<div class='nums'><font>" + res.allCount + "</font><span>笔</span></div>";
				content += "</div>";
				content += "</div>";
				content += "<div class='layui-col-md3 br'>";
				content += "<div class='layui-col-md6'>";
				content += "<div class='title'>健身器材售量</div>";
				content += "<div class='nums'><font>" + res.count1 + "</font><span>笔</span></div>";
				content += "</div>";
				content += "<div class='layui-col-md6'>";
				content += "<div class='title'>运动装备售量</div>";
				content += "<div class='nums'><font>" + res.count2 + "</font><span>笔</span></div>";
				content += "</div>";
				content += "</div>";
				content += "<div class='layui-col-md3 br'>";
				content += "<div class='layui-col-md6'>";
				content += "<div class='title'>健身器材售额</div>";
				content += "<div class='nums'><font>" + res.money1 + "</font><span>元</span></div>";
				content += "</div>";
				content += "<div class='layui-col-md6'>";
				content += "<div class='title'>运动装备销售额</div>";
				content += "<div class='nums'><font>" + res.money2 + "</font><span>元</span></div>";
				content += "</div>";
				content += "</div>";
				content += "<div class='layui-col-md2'>";
				content += "<div class='title'>金额合计</div>";
				var money = res.money1 + res.money2
				content += "<div class='nums'><font style='color: red;font-size:16px;'>" + money + "</font>";
				content += "</div>";
				content += "</div>";
				$("#recordsNum").html(content);

			}
		).error(function() { layer.msg(error+"网络异常请重试")});
	});
}


//记录渲染到页面
function showRecords(res) {
	var content = "";
	$.each(res.records, function (index, records) {

		content += "<tr>";
		content += "<th>" + records.records_time + "</th>";
		content += "<th>" + records.records_uuid + "</th>";
		content += "<th>" + records.cid + "</th>";
		content += "<th>" + records.goods.ptype.ptype_name + "</th>";
		content += "<th>" + records.goods.pctype.pctype_name + "</th>";
		content += "<th>" + records.goods.goods_name + "</th>";
		if (records.start_time != null && records.end_time !== null) {
			var time = diffTime(records.start_time, records.end_time);
			content += "<th>" + time + "小时</th>";
		} else {
			content += "<th></th>";
		}

		content += "<th>" + records.records_money + "</th>";
		content += "</tr>";
	}

	);

	$("#recordsList").html(content);
}

function showDataByTime(res) {
	var title = new Array();
	var data = new Array();
	var data1 = new Array();
	var time1 = "";
	$.each(res.records, function (index, records) {
		var date = Time(records.records_time);
		title.push(date);
		title = unique(title);

	});
	for (var i = 0; i < title.length; i++) {
		time1 = title[i];
		$.get("http://localhost:8080/selectCountByTime.do", {
			"records_time": time1
		},
			function (dat) {
				$.each(dat.count, function (index, count) {
					data.push(count);
				});
				$.each(dat.money, function (index, money) {
					data1.push(money);
				});

				echartNum(title, data, data1);
			}).error(function() { layer.msg(error+"网络异常请重试")});
	}
}

//求时间差工具
function diffTime(startTime, endTime) {
	//如果时间格式是正确的，那下面这一步转化时间格式就可以不用了
	var dateBegin = new Date(startTime.replace(/-/g, "/")); //将-转化为/，使用new Date，获取结束时间
	var dateEnd = new Date(endTime.replace(/-/g, "/")); //获取开始时间
	var dateDiff = dateEnd.getTime() - dateBegin.getTime(); //时间差的毫秒数
	var dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000)); //计算出相差天数
	var leave1 = dateDiff % (24 * 3600 * 1000) //计算天数后剩余的毫秒数
	var hours = Math.floor(leave1 / (3600 * 1000)) //计算出小时数
	var time = (dayDiff * 24 + hours)
	return time;
}

//时间转化格式工具
function Time(time) {
	//如果时间格式是正确的，那下面这一步转化时间格式就可以不用了
	var date = new Date(Date.parse(time.replace(/-/g, "/"))); //将-转化为/，使用new Date，获取时间;
	var month = date.getMonth() + 1;
	if (month < 10) {
		month = "0" + month
	}
	var day = date.getDate();
	if (day < 10) {
		day = "0" + day
	}
	var year = date.getFullYear();
	var timeFormat = year + "-" + month + "-" + day;
	return timeFormat
}


//数组去重工具
function unique(array) {
	var n = [array[0]]; //结果数组
	//从第二项开始遍历
	for (var i = 1; i < array.length; i++) {
		//如果当前数组的第i项在当前数组中第一次出现的位置不是i，
		//那么表示第i项是重复的，忽略掉。否则存入结果数组
		if (array.indexOf(array[i]) == i) n.push(array[i]);
	}
	return n;
}