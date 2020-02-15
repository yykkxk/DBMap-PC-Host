package web2;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteJDBC {
	private static String Drivde="org.sqlite.JDBC";
	
	public SqliteJDBC() {
		try {
            Class.forName(Drivde);// 加载驱动,连接sqlite的jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//连接数据库zhou.db,不存在则创建
            Statement statement=connection.createStatement();   //创建连接对象，是Java的一个操作数据库的重要接口
            String sql="create table tables(name varchar(20),pwd varchar(20))"; 
                statement.executeUpdate("drop table if exists tables");//判断是否有表tables的存在。有则删除
            statement.executeUpdate(sql);            //创建数据库
            statement.executeUpdate("insert into tables values('ding','166')");//向数据库中插入数据
            statement.executeUpdate("insert into tables values('bin','155')");//向数据库中插入数据
            
            String sql2 = "create table fav_table(" +
                    "line varchar(50)," +
                    "lati long," +
                    "longi long," +
                    "remark varchar(50))";
            statement.executeUpdate(sql2);
//            ResultSet rSet=statement.executeQuery("select*from tables");//搜索数据库，将搜索的放入数据集ResultSet中
//            while (rSet.next()) {            //遍历这个数据集
//                System.out.println("姓名："+rSet.getString(1));//依次输出 也可以这样写 rSet.getString(“name”)
//                System.out.println("密码："+rSet.getString("pwd"));
//            }
//            System.out.println("-------------");
//            rSet.close();//关闭数据集
            connection.close();//关闭数据库连接
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
    }
	
}
