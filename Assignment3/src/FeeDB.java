import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FeeDB {
    public static int save(int mcode,String dos,String facility1,String facility2){
        int status = 0;
        int value = 0;
        try{
            Connection con = Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select fee from facility_fee where facility='"+facility1+"'");
            while (rs.next())
                value = rs.getInt("fee");

            PreparedStatement stmt = con.prepareStatement("insert into customer_fee(mcode,dos,fc1,fc2,fees) values(?,?,?,?,?)");
            stmt.setInt(1,mcode);
            stmt.setString(2,dos);
            stmt.setString(3,facility1);
            stmt.setString(4,facility2);
            stmt.setInt(5,value);

            status=stmt.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }

    public static int update(int mcode,String facility){
        int status = 0;
        int value = 0;
        try{
            Connection con = Database.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select fee from facility_fee where facility='"+facility+"'");
            while (rs.next())
                value = rs.getInt("fee");

            stm.executeUpdate("update customer_fee set fc2='"+facility+"' where mcode="+mcode);
            stm.executeUpdate("update customer_fee set fees="+value+"+fees where mcode="+mcode);
            con.close();
            stm.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return status;

    }
}
