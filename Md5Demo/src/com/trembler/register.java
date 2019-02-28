package com.trembler;

import com.ndktools.javamd5.Mademd5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "register",urlPatterns = {"/register"})
public class register extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		//获取用户名和密码
		String u = request.getParameter("username");
		String p = request.getParameter("password");

		//设置数据库连接信息
		String DBUrl = "jdbc:mariadb://localhost:3306/md5?useUnicode=true&characterEncoding=UTF-8";
		String DBUser = "root";
		String DBPwd = "6!=5QzW*i";

		PrintWriter pw = response.getWriter();

		ResultSet rs;
		PreparedStatement sm;
		Connection ct = null;

		String str = "";
		//设置md5相关
		try {
			Mademd5 md5 = new Mademd5();
			str = md5.toMd5(p+"qwer");
			System.out.println(str);
		}catch (Exception e)
		{
			e.printStackTrace();
		}

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			ct = DriverManager.getConnection(DBUrl, DBUser, DBPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//到数据库中验证用户

			//创建statement
			sm = ct.prepareStatement("select passwd from user where name=?");
			sm.setString(1, u);
			rs = sm.executeQuery();
			//根据结果判断
			if (rs.next()) {
				pw.println("此用户名已存在");
			} else {
				if (u != null || p != null) {
					sm = ct.prepareStatement("insert into user (name,passwd) values (?,?)");
					sm.setString(1, u);
					sm.setString(2, str);
					sm.executeUpdate();
					System.out.println(u+" "+p);
					pw.println("注册成功");
				} else {
					pw.println("注册信息不能为空");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//释放资源
		method method = new method();
		method.closeDB();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}
}
