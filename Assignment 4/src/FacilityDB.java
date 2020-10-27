import java.sql.Connection;
import java.sql.PreparedStatement;

public class FacilityDB {


    public static int save(String facility,int fee){
        int status = 0;
        try{
            Connection con = Database.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into facility_fee(facility,fee) values(?,?)");
            stmt.setString(1,facility);
            stmt.setInt(2,fee);


            status=stmt.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }
}
