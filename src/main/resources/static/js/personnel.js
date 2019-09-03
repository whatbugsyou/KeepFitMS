//部门
var dept_vm = new Vue({
    el: '#deptManagement',
    data: {
        deptList: [{
            dept_id: 0,
            dept_name: '教练部',
            dept_numOfMembers: 100,
        }, ],
        addingDept: {
            dept_name: '教练部'
        },
        modifingDept: {
            dept_id: 0,
            dept_name: '',
            dept_numOfMembers: 100,
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
        modifyDept: function(index) {
            $.ajax({
                type: "post",
                url: "modifyDept.do",
                data: this.modifingDept,
                dataType: "dataType",
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
                dataType: "dataType",
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
        getAllDept: function() {
            $.ajax({
                type: "post",
                url: "getAllDept.do",
                data: '',
                dataType: "dataType",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('获取部门数据成功');
                        this.deptList = response[allDeptData];
                    } else {
                        alert('获取失败');
                    }
                }
            });
        }
    },

})

//员工
var emp_vm = new Vue({
    el: '#empManagement',
    data: {
        empList: [{
            emp_id: 0,
            emp_name: '张三',
            job_name: '瑜伽教练',
            emp_hiredate: '',
            dept_name: '教练部',
            emp_phone: '131-0020-2000'
        }, {
            emp_id: 0,
            emp_name: '李四',
            job_name: '推销员',
            emp_hiredate: '',
            dept_name: '市场部',
            emp_phone: '13100202000'
        }, {
            emp_id: 0,
            emp_name: '王五',
            job_name: '前台文员',
            emp_hiredate: '',
            dept_name: '人事部',
            emp_phone: '13100202000'
        }],
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
        modifyEmp: function(index) {
            $.ajax({
                type: "post",
                url: "modifyEmp.do",
                data: this.modifingEmp,
                dataType: "dataType",
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
                dataType: "dataType",
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
                type: "post",
                url: "getAllEmp.do",
                data: '',
                dataType: "dataType",
                success: (response) => {
                    if (response["code"] == 'ok') {
                        alert('获取部门数据成功');
                        this.EmpList = response[allEmpData];
                    } else {
                        alert('获取失败');
                    }
                }
            });
        }
    },
})