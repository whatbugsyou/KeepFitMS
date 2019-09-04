var vue = new Vue({
    el: '#wardrobeHtml',
    data: {
        wardrobeList_all: [{ wardrobe_id: 1, wardrobe_name: 'NO01', wardrobe_status: 0 },
        { wardrobe_id: 2, wardrobe_name: 'NO02', wardrobe_status: 1 },
        { wardrobe_id: 3, wardrobe_name: 'NO03', wardrobe_status: 0 },
        { wardrobe_id: 4, wardrobe_name: 'NO11', wardrobe_status: 2 },
        { wardrobe_id: 5, wardrobe_name: 'NO21', wardrobe_status: 0 },
        { wardrobe_id: 6, wardrobe_name: 'NO31', wardrobe_status: 1 }],//所有的衣柜信息
        memberList: [{ cid: "001", mname: "梁伟国" }, { cid: "012", mname: "曾胜哲" }, { cid: "123", mname: "黄真亮" }],//会员信息
        WRList_all: [{ wr_id: 1, cid: "001", mname: "梁伟国", wardrobe_name: "NO02", wr_regtime: "", wr_status: "租用中", wr_starttime: "", wr_endtime: "", wr_deposit: "10元", wr_desc: "" },
        { wr_id: 2, cid: "012", mname: "曾胜哲", wardrobe_name: "NO31", wr_regtime: "", wr_status: "租用中", wr_starttime: "", wr_endtime: "", wr_deposit: "10元", wr_desc: "" },
        { wr_id: 3, cid: "123", mname: "黄真亮", wardrobe_name: "NO31", wr_regtime: "", wr_status: "已完成", wr_starttime: "", wr_endtime: "", wr_deposit: "10元", wr_desc: "" },
        { wr_id: 4, cid: "001", mname: "梁伟国", wardrobe_name: "NO11", wr_regtime: "", wr_status: "已完成", wr_starttime: "", wr_endtime: "", wr_deposit: "10元", wr_desc: "" }],//衣柜出租记录信息
        wardrobeList_free: [],//空闲中的柜子集合（衣柜信息模块的）
        wardrobeList_rented: [],//租借中的柜子集合（衣柜信息模块的）
        wardrobeList_broken: [],//故障维修中的柜子集合（衣柜信息模块的）
        selectedWardrobeL: "",//搜索框的衣柜编号（衣柜信息模块的）
        showInfoModalW: [],//显示衣柜状态信息的模块，需要显示什么就给他赋对应的值。（衣柜信息模块的）
        WRList_rented: [],//租借中的租借记录集合（出租记录信息模块的）
        WRList_complete: [],//已完成的租借记录集合（出租记录信息模块的）
        selectedWardrobeR: "",//搜索框的衣柜编号（出租记录信息模块的）
        showInfoModalWR: [],//显示衣柜出租记录信息的模块，需要显示什么就给他赋对应的值。（出租记录信息模块的）
        operaMInfo: { status: "", rentedCid:"",rentedMname:"",defaultCid: "",defaultMname: "",defaultDeposit: "10元",defaultDesc: ""},//点击操作时,根据衣柜状态不同显示不同的模态框
        // status：柜子状态
        // defaultCid: 默认会员卡号
        // defaultMname: 默认会员名
        // defaultDeposit: 押金默认为10元
        // defaultDesc: 默认备注
        // rentedCid: 当前租借会员卡号
        // rentedMname:当前租借会员姓名

    },
    methods: {
        // findAllWardrobe: function () {
        //     var _this = this;
        //     axios.get("/WardrobeController/findAll.do").then(function (response) {
        //         _this.wardrobeList_all = response;
        //         console.log(_this.wardrobeList_all);
        //     }).catch(function (err) {
        //         console.log(err);
        //     });
        // },
        //获取到所有的衣柜信息后，顺便按照状态值进行分类
        findAllWardrobe: function () {
            var _this = this;
            //每发起一次查看所有衣柜的请求，需要将以下几个对象数组清空，然后再赋值
            _this.showInfoModalW = [];
            _this.wardrobeList_free = [];
            _this.wardrobeList_rented = [];
            _this.wardrobeList_broken = [];
            _this.showInfoModalW = _this.wardrobeList_all;
            $.each(_this.wardrobeList_all, function (index, data) {
                //将衣柜按照空闲、租借中、故障维修分类
                var flag = data.wardrobe_status;
                if (flag == 0) {
                    _this.wardrobeList_free.push(data);
                } else if (flag == 1) {
                    _this.wardrobeList_rented.push(data);
                } else {
                    _this.wardrobeList_broken.push(data);
                }
            });
        },
        //获取到所有的衣柜出租记录信息后，顺便按照状态值进行分类
        findAllWR: function(){
            var _this = this;
            //每发起一次查看所有衣柜出租记录的请求，需要将以下几个对象数组清空，然后再赋值
            _this.showInfoModalWR = [];
            _this.WRList_rented = [];
            _this.WRList_complete = [];
            _this.showInfoModalWR = _this.WRList_all;
            $.each(_this.WRList_all, function (index, data) {
                //将衣柜按照空闲、租借中、故障维修分类
                var flag = data.wr_status;
                if (flag == "租用中") {
                    _this.WRList_rented.push(data);
                } else if (flag == "已完成") {
                    _this.WRList_complete.push(data);
                }
            }); 
        },
        //搜索单个衣柜，不传入后台，直接提示error
        searchSingleW: function () {
            console.log(this.selectedWardrobeL);
            this.showInfoModalW = [];
        },
        //输入衣柜名，搜索出租记录
        searchWR: function(){
            console.log("当前的搜索的衣柜名："+this.selectedWardrobeR);
        },
        //显示衣柜的背景时，根据状态不同赋予不同的背景
        addBg: function (bgFlag) {
            var arr = ['bg-success', 'bg-secondary', 'bg-danger'];
            return arr[bgFlag];
        },
        //显示衣柜记录的背景，已完成的为暗颜色
        showWRBg: function(WR){
            if(WR.wr_status == "已完成"){
                return "text-muted";
            }
            if(WR.wr_status == "租用中"){
                return "text-info";
            }
        },
        //点击操作时，根据点击的衣柜不同，模态框展示不同信息(空闲、在租、故障)
        operateMadal: function (wardrobe) {
            this.operaMInfo.status = wardrobe.wardrobe_status;
            console.log("点击的衣柜名："+wardrobe.wardrobe_name);
            var clickName = wardrobe.wardrobe_name;
            var _this = this;
            $.each(_this.WRList_all, function (index, data) {
                //将衣柜按照空闲、租借中、故障维修分类
                // var flag = data.wardrobe_name;
                if (clickName == data.wardrobe_name) {
                    _this.operaMInfo.rentedCid = data.cid;
                    _this.operaMInfo.rentedMname = data.mname;
                }
            });
        },
        //租柜
        rentWardrobe: function () {
            if (!this.operaMInfo.defaultCid) {
                alert("你还没有输入会员卡号");
                return;
            }
            if (!this.operaMInfo.defaultMname) {
                alert("你还没有输入会员姓名");
                return;
            }
        },
        //退柜
        cancelWardrobe: function(){
            alert("退柜成功");
        }
    },
    created() {
        this.findAllWardrobe();
        this.findAllWR();
    }
});

$(function () {

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
    $('#selectWRCategory').change(function (){
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


