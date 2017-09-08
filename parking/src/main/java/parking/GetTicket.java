package parking;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetTicket
 */
@WebServlet("/GetTicket")
public class GetTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		 	
		// creating an object of getterSetter class to assign value to retrieve it DBmanager class for better management		
		GetterSetter objgetset= new GetterSetter();
		
		//Getting the field value from jsp page
		double varAmount =Double.parseDouble(request.getParameter("Amount"));
		int varRegID=Integer.parseInt(request.getParameter("RegID"));
		//setting both values to getter and setter class to retrieve  DB manager
		objgetset.setRegID(varRegID);
		objgetset.setAmount(varAmount);
		
		
		//Checking the condition and updating the start time and end time based on amount.
		
		if(varAmount<1.5){
			
			String message = "Minum amount is $1.5";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
			
			
		}
		
		if(varAmount>=1.5 && varAmount<3){
			
		objgetset.setHrsrequest(1); // If the user amount is between 1.5 and 3 then hrs is set as 1hr.
		
		int timeout=3600;
				
						
		java.util.Date date=new java.util.Date(); //this and below line is to get the current SQl time 
	   	java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		objgetset.setStarttime(sqlTime);  //Assigning the start time to getter setter class
		
	 java.sql.Timestamp endtime = new java.sql.Timestamp(sqlTime.getTime()+(1000 * 60 * 60 * 1)); // if the amount between 1.5 and 3 then adding 
	 //1 hr to the current time and setting as end time 
		
	    objgetset.setParkingEndTime(endtime); // Setting the end time in getter and setter class.
	    
	    request.setAttribute("value", timeout);
	    try {
			DBManager.issueTicket(objgetset); //calling the issue ticket function in DBManager class and passing getter setter "objgetset"
			//to that function. 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	   
	        
				
		}
		// Same comment as above for this scenario also.
		
		if(varAmount>=3){
			objgetset.setHrsrequest(2);
			
			int timeout=7200;
			
			
			java.util.Date date=new java.util.Date();
		  	java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		  	objgetset.setStarttime(sqlTime);  
		    java.sql.Timestamp endtime = new java.sql.Timestamp(sqlTime.getTime()+(1000 * 60 * 60 * 2));
		    objgetset.setParkingEndTime(endtime);
		    
		    request.setAttribute("value", timeout);
		    
		    try {
				DBManager.issueTicket(objgetset); //calling the issue ticket function in DBManager class and passing getter setter "objgetset"
				//to that function. 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			}
		
													
		
		
		/*boolean timeout=false;
		HttpSession session = request.getSession();
		String varSession =(String) session.getAttribute("username");
		
		objgetset.setSession(varSession);
		
		timeout=DBManager.getLeftoutTime(objgetset);
		
		if(timeout=true){
			String message = "Parking time is over";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
			
		}*/
				
		// After inserting the value redirecting to home page again.
		String message = "Ticket Issue Successful";
		request.setAttribute("", message);
		request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
		
		
	}

}
