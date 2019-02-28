/**
 * Description:
 * Create by IntelliJ IDEA
 * Author:Trembler
 * Time:2018/11/22 17:21
 **/
package com.trembler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class method {

	//复用关闭数据库连接方法
	public void closeDB(){

		ResultSet rs = null;
		PreparedStatement sm = null;
		Connection ct = null;

		try {

			if(rs!=null)
			{
				rs.close();
			}
			if(sm!=null)
			{
				sm.close();
			}
			if(ct!=null)
			{
				ct.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
