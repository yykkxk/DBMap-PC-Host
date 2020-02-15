package web2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet.action"})
public class SearchServlet extends HttpServlet {

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
    	
    	resp.setContentType("application/json;charset=UTF-8");  
        PrintWriter out = resp.getWriter();
        String mesg = "id mesg";
        String search = req.getParameter("search");
        
//        JSONObject Json = new JSONObject();
        JSONArray JsonArray = new JSONArray();
        
        try {
            Class.forName(Drivde);// ��������,����sqlite��jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//�������ݿ�zhou.db,�������򴴽�
            Statement statement=connection.createStatement();   //�������Ӷ�����Java��һ���������ݿ����Ҫ�ӿ�
            
            ResultSet rSet=statement.executeQuery("select*from fav_table where line like '%" + search + "%'");
            while (rSet.next()) {
                String line11 = rSet.getString("line");
                JsonArray.put(line11);//��JSONObject������ӵ�Json������

            }

            rSet.close();//�ر����ݼ�
            connection.close();//�ر����ݿ�����
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        out.print(JsonArray.toString());  
        out.flush();  
        out.close(); 
    }

}