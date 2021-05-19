package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	static Connection conn=null;
	java.sql.PreparedStatement pst;
	public static Connection dbconnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/slip2","root","");
			return conn;
		}
		catch(Exception e2)
		{
			System.out.println(e2);
			return  null;
		}
	}
	
	public static void dbdisconnect() throws Exception
	{
		try {
			if(conn!=null && !conn.isClosed() ) {
				conn.close();
			}
		}
		catch(Exception e){
			
			throw e;
		}
	}

}
