package parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class DBManager {
	
 
public static int insertNewuser(GetterSetter sets) {
	
	Connection conn= ConnectionManager.getInstance().getConnection();
		int count =0;
	
	try{
		
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String sqlQuery=("INSERT INTO parking.registration (Name,VehicleNumber,PhoneNumber,password) VALUES(?,?,?,?)");

	PreparedStatement prepstmt = conn.prepareStatement(sqlQuery);
	
	//prepstmt.setInt(1, (Integer) null);
	 prepstmt.setString(1, sets.getName());
	 prepstmt.setString(2, sets.getVehicleNum());
	 prepstmt.setInt(3, sets.getMobnum());
	 prepstmt.setString(4, sets.getPwd());
	 	 		 
	 count=prepstmt.executeUpdate();

		
	 	 
	}catch(SQLException e){
		System.out.println(e);
	}
	
		
	ConnectionManager.getInstance().closeConnection();
	return count;
	
}
//*******************
public static int issueTicket(GetterSetter sets) throws ParseException {
	
	Connection conn= ConnectionManager.getInstance().getConnection();
	java.util.Date date=new java.util.Date();
    
    java.sql.Date sqlDate=new java.sql.Date(date.getTime());
   
	    
    int count =0;
	
	try{
		
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//String sqlQuery=("INSERT INTO parking.ticketissue (registrationID,dateofParking,time,Hoursrequested,parkingendtime,Amount) VALUES(?,2017-09-01,?,?,?)");
	
	String sqlQuery=("INSERT INTO parking.ticketissue (registrationID,dateofParking,starttime,Hoursrequested,endtimestamp,Amount,parkingStatus) VALUES(?,?,?,?,?,?,1)");
	PreparedStatement prepstmt = conn.prepareStatement(sqlQuery);
	
	
	prepstmt.setInt(1,sets.getRegID());
	prepstmt.setDate(2, sqlDate);
	prepstmt.setTimestamp(3, sets.getStarttime());
	prepstmt.setDouble(4, sets.getHrsrequest());
	prepstmt.setTimestamp(5, sets.getParkingEndTime());
	prepstmt.setDouble(6, sets.getAmount());
	 
	count=prepstmt.executeUpdate();

	}catch(SQLException e){
		e.printStackTrace();
	}
	
		
	ConnectionManager.getInstance().closeConnection();
	return count;
	
}


//************************

//*************Check the Time difference and print*********

/*public static boolean getLeftoutTime(GetterSetter sets){
	
	Connection conn= ConnectionManager.getInstance().getConnection();
	ResultSet myresultset;
	boolean timeout=false;
	try{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sqlQuery=("SELECT parking.registration.Name,parking.ticketissue.endtimestamp FROM parking.registration INNER JOIN parking.ticketissue ON registration.registrationID=11");

		PreparedStatement prepstmt = conn.prepareStatement(sqlQuery);
		
		myresultset=prepstmt.executeQuery();
		while(myresultset.next()){
			
			java.util.Date date=new java.util.Date();
		  	java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		  	
		  	//if(myresultset.getTimestamp("endtimestamp").equals(sqlTime.getTime()) && sets.getSession().equals(anObject)))){
		  	if(myresultset.getString("name").equals(sets.getSession())){
		  		
		  		timeout=true;
		  		
		  		System.out.println("Alert User");
		  	}
		  	  													
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
			
		}
			
	ConnectionManager.getInstance().closeConnection();
	return false;
	
	}*/

//*************Check the Time difference and print*********


public static boolean checklogin(GetterSetter sets){
	Connection conn= ConnectionManager.getInstance().getConnection();
	ResultSet myresultset;
	boolean loginSuccess = false;
	
	try{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sqlQuery=("SELECT *FROM parking.registration");

		PreparedStatement prepstmt = conn.prepareStatement(sqlQuery);
		
		myresultset=prepstmt.executeQuery();
		while(myresultset.next()){
			
			if(myresultset.getString("Name").equals(sets.getUserName()) && myresultset.getString("password").equals(sets.getPassowrd())){
				
				loginSuccess=true;
				break;
				
				}else{
					
					loginSuccess=false;
				}

						
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
			
		}
			
	ConnectionManager.getInstance().closeConnection();
	return loginSuccess;
		
			
	}
	

}
