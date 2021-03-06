package model;

import java.sql.*; 

public class Buyer {


//A common method to connect to the DB
   private Connection connect() 
     { 
       Connection con = null; 
   try
     { 
     Class.forName("com.mysql.jdbc.Driver"); 

      //Provide the correct details: DBServer/DBName, username, password 
     con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyer", "root", ""); 
     
     System.out.println("sucessfully connected");
     } 
    catch (Exception e) 
    {e.printStackTrace();} 
     return con; 
    } 
   public String insertBuyer(String code, String name, String email, int contactNumber, String address) 
   { 
     String output = ""; 
     try
    { 
     Connection con = connect(); 
     if (con == null) 
     {
   	  return "Error while connecting to the database for inserting."; 
      }
     
     // create a prepared statement
     
     String query = " insert into Buyer (`Buyer ID`,`Buyer Code`,`Buyer Name`,`Buyer Email`,`Buyer Contact Number`,`Buyer Address`)"+ " values (?, ?, ?, ?, ?, ?)"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
     
     //System.out.println("1");
    // binding values
    preparedStmt.setInt(1, 0); 
    preparedStmt.setString(2, code); 
    preparedStmt.setString(3, name); 
    preparedStmt.setString(4, email); 
    preparedStmt.setInt(5,contactNumber); 
    preparedStmt.setString(6,address);
    
    System.out.println(code);
    System.out.println(name);
    System.out.println("2");
   // execute the statement3
   preparedStmt.execute(); 
   System.out.println("3");
   con.close(); 
   output = "Inserted successfully"; 
   } 
   catch (Exception e) 
   { 
   output = "Error while inserting buyer.";
   e.printStackTrace();
   //System.err.println(e.getMessage()); 
   } 
   return output; 
} 
   
   public String readBuyer() 
{ 
   String output = ""; 
try
{ 
   Connection con = connect(); 
   if (con == null) 
{
   	return "Error while connecting to the database for reading."; 
} 
   
  //System.out.println("4");
//Prepare the html table to be displayed
  output = "<table border='1'><tr><th>Buyer Code</th><th>Buyer Name</th>" + "<th>Buyer Email</th>" + "<th>Buyer Conatact Number</th>" +"<th>Buyer Address</th>" +"<th>Update</th><th>Remove</th></tr>"; 

   String query = "select * from Buyer"; 
   Statement stmt = con.createStatement(); 
   ResultSet rs = stmt.executeQuery(query); 
   
  // System.out.println("5");
  // iterate through the rows in the result set
   while (rs.next()) 
{ 
   String BuyerID = Integer.toString(rs.getInt("Buyer ID")); 
   String BuyerCode = rs.getString("Buyer Code"); 
   String BuyerName = rs.getString("Buyer Name");
   String BuyerEmail = rs.getString("Buyer Email");
   String BuyerContactNumber = rs.getString("Buyer Contact Number"); 
   String BuyerAddress = rs.getString("Buyer Address"); 
   
   
  // System.out.println("6");

   // Add into the html table
    output += "<tr><td>" + BuyerCode + "</td>"; 
    output += "<td>" + BuyerName + "</td>"; 
    output += "<td>" + BuyerEmail + "</td>"; 
    output += "<td>" + BuyerContactNumber + "</td>"; 
    output += "<td>" + BuyerAddress +"</td>";
    
    //System.out.println("7");
    
   // buttons
   output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + BuyerID + "'>" + "</form></td></tr>"; 
 } 
   con.close(); 
   // Complete the html table
   output += "</table>"; 
 } 
   catch (Exception e) 
 { 
   output = "Error while reading Buyer."; 
   e.printStackTrace();
   //System.err.println(e.getMessage());  
 } 
   return output; 
 } 
   
   public String updateBuyer(int ID, String code, String name, String email, int contactNum, String address)
   { 
    String output = ""; 
    try
  { 
  Connection con = connect(); 
  if (con == null) 
 {
	return "Error while connecting to the database for updating."; 
	
	} 
  
  //System.out.println("a");
//create a prepared statement
   String query = "UPDATE Buyer SET `Buyer Code`=?,`Buyer Name`=?,`Buyer Email`=?,`Buyer Contact Number`=?,`Buyer Address`=? WHERE `Buyer ID`=?"; 
   PreparedStatement preparedStmt = con.prepareStatement(query); 
   
   
   //System.out.println("b");
//binding values
   preparedStmt.setString(1, code); 
   preparedStmt.setString(2, name);
   preparedStmt.setString(3, email); 
   preparedStmt.setInt(4,contactNum); 
   preparedStmt.setString(5, address); 
   preparedStmt.setInt(6, ID); 
   
  // System.out.println("c");

   System.out.println(code);
   System.out.println(name);
   System.out.println(email);
   System.out.println(contactNum);
   System.out.println(address);
   System.out.println(ID);
   
   
  // System.out.println("d");
   // execute the statement
   preparedStmt.execute(); 
   
   //System.out.println("e");
   con.close(); 
   output = "Updated Successfully"; 
   } 
   catch (Exception e) 
   { 
   output = "Error while updating the Buyer.";
   e.printStackTrace();
   //System.err.println(e.getMessage()); 
   } 
   return output; 
   } 
   
   public String deleteBuyer(String BuyerID) 
   { 
        String output = ""; 
      try
       { 
         Connection con = connect(); 
         if (con == null) 
       {
	       return "Error while connecting to the database for deleting."; 
        } 
         System.out.println("down");
    // create a prepared statement
       String query = "delete from Buyer where `Buyer ID`=?"; 
       PreparedStatement preparedStmt = con.prepareStatement(query); 
       
       int buyerID=Integer.parseInt(BuyerID);
      // System.out.println("sit :"+buyerID);

    // binding values
       preparedStmt.setInt(1,buyerID); 
       
      // System.out.println("up");
    // execute the statement
       preparedStmt.execute(); 
       
       //System.out.println("ui");
       con.close(); 
        output = "Deleted successfully"; 
        } 
       catch (Exception e) 
      
   { 
       output = "Error while deleting the Buyer.";
       e.printStackTrace();
       //System.err.println(e.getMessage());
  } 
   return output;
}
  
}
