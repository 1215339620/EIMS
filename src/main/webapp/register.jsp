<%--
  Created by IntelliJ IDEA.
  User: 麦
  Date: 2022/7/30
  Time: 8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet">
</head>

<body>
    <div class="form-div">
        <div class="reg-content">
            <h1>欢迎注册</h1>
            <span>已有帐号？</span> <a href="login.jsp">登录</a>
            <br>
        </div>
        <form id="reg-form" action="/works/registerServlet" method="post" autocomplete="off">

            <table>
                <p id="username_err" class="err_msg">${register_msg}</p>
                <tr>
                    <td class="inputs">
                        <input name="username" type="text" id="username" placeholder="账号">
                        <br>
                        <p class="err_msg">${username_msg}</p>
                    </td>

                </tr>

                <tr>
                    <td class="inputs">
                        <input name="password" type="password" id="password" placeholder="密码">
                        <br>
                    </td>
                </tr>
                <tr>
                    <td class="inputs">
                        <input name="password2" type="password" id="password2" placeholder="确认密码">
                        <br>
                        <p id="password_err" class="err_msg" style="display: none;">两次输入密码不正确</p>
                    </td>

                </tr>
                <tr>
                    <td class="inputs">
                        <input name="name" type="text" id="name" placeholder="员工姓名">
                        <br>
                    </td>
                </tr>
                <tr>
                    <td class="inputs">
                        <input name="phone" type="text" id="phone" placeholder="手机号码">
                        <br>
                        <p class="err_msg">${phone_msg}</p>
                    </td>
                </tr>

                <tr>
                    <td class="inputs">
                        <input name="checkCode" type="text" id="checkCode" placeholder="验证码">
                        <img id="checkCodeImg" src="/works/checkCodeServlet">
                        <a href="#" id="changeImg">看不清？</a>
                        <p class="err_msg">${checkCode_msg}</p>
                    </td>
                </tr>

            </table>

            <div class="buttons">
                <input value="注 册" type="submit" id="reg_btn">
            </div>
            <br class="clear">
        </form>

    </div>
    <script src="js/jquery-2.1.1.min.js"></script>
    <script>
        window.onload = function () {
            document.getElementById("changeImg").onclick = function () {
                document.getElementById("checkCodeImg").src = "/works/checkCodeServlet?" + (new Date()
                    .getMilliseconds());
            }
            document.getElementById("checkCodeImg").onclick = function () {
                document.getElementById("checkCodeImg").src = "/works/checkCodeServlet?" + (new Date()
                    .getMilliseconds());
            }

            var password_err = document.getElementById("password_err");
            var psd = document.getElementById("password");
            var psd2 = document.getElementById("password2");
            psd.onblur = function () {
                if (psd2.value !== psd.value) {
                    document.getElementById("password_err").style.display = "";
                } else {
                    document.getElementById("password_err").style.display = "none";
                }
            }
            psd2.onblur = function () {
                if (psd2.value !== psd.value) {
                    document.getElementById("password_err").style.display = "";
                } else {
                    document.getElementById("password_err").style.display = "none";
                }
            }

        }
    </script>
</body>

</html>