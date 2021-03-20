//Divyam Solanki
//201951198

import java.sql.*;
import java.util.*;

public class Question2Queries {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Question3","root","Divyam@123");
            // Statement stmt = con.createStatement();

            while(true)
            {
                System.out.println("1. Query for part e in Question 2");
                System.out.println("2. Query for part f in Question 2");
                System.out.println("3. Query for part g in Question 2");
                System.out.println("4. Query for part h in Question 2");
                System.out.println();
                int k = sc.nextInt();
                switch (k) {
                    case 1 : 
                        Statement stmt1 = con.createStatement();
                        ResultSet res = stmt1.executeQuery("SELECT * from Customer where Customer.Customerno in(select Cust_Order.Customerno from Cust_Order group by Cust_Order.Customerno having count(Cust_Order.Customerno)>3);");
                        System.out.println("The Output of the query will be as below : ");
                        while(res.next())
                        System.out.println(res.getString(1) + " | " + res.getString(2) + " | ");
                        System.out.println();
                        break;
                    case 2 : 
                        Statement stmt2 = con.createStatement();
                        ResultSet res1 = stmt2.executeQuery("SELECT * from item where Item.Unit_price <(select AVG(unit_price) from item);");
                        System.out.println("The Output of the query will be as below : ");
                        while(res1.next())
                        System.out.println(res1.getString(1) + " | " + res1.getString(2) + " | " );
                        System.out.println();
                        break;
                    case 3 : 
                        Statement stmt3 = con.createStatement();
                        ResultSet res2 = stmt3.executeQuery("SELECT Orderno, SUM(qty) from Order_item GROUP BY Orderno;");
                        System.out.println("The Output of the query will be as below : ");
                        while(res2.next())
                        System.out.println(res2.getString(1) + " | " + res2.getString(2));
                        System.out.println();
                        break;
                    case 4 : 
                        Statement stmt4 = con.createStatement();
                        ResultSet res3 = stmt4.executeQuery("SELECT * FROM Item WHERE Itemno IN(SELECT Itemno FROM Order_Item GROUP BY Itemno HAVING COUNT(itemno) >=(SELECT (COUNT(*)/4) FROM Cust_Order));");
                        System.out.println("The Output of the query will be as below : ");
                        while(res3.next())
                        System.out.println(res3.getString(1) + " | " + res3.getString(2) + " | " + res3.getString(3));
                        System.out.println();
                        break;
                    default : 
                        System.out.println("No such input try again");
                        break;
                        
                }
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
