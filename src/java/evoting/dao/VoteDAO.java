
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
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

public class VoteDAO {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7;
    private static Statement st;
    static
    {
        try
        {
            st=DBConnection.getConnection().createStatement();
            ps1=DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
            ps2=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from user_details,candidate where user_details.adhar_no=candidate.user_id and candidate_id=?");
            ps3=DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
            ps4=DBConnection.getConnection().prepareStatement("select candidate_id,count(*) as votes_obt from voting group by candidate_id order by votes_obt desc");
            ps5=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from user_details,candidate where user_details.adhar_no=candidate.userid and candidate.city=(select city from user_Details where adhar_no=?)");
            ps6=DBConnection.getConnection().prepareStatement("select party,count(*) as vote from candidate,voting where voting.candidate_id=candidate.candidate_id group by party order by 2 desc");
            ps7=DBConnection.getConnection().prepareStatement("select gender,count(*) as vote from user_Details,voting where voting.voter_id=user_Details.adhar_no group by gender order by 2 desc");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static String verifyVote(String userid)throws SQLException
    {
        ps1.setString(1,userid);
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
    public static String getCandidateId(String userid)throws SQLException
    {
        ps1.setString(1,userid);
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }

    public static CandidateInfo getVote(String cid)throws Exception{
    ps2.setString(1, cid);
 ResultSet rs=ps2.executeQuery();
 CandidateInfo c=new CandidateInfo();
 byte[]buffer;
 ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
      int byteRead;
      byte[]img;
      String base64img;     
if(rs.next()){
    c.setCandidateId(rs.getString(1));
    c.setCandidateName(rs.getString(2));
    c.setParty(rs.getString(3));
    Blob b=rs.getBlob(4);
    InputStream in=b.getBinaryStream();
    buffer =new byte[24096];//creating byte array step 3
          byteRead=-1;
          while((byteRead=in.read(buffer))!=-1){//step 4
              outputStream.write(buffer,0, byteRead);//step 5
          }
          img=outputStream.toByteArray();//step 6
          Base64.Encoder en=Base64.getEncoder();//creating encoder object
          base64img=en.encodeToString(img);//by calling encodeToString return string form of img array
          c.setSymbol(base64img);
}
return c;
}
    
    public static boolean addVote(VoteDTO vote)throws SQLException
    {
        ps3.setString(1,vote.getCandidateId());
        ps3.setString(2,vote.getVoterId());
        return (ps3.executeUpdate()!=0);
    }
    
    public static Map<String,Integer> getResult()throws SQLException
    {
        Map<String,Integer> result=new LinkedHashMap();
        ResultSet rs=ps4.executeQuery();
        while(rs.next())
        {
            result.put(rs.getString(1),rs.getInt(2));
        }
        return result;
    }
    
    public static int getVoteCount()throws SQLException
    {
        ResultSet rs=st.executeQuery("Select count(*) from voting");
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
    
    public static Map<String,Integer> getPartyResult()throws SQLException{
  ResultSet rs=ps6.executeQuery();
  Map<String,Integer> vote=new LinkedHashMap<>();
  while(rs.next()){
      vote.put(rs.getString(1),rs.getInt(2));
  }
  return vote;
}
public static Map<String,Integer> getGenderResult()throws SQLException{
  ResultSet rs=ps7.executeQuery();
  Map<String,Integer> vote=new LinkedHashMap<>();
  while(rs.next()){
      vote.put(rs.getString(1),rs.getInt(2));
  }
  return vote;
}

public static ArrayList<CandidateInfo> getDetails(String uid)throws Exception{
ps5.setString(1, uid);
 ResultSet rs=ps5.executeQuery();  
 ArrayList<CandidateInfo>candidate=new ArrayList();
while(rs.next()){
 byte[]buffer;
 ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
    int byteRead;
    byte[]img;
    String base64img; 
    CandidateInfo c=new CandidateInfo();
    c.setCandidateId(rs.getString(1));
    c.setCandidateName(rs.getString(2));
    c.setParty(rs.getString(3));
    Blob b=rs.getBlob(4);
    InputStream in=b.getBinaryStream();
    buffer =new byte[24096];//creating byte array step 3
          byteRead=-1;
          while((byteRead=in.read(buffer))!=-1){//step 4
              outputStream.write(buffer,0, byteRead);//step 5
          }
          img=outputStream.toByteArray();//step 6
          Base64.Encoder en=Base64.getEncoder();//creating encoder object
          base64img=en.encodeToString(img);//by calling encodeToString return string form of img array
           c.setParty(rs.getString(3));
           c.setSymbol(base64img);
           candidate.add(c);
}
return candidate;
}
}
