<%--
  Description:
  Created by IntelliJ IDEA.
  Author:Trembler
  Time:2018/11/22 17:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MD5</title>
  </head>
  <body>
  <form action="login" method="post">
    <table>
      <tr>
        <td align="right"/>用户名：
        <td align="left"/><input type="text" name="username"/>
      </tr>

      <tr>
        <td align="right"/>密码：
        <td align="left"/><input type="text" name="password"/>
      </tr>
      <tr>
        <td align="right"/><input type="submit" value="登录"/>
        <td align="left"/><input type="submit" value="注册" formaction="register"/>
      </tr>

    </table>
  </form>
  </body>
</html>
