package myServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.daoHobby;
import Entities.Hobby;

/**
 * Servlet implementation class interestServlet
 */
@WebServlet("/interestServlet")
public class interestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public interestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userID=request.getSession().getAttribute("userID").toString();
		String sport=request.getParameter("sport");
		String movie=request.getParameter("movie");
		String music=request.getParameter("music");		
		String book=request.getParameter("book");
		System.out.println("sport:"+sport);
		System.out.println("movie:"+movie);
		System.out.println("music:"+music);
		System.out.println("book:"+book);
		Hobby newHobby=new Hobby(userID,Integer.parseInt(sport),Integer.parseInt(music),Integer.parseInt(movie),Integer.parseInt(book));
		daoHobby dao=new daoHobby();
		dao.insertHobby(newHobby);
		response.sendRedirect("message.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
