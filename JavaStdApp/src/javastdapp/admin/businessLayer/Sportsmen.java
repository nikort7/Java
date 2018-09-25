/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp.admin.businessLayer;

/**
 *
 * @author Никита
 */
public class Sportsmen {
    
    private String fio;
    private String bdate;
    private String userphone;
    private String password;
    private String sex;
    private int country;
    private int sport;
    
    public Sportsmen() {
    }
    
    public Sportsmen(String fio, String bdate, String userphone, String password, String sex, int country, int sport) {
        this.fio = fio;
        this.bdate = bdate;
        this.userphone = userphone;
        this.password = password;
        this.password = password;
        this.sex = sex;
        this.country = country;
        this.sport = sport;
    }
    
    public void setFIO(String FIO){
        this.fio = FIO;
    }    
    public void setBDate(String bdate){
        this.bdate = bdate;
    }    
    public void setUserphone(String userphone){
        this.userphone = userphone;
    }    
    public void setPassword(String password){
        this.password = password;
    }    
    public void setSex(String sex){
        this.sex = sex;
    }    
    public void setCountry(int country){
        this.country = country;
    }
    public void setSport(int sport){
        this.sport = sport;
    }
    
    
    public String getFIO(){
        return this.fio;
    }
    public String getBDate(){
        return this.bdate;
    }
    public String getUserphone(){
        return this.userphone;
    }
    public String getPassword(){
        return this.password;
    }
    public String getSex(){
        return this.sex;
    }
    public int getCountry(){
        return this.country;
    }
    public int getSport(){
        return this.sport;
    }
    
}
