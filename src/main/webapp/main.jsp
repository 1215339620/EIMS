<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>员工端</title>

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
    .mainContent form{
        text-align: center;
    }
    p{
        margin: 0;
        color: red;
    }
    #back {
        width: 100px;
        height: 40px;
    }
    #wrapper{
        display: flex;
        height: 100%;
    }
    .navbar-default{
        flex: 3;
        height: 100%;
        float: left;
        background-color: #2f4050;
    }
    #page-wrapper{
        flex:7;
        float: right;
        height: 100%;
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
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${user.username},欢迎您</strong>
                             </span> <span class="text-muted text-xs block">普通员工</span></span>
                            </a>
                        </div>
                    </li>
                    <li>
                        <a href="#" id="allEmp">员工信息查询</a>
                    </li>
                    <li>
                        <a href="#" id="selectSalaryById">工资查看</a>
                    </li>
                    <li>
                        <a href="#" id="empLeaves">请假审批</a>
                    </li>
                    <li>
                        <a href="#" id="changePassword">修改密码</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message">欢迎使用员工端</span>
                        </li>
                        <li>
                            <a id="quit">
                                <i class="fa fa-sign-out"></i> 退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row" id="main_Content">
                <h1 id="welcome">亲爱的员工，欢迎您</h1>
                <button id="addLeave" style="float: right;display: none;" class="layui-btn layui-btn-normal">
                    请&nbsp;&nbsp;假
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
        eid=${user.eid};
        deptNo=${user.deptNo}
        document.getElementById("quit").onclick=function () {
            alert("退出成功");
            location.href = "http://localhost:8080/works/";
        }
        document.getElementById("allEmp").onclick=function (){
            mainSelectAll();
            document.getElementById("addLeave").style.display="none"
        }
        document.getElementById("selectSalaryById").onclick=function (){
            selectSalaryById(${user.eid});
            document.getElementById("addLeave").style.display="none"
        }
        document.getElementById("empLeaves").onclick=function () {
            empLeaves(${user.eid});
        }
        document.getElementById("addLeave").onclick=function () {
            addLeaves(${user.eid},${user.deptNo});
        }

        document.getElementById("changePassword").onclick=function () {
            changepsd(${user.eid});
            document.getElementById("addLeave").style.display = "none"
        }
    </script>
</body>
</html>

