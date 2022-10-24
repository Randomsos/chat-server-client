package it.fi.meucci;

import java.io.*;
import java.net.*;
public class ClientStr {
    String nomeServer="localhost";
    int portaServer = 7777;
    Socket miosocket;
    BufferedReader tastiera; 
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalSever;

    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione: ");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalSever = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }

        catch (UnknownHostException e){
            System.err.println("Host sconosciuto");
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }

        return miosocket;
    }

    public void comunica(){
        try{
            for(;;){
                System.out.println("4 inserisci la stringa da trasmettere dal server: "+'\n');
                stringaUtente = tastiera.readLine();
                System.out.println("5 invio la stringa al server");
                outVersoServer.writeBytes(stringaUtente+'\n');

                stringaRicevutaDalServer= inDalSever.readLine();
                System.out.println("8 risposta del server" + '\n'+stringaRicevutaDalServer);
            }
            //System.out.println("9 CLIENT: termina nelaborazione e chiude connessione");
            //miosocket.close();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione");
            System.exit(1);
        }
        }

}
