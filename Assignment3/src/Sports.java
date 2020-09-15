import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
class MultiThreadingDemo implements Runnable
{
    int mcode;
    long phone;
    String name,address,doj;
    public MultiThreadingDemo(int mcode,String name,String address,String doj,long phone) {
        this.address = address;
        this.doj = doj;
        this.mcode = mcode;
        this.name = name;
        this.phone = phone;
    }
    public void run()
    {
        try {
            System.out.println("\nMembership Code : "+mcode+"\nNAME : "+name+"\nDate of joining : "+doj+
                    "\nAddress: "+address + "\nPhone no.: "+phone + "\n");
        }
        catch(Exception e) {
            System.out.println("Error Occured\n");
        }
    }
}
public class Sports {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Sports s = new Sports();

        String t;



        while(true){
            System.out.println("Admin(A) or Customer(C)");
            String st = sc.next();

            if (st.equals("C")){

                while(true){
                    System.out.println("1.Input details\n2.Display details\n3.Show available facilities\n4.Input fee details\n5.Add more facility\n6.Print your facilities\n0.Exit");
                    t = sc.next();
                    if (t.equals("0")) {
                        break;
                    }
                    switch (t){
                        case "1":{
                            System.out.println("Enter name: ");
                            String name = sc.next();
                            System.out.println("Enter address: ");
                            String address = sc.next();
                            System.out.println("Enter doj (yyyy-mm-dd): ");
                            String doj = sc.next();
                            System.out.println("Enter Phone number: ");
                            long phone = sc.nextLong();
                            sc.nextLine();
                            int i = CustomerDB.save(name,address,doj,phone);
                            if(i>0){
                                System.out.println("Data added successfully");
                            }
                            break;
                        }
                        case "2":{


                            try{
                                Connection con = Database.getConnection();
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery("select * from customer");
                                while(rs.next()){
                                    System.out.println(
                                            "\nMembership code: " +rs.getInt(1) +
                                                    "\nName: " + rs.getString(2) +
                                                    "\nAddress: " + rs.getString(3)
                                                    + "\nDate of joining: " + rs.getString(4) +
                                                    "\nPhone: " + rs.getLong(5) + "\n");
                                }
                            }catch(Exception e){
                                System.out.println(e);
                            }break;

                        }
                        case "3":{

                            try{
                                Connection con = Database.getConnection();
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery("select * from facility_fee");
                                while(rs.next()){
                                    System.out.println(
                                            rs.getString(1) + " " +
                                                    "Rs." + rs.getInt(2));
                                }
                            }catch(Exception e){
                                System.out.println(e);
                            }
                            break;
                        }
                        case "4":{
                            System.out.println("Enter facility you want: ");
                            String fclity = sc.next();
                            System.out.println("Enter Member code: ");
                            int mcode = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter date of submission of fees: ");
                            String dos = sc.next();


                            int i = FeeDB.save(mcode,dos,fclity,null);
                            if(i>0){
                                System.out.println("Data added successfully");
                            }
                            break;


                        }
                        case "5":{


                            System.out.println("Enter Member code: ");
                            int mcode = sc.nextInt();
                            sc.nextLine();

                            //convert String to medium Text in sql as it does not take in Swimming with instructor
                            System.out.println("Enter facility you want: ");
                            String fclity = sc.nextLine();

                            int i = FeeDB.update(mcode,fclity);
                            if(i>0){
                                System.out.println("Data added successfully");
                            }
                            break;
                        }
                        case "6":{
                            System.out.println("Enter Membership code: ");
                            int code = sc.nextInt();

                            try{
                                Connection con = Database.getConnection();
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery("select fc1,fc2,fees from customer_fee where mcode="+code);
                                while(rs.next()){
                                    System.out.println(
                                            "\nFacility 1: " +  rs.getString("fc1") +
                                                    "\nFacility 2:" + rs.getString("fc2")
                                                    + "\nTotal amount: " + rs.getInt("fees") + "\n");
                                }
                            }catch(Exception e){
                                System.out.println(e);
                            }

                            break;
                        }
                    }
                }


            }else{

                while (true) {
                    System.out.println("1.Add facility\n2.Display facility\n3.Display all customer\n0.Exit");
                    t = sc.next();
                    if (t.equals("0")) {
                        break;
                    }
                    switch (t){
                        case "1":{
                            System.out.println("Enter facility name: ");
                            String se = sc.next();
                            System.out.println("Enter fees of facility: ");
                            int fee_ = sc.nextInt();
                            int i = FacilityDB.save(se,fee_);
                            if(i>0){
                                System.out.println("Data added successfully");
                            }

                            break;
                        }
                        case "2":{
                            try{
                                Connection con = Database.getConnection();
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery("select * from facility_fee");
                                while(rs.next()){
                                    System.out.println(rs.getString(1) + " " + rs.getInt(2));
                                }
                            }catch(Exception e){
                                System.out.println(e);
                            }
                            break;
                        }
                        case "3":{

                            try{
                                Connection con = Database.getConnection();
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery("select * from customer");
                                while(rs.next()){

                                    int mcode = rs.getInt("mcode");
                                    String name = rs.getString("name");
                                    String address = rs.getString("address");
                                    long phone = rs.getLong("phone");
                                    String doj = rs.getString("doj");

                                    Runnable r= new MultiThreadingDemo(mcode,name,address,doj,phone);
                                    new Thread(r).start();
                                }
                            }catch(Exception e){
                                System.out.println(e);
                            }
                        }
                    }
                }

            }
            System.out.println("Do you want to continue?");
            String a = sc.next();
            if(a.equals("Y")) {
                continue;
            } else {
                break;
            }
        }





    }
}
