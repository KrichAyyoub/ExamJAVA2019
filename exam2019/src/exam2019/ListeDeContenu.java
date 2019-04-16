/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krich
 */
public class ListeDeContenu {

    private Date date;
    private String text;
    private Personne personne;
    private String nomListe;
    

    public ListeDeContenu(Date date, String text, Personne personne) {
        this.date = date;
        this.text = text;
        this.personne = personne;
        
    }
        
    public ListeDeContenu(String nomListe , String text , String mail_diffuseur) {
        this.nomListe = nomListe;
        this.text = text;

        try{
            this.personne = new Personne(mail_diffuseur) ;
            System.out.println("liste crée ! nom de la liste: " +this.nomListe +
                    ", text choisi: "+ this.text+ ", adresse mail du proprietaire: "
                    +this.personne.getMailAdress());
           // AllList.add(this); //on garde une liste de toutes les listes qu'on crée
        } catch(Exception e){
            Logger.getLogger(ListeDeDiscution.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("liste non créer");}
    }



    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "ListeDeContenu{" + "date=" + date + ", text=" + text + ", personne=" + personne + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.text);
        hash = 97 * hash + Objects.hashCode(this.personne);
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
        final ListeDeContenu other = (ListeDeContenu) obj;
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.personne, other.personne)) {
            return false;
        }
        return true;
    }

    public String getNomListe() {
        return nomListe;
    }
    
    

}
