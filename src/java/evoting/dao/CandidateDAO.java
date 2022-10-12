
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.AddCandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class CandidateDAO {
    private static Statement st;
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10;
    static
    {
        try
        {  
            st=DBConnection.getConnection().createStatement();
            ps=DBConnection.getConnection().prepareStatement("select max(candidate_id) from candidate");
            ps1=DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
            ps2=DBConnection.getConnection().prepareStatement("select distinct city from user_details");
            ps3=DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
            ps4=DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=?");
            ps5=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from candidate,user_details where candidate.user_id=user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
            ps6=DBConnection.getConnection().prepareStatement("select user_id from candidate");
            ps7=DBConnection.getConnection().prepareStatement("select city,party from candidate");
            ps8=DBConnection.getConnection().prepareStatement("update candidate set party=?,symbol=?,city=? where candidate_id=?");
            ps9=DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
            ps10=DBConnection.getConnection().prepareStatement("select symbol from candidate where party=?");  
        }
        catch(SQLException ex)
        {
           ex.printStackTrace();
        }
    }
public static String getNewId()throws SQLException
{
    ResultSet rs=ps.executeQuery();
    rs.next();
    String cid=rs.getString(1);
    if(cid==null)
        return "C101";
    else
    {
        int id=Integer.parseInt(cid.substring(1));
        return "C"+(id+1);
    }
}
public static String getUserNameById(String uid)throws SQLException
{
    ps1.setString(1,uid);
    ResultSet rs=ps1.executeQuery();
    if(rs.next())
        return rs.getString(1);
    else
        return null;
}

public static ArrayList<String> getCity()throws SQLException
{
    ArrayList <String> cityList=new ArrayList<>();
    ResultSet rs=ps2.executeQuery();
    while(rs.next())
       cityList.add(rs.getString(1));
    return cityList;
}
public static ArrayList<String> getUserId()throws SQLException
{
    ArrayList<String> uidList=new ArrayList<>();
    ResultSet rs=ps6.executeQuery();
    while(rs.next())
        uidList.add(rs.getString(1));
    return uidList;
}
public static boolean addCandidate(AddCandidateDTO candidate)throws SQLException
{
    ps3.setString(1,candidate.getCandidateId());
    ps3.setString(2,candidate.getParty());
    ps3.setString(3,candidate.getUserId());
    ps3.setBinaryStream(4,candidate.getSymbol());
    ps3.setString(5,candidate.getCity());
    return (ps3.executeUpdate()!=0);
}

public static boolean updateCandidate(AddCandidateDTO candidate)throws SQLException
{
    ps8.setString(1,candidate.getParty());
    ps8.setBinaryStream(2,candidate.getSymbol());
    ps8.setString(3,candidate.getCity());
    ps8.setString(4,candidate.getCandidateId());
    return (ps8.executeUpdate()!=0);
}

public static boolean deleteCandidate(String cid)throws SQLException
{
    ps9.setString(1, cid);
    return ps9.executeUpdate()!=0;
}
public static ArrayList<String> getCandidateId()throws SQLException
{
    ResultSet rs=st.executeQuery("select candidate_id from candidate");
    ArrayList<String> id=new ArrayList<>();
    while(rs.next())
    {
        id.add(rs.getString(1));
    }
    return id;
}
public static CandidateDetails getDetailsById(String cid)throws Exception
{
    ps4.setString(1,cid);
    ResultSet rs=ps4.executeQuery();
    CandidateDetails candidate=new CandidateDetails();
    Blob blob;
    InputStream ipStream;
    ByteArrayOutputStream opStream;
    byte[] buffer;
    int bytesRead;
    byte[] imageBytes;
    String base64Image;
    if(rs.next())
    {
        blob=rs.getBlob(4);
        ipStream=blob.getBinaryStream();
        opStream=new ByteArrayOutputStream();
        buffer=new byte[4096];
        bytesRead=-1;
        while((bytesRead=ipStream.read(buffer))!=-1)
        {
            opStream.write(buffer,0,bytesRead);
        }
        imageBytes=opStream.toByteArray();
        Base64.Encoder en=Base64.getEncoder();
        base64Image=en.encodeToString(imageBytes);
        candidate.setSymbol(base64Image);
        candidate.setCandidateId(cid);
        candidate.setCandidateName(getUserNameById(rs.getString(3)));
        candidate.setParty(rs.getString(2));
        candidate.setCity(rs.getString(5));
        candidate.setUserid(rs.getString(3));
    }
    return candidate;
}

public static ArrayList<CandidateInfo> viewCandidate(String userId)throws SQLException,IOException
{
    ArrayList<CandidateInfo> candidateList=new ArrayList<>();
    ps5.setString(1, userId);
    ResultSet rs=ps5.executeQuery();
    Blob blob;
    InputStream ipStream;
    ByteArrayOutputStream opStream;
    byte[] buffer;
    int bytesRead;
    byte[] imageBytes;
    String cImage;
    while(rs.next())
    {
        blob=rs.getBlob(4);
        ipStream=blob.getBinaryStream();
        opStream=new ByteArrayOutputStream();
        buffer=new byte[4096];
        bytesRead=-1;
        while((bytesRead=ipStream.read(buffer))!=-1)
        {
            opStream.write(buffer,0,bytesRead);
        }
        imageBytes=opStream.toByteArray();
        Base64.Encoder en=Base64.getEncoder();
        cImage=en.encodeToString(imageBytes);
        CandidateInfo candidate=new CandidateInfo();
        candidate.setSymbol(cImage);
        candidate.setCandidateId(rs.getString(1));
        candidate.setCandidateName(rs.getString(2));
        candidate.setParty(rs.getString(3));
        candidateList.add(candidate);
    }
    return candidateList;
}

 public static Map<String,String> getParty()throws SQLException
    {
        Map<String,String> result=new LinkedHashMap();
        ResultSet rs=ps7.executeQuery();
        while(rs.next())
        {
            result.put(rs.getString(1),rs.getString(2));
        }
        return result;
    }
 
   public static String getSymbol(String party)throws Exception{
        ps10.setString(1, party);
        ResultSet rs=ps10.executeQuery();
        if(rs.next()){
            Blob b=rs.getBlob(1);
            InputStream ip=b.getBinaryStream();
            byte []buffer=new byte[20496];
            int byteRead=-1;
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            while((byteRead=ip.read(buffer))!=-1){
                outputStream.write(buffer,0,byteRead);
            }
            byte[]img=outputStream.toByteArray();
            Base64.Encoder en=Base64.getEncoder();
           return en.encodeToString(img);
        }
        return null;
    }
}
