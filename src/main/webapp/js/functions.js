function selectAll() {
    document.getElementById("addEmp").style.display = '';
    document.getElementById("addDept").style.display = 'none';
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllEmpServlet"
    }).then(function (response) {
        let emps = response.data;
        let tableData = "<tr align='center'>\n" +
            "<th>员工编号</th>\n" +
            "<th>姓名</th>\n" +
            "<th>性别</th>\n" +
            "<th>年龄</th>\n" +
            "<th>电话</th>\n" +
            "<th>邮箱</th>\n" +
            "<th>地址</th>\n" +
            "<th>工作岗位</th>\n" +
            "<th>部门编号</th>\n" +
            "<th>操作</th>\n" +
            "</tr>";
        for (let i = 0; i < emps.length; i++) {
            let emp = emps[i];
            if (emp.age === 0) {
                emp.age = "（未设置）";
            }
            if (emp.email == null) {
                emp.email = "（未设置）";
            }
            if (emp.address == null) {
                emp.address = "（未设置）";
            }
            if (emp.job == null) {
                emp.job = "（未设置）";
            }
            tableData += `<tr align='center'>
                <td>` + emp.eid + `</td>
                <td>` + emp.name + `</td>
                <td>` + emp.sex + `</td>
                <td>` + emp.age + `</td>
                <td>` + emp.phone + `</td>
                <td>` + emp.email + `</td>
                <td>` + emp.address + `</td>
                <td>` + emp.job + `</td>
                <td>` + emp.deptno + `</td>
                <td> <button class="layui-btn layui-btn-sm" onclick="change(` + emp.eid + `)"><i class="el-icon-edit"></i></button> <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="del(` + emp.eid + `)" ><i class="el-icon-delete"></i></button> </td>
                </tr>`
        }
        //设置表格数据
        mainContent.innerHTML = "<table  class=\"table table-hover table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
    })
}

function addBtn() {

    document.getElementById("addEmp").style.display = 'none';
    mainContent.innerHTML = '';
    let content = `<h2>添加员工</h2>
         <form action="" method="post">
        <label for="username">员工账号：&nbsp;&nbsp;&nbsp;</label><input name="username" id="username"><p style="display: none;">请输入合法的账号</p><br>
        <label for="newpassword">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;&nbsp;&nbsp;</label><input name="newpassword" id="newpassword"><p style="display: none;">请输入密码</p><br>
        <label for="newpassword2">确认密码：&nbsp;&nbsp;&nbsp;</label><input name="newpassword2" id="newpassword2">
            <p style="display: none;" id="newpsd_err">两次密码不一致</p><br>
        <label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;&nbsp;&nbsp;</label><input name="name" id="name">\n<p style="display: none;">请输入姓名</p><br>
        &nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;&nbsp;&nbsp;
        <label> <input type="radio" name="sex" value="男">男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <label><input type="radio" name="sex" value="女">女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <br>
        <label for="age">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：&nbsp;&nbsp;&nbsp;</label><input name="age" id="age"><br>
        <label for="phone">手机号码：&nbsp;&nbsp;&nbsp;</label><input name="phone" id="phone">\n<p style="display: none;">请输入正确手机号</p><br>
        <label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：&nbsp;&nbsp;&nbsp;</label><input name="email" id="email"><br>
        <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;&nbsp;&nbsp;</label><input name="address" id="address"><br>
        <label for="job">工作岗位：&nbsp;&nbsp;&nbsp;</label><input name="job" id="job"><br>
        <label>部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：&nbsp;&nbsp;
        <select name="deptNo" id="deptNo">

        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><br>
        <input type="button" class="layui-btn" id="btn" value="提交信息"><input type="button" class="layui-btn" id="back" value="返回上一页">
        </form>`;
    mainContent.innerHTML = content;
    selectDeptValue(0);
}

function selectDeptValue(empDeptNo) {

    let deptName = $("#deptNo");
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllDeptServlet"
    }).then(function (response) {
        data=response.data;
        deptName.append("<option selected disabled>请选择</option>")
        for (let i = 0; i <data.length ; i++) {
            if (empDeptNo===data[i].deptNo){
                deptName.append(`<option selected value=` + response.data[i].deptNo + `>` + response.data[i].deptName + `</option>`)
            }else {
                deptName.append(`<option value=` + response.data[i].deptNo + `>` + response.data[i].deptName + `</option>`)
            }
        }

        // deptName.innerHTML = `<option value=0></option>`;
        // for (let i = 0; i < (response.data).length; i++) {
        //     deptName.innerHTML += `<option value=` + response.data[i].deptNo + `>` + response.data[i].deptName + `</option>`;
        // }
    })
}

function addEmp() {
    twopsd();
    document.getElementById("back").onclick = function () {
        selectAll();
        document.getElementById("search").style.display = 'block';
    }
    document.getElementById("btn").onclick = function () {
        //将表单数据转化为json
        var empData = {
            username: "",
            password: "",
            name: "",
            sex: "",
            age: "",
            phone: "",
            email: "",
            address: "",
            job: "",
            deptNo: "",
        }
        //获取表单数据
        let username = document.getElementById("username").value;
        let password = document.getElementById("newpassword").value;
        let name = document.getElementById("name").value;
        let sex = document.getElementsByName("sex");
        for (let i = 0; i < sex.length; i++) {
            if (sex[i].checked) {
                empData.sex = sex[i].value;
            }
        }
        let age = document.getElementById("age").value;
        let phone = document.getElementById("phone").value;
        let email = document.getElementById("email").value;
        let address = document.getElementById("address").value;
        let job = document.getElementById("job").value;
        let deptNo = document.getElementById("deptNo").value;
        if (username === "" && username.trim() === "") {

        }
        //设置数据
        empData.username = username;
        empData.password = password;
        empData.name = name;
        empData.age = age;
        empData.phone = phone;
        empData.email = email;
        empData.address = address;
        empData.job = job;
        empData.deptNo = deptNo;
        //发送ajax请求
        axios({
            method: "post",
            url: "http://localhost:8080/works/works/addEmpServlet",
            data: empData
        }).then(function (resp) {
            console.log(resp.data)
            if (resp.data === "success") {
                alert("添加成功！");
                selectAll();
                document.getElementById("search").style.display = 'block';
            } else if (resp.data === "user") {
                alert("用户名不能为空");
            } else if (resp.data === "name") {
                alert("姓名不能为空！")
            } else if (resp.data === "phone") {
                alert("手机号码应为11位")
            } else if (resp.data === "phone1") {
                alert("手机号码不能为空！")
            } else {
                alert("密码不能为空！")
            }
        })
    }
}

function change(id) {
    document.getElementById("search").style.display = 'none';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectEmpByIdServlet?id=" + id
    }).then(function (resp) {
        let emp = resp.data;
        var eid = emp.eid;
        let empName = emp.name;
        let empSex = emp.sex;
        let empAge = emp.age;
        let empPhone = emp.phone;
        let empEmail = emp.email;
        let empAddress = emp.address;
        let empJob = emp.job;
        let empDeptNo = emp.deptno
        console.log(empDeptNo)
        console.log(typeof empDeptNo)
        mainContent.innerHTML = '';
        let content = `<h2>修改员工信息</h2>
        <form action="" method="post">
        <label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;&nbsp;&nbsp;</label><input name="name" id="name" value="` + empName + `">\n<p style="display: none;">请输入姓名</p><br>
        性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;&nbsp;&nbsp;
        <label> <input type="radio" name="sex" value="男" id="man" >男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <label><input type="radio" name="sex" value="女" id="woman" >女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <br>
        <label for="age">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：&nbsp;&nbsp;&nbsp;</label><input name="age" id="age" value="` + empAge + `"><br>
        <label for="phone">手机号码：&nbsp;&nbsp;&nbsp;</label><input name="phone" id="phone" value="` + empPhone + `">\n<p style="display: none;">请输入正确手机号</p><br>
        <label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：&nbsp;&nbsp;&nbsp;</label><input name="email" id="email" value="` + empEmail + `"><br>
        <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;&nbsp;&nbsp;</label><input name="address" id="address" value="` + empAddress + `"><br>
        <label for="job">工作岗位：&nbsp;&nbsp;&nbsp;</label><input name="job" id="job" value="` + empJob + `"><br>
        <label>部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：&nbsp;&nbsp; 
        <select name="deptNo" id="deptNo">
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </label><br>
        <input type="button" class="layui-btn" id="btn" value="提交信息"><input type="button" class="layui-btn" id="back" value="返回上一页">
        </form>`

        mainContent.innerHTML = content;
        selectDeptValue(empDeptNo);
        if (empSex === "男") {
            document.getElementById("man").checked = true;
        } else {
            document.getElementById("woman").checked = true;
        }
        if (empEmail===undefined){
            $("#email").val(empPhone+"@qq.com")
        }
        if (empAddress===undefined){
            $("#address").val("河南")
        }
        if (empJob===undefined){
            $("#job").val("后端开发")
            empDeptNo=8801;
        }
        document.getElementById("back").onclick = function () {
            selectAll();
            document.getElementById("search").style.display = 'block';
        }
        document.getElementById("btn").onclick = function () {
            //将表单数据转化为json
            var empData = {
                eid: "",
                name: "",
                sex: "",
                age: "",
                phone: "",
                email: "",
                address: "",
                job: "",
                deptNo: "",
            }
            //获取表单数据
            let name = document.getElementById("name").value;
            let sex = document.getElementsByName("sex");
            for (let i = 0; i < sex.length; i++) {
                if (sex[i].checked) {
                    empData.sex = sex[i].value;
                }
            }
            let age = document.getElementById("age").value;
            let phone = document.getElementById("phone").value;
            let email = document.getElementById("email").value;
            let address = document.getElementById("address").value;
            let job = document.getElementById("job").value;
            let deptNo = document.getElementById("deptNo").value;
            //设置数据
            empData.eid = eid;
            empData.name = name;
            empData.age = age;
            empData.phone = phone;
            empData.email = email;
            empData.address = address;
            empData.job = job;
            empData.deptNo = deptNo;
            //发送ajax请求
            axios({
                method: "post",
                url: "http://localhost:8080/works/works/updateEmpServlet?id=" + id,
                data: empData
            }).then(function (resp) {
                if (resp.data === "updateEmpSuccess") {
                    alert("修改信息成功！");
                    selectAll();
                }

            })
        }
    })
    document.getElementById("addEmp").style.display = 'none';
}

function del(id) {
    let a = confirm("是否要永久删除该员工信息？（物理删除）")
    if (a) {
        axios({
            method: "get",
            url: "http://localhost:8080/works/works/deleteServlet?id=" + id
        }).then(function (resp) {
            if (resp.data === "deleteSuccess") {
                alert("删除成功！");
                selectAll();
            }

        })
    }
}

function selectAllDept() {
    document.getElementById("addEmp").style.display = 'none';
    document.getElementById("addDept").style.display = '';
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllDeptServlet"
    }).then(function (response) {
        let depts = response.data;
        let tableData = "<tr align='center'>\n" +
            "<th>部门编号</th>\n" +
            "<th>部门名称</th>\n" +
            "<th>部门介绍</th>\n" +
            "<th>操作</th>\n" +
            "</tr>";
        for (let i = 0; i < depts.length; i++) {
            let dept = depts[i];
            tableData += `<tr align='center'>
                <td>` + dept.deptNo + `</td>
                <td>` + dept.deptName + `</td>
                <td>` + dept.introduction + `</td>
                <td> <button class="layui-btn layui-btn-sm" onclick="changeDept(` + dept.deptNo + `)"><i class="el-icon-edit"></i></button> <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="delDept(` + dept.deptNo + `)" ><i class="el-icon-delete"></i></button> </td>
                </tr>`
        }
        //设置表格数据
        mainContent.innerHTML = "<table  class=\"table table-hover table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
    })
}

function addDept() {
    document.getElementById("addDept").style.display = 'none';
    mainContent.innerHTML = '';
    let content = `<form action="" method="post">
    <label for="deptNo">部门编号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="deptNo" id="deptNo"><br>
    <p style="display: none">请输入合法的部门编号</p>
    <label for="deptName">部门名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="deptName" id="deptName"><br>
    <p style="display: none">请输入部门名称</p>
    <label for="introduction">部门介绍：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="introduction" id="introduction"><br>
    <input type="button" class="layui-btn" id="btn" value="提交信息">
    <input type="button" class="layui-btn" id="back" value="返回上一页">
`;
    mainContent.innerHTML = content;
}

function addDeptBtn() {
    document.getElementById("back").onclick = function () {
        selectAllDept();
    }
    document.getElementById("btn").onclick = function () {
        //将表单数据转化为json
        var deptData = {
            deptNo: "",
            deptName: "",
            introduction: ""
        }
        //获取表单数据
        let deptNo = document.getElementById("deptNo").value;
        let deptName = document.getElementById("deptName").value;
        let introduction = document.getElementById("introduction").value;

        //设置数据
        deptData.deptNo = deptNo;
        deptData.deptName = deptName;
        deptData.introduction = introduction;
        //发送ajax请求
        axios({
            method: "get",
            url: "http://localhost:8080/works/works/deleteDeptAfterAddServlet?deptNo=" + deptNo
        }).then(function (resp) {
            if (resp.data === "deleteDeptAfterAddSuccess") {
                alert("逻辑添加成功！");
                selectAllDept();
            } else {
                axios({
                    method: "post",
                    url: "http://localhost:8080/works/works/addDeptServlet",
                    data: deptData
                }).then(function (resp) {
                    if (resp.data === "addDeptSuccess") {
                        alert("添加成功！");
                        selectAllDept();
                    }
                })
            }
        })
    }
}

function changeDept(deptNo) {
    document.getElementById("addDept").style.display = 'none';
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectDeptByIdServlet?deptNo=" + deptNo
    }).then(function (resp) {
        let dept = resp.data;
        var id = dept.id;
        let deptNo = dept.deptNo;
        let deptNmae = dept.deptName;
        let introduction = dept.introduction;
        let content = `<form action="" method="post">
    <label for="deptNo">部门编号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="deptNo" id="deptNo" value="` + deptNo + `"><br>
    <p style="display: none">请输入合法的部门编号</p>
    <label for="deptName">部门名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="deptName" id="deptName" value="` + deptNmae + `"><br>
    <p style="display: none">请输入部门名称</p>
    <label for="introduction">部门介绍：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="introduction" id="introduction" value="` + introduction + `"><br>
    <input type="button" class="layui-btn" id="btn" value="提交信息">
    <input type="button" class="layui-btn" id="back" value="返回上一页">`
        mainContent.innerHTML = content;
        document.getElementById("back").onclick = function () {
            selectAllDept();
        }
        document.getElementById("btn").onclick = function () {
            //将表单数据转化为json
            var deptData = {
                id: "",
                deptNo: "",
                deptName: "",
                introduction: ""
            }
            //获取表单数据
            let deptId = id;
            let deptNo = document.getElementById("deptNo").value;
            let deptName = document.getElementById("deptName").value;
            let introduction = document.getElementById("introduction").value;

            //设置数据
            deptData.id = deptId;
            deptData.deptNo = deptNo;
            deptData.deptName = deptName;
            deptData.introduction = introduction;
            //发送ajax请求
            axios({
                method: "post",
                url: "http://localhost:8080/works/works/updateDeptServlet?deptNo=" + deptNo,
                data: deptData
            }).then(function (resp) {
                if (resp.data === "updateDeptSuccess") {
                    alert("修改部门信息成功！");
                    selectAllDept();
                }
            })
        }
    })
}

function delDept(deptNo) {
    let a = confirm("是否要删除该部门信息？（逻辑删除）")
    if (a) {
        axios({
            method: "get",
            url: "http://localhost:8080/works/works/deleteDeptServlet?deptNo=" + deptNo
        }).then(function (resp) {
            if (resp.data === "deleteDeptSuccess") {
                alert("删除成功！");
                selectAllDept();
            }

        })
    }
}

function selectAllSal() {
    document.getElementById("addDept").style.display = "none";
    document.getElementById("addEmp").style.display = "none";
    mainContent.innerHTML = "";
    welcome.innerHTML = "";
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllSalServlet"
    }).then(function (response) {
        let sals = response.data;
        let tableData = `<tr align='center'>
            <th>员工姓名</th>
            <th>工作岗位</th>
            <th>薪资</th>
            <th>操作</th>
            </tr>`;
        for (let i = 0; i < sals.length; i++) {
            let sal = sals[i];
            if (sal.salary === 0) {
                sal.salary = "(未设置)";

            }
            tableData += `<tr align='center'>
                <td>` + sal.name + `</td>
                <td>` + sal.job + `</td>
                <td>` + sal.salary + `</td>
                <td> <button class="layui-btn layui-btn-sm" onclick="changeSal(` + sal.eid + `)"><i class="el-icon-edit"></i></button> </td>
                </tr>`
        }
        //设置表格数据
        mainContent.innerHTML = "<table  class=\"table table-hover table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
    })
}

function changeSal(eid) {
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectSalByIdServlet?eid=" + eid
    }).then(function (resp) {
        let sal = resp.data;
        var id = sal.eid;
        let name = sal.name;
        let job = sal.job;
        let salary = sal.salary;
        let content = `<form action="" method="post">
    <label for="name">职工姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="name" id="name" value="` + name + `" disabled><br>
    <label for="job">职工岗位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="job" id="job" value="` + job + `" disabled><br>
    <label for="salary">职工薪资：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="salary" id="salary" value="` + salary + `"><br>
    <input type="button" class="layui-btn" id="btn" value="提交信息">
    <input type="button" class="layui-btn" id="back" value="返回上一页">`
        mainContent.innerHTML = content;
        document.getElementById("back").onclick = function () {
            selectAllSal();
        }
        document.getElementById("btn").onclick = function () {
            //将表单数据转化为json
            var salData = {
                eid: "",
                name: "",
                job: "",
                salary: ""
            }
            //获取表单数据
            let eid = id;
            let name = document.getElementById("name").value;
            let job = document.getElementById("job").value;
            let sal = document.querySelector("#salary").value;

            console.log(sal);
            //设置数据
            salData.eid = eid;
            salData.name = name;
            salData.job = job;
            salData.salary = sal;
            //发送ajax请求
            axios({
                method: "get",
                url: "http://localhost:8080/works/works/selectSalByIdServlet2?eid=" + eid
            }).then(function (resp) {
                if (resp.data === "addSalById") {
                    axios({
                        method: "post",
                        url: "http://localhost:8080/works/works/addSalServlet",
                        data: salData
                    }).then(function (resp) {
                        if (resp.data === "addSalSuccess") {
                            alert("添加并修改薪资成功！");
                            selectAllSal();
                        }
                    })
                } else {
                    axios({
                        method: "post",
                        url: "http://localhost:8080/works/works/updateSalServlet?eid=" + eid,
                        data: salData
                    }).then(function (resp) {
                        if (resp.data === "updateSalSuccess") {
                            alert("修改薪资成功！");
                            selectAllSal();
                        }
                    })
                }
            })
        }
    })
}

function selectAllLeaves() {
    document.getElementById("addDept").style.display = "none";
    document.getElementById("addEmp").style.display = "none";
    mainContent.innerHTML = "";
    welcome.innerHTML = "";
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllLeavesServlet"
    }).then(function (response) {
        let leaves = response.data;
        let tableData = `<tr align='center'>
            <th>员工姓名</th>
            <th>部门名称</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>请假时间</th>
            <th>请假原因</th>
            <th>是否同意</th>
            </tr>`;
        for (let i = 0; i < leaves.length; i++) {
            let leave = leaves[i];
            if (leave.isAgree === 0) {
                leave.isAgree = "待审批";
            } else if (leave.isAgree === 1) {
                leave.isAgree = "已同意";
            } else if (leave.isAgree === 2) {
                leave.isAgree = "未同意"
            }
            tableData += `<tr align='center'>
                <td>` + leave.eName + `</td>
                <td>` + leave.deptName + `</td>
                <td>` + leave.begin + `</td>
                <td>` + leave.end + `</td>
                <td>` + leave.lTime + `</td>
                <td>` + leave.reason + `</td>
                <td>` + leave.isAgree + `</td>
                </tr>`
        }
        //设置表格数据
        mainContent.innerHTML = "<table  class=\"table table-hover table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
    })
}

//负责任人查找员工信息
function mainSelectAll() {
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllEmpServlet"
    }).then(function (response) {
        let emps = response.data;
        let tableData = "<tr align='center'>\n" +
            "<th>员工编号</th>\n" +
            "<th>姓名</th>\n" +
            "<th>性别</th>\n" +
            "<th>年龄</th>\n" +
            "<th>电话</th>\n" +
            "<th>邮箱</th>\n" +
            "<th>地址</th>\n" +
            "<th>工作岗位</th>\n" +
            "<th>部门</th>\n" +
            "</tr>";
        for (let i = 0; i < emps.length; i++) {
            let emp = emps[i];
            if (emp.age === 0) {
                emp.age = "（未设置）";
            }
            if (emp.email == null) {
                emp.email = "（未设置）";
            }
            if (emp.address == null) {
                emp.address = "（未设置）";
            }
            if (emp.job == null) {
                emp.job = "（未设置）";
            }
            if (emp.deptno === 8801) {
                emp.deptno = "后端开发";
            } else if (emp.deptno === 8802) {
                emp.deptno = "前端开发";
            } else if (emp.deptno === 8800) {
                emp.deptno = "BOSS";
            } else {
                emp.deptno = "(未设置)";
            }
            tableData += `<tr align='center'>
                <td>` + emp.eid + `</td>
                <td>` + emp.name + `</td>
                <td>` + emp.sex + `</td>
                <td>` + emp.age + `</td>
                <td>` + emp.phone + `</td>
                <td>` + emp.email + `</td>
                <td>` + emp.address + `</td>
                <td>` + emp.job + `</td>
                <td>` + emp.deptno + `</td>
                </tr>`
        }
        //设置表格数据
        mainContent.innerHTML = "<table  class=\"table table-hover table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
    })
}

//负责人查找本部门员工信息
function mainSelectDeptEmp(deptNo) {
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/mainSelectEmpByDeptNoServlet?deptNo=" + deptNo
    }).then(function (response) {
        let emps = response.data;
        let tableData = "<tr align='center'>\n" +
            "<th>员工编号</th>\n" +
            "<th>姓名</th>\n" +
            "<th>性别</th>\n" +
            "<th>年龄</th>\n" +
            "<th>电话</th>\n" +
            "<th>邮箱</th>\n" +
            "<th>地址</th>\n" +
            "<th>工作岗位</th>\n" +
            "<th>部门</th>\n" +
            "<th>操作</th>\n" +
            "</tr>";
        for (let i = 0; i < emps.length; i++) {
            let emp = emps[i];
            if (emp.age === 0) {
                emp.age = "（未设置）";
            }
            if (emp.email == null) {
                emp.email = "（未设置）";
            }
            if (emp.address == null) {
                emp.address = "（未设置）";
            }
            if (emp.job == null) {
                emp.job = "（未设置）";
            }
            if (emp.deptno === 8801) {
                emp.deptno = "后端开发";
            } else if (emp.deptno === 8802) {
                emp.deptno = "前端开发";
            } else {
                emp.deptno = "BOSS";
            }
            tableData += `<tr align='center'>
                <td>` + emp.eid + `</td>
                <td>` + emp.name + `</td>
                <td>` + emp.sex + `</td>
                <td>` + emp.age + `</td>
                <td>` + emp.phone + `</td>
                <td>` + emp.email + `</td>
                <td>` + emp.address + `</td>
                <td>` + emp.job + `</td>
                <td>` + emp.deptno + `</td>
                <td> <button class="layui-btn layui-btn-sm" onclick="mainChange(` + emp.eid + `)"><i class="el-icon-edit"></i></button></td>
                </tr>`
        }
        //设置表格数据
        mainContent.innerHTML = "<table  class=\"table table-hover  table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
    })
}

//负责人修改本部门人员信息
function mainChange(id) {
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectEmpByIdServlet?id=" + id
    }).then(function (resp) {
        let emp = resp.data;
        var eid = emp.eid;
        let empName = emp.name;
        let empSex = emp.sex;
        let empAge = emp.age;
        let empPhone = emp.phone;
        let empEmail = emp.email;
        let empAddress = emp.address;
        let empJob = emp.job;
        let empDeptNo = emp.deptno
        console.log(empDeptNo);
        mainContent.innerHTML = '';
        let content = `<h2>修改员工信息</h2>
        <form action="" method="post">
        <label for="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;&nbsp;&nbsp;</label><input name="name" id="name" value="` + empName + `">\n<p style="display: none;">请输入姓名</p><br>
        性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：&nbsp;&nbsp;&nbsp;&nbsp;
        <label> <input type="radio" name="sex" value="男" id="man" >男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <label><input type="radio" name="sex" value="女" id="woman" >女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <br>
        <label for="age">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：&nbsp;&nbsp;&nbsp;</label><input name="age" id="age" value="` + empAge + `"><br>
        <label for="phone">手机号码：&nbsp;&nbsp;&nbsp;</label><input name="phone" id="phone" value="` + empPhone + `">\n<p style="display: none;">请输入正确手机号</p><br>
        <label for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：&nbsp;&nbsp;&nbsp;</label><input name="email" id="email" value="` + empEmail + `"><br>
        <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;&nbsp;&nbsp;</label><input name="address" id="address" value="` + empAddress + `"><br>
        <label for="job">工作岗位：&nbsp;&nbsp;&nbsp;</label><input name="job" id="job" value="` + empJob + `"><br>
        <input type="button" class="layui-btn" id="btn" value="提交信息"><input type="button" class="layui-btn" id="back" value="返回上一页">
        </form>`

        mainContent.innerHTML = content;
        if (empSex == "男") {
            document.getElementById("man").checked = true;
        } else {
            document.getElementById("woman").checked = true;
        }
        document.getElementById("back").onclick = function () {
            mainSelectDeptEmp(empDeptNo);
        }
        document.getElementById("btn").onclick = function () {
            //将表单数据转化为json
            var empData = {
                eid: "",
                name: "",
                sex: "",
                age: "",
                phone: "",
                email: "",
                address: "",
                job: "",
                deptNo: "",
            }
            //获取表单数据
            let name = document.getElementById("name").value;
            let sex = document.getElementsByName("sex");
            for (let i = 0; i < sex.length; i++) {
                if (sex[i].checked) {
                    empData.sex = sex[i].value;
                }
            }
            let age = document.getElementById("age").value;
            let phone = document.getElementById("phone").value;
            let email = document.getElementById("email").value;
            let address = document.getElementById("address").value;
            let job = document.getElementById("job").value;
            let deptNo = empDeptNo;
            //设置数据
            empData.eid = eid;
            empData.name = name;
            empData.age = age;
            empData.phone = phone;
            empData.email = email;
            empData.address = address;
            empData.job = job;
            empData.deptNo = deptNo;
            //发送ajax请求
            axios({
                method: "post",
                url: "http://localhost:8080/works/works/updateEmpServlet?id=" + id,
                data: empData
            }).then(function (resp) {
                if (resp.data === "updateEmpSuccess") {
                    alert("修改信息成功！");
                    mainSelectDeptEmp(deptNo);
                }
            })
        }
    })
}

//负责人查询请假信息
function selectLeavesById(eid) {
    mainContent.innerHTML = "";
    welcome.innerHTML = "";
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectLeavesByIdServlet?eid=" + eid
    }).then(function (response) {
        let leaves = response.data;
        let tableData = `<tr align='center'>
            <th>员工姓名</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>请假时间</th>
            <th>请假原因</th>
            <th>是否同意</th>
            </tr>`;
        for (let i = 0; i < leaves.length; i++) {
            let leave = leaves[i];
            if (leave.isAgree === 0) {
                leave.isAgree = "待审批";
                tableData += `<tr align='center'>
                <td>` + leave.eName + `</td>
                <td>` + leave.begin + `</td>
                <td>` + leave.end + `</td>
                <td>` + leave.lTime + `</td>
                <td>` + leave.reason + `</td>
                <td><a id="isAgree" class="layui-btn layui-btn-radius layui-btn-normal" onclick="isAgree(` + leave.id + `,` + eid + `)">同意</a>&nbsp;&nbsp;&nbsp;<a id="isNotAgree" class="layui-btn layui-btn-radius layui-btn-danger" onclick="isNotAgree(` + leave.id + `,` + eid + `)">拒绝</a></td>
                </tr>`
                //设置表格数据
                mainContent.innerHTML = "<table  class=\"table table-hover table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
            } else if (leave.isAgree === 1) {
                leave.isAgree = "已同意";
                tableData += `<tr align='center'>
                <td>` + leave.eName + `</td>
                <td>` + leave.begin + `</td>
                <td>` + leave.end + `</td>
                <td>` + leave.lTime + `</td>
                <td>` + leave.reason + `</td>
                <td>` + leave.isAgree + `</td>
                </tr>`
                mainContent.innerHTML = "<table  class=\"table table-hover  table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
            } else if (leave.isAgree === 2) {
                leave.isAgree = "已拒绝"
                tableData += `<tr align='center'>
                <td>` + leave.eName + `</td>
                <td>` + leave.begin + `</td>
                <td>` + leave.end + `</td>
                <td>` + leave.lTime + `</td>
                <td>` + leave.reason + `</td>
                <td>` + leave.isAgree + `</td>
                </tr>`
                mainContent.innerHTML = "<table  class=\"table table-hover  table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
            }
        }

    })
}

function isAgree(id, eid) {
    let a = confirm("您确定同意该职工的请假审批吗？");
    if (a) {
        axios({
            method: "post",
            url: "http://localhost:8080/works/works/leaveIsAgreeServlet?id=" + id
        }).then(function (resp) {
            if (resp.data === "isAgree") {
                alert("已同意！")
                selectLeavesById(eid);
            }
        })
    }
}

function isNotAgree(id, eid) {
    let b = confirm("是否拒绝该职工的请假审批？");
    if (b) {
        axios({
            method: "post",
            url: "http://localhost:8080/works/works/leaveIsNotAgreeServlet?id=" + id
        }).then(function (resp) {
            if (resp.data === "isNotAgree") {
                alert("已拒绝该员工的请假")
                selectLeavesById(eid);
            }
        })
    }
}

//根据登录账户eid获取员工薪资
function selectSalaryById(eid) {
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectSalaryByIdServlet?eid=" + eid
    }).then(function (resp) {
        let sal = resp.data;
        if (sal == null) {
            mainContent.innerHTML = ` <p>还没有您的薪资信息，请联系您的负责人</p> `;
        } else {
            mainContent.innerHTML = `<p>您的员工编号为&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;` + sal.eid + `</p>
<p>您的薪资为&nbsp;&nbsp;&nbsp;&nbsp;` + sal.salary + `</p>`;
        }
    })
}

//员工请假
function empLeaves(eid) {
    document.getElementById("addLeave").style.display = ""
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/empSelectLeavesByIdServlet?eid=" + eid
    }).then(function (response) {
        let leaves = response.data;
        if (leaves.length === 0) {
            mainContent.innerHTML = ` <p>还没有您的请假信息哦，亲，请继续保持！</p> `;
        } else {
            let tableData = "<tr align='center'>\n" +
                "<th>员工编号</th>\n" +
                "<th>开始时间</th>\n" +
                "<th>结束时间</th>\n" +
                "<th>请假时长</th>\n" +
                "<th>请假原因</th>\n" +
                "<th>是否同意</th>\n" +
                "</tr>";
            for (let i = 0; i < leaves.length; i++) {
                let leave = leaves[i];
                if (leave.isAgree === 0) {
                    leave.isAgree = "未审批"
                } else if (leave.isAgree === 1) {
                    leave.isAgree = "同意"
                } else {
                    leave.isAgree = "拒绝"
                }
                tableData += `<tr align='center'>
                <td>` + leave.eid + `</td>
                <td>` + leave.begin + `</td>
                <td>` + leave.end + `</td>
                <td>` + leave.lTime + `</td>
                <td>` + leave.reason + `</td>
                <td>` + leave.isAgree + `</td>
                </tr>`
            }
            //设置表格数据
            mainContent.innerHTML = "<table  class=\"table table-hover  table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
        }
    })
}

function addLeaves(eid, deptNo) {
    var tid = eid;
    var tDeptNo = deptNo;
    document.getElementById("addLeave").style.display = "none"
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    let content = ` <div class="main">
        <form action="#" method="post">
            <h1>员工请假申请</h1>
            <label for="begin">开始时间：</label>
            <input type="datetime-local" name="begin" id="begin">
            <br>
            <label for="end">结束时间：</label>
            <input type="datetime-local" name="end" id="end"><br>
            请假总时长:<input id="times" disabled>
            <div class="leaveReason">
                <label for="reason" style="top:0">请假原因：</label>
                <input name="reason" id="reason"><br>
            </div>
            <input type="button" class="layui-btn" id="btn" value="提交信息">
            <input type="button" class="layui-btn" id="back" value="返回上一页">
        </form>
    </div>`
    mainContent.innerHTML = content;
    var begin = document.querySelector("#begin");
    var end = document.querySelector("#end");
    var times=document.getElementById("times");
    var timeStamp;
    var timeStamp2;
    begin.onblur = function () {
        timeStamp = new Date(begin.value).getTime();
        if (times.value===""){

        }else if (timeStamp>timeStamp2){
            alert("错误！请假时间错误！")
            begin.value=""
            end.value=""
            document.getElementById("times").value = "";
        }else {
            let leavetimes = ac(timeStamp, timeStamp2);
            document.getElementById("times").value=leavetimes;
        }
    }
    end.onblur = function () {
        timeStamp2 = new Date(end.value).getTime();
        if (timeStamp>timeStamp2){
            alert("错误！请假时间错误！")
            begin.value=""
            end.value=""
            document.getElementById("times").value = "";
        }else {
            let leavetimes = ac(timeStamp, timeStamp2);
            document.getElementById("times").value = leavetimes;
        }
    }

    // 返回格式为xx天xx小时xx分钟
    function ac(timeStamp, timeStamp2) {
        // 两个时间戳相差的毫秒数
        var usedTime = timeStamp2 - timeStamp;
        // 计算相差的天数
        var days = Math.floor(usedTime / (24 * 3600 * 1000));
        // 计算天数后剩余的毫秒数
        var leave1 = usedTime % (24 * 3600 * 1000);
        // 计算出小时数
        var hours = Math.floor(leave1 / (3600 * 1000));
        // 计算小时数后剩余的毫秒数
        var leave2 = leave1 % (3600 * 1000);
        // 计算相差分钟数
        var minutes = Math.floor(leave2 / (60 * 1000));
        var time = days + "天" + hours + "时" + minutes + "分";
        return time;
    }

    document.getElementById("back").onclick = function () {
        empLeaves(eid);
    }
    document.getElementById("btn").onclick = function () {
        //将表单数据转化为json
        var leaveData = {
            eid: "",
            begin: "",
            end: "",
            lTime: "",
            reason: "",
        }
        //获取表单数据
        let eid = tid;
        let begin = document.getElementById("begin").value;
        let end = document.getElementById("end").value;
        let times = document.getElementById("times").value;
        let reason = document.getElementById("reason").value;
        let deptNo = tDeptNo;

        //设置数据
        leaveData.eid = eid;
        leaveData.begin = begin;
        leaveData.end = end;
        leaveData.lTime = times;
        leaveData.reason = reason;
        leaveData.deptNO = deptNo;
        //发送ajax请求
        axios({
            method: "post",
            url: "http://localhost:8080/works/works/addLeaveServlet?deptNo=" + deptNo,
            data: leaveData
        }).then(function (resp) {
            if (resp.data === "addLeaveSuccess") {
                alert("请假提交成功！");
                empLeaves(eid);
            }
        })
    }
}

function changepsd(id) {
    welcome.innerHTML = "";
    mainContent.innerHTML = '';
    let content = ` <div class="main">
        <form action="#" method="post">
            <h2>修改密码</h2>
            <label for="oldpassword">原始密码：</label>
            <input type="text" name="oldpassword" id="oldpassword">
            <p></p>
            <label for="newpassword">  &nbsp; &nbsp;新密码：</label>
            <input type="password" name="newpassword" id="newpassword"><br>
            <label for="newpassword2">确认密码：</label>
            <input type="password" name="newpassword2" id="newpassword2"><br>
            <p id="newpsd_err" style="display: none;">两次密码输入不一致</p>
            <input type="button" class="layui-btn" id="btn" value="提交信息">
        </form>
    </div>`;
    mainContent.innerHTML = content;
    twopsd();
    document.getElementById("btn").onclick = function () {
        //将表单数据转化为json
        var changeData = {
            eid: "",
            oldpassword: "",
            newpassword: "",
            newpassword2: "",
        }
        //获取表单数据
        let eid = id;
        let oldpassword = document.getElementById("oldpassword").value;
        let newpassword = document.getElementById("newpassword").value;
        let newpassword2 = document.getElementById("newpassword2").value;

        //设置数据
        changeData.eid = eid;
        changeData.oldpassword = oldpassword;
        changeData.newpassword = newpassword;
        changeData.newpassword2 = newpassword2;
        console.log(changeData)
        //发送ajax请求
        axios({
            method: "post",
            url: "http://localhost:8080/works/works/changePasswordServlet?eid" + changeData.eid,
            data: changeData
        }).then(function (resp) {
            if (resp.data === "changeSuccess") {
                alert("密码修改成功！");
            } else if (resp.data === "err") {
                alert("原密码输入错误！");
            } else if (resp.data === "new_err") {
                alert("两次新密码输入不一致！");
            }
        })
    }
}

//密码不一致的错误
function twopsd() {
    var psd = document.getElementById("newpassword");
    var psd2 = document.getElementById("newpassword2");
    var iserr = 0;
    psd.onblur = function () {
        if (psd2.value != psd.value) {
            document.getElementById("newpsd_err").style.display = "";
            iserr = 1;
        } else {
            document.getElementById("newpsd_err").style.display = "none";
            iserr = 0;
        }
    }
    psd2.onblur = function () {
        if (psd2.value != psd.value) {
            document.getElementById("newpsd_err").style.display = "";
            iserr = 1;
        } else {
            document.getElementById("newpsd_err").style.display = "none";
            iserr = 0;
        }
    }
}

//查询员工账号密码
function selectAllPassword() {
    mainContent.innerHTML = "";
    welcome.innerHTML = "";
    axios({
        method: "get",
        url: "http://localhost:8080/works/works/selectAllPasswordServlet"
    }).then(function (response) {
        let all = response.data;
        let tableData = `<tr align='center'>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>账号</th>
            <th>操作</th>
            </tr>`;
        for (let i = 0; i < all.length; i++) {
            let al = all[i];
            tableData += `<tr align='center'>
                <td>` + al.eid + `</td>
                <td>` + al.name + `</td>
                <td>` + al.username + `</td>
                <td><a id="change" class="layui-btn layui-btn-radius layui-btn-danger" onclick="changepsd2(` + al.eid + `)">重置密码</a></td>
                </tr>`
            mainContent.innerHTML = "<table  class=\"table table-hover  table-bordered\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
        }

    })
}

function changepsd2(eid) {
    let a = confirm("是否要重置该用户的密码？（重置密码为123）");
    if (a) {
        axios({
            method: "get",
            url: "http://localhost:8080/works/works/changepsd2Servlet?eid=" + eid
        }).then(function (resp) {
            if (resp.data === "success") {
                alert("重置密码成功");
                selectAllPassword();
            }
        })
    }
}