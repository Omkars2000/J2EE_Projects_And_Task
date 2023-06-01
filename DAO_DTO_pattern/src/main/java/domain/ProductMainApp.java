package domain;

import java.util.*;


import dao.ProductDAO;
import dto.Product;

public class ProductMainApp {

	 static Scanner sc1=new Scanner(System.in);
	public static void main(String[] args) {
      System.out.println("select mod3e of opertation ");
      System.out.println("1:Add Product ");
      System.out.println("2:Remove Product");
      System.out.println("3:Disjplay Product");
      int choice=sc1.nextInt();
      if(choice==1) {
    	  insertProduct();
      }else if(choice==2) {
    	  removeProduct();
      }else if(choice==3) {
    	  showProduct();
      }else
    	  System.out.println("Invalid Choice");

	}
	static void insertProduct() {
		System.out.println("Enter Product Name");
		String name=sc1.next();
		System.out.println("Enter Product Price");
         double price=sc1.nextDouble();
         //add data to DTO object
         Product p1=new Product();
         p1.setProductName(name);
         p1.setProductPrice(price);
         //pass DTO object as argument to AddProduct() method
         ProductDAO d1=new ProductDAO();
         int count=d1.addProduct(p1);
 		System.out.println(count+"Record inserted Successfully");
 		commitRollback();
	}
	static void removeProduct() {
		System.out.println("Enter Product id");
      int id=sc1.nextInt();
      Product p1=new Product();
      p1.setProductId(id);
      ProductDAO d1=new ProductDAO();
      int count=d1.deleteProduct(p1);
		System.out.println(count+"Record deleted Successfully");
		commitRollback();
	}

	static  void showProduct() {
	      ProductDAO d1=new ProductDAO();
		  List <Product> li = d1.displayProduct();
		  Iterator<Product> itr= li.iterator();
		  while(itr.hasNext()) {
			  System.out.println(itr.next());
		  }
		   
	}
	static void commitRollback() {
	      ProductDAO d1=new ProductDAO();
	      System.out.println("1:Commit Product");
	      System.out.println("2:Rollback Product");
	      int choice=sc1.nextInt();
	      if(choice==1) {
	    	  d1.commit();
	    	  System.out.println("Record Commited Succsesfully");
	      }else if(choice==2) {
	    	  d1.rollBack();
	    	  
	      }

	}
}
