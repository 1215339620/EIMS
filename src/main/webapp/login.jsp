<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"  %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>login</title>
        <link href="css/login.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet">
    </head>
    
    <body>
    <div id="loginDiv" style="height: 350px">
        <form action="/works/loginServlet" id="form" method="post">
            <h1 id="loginMsg">员工登录</h1>
            <div id="errorMsg">${login_msg} ${register_msg}</div>
            <p>账号:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>
            <p>密码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
            <p>记住我：<input id="remember" name="remember" value="1" type="checkbox"></p>
            <div id="subDiv">
                <input type="submit" class="button" value="登录" style="margin-left: 66px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="register.jsp" id="register">注册</a>
            </div>
        </form>
    </div>
    </body>
</html>