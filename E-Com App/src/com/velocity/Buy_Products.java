package com.velocity;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.Scanner;

	public class Buy_Products {
		
		public static void buyProduct() {
			
		    System.out.print("Enter your userID: ");
		    Scanner sc=new Scanner(System.in);
		    int userID = sc.nextInt();

		    System.out.print("Enter the product id to buy product>> ");
		    int productId = sc.nextInt();

		    System.out.print("Enter the quantity>> ");
		    int quantity = sc.nextInt();
		    sc.nextLine(); // consume newline
		   

		    try  {
		        // Check if item already in cart
		    	Connection con=DbConnection.DBConnection();
		    	PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM CARTS WHERE USERID = ? AND PRODUCT_ID = ?");
		    	ps.setInt(1, userID);
		    	ps.setInt(2, productId);
		    	ResultSet rs=ps.executeQuery();
		    	while(rs.next()) {
		    	int count = rs.getInt(1);
		    	
		            if (count > 0) {
		                // Update quantity
		                
		            	try {
	                        PreparedStatement updateStmt = con.prepareStatement(
	                            "UPDATE CARTS SET QUANTITY = QUANTITY + ? WHERE USERID = ? AND PRODUCT_ID = ?"
	                        );
	                        updateStmt.setInt(1, quantity);
	                        updateStmt.setInt(2, userID);
	                        updateStmt.setInt(3, productId);
	                        updateStmt.executeUpdate();
	                        updateStmt.close();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                } else {
	                    // Insert new item into cart
	                    try {
	                        PreparedStatement insertStmt = con.prepareStatement(
	                            "INSERT INTO CARTS (USERID, PRODUCT_ID, QUANTITY) VALUES (?, ?, ?)"
	                        );
	                        insertStmt.setInt(1, userID);
	                        insertStmt.setInt(2, productId);
	                        insertStmt.setInt(3, quantity);
	                        insertStmt.executeUpdate();
	                        insertStmt.close();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
		          

		            // Ask if user wants to view cart
		            System.out.print("Do you want to view cart (Yes/No)>> ");
		            String choice = sc.nextLine();

		            if (choice.equalsIgnoreCase("Yes")) {
		                displayCart(userID);
		            }
		    	}
		    	rs.close();
		    	con.close();
		    	ps.close();
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		

			public static void displayCart(int userID) {
				
			    String query = "SELECT P.PRODUCT_ID, P.PRODUCT_NAME, P.PURCHASE_PRICE, C.QUANTITY " +
			                   "FROM CARTS C JOIN PRODUCTS P ON C.PRODUCT_ID = P.PRODUCT_ID " +
			                   "WHERE C.USERID = ?";

			    try (Connection con = DbConnection.DBConnection();
			         PreparedStatement stmt = con.prepareStatement(query)) {

			        stmt.setInt(1, userID);
			        ResultSet rs = stmt.executeQuery();
			       
			        System.out.println("Cart Contents:");
			        System.out.println("ID\tName\tPrice\tQuantity");
			        while (rs.next()) {
			            int id = rs.getInt("PRODUCT_ID");
			            String name = rs.getString("PRODUCT_NAME");
			            double price = rs.getDouble("PURCHASE_PRICE");
			            int qty = rs.getInt("QUANTITY");

			            System.out.println(id + "\t" + name + "\t" + price + "\t" + qty);
			            
			        }

			    } catch (Exception e) {
			        e.printStackTrace();
			    }  
			}


			
		


	}