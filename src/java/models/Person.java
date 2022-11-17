
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author dev
 */

public class Person implements Serializable{
    private int id;
    private String name;
    private String email;
    private String sex;
    private Date date;
    private String stringdate;

    public String getStringdate() {
        return stringdate;
    }

    public void setStringdate(String stringdate) {
        this.stringdate = stringdate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public Person() {
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", email=" + email + ", sex=" + sex + ", date=" + date + '}';
    }



    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    
}
