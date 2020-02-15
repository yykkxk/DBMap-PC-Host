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

@WebServlet(name = "LocServlet", urlPatterns = {"/LocServlet.action"})
public class LocServlet extends HttpServlet {

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
        String line = req.getParameter("line");
        
        JSONObject Json = new JSONObject();
//        JSONArray JsonArray = new JSONArray();
        
        try {
            Class.forName(Drivde);// 加载驱动,连接sqlite的jdbc
            Connection connection=DriverManager.getConnection("jdbc:sqlite:zhou.db");//连接数据库zhou.db,不存在则创建
            Statement statement=connection.createStatement();   //创建连接对象，是Java的一个操作数据库的重要接口
            
            ResultSet rSet=statement.executeQuery("select*from fav_table where line='" + line + "'");
            while (rSet.next()) {
                String remark = rSet.getString("remark");
                long lati = rSet.getLong("lati");
                long longi = rSet.getLong("longi");
                Json.put("line", line);
                Json.put("lati", lati);
                Json.put("longi", longi);
                Json.put("remark", remark);
            }

            rSet.close();//关闭数据集
            connection.close();//关闭数据库连接
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        out.print(Json.toString());  
        out.flush();  
        out.close(); 
    }

}