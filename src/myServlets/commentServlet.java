package myServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.daoCommentary;
import Entities.Commentary;

/**
 * Servlet implementation class commentServlet
 */
@WebServlet("/commentServlet")
public class commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		try{
			request.setCharacterEncoding("UTF-8");
			String id_TS=request.getParameter("scratchID");
			String content=request.getParameter(id_TS);
			daoCommentary dao=new daoCommentary();
			Commentary newComment=new Commentary();
			newComment.setUserID(request.getSession().getAttribute("userID").toString());
			newComment.setContent(content);
			newComment.setActionUserID(id_TS.substring(0,8));
			newComment.setActionTS(id_TS.substring(8, id_TS.length()));
			dao.insertComment(newComment);
			System.out.println("id:"+id_TS.substring(0, 8));
			System.out.println("TS:"+id_TS.substring(8, id_TS.length()));
			System.out.println("取出来的值是"+content);
			response.sendRedirect("/NeuWorld/userPage.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
