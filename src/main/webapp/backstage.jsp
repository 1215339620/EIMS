<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>后台</title>

    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">
    <link rel="stylesheet" href="layui/css/layui.css">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <style>
        input, select {
            margin: 10px;
        }

        #btn {
            width: 150px;
            height: 40px;
        }

        .mainContent form {
            text-align: center;
        }

        p {
            margin: 0;
            color: red;
        }

        #back {
            width: 100px;
            height: 40px;
        }

        #wrapper {
            display: flex;
        }

        .navbar-default {
            flex: 3;
            height: 100%;
            float: left;
            background-color: #2f4050;
        }

        #page-wrapper {
            flex: 7;
            float: right;
            height: 100%;
        }

        .search {
            float: left;
            margin-left: 5px;
            margin-top: 7px;
            display: none;
        }
    </style>

</head>
<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs">
                                    <strong class="font-bold">${user.username},欢迎您</strong>
                                    </span>
                                    <span class="text-muted text-xs block">管理员</span>
                                </span>
                        </a>
                    </div>
                </li>
                <li>
                    <a href="#1" id="emp">员工信息</a>
                </li>
                <li>
                    <a href="#2" id="dept">部门信息</a>
                </li>
                <li>
                    <a href="#3" id="salary1">工资信息</a>
                </li>
                <li>
                    <a href="#4" id="selectLeaves">请假信息</a>
                </li>
                <li>
                    <a href="#4" id="selectPassword">查询账号密码</a>
                </li>
                <li>
                    <a href="#5" id="changePassword">修改密码</a>
                </li>
            </ul>

        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="search" id="search">
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" data-toggle="tab">
                                查询
                            </a>
                        </li>
                        <li><a href="#ios" data-toggle="tab">多条件查询</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <select name="condition" id="condition">
                                <option value="searchName">姓名</option>
                                <option value="searchEid">编号</option>
                            </select>
                            <input type="text" id="searchC">
                            <button class="layui-btn layui-btn-normal" id="searchBtn1">搜索</button>
                        </div>
                        <div class="tab-pane fade" id="ios">
                            <select name="condition1" id="condition1">
                                <option value="searchDept">部门编号</option>
                                <option value="searchJob">年龄</option>
                            </select>
                            <input type="number" id="searchC1">
                            <select name="condition2" id="condition2">
                                <option value="searchName">姓名</option>
                                <option value="searchAddress">地址</option>
                            </select>
                            <input type="text" id="searchC2">
                            <button class="layui-btn layui-btn-normal" id="searchBtn2">搜索</button>
                        </div>
                    </div>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">欢迎使用员工管理系统</span>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="quit">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row" id="main_Content">
            <h1 id="welcome">尊敬的管理员，欢迎您</h1>

            <button id="addEmp" style="float: right;display: none;" class="layui-btn layui-btn-normal">
                添加员工
            </button>
            <button id="addDept" style="float: right;display: none;" class="layui-btn layui-btn-normal">
                添加部门
            </button>
            <div class="mainContent" id="mainContent" style="margin-left: 15px">
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js?v=3.4.0"></script>
<script src="js/axios-0.18.0.js"></script>
<script src="js/functions.js"></script>
<script>
    window.onload = function () {
        eid =${user.eid};
        deptNo = ${user.deptNo}
            //退出页面
            document.getElementById("quit").onclick = function () {
                alert("退出成功");
                location.href = "http://localhost:8080/works/";
            }

        var table = document.getElementById("table");
        var welcome = document.getElementById("welcome");
        let mainContent = document.getElementById("mainContent");

        document.getElementById("addEmp").onclick = function () {
            // location.href="http://localhost:8080/works/addEmp.html";
            addBtn();
            addEmp();
            document.getElementById("search").style.display = 'none';
        }
        document.getElementById("addDept").onclick = function () {
            addDept();
            addDeptBtn()
        }

        //点击查看员工信息
        document.getElementById("emp").onclick = function () {
            selectAll();
            document.getElementById("search").style.display = 'block';
        }
        //查看部门信息
        document.getElementById("dept").onclick = function () {
            selectAllDept();
            document.getElementById("search").style.display = 'none';
        }
        document.getElementById("salary1").onclick = function () {
            selectAllSal();
            document.getElementById("search").style.display = 'none';
        }
        document.getElementById("selectLeaves").onclick = function () {
            selectAllLeaves();
            document.getElementById("search").style.display = 'none';
        }
        document.getElementById("selectPassword").onclick = function () {
            selectAllPassword();
            document.getElementById("addEmp").style.display = "none";
            document.getElementById("addDept").style.display = "none";
            document.getElementById("search").style.display = 'none';
        }
        document.getElementById("changePassword").onclick = function () {
            changepsd(${user.eid});
            document.getElementById("addEmp").style.display = "none";
            document.getElementById("addDept").style.display = "none";
            document.getElementById("search").style.display = 'none';
        }
        document.getElementById("searchBtn1").onclick = function () {
            var searchC = document.getElementById("searchC");
            var condition = document.getElementById("condition").value;
            var url;
            if (condition === "searchName") {
                url = "http://localhost:8080/works/works/searchName?name=" + searchC.value;
            } else {
                url = "http://localhost:8080/works/works/searchEid?eid=" + searchC.value;
            }
            if (searchC.value !== "") {
                axios({
                    method: "post",
                    url: url
                }).then(function (resp) {
                    let emps = resp.data;
                    searchEmp(emps);
                    console.log(searchC)
                })
            } else {
                alert("请填写完整信息！")
            }


        }
        document.getElementById("searchBtn2").onclick = function () {
            var condition1 = document.getElementById("condition1").value;
            var condition2 = document.getElementById("condition2").value;
            var searchC1 = document.getElementById("searchC1").value;
            var searchC2 = document.getElementById("searchC2").value;
            var url;
            if (condition1 === "searchDept" && condition2 === "searchName") {
                if (searchC1 === "") {
                    url = "http://localhost:8080/works/works/searchName?name=" + searchC2;
                } else if (searchC2 === "") {
                    url = "http://localhost:8080/works/works/searchDept?dept=" + searchC1;
                } else {
                    url = "http://localhost:8080/works/works/searchDeptName?dept=" + searchC1 + "&&name=" + searchC2;
                    console.log(url)
                }
            } else if (condition1 === "searchDept" && condition2 === "searchAddress") {
                if (searchC1 === "") {
                    url = "http://localhost:8080/works/works/searchAddress?address=" + searchC2;
                } else if (searchC2 === "") {
                    url = "http://localhost:8080/works/works/searchDept?dept=" + searchC1;
                } else {
                    url = "http://localhost:8080/works/works/searchDeptAddress?dept=" + searchC1 + "&&address=" + searchC2;
                }
            } else if (condition1 === "searchJob" && condition2 === "searchName") {
                if (searchC1 === "") {
                    url = "http://localhost:8080/works/works/searchName?name=" + searchC2;
                } else if (searchC2 === "") {
                    url = "http://localhost:8080/works/works/searchAge?age=" + searchC1;
                } else {
                    url = "http://localhost:8080/works/works/searchAgeName?age=" + searchC1 + "&&name=" + searchC2;
                }

            } else if (condition1 === "searchJob" && condition2 === "searchAddress") {
                if (searchC1 === "") {
                    url = "http://localhost:8080/works/works/searchAddress?address=" + searchC2;
                } else if (searchC2 === "") {
                    url = "http://localhost:8080/works/works/searchAge?age=" + searchC1;
                } else {
                    url = "http://localhost:8080/works/works/searchAgeAddress?age=" + searchC1 + "&&address=" + searchC2;
                }
            }
            axios({
                method: "post",
                url: url
            }).then(function (resp) {
                let emps = resp.data;
                searchEmp(emps);
            })

        }

        function searchEmp(emps) {
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
                <td> <button class="layui-btn layui-btn-sm" onclick="change(` + emp.eid + `)">修改</button> <button class="layui-btn layui-btn-sm" onclick="del(` + emp.eid + `)" >删除</button> </td>
                </tr>`
            }
            //设置表格数据
            mainContent.innerHTML = "<table  class=\"table table-hover\" id=\"table\" cellSpacing=\"0\" width=\"95%\">" + tableData + "</table>";
        }


    }
</script>
</body>

</html>
