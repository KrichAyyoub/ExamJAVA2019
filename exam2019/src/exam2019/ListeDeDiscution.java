/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;


    	
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.mail.internet.AddressException;



/**
 *
 * @author krich
 */


public class ListeDeDiscution {

    /**
     * @param args the command line arguments
     */
    public String nomListe;
    public theme theme;
    public enum theme {
        sociale ("sociale"),
        evenement ("evenement"),
        reunion ("reunion"),
        nouvelles ("nouvelles");
        private  String theme;
        theme(String theme) {
            this.theme=theme;
        }
    }
    public Personne diffuseur;
    private String password;
    private List<Personne> abonnes = new ArrayList();

    //Getters & Setters

    public String getNomListe() {
        return nomListe;
    }

    public void setNomListe(String nomListe) {
        this.nomListe = nomListe;
    }

    public String getTheme() {
        return theme.theme;
    }

    public void setTheme(theme theme) {
        this.theme = theme;
    }

    public Personne getDiffuseur() {
        return diffuseur;
    }

    public void setDiffuseur(Personne diffuseur) {
        this.diffuseur = diffuseur;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  List<Personne> getAbonnes() {
        return abonnes;
    }

    public void setAbonnes(List<Personne> abonne) {
        abonnes = abonne;
    }


    //constructeur

    public ListeDeDiscution(String nomListe, String th, String mail_diffuseur, String password) {
        this.nomListe = nomListe;
        this.theme = theme.valueOf(th);
        this.password = password;
        try{
            this.diffuseur = new Personne(mail_diffuseur) ;
            System.out.println("liste crée ! nom de la liste: " +this.nomListe +
                    ", theme choisi: "+ this.theme.theme+ ", adresse mail du proprietaire: "
                    +this.diffuseur.getMailAdress());
           // AllList.add(this); //on garde une liste de toutes les listes qu'on crée
        } catch(Exception e){
            Logger.getLogger(ListeDeDiscution.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("liste non créer");}
    }



    //ajouter abonne
    public boolean addAbonne(String mailabonne) {
        Personne abonne;
        try {
            abonne = new Personne(mailabonne);
            abonnes.add(abonne);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ListeDeDiscution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }


    @Override
    public String toString() {
        return "ListeDeDiffusion{" +
                "nomListe='" + nomListe + '\'' +
                ", theme=" + theme +
                ", diffuseur=" + diffuseur +
                ", password='" + password + '\'' +
                ", abonnes=" + abonnes +
                '}';
    }

    //supprimer abonne
    public  boolean removeAbonne(String mailabonne) {
        for(Personne abonne : abonnes)
            if (abonne.getMailAdress().equals(mailabonne))
            {
                System.out.println("Abonné trouvé !");
                abonnes.remove(abonne);
                System.out.println("Abonné supprimé avec succes");
                return true;
            }
        System.out.println("Abonné n'existe pas");
        return false;
    }

}
    
