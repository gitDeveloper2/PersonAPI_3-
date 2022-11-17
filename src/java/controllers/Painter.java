/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author dev
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class Painter implements Serializable{
    @ManagedProperty( value="#{facepainter}")
    public Facepainter facepainter;

    public Facepainter getFacepainter() {
        return facepainter;
    }

    public void setFacepainter(Facepainter facepainter) {
        this.facepainter = facepainter;
    }
    
    public void person(){
    facepainter.setCenterContent("index7.xhtml");
    }
    
    public void clearhome(){
        facepainter.setCenterContent("");
    }
}
