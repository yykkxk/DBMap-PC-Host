package web2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet.action"})
public class SaveServlet extends HttpServlet {

	private static String Drivde="org.sqlite.JDBC";
	private SqliteJDBC sqlJDBC = new SqliteJDBC();
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println(req.toString());
    	
    	resp.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = resp.getWriter();
//        Boolean flag = false;
        String mesg = "id mesg";
        String line = req.getParameter("line");  
        String remark = req.getParameter("remark");
        String lati = req.getParameter("lati");
        String longi = req.getParameter("longi");  
        
        try {
            Class.forName(Drivde);// 加载驱动,连接sqlite的jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//连接数据库zhou.db,不存在则创建
            Statement statement=connection.createStatement();   //创建连接对象，是Java的一个操作数据库的重要接口
            
            ResultSet rSet=statement.executeQuery("select*from fav_table");//搜索数据库，将搜索的放入数据集ResultSet中
            while (rSet.next()) {
                
                String id11 = rSet.getString("line");
    			if(id11.equals(line)) {
//    				flag = false;
    				mesg = "id has been registered";
    			}
            }
            
            if(!mesg.equals("id has been registered")) {
            	statement.executeUpdate("insert into fav_table values('"
             + line + "','" + lati +"','" + longi + "','" + remark + "')");
            	mesg = "id registered successfully";
            }
			
            rSet.close();//关闭数据集
            connection.close();//关闭数据库连接
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        out.print(mesg);  
        out.flush();  
        out.close(); 
    }

}