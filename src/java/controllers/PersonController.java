
package controllers;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import models.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import javax.enterprise.inject.spi.Bean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import services.PersonService;

/**
 *
 * @author dev
 */
//@Named
//@SessionScoped
@ManagedBean
@ViewScoped

public class PersonController implements Serializable{
    private int id;
    private String name;
    private String email;
    private String sex;
    private java.sql.Date date;
    private String date_temp;
    private java.util.Date start_date;
    private java.util.Date ender_date;
    private String stringdate;

    public java.sql.Date toSqlDate(java.util.Date date){
       return new java.sql.Date(date.getTime());
    }
    public java.util.Date getStart_date() {
        return start_date;
    }

    public void setStart_date(java.util.Date start_date) {
        this.start_date = start_date;
    }

    public java.util.Date getEnder_date() {
        return ender_date;
    }

    public void setEnder_date(java.util.Date ender_date) {
        this.ender_date = ender_date;
    }

    
    

    public String getStringdate() {
        return stringdate;
    }

    public void setStringdate(String stringdate) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        this.stringdate=df.format(date);
        //this.stringdate = stringdate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate_temp() {
        return date_temp;
    }

    public void setDate_temp(String date_temp) {
        this.date_temp = date_temp;
    }

   

   

//    public String getStart_date() {
//        return start_date;
//    }
//
//    public void setStart_date(String start_date) {
//        this.start_date = start_date;
//    }
//
//    public String getEnd_date() {
//        return end_date;
//    }
//
//    public void setEnd_date(String end_date) {
//        this.end_date = end_date;
//    }
    private List<Person> searchItems;

 
    public void setSearchItems(List<Person> searchItems) {
        this.searchItems = searchItems;
    }
   
public List<Person> getSearchItems(){
    if(searchItems==null||searchItems !=null){
         List<Person> list=this.search6();
    this.setSearchItems(list);
        return searchItems;
    }
         
    return null;
    
 
}    
    
//public void search(){
//    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM,d,YYYY", Locale.ENGLISH);
//    
//    LocalDate date1=LocalDate.parse(start_date,formatter);
//    LocalDate date2=LocalDate.parse(end_date,formatter);
//
//   List<Person> result;
//        result=persons.stream().filter((person)->{
//            LocalDate old1= LocalDate.parse(person.getStart_date(),formatter);
//            LocalDate old2= LocalDate.parse(person.getEnd_date(),formatter);
//            if  (old1.isAfter(date1)|old1.isEqual(date1) && old2.isBefore(date2)|old2.isEqual(date2)){
//                return true;}
//            else
//            return false;
//        }).collect(Collectors.toList());
//}


    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    private List<Person> persons;
  @ManagedProperty(value = "#{personservice}")
    private PersonService personService;
  private Map<String,Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    @PostConstruct
    public void init() {
        try {
            persons=personService.getPersons();
            //start of search
          
   
        //end of search
        } catch (SQLException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
public List<Person> search6() {
     
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Person> list=new ArrayList<>();
        try{
           
//        final java.sql.Date dateIn1 =new java.sql.Date(start_date.getTime());
//         
//        final java.sql.Date dateIn2 =new java.sql.Date(end_date.getTime());

            final java.sql.Date dateIn1 =toSqlDate(start_date);
         final java.sql.Date dateIn2 =toSqlDate(ender_date);
        System.out.println("Inside stry");
           System.out.println("search6() called" +"date1="+dateIn1);
        
                list= persons.stream().filter((person)->{return (//person.getId()>100
                person.getDate().after(dateIn1)
                || 
                person.getDate().equals(dateIn1)&&
                person.getDate().before(dateIn2)||
                person.getDate().equals(dateIn2)
                );           }).collect(Collectors.toList());
        searchItems=list;
            setSearchItems(list);
        System.out.println("search6()"+list);
          }catch(Exception e){
            
        }
    
        return list;       
}  
    public PersonController() {
    }
    
//    public PersonController(int id, String name, List<Person> persons, PersonService personService) {
//        this.id = id;
//        this.name = name;
//        this.persons = persons;
//        this.personService = personService;
//    }
    
    

//    public PersonService getPersonService() {
//        return personService;
//    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    

    public List<Person> getPersons() {
        return persons;
    }
//
//    public void setPersons(List<Person> persons) {
//        this.persons = persons;
//    }
    



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public void create() throws SQLException{
        Person p=new Person();
        p.setName(name);
        p.setEmail(email);
        p.setSex(sex);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");

       try{
//        date=df.parse(date_temp);
            date=java.sql.Date.valueOf(date_temp);
       }catch(Exception e){
           System.out.println(e); 
       }
//       java.sql.Date d=java.sql.Date(date.getTime());
//        p.setDate(new java.sql.Date.];
        
        p.setDate(date);
        personService.create(p);
        reload();
        //return "template?faces-redirect=true";
    }
    public  String delete() throws SQLException{
        
        Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id=Integer.parseInt(params.get("delete"));
        personService.delete(id);
      
        return "template?faces-redirect=true";
    }
//    public String update(Person p) throws SQLException{
//        System.out.println(p);
////    Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
////    String id=params.get("update");
//   // Person p=new Person();
////    p.setId(Integer.parseInt(id));
////    p.setName(name);
////        System.out.println("youg"+this.id)
//
//    personService.update(p);
//     return "template?faces-redirect=true";
//    }
//       public  String edit() throws SQLException{
//            
//           FacesContext fc=FacesContext.getCurrentInstance();
//           Map<String,String> params=fc.getExternalContext().getRequestParameterMap();
//           int id=Integer.parseInt(params.get("edit1"));
//          Person p=personService.get(id);
//           
//           sessionMap.put("editedperson", p);
//        return "/template.xhtml?faces-redirect=true";
//    }
//
//    public void onRowEdit(RowEditEvent event) {
//  FacesMessage msg = new FacesMessage("Car Edited", event.g);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//     
//    public void onRowCancel(RowEditEvent event) {
//       FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//    
//public void onCellEdit(CellEditEvent event){
//     System.out.println("eafeadjkdfjdsakfkdnk");
//    Object oldValue=event.getOldValue();
//    Object newValue =event.getNewValue();
//    
//    if(newValue!=null && !newValue.equals(oldValue)){
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//}
        public void onRowEdit(RowEditEvent event) throws SQLException {
            
            FacesMessage msg = new FacesMessage("Car Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        personService.update((Person) event.getObject());
        
        
    }
     
        
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
public void reload(){
   ExternalContext ec=FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest)ec.getRequest()).getRequestURI());
        } catch (IOException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}





  
}
