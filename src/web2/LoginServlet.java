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
            Class.forName(Drivde);// ��������,����sqlite��jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//�������ݿ�zhou.db,�������򴴽�
            Statement statement=connection.createStatement();   //�������Ӷ�����Java��һ���������ݿ����Ҫ�ӿ�

            ResultSet rSet=statement.executeQuery("select*from tables");//�������ݿ⣬�������ķ������ݼ�ResultSet��
            while (rSet.next()) {            //����������ݼ�
                System.out.println("������"+rSet.getString(1));//������� Ҳ��������д rSet.getString(��name��)
                System.out.println("���룺"+rSet.getString("pwd"));
                
                String pwd = rSet.getString("pwd");
                String name = rSet.getString("name");
    			if(pwd.equals(password)&&name.equals(username)) {
//    				flag = true;
    				mesg = "login successfully";
    			}
            }
			
            rSet.close();//�ر����ݼ�
            connection.close();//�ر����ݿ�����
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        out.print(mesg);  
        out.flush();  
        out.close(); 
    }

}