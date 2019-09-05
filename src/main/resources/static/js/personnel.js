//部门
var dept_vm = new Vue({
    el: '#deptManagement',
    data: {
        deptList: [{
            dept_id: 0,
            dept_name: '教练部',
            dept_memberNum: 100,
        }, {
            dept_id: 1,
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
        }
    },
    methods: {
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
                        this.userList.splice(index, 1);
                    } else {
                        alert('删除失败');
                    }
                }
            })
        },
        modifyDept: function() {
            $.ajax({
                type: "post",
                url: "modifyDept.do",
                data: this.modifingDept,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('添加成功');
                        this.deptList.push(response["newDept"]);
                    } else {
                        alert('添加失败');
                    }
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
                    } else {
                        alert(response["msg"]);
                    }
                }
            });
        },
        getAllDept: function() {
            $.ajax({
                type: "get",
                url: "getAllDept.do",
                data: '',
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        console.log('获取部门数据成功');
                        this.deptList = response["allDeptData"];
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
        this.getAllDept();
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
            emp_name: '李四',
            job_name: '前台',
            emp_hiredate: '',
            dept_name: '人事部',
            emp_phone: '12312341234'
        },
        modifingEmp: {
            emp_id: -1,
            new_emp_name: '',
        },
        deletingDept: {
            index: -1
        }
    },
    methods: {
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
                        this.userList.splice(index, 1);
                    } else {
                        alert('删除失败');
                    }
                }
            })
        },
        modifyEmp: function() {
            $.ajax({
                type: "post",
                url: "modifyEmp.do",
                data: this.modifingEmp,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('添加成功');
                        this.empList.push(response["newEmp"]);
                    } else {
                        alert('添加失败');
                    }
                }
            });
        },
        addEmp: function() {
            $.ajax({
                type: "post",
                url: "addEmp.do",
                data: this.addingEmp,
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('添加成功');
                        this.empList.push(response["newEmp"]);
                    } else {
                        alert('添加失败');
                    }
                }
            });
        },
        getAllEmp: function() {
            $.ajax({
                type: "get",
                url: "getAllEmp.do",
                data: '',
                dataType: "json",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        console.log('获取员工数据成功');
                        this.empList = response["allEmpData"];
                    } else {
                        alert('获取员工数据失败');
                    }
                },
                error: (response) => {
                    alert('网络请求失败');
                }
            });
        }
    },
})