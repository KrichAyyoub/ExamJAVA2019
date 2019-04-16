/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

/**
 *
 * @author krich
 */

import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;


public class Personne {
    

    private String mailAdress;
    private String nom;
    private int id;

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public Personne(String mailAdress){
       // if(emailvalidatorr(mailAdress))
            this.mailAdress = mailAdress;


    }


    public boolean emailvalidatorr(String mail)
    {
        boolean isValid = false;
        try{
            //InternetAddress internetaddress = new InternetAddress(mail);
            //internetaddress.validate();
            isValid = true;
        } catch(Exception e)
        {
            System.out.println("ERREUR de creation: "+mail+ " est une adress mail invalide! ");
        }
        return isValid;

    }
}
