var vue = new Vue({
    el: '#wardrobeHtml',
    data: {
        wardrobeList_all: [],//所有的衣柜信息
        memberList: [],//会员信息
        wardrobeList_free: [],//空闲中的柜子集合（衣柜信息模块的）
        wardrobeList_rented: [],//租借中的柜子集合（衣柜信息模块的）
        wardrobeList_broken: [],//故障维修中的柜子集合（衣柜信息模块的）
        selectedWardrobeL: "",//搜索框的衣柜编号（衣柜信息模块的）
        showInfoModalW: [],//显示衣柜状态信息的模块，需要显示什么就给他赋对应的值。（衣柜信息模块的）
        WRList_all: [],//衣柜出租记录信息
        WRList_rented: [],//租借中的租借记录集合（出租记录信息模块的）
        WRList_complete: [],//已完成的租借记录集合（出租记录信息模块的）
        selectedWardrobeR: "",//搜索框的衣柜编号（出租记录信息模块的）
        showInfoModalWR: [],//显示衣柜出租记录信息的模块，需要显示什么就给他赋对应的值。（出租记录信息模块的）
        operaMInfo: { wname:"",status: "", wrId:"", rentedCid: "", rentedMname: "", defaultCid: "", defaultMname: "", defaultDeposit: 10, defaultDesc: "" },//点击操作时,根据衣柜状态不同显示不同的模态框
        // wname：当前衣柜名称(传入后台的参数用)
        // status：柜子状态(用在html中，作用在于展示不同操作框)
        // wrId: 出租记录id(对应租借中的操作框)
        // rentedCid: 当前租借会员卡号(对应租借中的操作框)
        // rentedMname:当前租借会员姓名(对应租借中的操作框)
        // defaultCid: 默认会员卡号(对应空闲的操作框)
        // defaultMname: 默认会员名(对应空闲的操作框)
        // defaultDeposit: 押金默认为10元(对应空闲的操作框)
        // defaultDesc: 默认备注(对应空闲的操作框)
        wrRegTime: {start:"",end:""},//出租记录的开始和结束时间
    },
    methods: {
        //获取所有的衣柜信息，顺便按照状态值进行分类
        findAllWardrobe: function () {
            var _this = this;
            //每发起一次查看所有衣柜的请求，需要将以下几个对象数组清空，然后再赋值
            _this.showInfoModalW = [];
            _this.wardrobeList_free = [];
            _this.wardrobeList_rented = [];
            _this.wardrobeList_broken = [];
            axios.get("/wardrobe/getAllW.do").then(function (response) {
                _this.wardrobeList_all = response.data;
                _this.showInfoModalW = _this.wardrobeList_all;
                $(_this.wardrobeList_all).each(function () {
                    //将衣柜按照空闲、租借中、故障维修分类
                    var flag = this.wardrobe_status;
                    if (flag == 0) {
                        _this.wardrobeList_free.push(this);
                    } else if (flag == 1) {
                        _this.wardrobeList_rented.push(this);
                    } else {
                        _this.wardrobeList_broken.push(this);
                    }
                });
            }).catch(function (err) {
                console.log(err);
            });
        },
        //获取所有的衣柜信息，顺便按照状态值进行分类
        findAllMember: function () {
            var _this = this;
            //每发起一次查看所有会员的请求，需要将会员的对象数组清空，然后再赋值
            _this.memberList = [];
            axios.get("/wardrobe/getAllM.do").then(function (response) {
                mlist = response.data;
                $.each(mlist, function (index, data) {
                    //将会员名与会员卡号写入对象数组
                    var singleM = {cid:"",mname:""};
                    singleM.cid = data.card.cid;
                    singleM.mname = data.mname;
                    _this.memberList.push(singleM);
                });
            }).catch(function (err) {
                console.log(err);
            });
        },
        //获取所有的衣柜出租记录信息，顺便按照状态值进行分类
        findAllWardrobeRecords: function () {
            var _this = this;
            //每发起一次查看所有衣柜出租记录的请求，需要将以下几个对象数组清空，然后再赋值
            _this.showInfoModalWR = [];
            _this.WRList_rented = [];
            _this.WRList_complete = [];
            axios.get("/wardrobe/getAllWR.do").then(function (response) {
                _this.WRList_all = response.data;
                _this.showInfoModalWR = _this.WRList_all;
                $.each(_this.WRList_all, function (index, data) {
                    //将衣柜出租记录按照租借中、已完成分类
                    var flag = data.wr_status;
                    if (flag == "租用中") {
                        _this.WRList_rented.push(data);
                    } else if (flag == "已完成") {
                        _this.WRList_complete.push(data);
                    }
                });
            }).catch(function (err) {
                console.log(err);
            });
        },
        //显示衣柜的背景时，根据状态不同赋予不同的背景
        addBg: function (bgFlag) {
            var arr = ['bg-success', 'bg-secondary', 'bg-danger'];
            return arr[bgFlag];
        },
        //显示衣柜记录的背景，已完成的为暗颜色
        showWRBg: function (WR) {
            if (WR.wr_status == "已完成") {
                return "text-muted";
            }
            if (WR.wr_status == "租用中") {
                return "text-info";
            }
        },
        //点击操作时，根据点击的衣柜不同，打开不同的模态框(空闲、在租、故障)
        operateMadal: function (wardrobe) {
            var _this = this;
            _this.operaMInfo.status = wardrobe.wardrobe_status;
            _this.operaMInfo.wname = wardrobe.wardrobe_name;
            var clickName = wardrobe.wardrobe_name;
            console.log("点击的衣柜名："+clickName);
            //如果点开的是在租中的衣柜，还需要记录一些信息
            $(_this.WRList_rented).each(function () {
                if (clickName == this.wardrobe_name) {
                    _this.operaMInfo.wrId = this.wr_id;
                    _this.operaMInfo.rentedCid = this.cid;
                    _this.operaMInfo.rentedMname = this.mname;
                }
            });
        },
        //设置衣柜成故障状态
        setFault: function(status){
            var _this = this;
            var flag=confirm("确定执行此操作吗？");
            if (flag == true){
                console.log("正在执行后台函数");
                axios.get("/wardrobe/setWStatus.do",{
                    params: {
                        wardrobe_name: _this.operaMInfo.wname,
                        wardrobe_status: status,
                    }
                }).then(function (response) {
                    console.log("返回值为"+response.data);
                    if(response.data == "success"){
                        alert('设置成功');
                        window.location.reload();
                    }
                }).catch(function (err) {
                    console.log(err);
                });
            }
        },
        //租柜
        rentWardrobe: function () {
            var _this = this;
            if (!this.operaMInfo.defaultCid) {
                alert("你还没有输入会员卡号");
                return;
            }
            if (!this.operaMInfo.defaultMname) {
                alert("你还没有输入会员姓名");
                return;
            }
            axios.get("/wardrobe/rentW.do",{
                params: {
                    cid: _this.operaMInfo.defaultCid,
                    mname: _this.operaMInfo.defaultMname,
                    wardrobe_name: _this.operaMInfo.wname,
                    wr_deposit: _this.operaMInfo.defaultDeposit,
                    wr_desc: _this.operaMInfo.defaultDesc,
                }
            }).then(function (response) {
                if(response.data == "success"){
                    alert('租用成功');
                    window.location.reload();
                } else {
                	alert(response.data);
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        //退柜
        cancelWardrobe: function () {
            var _this = this;
            axios.get("/wardrobe/cancelW.do",{
                params: {
                    //传参：衣柜记录的id
                    wr_id: _this.operaMInfo.wrId,
                }
            }).then(function (response) {
                console.log(response.data);
                if(response.data == "success"){
                    alert('退柜成功');
                    window.location.reload();
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        //出租记录栏的退柜按钮
        cancelWardrobe2: function(wr_id){
            this.operaMInfo.wrId = wr_id;
            this.cancelWardrobe();
        }
    },
    created() {
        this.findAllWardrobe();
        this.findAllMember();
        this.findAllWardrobeRecords();
    }
});

$(function () {

    //不显示“请选择”这个选项
    $('#searchHide1').attr("style","display:none;");
    $('#searchHide2').attr("style","display:none;");


    //选择会员卡号的时候联立会员姓名
    $('#selectCid').change(function () {
        var input_cid = vue.operaMInfo.defaultCid;
        $(vue.memberList).each(function () {
            if (input_cid == this.cid) {
                vue.operaMInfo.defaultMname = this.mname;
            }
        });
    });

    //选择会员姓名的时候联立会员卡号
    $('#selectMname').change(function () {
        var input_mname = vue.operaMInfo.defaultMname;
        $(vue.memberList).each(function () {
            if (input_mname == this.mname) {
                vue.operaMInfo.defaultCid = this.cid;
            }
        });
    });

    //选择不同类别的衣柜
    $('#selectWCategory').change(function () {
        var selected_wc = $(this).val();// 取值：当前选择好的衣柜状态
        console.log("当前选择的衣柜状态类别：" + selected_wc);
        if (selected_wc == 1) {
            vue.showInfoModalW = vue.wardrobeList_all;
        } else if (selected_wc == 2) {
            vue.showInfoModalW = vue.wardrobeList_free;
        } else if (selected_wc == 3) {
            vue.showInfoModalW = vue.wardrobeList_rented;
        } else if (selected_wc == 4) {
            vue.showInfoModalW = vue.wardrobeList_broken;
        } else {
            vue.showInfoModalW = [];
        }
        vue.selectedWardrobeL = ""//清空：搜索框的衣柜编号
    });

    //选择不同类别的衣柜出租记录
    $('#selectWRCategory').change(function () {
        vue.wrRegTime.start = "";//开始时间的状态要变，变成未选择
        vue.wrRegTime.end = "";//结束时间的状态要变，变成未选择
        var selected_WRC = $(this).val();// 取值：当前选择好的衣柜状态
        console.log("当前选择的出租记录类别：" + selected_WRC);
        if (selected_WRC == 1) {
            vue.showInfoModalWR = vue.WRList_all;
        } else if (selected_WRC == 2) {
            vue.showInfoModalWR = vue.WRList_rented;
        } else if (selected_WRC == 3) {
            vue.showInfoModalWR = vue.WRList_complete;
        } else {
            vue.showInfoModalWR = [];
        }
        vue.selectedWardrobeR = ""//清空：搜索框的衣柜编号
    });

    //根据开始时间，筛选WR
    $('#wRDateStart').change(function (){
        var startTimeOfInput = vue.wrRegTime.start;
        var endTimeOfInput = vue.wrRegTime.end;
        var tempWRList = [];
        if(vue.wrRegTime.end != ""){
            $(vue.WRList_all).each(function () {
                var wrRegTime = this.wr_regtime;
                if (startTimeOfInput < wrRegTime && wrRegTime < endTimeOfInput) {
                    console.log("WR集合中满足条件的时间："+this.wr_regtime);
                    tempWRList.push(this);
                }
            });
        } else{
            $(vue.WRList_all).each(function () {
                var wrRegTime = this.wr_regtime;
                if (startTimeOfInput < wrRegTime) {
                    console.log("WR集合中满足条件的时间："+this.wr_regtime);
                    tempWRList.push(this);
                }
            });
        }
        vue.showInfoModalWR = tempWRList;
    });

    //根据结束时间，筛选WR
    $('#wRDateEnd').change(function (){
        var startTimeOfInput = vue.wrRegTime.start;
        var endTimeOfInput = vue.wrRegTime.end;
        var tempWRList = [];
        if(vue.wrRegTime.start != ""){
            $(vue.WRList_all).each(function () {
                var wrRegTime = this.wr_regtime;
                if (startTimeOfInput < wrRegTime && wrRegTime < endTimeOfInput) {
                    console.log("WR集合中满足条件的时间："+this.wr_regtime);
                    tempWRList.push(this);
                }
            });
        } else{
            $(vue.WRList_all).each(function () {
                var wrRegTime = this.wr_regtime;
                if (wrRegTime < endTimeOfInput) {
                    console.log("WR集合中满足条件的时间："+this.wr_regtime);
                    tempWRList.push(this);
                }
            });
        }
        vue.showInfoModalWR = tempWRList;
    });

    //搜索衣柜时自动匹配
    $("#wstext").keyup(function () {
        $("#selectWCategory").val("0");//衣柜的状态选择要变，变成请选择
        vue.showInfoModalW = [];//清空：显示衣柜状态信息的模块
        var input_cont = vue.selectedWardrobeL;
        console.log("当前衣柜编号搜索框输入的是" + input_cont);
        if (input_cont == "") {
            return false;
        }
        $(vue.wardrobeList_all).each(function () {
            var wname = this.wardrobe_name;
            if (wname.indexOf(input_cont) >= 0) {
                vue.showInfoModalW.push(this);
            }
        });
        console.log("查得的数据条数" + vue.showInfoModalW.length);
    });

    //搜索出租记录时自动匹配
    $("#wrstext").keyup(function () {
        $("#selectWRCategory").val("0");//出租记录的状态选择要变，变成请选择
        vue.wrRegTime.start = "";//开始时间的状态要变，变成未选择
        vue.wrRegTime.end = "";//结束时间的状态要变，变成未选择
        vue.showInfoModalWR = [];//清空：显示出租记录信息的模块
        var input_cont = vue.selectedWardrobeR;
        console.log("当前衣柜编号搜索框输入的是" + input_cont);
        if (input_cont == "") {
            return false;
        }
        $(vue.WRList_all).each(function () {
            var wname = this.wardrobe_name;
            if (wname.indexOf(input_cont) >= 0) {
                vue.showInfoModalWR.push(this);
            }
        });
        console.log("查得的数据条数" + vue.showInfoModalWR.length);
    });
});


