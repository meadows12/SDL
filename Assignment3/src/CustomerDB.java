import java.sql.*;
public class CustomerDB {
    public static int save(String name,String address,String doj,long number){
        int status = 0;
        try{
            Connection con = Database.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into customer(name,address,doj,phone) values(?,?,?,?)");
            stmt.setString(1,name);
            stmt.setString(2,address);
            stmt.setString(3,doj);
            stmt.setLong(4,number);

            status=stmt.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }

    public static void display(){
        try{
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");
            while(rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
