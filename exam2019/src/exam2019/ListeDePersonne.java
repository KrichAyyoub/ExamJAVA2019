/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krich
 */
public class ListeDePersonne {

    private Personne p;
    private String nom;

    public String getNom() {
        return nom;
    }

    public List<Personne> getPersonne() {
        return personne;
    }

    public String getNomListe() {
        return nomListe;
    }
    private List<Personne> personne = new ArrayList();

    private String nomListe;

    public ListeDePersonne(Personne p) {
        this.p = p;
    }

    public ListeDePersonne(String nomListe, String nom, String mail_diffuseur) {
        this.nomListe = nomListe;
        this.nom = nom;

        try {
            this.p = new Personne(mail_diffuseur);
            System.out.println("liste crée ! nom de la liste: " + this.nomListe
                    + ", nom de la personne " + this.nom + ", adresse mail de la personne: "
                    + this.p.getMailAdress());
            // AllList.add(this); //on garde une liste de toutes les listes qu'on crée
        } catch (Exception e) {
            Logger.getLogger(ListeDeDiscution.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("liste non créer");
        }
    }

    public Personne getP() {
        return p;
    }

    @Override
    public String toString() {
        return "ListeDePersonne{" + "p=" + p + '}';
    }

    public void setP(Personne p) {
        this.p = p;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.p);
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
        final ListeDePersonne other = (ListeDePersonne) obj;
        if (!Objects.equals(this.p, other.p)) {
            return false;
        }
        return true;
    }

}
