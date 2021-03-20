//Divyam Solanki
//201951198

import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class Question2Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Here we are connecting to Question_2 Database in MySQL in this Computer.
    
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Question3","root","Divyam@123");
            while(true) {
                System.out.println("Enter any choice as per the given options");
                System.out.println("1. Insert into Customer");
                System.out.println("2. Insert into Order");
                System.out.println("3. Insert into Item");
                System.out.println("4. To edit the value of the Order Item by adding");
                System.out.println("5. To edit the value of the Order Item by deleting");
                System.out.println();

                int t = sc.nextInt();
                switch(t) {
                    case 1 :
                        String Customer_Name, Customer_Number;
                        System.out.println("Please enter the Customer Name");
                        Customer_Name = sc.next();
                        System.out.println("Please enter the Customer Number");
                        Customer_Number = sc.next();
                        System.out.println();
                        System.out.println(Customer_Name + " :: " + Customer_Number);
                        //We need to check if the customer is legit of not here so we are checking for the first letter to be 
                        //starting with 'C' or 'c'.
                        if(Customer_Number.charAt(0) == 'C' || Customer_Number.charAt(0) == 'c'){
                            // Statement mystmt1 = con.createStatement();
                            String query1 = "insert into Customer " + "values (?,?);";
                            PreparedStatement stmt1 = con.prepareStatement(query1);
                            stmt1.setString(1, Customer_Number);
                            stmt1.setString(2, Customer_Name);
                            stmt1.executeUpdate();
                            System.out.println("The data has been inserted!!");
                        }
                        else {
                            System.out.println("We cannot find the given customer Number");
                        }
                        break;

                    case 2 :
                        String Order_Number,Item_Number1,Customer_Number1;
                        int Amount,Quantity;
                        Date dete;
                        System.out.println("Please enter the order Number here :");
                        Order_Number = sc.next();
                        System.out.println("Please enter the Customer Number here :");
                        Customer_Number1 = sc.next();
                        Long theDate = System.currentTimeMillis();
                        dete = new java.sql.Date(theDate);
                        System.out.println("Please enter the Amount : ");
                        Amount = sc.nextInt();
                        System.out.println(Order_Number + " :: " + Customer_Number1 + " :: " + dete + " :: " + Amount);
                        String query3 = "insert into Cust_Order "+"values (?,?,?,?)";
                        PreparedStatement stmt3 = con.prepareStatement(query3);
                        stmt3.setString(1,Order_Number);
                        stmt3.setString(2,Customer_Number1);
                        stmt3.setDate(3,dete);
                        stmt3.setInt(4,Amount);
                        stmt3.executeUpdate();
                        System.out.println("The data has been pushed in Customer Order table");

                        //Taking further data about this particular order
                        System.out.println();
                        System.out.println("Please enter the item number you want to purchase");
                        Item_Number1 = sc.next();
                        System.out.println("Please enter the quantity of the purchase ");
                        Quantity = sc.nextInt();
                        System.out.println(Item_Number1 + " :: " + Quantity);
                        String query4 = "insert into Order_item "+"values(?,?,?)";
                        PreparedStatement mystmt5 = con.prepareStatement(query4);
                        mystmt5.setString(1,Order_Number);
                        mystmt5.setString(2,Item_Number1);
                        mystmt5.setInt(3,Quantity);
                        mystmt5.executeUpdate();
                        System.out.println("The data is pushed in the Order_item table");
                        break;
                    case 3 :
                        String Item_Number, Item_Name;
                        int Price;
                        System.out.println("Please enter the Item Name");
                        Item_Name = sc.next();
                        System.out.println("Please enter the Item Number");
                        Item_Number = sc.next();
                        System.out.println("Please enter the Price per unit");
                        Price = sc.nextInt();
                        System.out.println(Item_Name + " :: " + Item_Number + " :: " + Price );
                        if(Item_Number.charAt(0) == 'I' || Item_Number.charAt(0) == 'i')
                        {
                        String query2 = "insert into Item "+"values (?,?,?)";
                        PreparedStatement stmt2 = con.prepareStatement(query2);
                        stmt2.setString(1,Item_Number);
                        stmt2.setString(2,Item_Name);
                        stmt2.setInt(3,Price);
                        stmt2.executeUpdate();
                        System.out.println("The data has been added here guys");
                        }
                        else
                        {
                            System.out.println("We cannnot find the item number you are looking for.");
                        }
                        break;
                    case 4 :
                        String Order_Number2, Item_Number2;
                        int Quantity1;
                        System.out.println("Please enter the Order Number : ");
                        Order_Number2 = sc.next();
                        System.out.println("Please enter the Item Number : ");
                        Item_Number2 = sc.next();
                        System.out.println("Please enter the quantity here : ");
                        Quantity1 = sc.nextInt();
                        String query5 = "insert into Order_item " + "values (?,?,?);";
                        PreparedStatement stmt6 = con.prepareStatement(query5);
                        stmt6.setString(1, Order_Number2);
                        stmt6.setString(2, Item_Number2);
                        stmt6.setInt(3, Quantity1);
                        stmt6.executeUpdate();
                        System.out.println("The data has been added!!");
                        //We need to update or delete the order_item
                        String newQuery = "Update Cust_Order set Ord_amtnumber = (select SUM(Order_item.qty * item.unit_price) from Order_item,item where Cust_Order.Orderno = Order_item.Orderno and Order_item.Itemno = Item.Itemno);";
                        PreparedStatement prep = con.prepareStatement(newQuery);
                        int rowChanged = prep.executeUpdate();
                        System.out.println("Row affected " + rowChanged);
                        break;
                    case 5 :
                        String Order_Number5, Item_Number5;
                        int g,h=0;
                        System.out.println("Please enter Order Number : ");
                        Order_Number5 = sc.next();
                        System.out.println("Please enter Item Number : ");
                        Item_Number5 = sc.next();
                        PreparedStatement newprep = con.prepareStatement("select * from Order_item where Orderno = ? and Itemno = ?;");
                        newprep.setString(1, Order_Number5);
                        newprep.setString(2, Item_Number5);
                        ResultSet res = newprep.executeQuery();
                        while(res.next()){
                            System.out.println("Please enter the number of Items in the order : ");
                            System.out.println(res.getInt(3));
                            h = res.getInt("qty");
                        }                        
                        System.out.println("Enter the quantity you want to delete : ");
                        g = sc.nextInt();
                        //If the quantity is less then we need to add 
                        if(g > h) {
                            System.out.println("The entered quantity is wrong please check");
                        }
                        else if (g < h){
                            String updatedQuery = "update Order_item " + "set qty = ?" + " where Orderno = ? and Itemno = ?";
                            PreparedStatement newPrep1 = con.prepareStatement(updatedQuery);
                            newPrep1.setInt(1, g);
                            newPrep1.setString(2, Order_Number5);
                            newPrep1.setString(3, Item_Number5); 
                            int rowChanged1 = newPrep1.executeUpdate();
                            System.out.println("Row changed is  " + rowChanged1 );
                            String newQuery2 = "update Cust_Order set Ord_amtnumber = (select SUM(Order_item.qty * item.unit_price) from Order_item,item where Cust_Order.Orderno = Order_item.Orderno and Order_item.Itemno = item.Itemno);";
                            
                            PreparedStatement newPrep4 = con.prepareStatement(newQuery2);
                            int rowChanged2 = newPrep4.executeUpdate();
                            System.out.println("The row changed is " + rowChanged2);

                        }
                        else {
                            String newQuery5 = "delete from Order_item " + "where Orderno = ? and Itemno = ?;";
                            PreparedStatement newPrep5 = con.prepareStatement(newQuery5);
                            newPrep5.setInt(1, g);
                            newPrep5.setString(2, Order_Number5);
                            newPrep5.setString(3, Item_Number5);
                            String newQuery6 = "update Cust_Order set Ord_amtnumber = (select SUM(Order_item.qty * item.unit_price) from Order_item,item where Cust_Order.Orderno = Order_item.Orderno and Order_item.Itemno = item.Itemno);";
                            PreparedStatement newPre6 = con.prepareStatement(newQuery6);
                            int rowChanged3 = newPre6.executeUpdate();
                            System.out.println("The row changed is " + rowChanged3);
                        }
                        break;

                }
            }
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
