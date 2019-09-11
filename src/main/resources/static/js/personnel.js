//部门
var dept_vm = new Vue({
    el: '#deptManagement',
    data: {
        deptList: [{
            dept_id: 1,
            dept_name: '教练部',
            dept_memberNum: 100,
        }, {
            dept_id: 3,
            dept_name: '市场部',
            dept_memberNum: 100,
        }],
        addingDept: {
            dept_id: 0,
            dept_name: '',
            dept_memberNum: 0,
        },
        modifingDept: {
            dept_id: 0,
            dept_name: '',
            dept_memberNum: 100,
        },
        deletingDept: {
            index: -1
        },
        pageInfo: {
            pageItemNum: 2, //一页显示数目
            pageNum: 1, //页数
            currentPageNum: 1, //当前页
        }
    },
    computed: {
        pageItemStartIndex: function() {
            var start = (this.pageInfo.currentPageNum - 1) * this.pageInfo.pageItemNum;
            return start;
        },
        pageItemEndIndex: function() {
            var end = 0;
            if (this.pageInfo.pageNum == this.pageInfo.currentPageNum) {
                if (this.deptList.length % this.pageInfo.pageItemNum != 0) {
                    end = this.pageItemStartIndex + this.deptList.length % this.pageInfo.pageItemNum;
                } else {
                    end = this.pageItemStartIndex + this.pageInfo.pageItemNum;
                }

            } else {
                end = this.pageInfo.currentPageNum * this.pageInfo.pageItemNum;
            }
            return end;
        },
    },
    methods: {
        nextPage: function() {
            if (this.pageInfo.currentPageNum != this.pageInfo.pageNum) {
                var prePageId = "#dept_page" + this.pageInfo.currentPageNum;
                $(prePageId).removeClass("active")
                this.pageInfo.currentPageNum = this.pageInfo.currentPageNum + 1;
                var nowPageId = "#dept_page" + this.pageInfo.currentPageNum;
                $(nowPageId).addClass("active")

            }
        },
        previousPage: function() {
            if (this.pageInfo.currentPageNum > 1) {
                var prePageId = "#dept_page" + this.pageInfo.currentPageNum;
                $(prePageId).removeClass("active")
                this.pageInfo.currentPageNum = this.pageInfo.currentPageNum - 1;
                var nowPageId = "#dept_page" + this.pageInfo.currentPageNum;
                $(nowPageId).addClass("active")
            }
        },
        showPage: function(index) {
            var prePageId = "#dept_page" + this.pageInfo.currentPageNum;
            $(prePageId).removeClass("active")
            this.pageInfo.currentPageNum = index;
            var nowPageId = "#dept_page" + index;
            $(nowPageId).addClass("active")

        },
        deleteDept: function(index) {
            var dept_id = this.deptList[index].dept_id;
            $.ajax({
                url: "deleteDept.do",
                data: {
                    'dept_id': dept_id
                },
                type: "POST",
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('删除成功');
                        this.deptList.splice(index, 1);
                        this.initPageInfo();
                    } else {
                        alert(response["msg"]);
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });

        },
        modifyDept: function() {
            $.ajax({
                type: "post",
                url: "modifyDept.do",
                data: this.modifingDept,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('修改成功');
                        for (dept of this.deptList) {
                            //有待考虑网速慢的过程中，请求还未返回又修改了输入框内容。则页面静态显示的数据会是修改后的数据，与数据库不一致。
                            if (dept.dept_id == this.modifingDept.dept_id) {
                                for (var key in dept) {
                                    dept[key] = this.modifingDept[key];
                                }
                                break;
                            }
                        }
                    } else {
                        alert(response["msg"]);
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        addDept: function() {
            $.ajax({
                type: "post",
                url: "addDept.do",
                data: this.addingDept,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('添加成功');
                        this.deptList.push(response["newDept"]);
                        this.initPageInfo();
                    } else {
                        alert(response["msg"]);
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
            this.initPageInfo();
        },
        getAllDept: function() {
            $.ajax({
                type: "get",
                url: "getAllDept.do",
                data: '',
                dataType: "json",
                async: false,
                success: (response) => {
                    if (response["code"] == 'ok') {
                        console.log('获取部门数据成功');
                        this.deptList = response["allDeptData"];
                        this.initPageInfo();
                    } else {
                        alert('获取失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        initPageInfo: function() {
            this.pageInfo.pageNum = Math.ceil(this.deptList.length / this.pageInfo.pageItemNum);
        },
    },
    created() {
        this.getAllDept();
        this.showPage(1);
    },

})

//职位
var job_vm = new Vue({
    el: '#jobManagement',
    data: {
        jobList: [{
            job_id: 1,
            job_name: '瑜伽教练',
            job_sal: 0,
            dept: {
                "dept_id": 0,
                "dept_name": "",
                "dept_memberNum": 0
            }
        }, {
            job_id: 3,
            job_name: '前台文员',
            job_sal: 0,
            dept: {
                "dept_id": 0,
                "dept_name": "",
                "dept_memberNum": 0
            }
        }],
        addingJob: {
            job_id: 1,
            job_name: '瑜伽教练',
            job_sal: 0,
            dept: {
                "dept_id": 0,
                "dept_name": "",
                "dept_memberNum": 0
            }
        },
        modifingJob: {
            job_id: 0,
            job_name: '',
            job_sal: 0,
            dept: {
                "dept_id": 0,
                "dept_name": "",
                "dept_memberNum": 0
            }
        },
        deletingJob: {
            index: -1
        }
    },
    methods: {
        deleteJob: function(index) {
            var job_id = this.jobList[index].job_id;
            $.ajax({
                url: "deleteJob.do",
                data: {
                    'job_id': job_id
                },
                type: "POST",
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('删除成功');
                        this.userList.splice(index, 1);
                    } else {
                        alert('删除失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            })
        },
        modifyJob: function() {
            $.ajax({
                type: "post",
                url: "modifyJob.do",
                data: this.modifingJob,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('修改成功');
                        for (job in this.jobList) {
                            //有待考虑网速慢的过程中，请求还未返回又修改了输入框内容。则页面静态显示的数据会是修改后的数据，与数据库不一致。
                            if (job.job_id == this.modifingJob.job_id) {
                                job = modifingJob;
                            }
                        }
                    } else {
                        alert('修改失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        addJob: function() {
            $.ajax({
                type: "post",
                url: "addJob.do",
                data: this.addingJob,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('添加成功');
                        this.jobList.push(response["newJob"]);
                    } else {
                        alert(response["msg"]);
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        getAllJob: function() {
            $.ajax({
                type: "get",
                url: "getAllJob.do",
                data: '',
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        console.log('获取职位数据成功');
                        this.jobList = response["allJobData"];
                    } else {
                        alert('获取失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        }
    },
    created() {
        this.getAllJob();
    },

})

//员工
var emp_vm = new Vue({
    el: '#empManagement',
    data: {
        empList: [{
                "emp_id": 1,
                "emp_name": "张三",
                "job": {
                    "job_id": 1,
                    "job_name": "普通教练",
                    "job_sal": 4000,
                    "dept": {}
                },
                "emp_hiredate": "2019-09-01",
                "emp_phone": "131-1234-1234",
                "dept": {
                    "dept_id": 1,
                    "dept_name": "教练部",
                    "dept_memberNum": 2
                }
            },
            {
                "emp_id": 2,
                "emp_name": "张女",
                "job": {
                    "job_id": 2,
                    "job_name": "瑜伽教练",
                    "job_sal": 4000,
                    "dept": {}
                },
                "emp_hiredate": "2019-09-01",
                "emp_phone": "131-1234-4567",
                "dept": {
                    "dept_id": 1,
                    "dept_name": "教练部",
                    "dept_memberNum": 2
                }
            },
            {
                "emp_id": 3,
                "emp_name": "李四",
                "job": {
                    "job_id": 3,
                    "job_name": "前台文员",
                    "job_sal": 4000,
                    "dept": {}
                },
                "emp_hiredate": "2019-09-02",
                "emp_phone": "131-9987-4567",
                "dept": {
                    "dept_id": 2,
                    "dept_name": "人事部",
                    "dept_memberNum": 2
                }
            }
        ],
        addingEmp: {
            "emp_id": 0,
            "emp_name": "",
            "job": {
                "job_id": 0,
                "job_name": "",
                "job_sal": 0,
                "dept": null
            },
            "emp_hiredate": "",
            "emp_phone": "",
            "dept": {
                "dept_id": 2,
                "dept_name": "",
                "dept_memberNum": 0
            }
        },
        modifingEmp: {
            "emp_id": 0,
            "emp_name": "",
            "emp_hiredate": "",
            "emp_phone": "",
            "dept": {
                "dept_id": 2,
                "dept_name": "",
                "dept_memberNum": 0
            },
            "job": {
                "job_id": 0,
                "job_name": "",
                "job_sal": 0,
                "dept": {}
            }
        },
        deletingEmp: {
            index: -1
        },
        pageInfo: {
            pageItemNum: 2, //一页显示数目
            pageNum: 1, //页数
            currentPageNum: 1, //当前页
        }
    },
    computed: {
        pageItemStartIndex: function() {
            var start = (this.pageInfo.currentPageNum - 1) * this.pageInfo.pageItemNum;
            return start;
        },
        pageItemEndIndex: function() {
            var end = 0;
            if (this.pageInfo.pageNum == this.pageInfo.currentPageNum) {
                if (this.empList.length % this.pageInfo.pageItemNum != 0) {
                    end = this.pageItemStartIndex + this.empList.length % this.pageInfo.pageItemNum;
                } else {
                    end = this.pageItemStartIndex + this.pageInfo.pageItemNum;
                }

            } else {
                end = this.pageInfo.currentPageNum * this.pageInfo.pageItemNum;
            }
            return end;
        },
    },
    methods: {
        initPageInfo: function() {
            this.pageInfo.pageNum = Math.ceil(this.empList.length / this.pageInfo.pageItemNum);
        },
        nextPage: function() {
            if (this.pageInfo.currentPageNum != this.pageInfo.pageNum) {
                var prePageId = "#emp_page" + this.pageInfo.currentPageNum;
                $(prePageId).removeClass("active")
                this.pageInfo.currentPageNum = this.pageInfo.currentPageNum + 1;
                var nowPageId = "#emp_page" + this.pageInfo.currentPageNum;
                $(nowPageId).addClass("active")

            }
        },
        previousPage: function() {
            if (this.pageInfo.currentPageNum > 1) {
                var prePageId = "#emp_page" + this.pageInfo.currentPageNum;
                $(prePageId).removeClass("active")
                this.pageInfo.currentPageNum = this.pageInfo.currentPageNum - 1;
                var nowPageId = "#emp_page" + this.pageInfo.currentPageNum;
                $(nowPageId).addClass("active")
            }
        },
        showPage: function(index) {
            var prePageId = "#emp_page" + this.pageInfo.currentPageNum;
            $(prePageId).removeClass("active")
            this.pageInfo.currentPageNum = index;
            var nowPageId = "#emp_page" + index;
            $(nowPageId).addClass("active")
        },
        deleteEmp: function(index) {
            var emp_id = this.empList[index].emp_id;
            $.ajax({
                url: "deleteEmp.do",
                data: {
                    'emp_id': emp_id
                },
                type: "POST",
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('删除成功');
                        this.empList.splice(index, 1);
                        this.initPageInfo();
                    } else {
                        alert('删除失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            })
        },
        modifyEmp: function() {
            $.ajax({
                type: "post",
                url: "modifyEmp.do",
                data: JSON.stringify(this.modifingEmp),
                contentType: "application/json",
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('修改成功');
                        for (emp of this.empList) {
                            if (emp.emp_id == this.modifingEmp.emp_id) {
                                for (var key in emp) {
                                    emp[key] = this.modifingEmp[key];
                                }
                                break;
                            }
                        }
                    } else {
                        alert('修改失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        addEmp: function() {
            $.ajax({
                type: "post",
                url: "addEmp.do",
                data: JSON.stringify(this.addingEmp),
                contentType: "application/json",
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('添加成功');
                        this.empList.push(response["newEmp"]);
                        this.initPageInfo();
                    } else {
                        alert(response["msg"]);
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        getAllEmp: function() {
            $.ajax({
                type: "get",
                url: "getAllEmp.do",
                data: '',
                dataType: "json",
                asysn: false,
                success: (response) => {
                    if (response["code"] == 'ok') {
                        console.log('获取员工数据成功');
                        this.empList = response["allEmpData"];
                        this.initPageInfo();
                    } else {
                        alert('获取员工数据失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        },
        getDeptListRef: function() {
            return dept_vm.deptList;
        },
        getJobListRef: function() {
            return job_vm.jobList;
        }
    },
    created() {
        this.getAllEmp();
        this.showPage(1);
    },
})

var attendance_vm = new Vue({
    el: '#attendanceManagement',
    data: {
        attendanceData: {

        },
        resultList: []
    },
    methods: {
        initTypeahead: function() {
            for (emp of emp_vm.empList) {
                var item = { id: emp.emp_id, name: emp.emp_name };
                this.resultList.push(item);
            }
            $(".typeahead").typeahead({
                source: this.resultList
            })
        }
    },
    created() {
        this.initTypeahead();
    }
})

// 绘制考勤表