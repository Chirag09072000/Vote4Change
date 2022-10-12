
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAO {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4;
    static
    {
        try
        {
            ps=DBConnection.getConnection().prepareStatement("Select user_type from user_details where adhar_no=? and password=?");
            ps1=DBConnection.getConnection().prepareStatement("select * from user_details Minus select * from user_details where adhar_no=?");
            ps2=DBConnection.getConnection().prepareStatement("select adhar_no from user_details minus select adhar_no from user_details where adhar_no=?");
            ps3=DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
            ps4=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
        }
        catch(Exception ex)
        {
            System.out.println("Error In DB communication:"+ex);
        }
    }
    public static String validateUser(UserDTO user)throws SQLException
    {
        ps.setString(1,user.getUserid());
        ps.setString(2,user.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
        public static ArrayList<UserDetails> getTable(String userid) throws SQLException
    {
        ps1.setString(1, userid);
        ArrayList<UserDetails> userList= new ArrayList<>();
        ResultSet rs=ps1.executeQuery();
        while(rs.next())
        {
            UserDetails user=new UserDetails();
            user.setUserid(rs.getString(1));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMobile(rs.getLong(7));
            userList.add(user);
        }
        return userList;
    }
         public static ArrayList<String> getAdharIds(String uid) throws SQLException
    {
        ArrayList<String> adharIdList=new ArrayList<>();
        ps2.setString(1, uid);
        ResultSet rs=ps2.executeQuery();
        while(rs.next())
        {
            adharIdList.add(rs.getString(1));
        }
        return adharIdList;
    } 
    
    public static boolean deleteUser(String userid) throws SQLException
    {
        ps3.setString(1, userid);
        return ps3.executeUpdate()!=0;
    }
    
    public static UserDetails getDetailsById(String uid) throws Exception
    {
        ps4.setString(1, uid);
        ResultSet rs=ps4.executeQuery();
        UserDetails userdet=new UserDetails();
        if(rs.next())
        {
            userdet.setUserid(uid);
            userdet.setPassword(rs.getString(2));
            userdet.setUsername(rs.getString(3));
            userdet.setAddress(rs.getString(4));
            userdet.setCity(rs.getString(5));
            userdet.setEmail(rs.getString(6));
            userdet.setMobile(rs.getLong(7));
        }
      return userdet;
    }
}


