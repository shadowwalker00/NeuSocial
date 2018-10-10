package myServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import DAO.daoUser;
import Entities.User;


/**
 * Servlet implementation class registServlet
 */
@WebServlet("/registServlet")
public class registServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 变量定义
		String newFileName;
		//定义smartUpload对象
		SmartUpload mySmartUpload = new SmartUpload();
		try {
			// 初始化smartUpload对象
			mySmartUpload.initialize(this.config,request,response);
			// 上载文件类型
			mySmartUpload.upload();
			//仅获得一个文件
			com.jspsmart.upload.File f1 = mySmartUpload.getFiles().getFile(0);
			//重新修改文件名
			newFileName=mySmartUpload.getRequest().getParameter("userID")+"."+f1.getFileExt();
			System.out.println(newFileName);
			//另存为文件，为指定的一个名字
			String savePath = request.getServletContext().getRealPath("./");
			savePath+="upload/icons";
			f1.saveAs(savePath+"/"+newFileName);
			System.out.println("路径:"+savePath+newFileName);
			System.out.println("路径长度:"+(savePath+newFileName).length());
			System.out.println("servletContext:"+savePath);
			
			//非文件类型的数据进行处理
			//插入数据库
			//过滤器处理乱码
			request.setCharacterEncoding("UTF-8");
			User newUser=new User();
			newUser.setUserID(mySmartUpload.getRequest().getParameter("userID"));
			newUser.setUserName(mySmartUpload.getRequest().getParameter("username"));
			newUser.setEmail(mySmartUpload.getRequest().getParameter("email"));
			newUser.setPassword(mySmartUpload.getRequest().getParameter("password"));
			newUser.setCollege(mySmartUpload.getRequest().getParameter("college"));
			newUser.setMajor(mySmartUpload.getRequest().getParameter("major"));
			newUser.setClassNum(mySmartUpload.getRequest().getParameter("class"));
			newUser.setSex(mySmartUpload.getRequest().getParameter("sex"));
			newUser.setBirth(mySmartUpload.getRequest().getParameter("birth"));
			newUser.setIconURL(newFileName);
			daoUser dao=new daoUser();
			dao.insertUser(newUser);
			response.sendRedirect("message.jsp");
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("产生错误了");
			}
	}
}