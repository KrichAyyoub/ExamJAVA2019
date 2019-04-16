/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krich
 */
public class VM1 {

    private ServerSocket serverSocket;
    private Socket socket;
    public static final int port = 10000;
    private static final int poolSize = 5;
    private ExecutorService pool = null;
    private Boolean isFinished = false;
    public ArrayList<ListeDeDiscution> AllList = new ArrayList();
    public ArrayList<ListeDePersonne> AllListp = new ArrayList();
    public ArrayList<ListeDeContenu> AllListc = new ArrayList();
    public VM2 p2;

    //Getters&setters
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public ArrayList<ListeDeDiscution> getAllList() {
        return AllList;
    }

    public void setAllList(ArrayList<ListeDeDiscution> AllList) {
        this.AllList = AllList;
    }

    //constructor
    public VM1(int port, int size) {
        try {
            serverSocket = new ServerSocket(port, size);
            pool = Executors.newFixedThreadPool(poolSize);
        } catch (IOException ex) {
            Logger.getLogger(VM1.class.
                    getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int getPort() {
        return port;
    }

    public ArrayList<ListeDePersonne> getAllListp() {
        return AllListp;
    }

    public ArrayList<ListeDeContenu> getAllListc() {
        return AllListc;
    }

    public VM2 getP2() {
        return p2;
    }

    public void ManageRequest() {
        while (!isFinished) {
            try {

                // SI VM1 est a 1 on excute avec l'esclave 1
                pool.execute(new Esclave(serverSocket.accept(), this));
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                try {
                    
                    // je met tous ca en commentaire parce que j'ai une erreur d'addresse deja utilise
                    // il faut juste deviser les packages dans plusieurs dossier 
                    // mettre les Virtuel Machines dans des dossiers a part et les client de test a part 
                    
                    
                    // SI VM1 n'est pas a 1 on excute avec l'esclave 2
                   // p2 = new VM2(VM2.port, 10);
                   // pool.execute(new EsclaveVM2(serverSocket.accept(), p2));
                   
                   
                   
                   
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                }
            }
        }
    }

    public boolean ManageList(ListeDeDiscution l) {
        boolean added = AllList.add(l);
        return added;
    }

    public boolean ManageListp(ListeDePersonne l) {
        boolean added = AllListp.add(l);
        return added;
    }

    public boolean ManageListc(ListeDeContenu l) {
        boolean added = AllListc.add(l);
        return added;
    }

    void DeleteList(ListeDeDiscution n) {
        AllList.remove(n);
    }

    void DeleteList(ListeDePersonne n) {
        AllListp.remove(n);
    }

    void DeleteList(ListeDeContenu n) {
        AllListc.remove(n);
    }

}
