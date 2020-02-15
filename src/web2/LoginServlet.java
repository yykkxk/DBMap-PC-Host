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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet.action"})
public class LoginServlet extends HttpServlet {

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
    	resp.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = resp.getWriter();
//        Boolean flag = false;
        String mesg = "login failed";
        String username = req.getParameter("username");  
        String password = req.getParameter("password");
        System.out.println("userName:"+username+ "password:"+password);
        
        try {
            Class.forName(Drivde);// 加载驱动,连接sqlite的jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//连接数据库zhou.db,不存在则创建
            Statement statement=connection.createStatement();   //创建连接对象，是Java的一个操作数据库的重要接口

            ResultSet rSet=statement.executeQuery("select*from tables");//搜索数据库，将搜索的放入数据集ResultSet中
            while (rSet.next()) {            //遍历这个数据集
                System.out.println("姓名："+rSet.getString(1));//依次输出 也可以这样写 rSet.getString(“name”)
                System.out.println("密码："+rSet.getString("pwd"));
                
                String pwd = rSet.getString("pwd");
                String name = rSet.getString("name");
    			if(pwd.equals(password)&&name.equals(username)) {
//    				flag = true;
    				mesg = "login successfully";
    			}
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