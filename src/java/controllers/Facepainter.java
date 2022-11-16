/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author dev
 */
@ManagedBean
@SessionScoped
public class Facepainter implements Serializable{
    public String centerContent="";

    public String getCenterContent() {
        return centerContent;
    }

    public void setCenterContent(String centerContent) {
        this.centerContent = centerContent;
    }
}
