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

@WebServlet(name = "login",urlPatterns = {"/login"})
public class login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		try {
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

			String str="";
			try {
				Mademd5 md5 = new Mademd5();
				str = md5.toMd5(p+"qwer");
			}catch (Exception e){
				e.printStackTrace();
			}

			try {
				Class.forName("org.mariadb.jdbc.Driver");
				ct = DriverManager.getConnection(DBUrl, DBUser, DBPwd);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//验证用户是否存在
			try {
				//到数据库中验证用户

				//创建statement
				sm = ct.prepareStatement("select passwd from user where name=?");
				sm.setString(1, u);
				rs = sm.executeQuery();
				//根据结果判断
				if (rs.next()) {
					//说明用户名存在
					if (rs.getString(1).equals(str)) {
						//是合法的
						pw.println("恭喜，登陆成功"+"  "+u);
					} else {
						pw.println("用户名或密码错误");
					}
				} else if (!rs.next()) {
					pw.println("用户名或密码错误");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			//释放资源
			method method = new method();
			method.closeDB();

			//返回

		} catch (Exception e){
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}
}
