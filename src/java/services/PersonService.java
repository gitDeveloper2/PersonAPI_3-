package services;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import models.Person;
import utils.MyDB_Connection;

/**
 *
 * @author dev
 */
//@Named
//@ApplicationScoped

@ManagedBean(name = "personservice")
@SessionScoped
public class PersonService implements Serializable {
private  MyDB_Connection db=new MyDB_Connection();
 Connection con=null;
 public List<Person> getPersons() throws SQLException{
          List<Person> persons;
          
         
         try {
            persons=new ArrayList<>();
            con=db.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from persons");
            while(rs.next()){
                int id=rs.getInt("id");
                String email=rs.getString("email");
                String name=rs.getString("name");
                String sex=rs.getString("sex");
                Date date=rs.getDate("date");
                
                Person p=new Person();
                p.setId(id);
                p.setName(name);
                p.setEmail(name);
                p.setSex(sex);
                p.setDate(date);
                    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                 p.setStringdate(df.format(date));
                
                persons.add(p);
            }
            return persons;
            
        } catch (SQLException ex) {
           System.out.print(ex);
        }finally{
             con.close();
         }
      return null;
    }
public void create(Person person) throws SQLException{
    MyDB_Connection db=new MyDB_Connection();
    Connection con=null;
    try{
        con=db.getConnection();
        String query="insert into persons(email,name,sex,date) values(?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, person.getEmail());
        ps.setString(2, person.getName());
        ps.setString(3, person.getSex());
        ps.setDate(4, person.getDate());
        ps.execute();
    }catch(SQLException e){
        System.out.println(e);
    }finally{
             con.close();
         }
}

    public Person get(int id) throws SQLException {
    Person p=new Person();
    try {
        
        con=db.getConnection();
        String query="select * from persons where id = ?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
   
        while(rs.next()){
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setEmail(rs.getString("email"));
            p.setSex(rs.getString("sex"));
        }
            
    } catch (SQLException ex) {
        System.out.println(ex);
        
    }finally{
             con.close();
         }
        return p;
    }

    public void update(Person p) throws SQLException {
    try {
         
        con=db.getConnection();
        int id=p.getId();
        String query="update persons set email = ?,name=?, sex=? where id ="+id;
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, p.getEmail());
        ps.setString(2, p.getName());
        ps.setString(3, p.getSex());
        
        ps.executeUpdate();
         System.out.println(p);
    } catch (SQLException ex) {
        System.out.println(ex);
    }finally{
             con.close();
         }
    
    }

    public void delete(int id) throws SQLException {
        
    try {
       
        con=db.getConnection();
        String query="delete from persons where id=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        
    } catch (SQLException ex) {
        System.out.println(ex);    }
    finally{
             con.close();
         }
        
    }


}
