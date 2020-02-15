package web2;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteJDBC {
	private static String Drivde="org.sqlite.JDBC";
	
	public SqliteJDBC() {
		try {
            Class.forName(Drivde);// ��������,����sqlite��jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//�������ݿ�zhou.db,�������򴴽�
            Statement statement=connection.createStatement();   //�������Ӷ�����Java��һ���������ݿ����Ҫ�ӿ�
            String sql="create table tables(name varchar(20),pwd varchar(20))"; 
                statement.executeUpdate("drop table if exists tables");//�ж��Ƿ��б�tables�Ĵ��ڡ�����ɾ��
            statement.executeUpdate(sql);            //�������ݿ�
            statement.executeUpdate("insert into tables values('ding','166')");//�����ݿ��в�������
            statement.executeUpdate("insert into tables values('bin','155')");//�����ݿ��в�������
            
            String sql2 = "create table fav_table(" +
                    "line varchar(50)," +
                    "lati long," +
                    "longi long," +
                    "remark varchar(50))";
            statement.executeUpdate(sql2);
//            ResultSet rSet=statement.executeQuery("select*from tables");//�������ݿ⣬�������ķ������ݼ�ResultSet��
//            while (rSet.next()) {            //����������ݼ�
//                System.out.println("������"+rSet.getString(1));//������� Ҳ��������д rSet.getString(��name��)
//                System.out.println("���룺"+rSet.getString("pwd"));
//            }
//            System.out.println("-------------");
//            rSet.close();//�ر����ݼ�
            connection.close();//�ر����ݿ�����
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
    }
	
}
