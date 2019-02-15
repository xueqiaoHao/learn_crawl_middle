package Spider.Middle.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.transform.Result;




/**
@author Griezmann
@version 2019年2月13日下午5:57:39
*/
public class DataBaseUtil {

	public static void main(String[] args) throws Exception {
		//第一步。注册驱动
		String mysqlDriver="com.mysql.jdbc.Driver";
		Class.forName(mysqlDriver);
		//第二步，数据库连接
		String url="jdbc:mysql://10.255.33.6:3306/test_hao";
		String username="etluser";
		String password="etluser";
		Connection dbconn=DriverManager.getConnection(url, username, password);
		//第三步，获取执行sql的对象
		
		//Statement stat=dbconn.createStatement();
		String sql="select * from news_item_info";
//		String sql="INSERT into news_item_info (title,source_url,post_time,insert_time) VALUES(?,?,?,?);";
		PreparedStatement ps=dbconn.prepareStatement(sql);
		//第四步、设定值并执行语句
//		ps.setString(1, "ps_title1");
//		ps.setString(2, "ps_url");
//		ps.setTimestamp(3,new Timestamp(new Date().getTime()));
//		ps.setTimestamp(4, new Timestamp(new Date().getTime()));
		ResultSet rs=ps.executeQuery(sql);
//		System.out.println(rs);
//		boolean exeResult=ps.execute();
		//第五步，查询处理结果
//		System.out.println(exeResult);
		while(rs.next()) {
			String title=rs.getString("title");
			String source_url=rs.getString("source_url");
			String post_time=rs.getString("post_time");
			String insert_time=rs.getString("insert_time");
			System.out.println(title+source_url+post_time+insert_time);
		}
		//第六步，关闭连接
		ps.close();
		dbconn.close();
	}

}
