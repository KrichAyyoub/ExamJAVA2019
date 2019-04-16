package exam2019;


import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @author krich
 * */


public interface Serveur_annexe extends Remote{
//extension de l’interface remote
public void showDigest(String s) throws RemoteException;
}
