package Commit_Rollback;

import java.sql.*;
import java.util.Scanner;

public class Demo1 {
  public static void main(String[] args) {
	   Scanner sc1=new Scanner(System.in);
	  Connection con =null;
	  PreparedStatement pstmt=null;
	  int id=5;
	  
	  String qry="delete from product_info where product_id=?";
	  
	  try {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root","sql@123");
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(qry);
		pstmt.setInt(1, id);
		int count=pstmt.executeUpdate();
		System.out.println("Do You Wont to UNDO");
		System.out.println("1:YES\n2:NO");
		int ch=sc1.nextInt();
		if(ch==1) {
			con.rollback();
			System.out.println("Tranzaction rollback");
		}else if(ch==2) {
			con.commit();
			System.out.println(count+" Product deleted");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  
}
}
