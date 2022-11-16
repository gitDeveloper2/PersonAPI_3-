
package controllers;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
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
import javax.enterprise.context.SessionScoped;

import javax.enterprise.inject.spi.Bean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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
    private String start_date;
    private String end_date;

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

   

   

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    private List<Person> searchItems;

 
    public void setSearchItems(List<Person> searchItems) {
        this.searchItems = searchItems;
    }
public List<Person> getSearchItems(){
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM,d,YYYY", Locale.ENGLISH); 
    java.sql.Date dateIn1;
    java.sql.Date dateIn2;
    try{
//        date=df.parse(date_temp);
            dateIn1=java.sql.Date.valueOf(start_date);
            dateIn2=java.sql.Date.valueOf(end_date);
            System.out.println(dateIn1+"date 1sdkfjks");
       }catch(Exception e){
           System.out.println(e); 
       }
        List<Person> list= persons.stream().filter((person)->{
           
            if  (person.getId()>=82){
                return true;}
            else
            return false;
        }).collect(Collectors.toList());
        
        searchItems=list;
        return searchItems;
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
            //        persons=new ArrayList<>();
//        persons.add(new Person(1, "Angela"));
//        persons.add(new Person(2, "Milo"));


persons=personService.getPersons();
        } catch (SQLException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        System.out.println("cadsfkdsfskller");
        Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id=Integer.parseInt(params.get("delete"));
        personService.delete(id);
      
        return "template?faces-redirect=true";
    }
    public String update(Person p) throws SQLException{
        System.out.println(p);
//    Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//    String id=params.get("update");
   // Person p=new Person();
//    p.setId(Integer.parseInt(id));
//    p.setName(name);
//        System.out.println("youg"+this.id)

    personService.update(p);
     return "template?faces-redirect=true";
    }
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


public void search6(){
    System.out.println("jogoo road");
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM,d,YYYY", Locale.ENGLISH); 
    java.sql.Date dateIn1;
    java.sql.Date dateIn2;
    try{
//        date=df.parse(date_temp);
            dateIn1=java.sql.Date.valueOf(start_date);
            dateIn2=java.sql.Date.valueOf(end_date);
            System.out.println(dateIn1+"date 1sdkfjks");
       }catch(Exception e){
           System.out.println(e); 
       }
        List<Person> list= persons.stream().filter((person)->{
           
            if  (person.getId()>=82){
                return true;}
            else
            return false;
        }).collect(Collectors.toList());
        
        searchItems=list;
        
}    
}
