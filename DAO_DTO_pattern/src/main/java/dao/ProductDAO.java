package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.Product;
public class ProductDAO {
	static Connection con=null;
	static {
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root","sql@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int addProduct(Product p) {
		int count=0;
		PreparedStatement pstmt=null;
		String query="insert into product_info (product_name,product_price)values(?,?)";
		try {
		
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,p.getProductName());
			pstmt.setDouble(2,p.getProductPrice());
		      count=pstmt.executeUpdate();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
		
	}
	public int deleteProduct(Product p) {
		int count=0;
		PreparedStatement pstmt=null;
		String query="delete from product_info where product_id=?";
		try {
		
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1,p.getProductId());
		
		      count=pstmt.executeUpdate();
				con.setAutoCommit(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public  List  displayProduct() {
      ResultSet rs=null;
      Statement stmt=null;
      
    
	  List <Product> li = new ArrayList<Product>();

      String qry="select * from product_info";
      try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7", "root", "sql@123");
		stmt = con.createStatement();
		rs=stmt.executeQuery(qry);
		
		
		while(rs.next()) {
			  Product pro=new Product();
		  pro.setProductId(rs.getInt(1)); 
		  pro.setProductName(rs.getString(2));
		  pro.setProductPrice(rs.getDouble(3));
		  li.add(pro);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
      return li;
	}
	public void commit() {
        try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void rollBack() {
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
