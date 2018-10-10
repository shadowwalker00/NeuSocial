package myServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.daoActions;
import Entities.Action;

/**
 * Servlet implementation class postTrendServlet
 */
@WebServlet("/postTrendServlet")
public class postTrendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postTrendServlet() {
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
		Action newAction=new Action();
		try{
			request.setCharacterEncoding("UTF-8");
			newAction.setUserID(request.getSession().getAttribute("userID").toString());
			newAction.setContent(request.getParameter("trendContent").toString());
			daoActions dao=new daoActions();
			dao.insertAction(newAction);
			response.sendRedirect("/NeuWorld/userPage.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
