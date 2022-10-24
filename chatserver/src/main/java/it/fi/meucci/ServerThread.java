package it.fi.meucci;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

class ServerThread extends Thread {

    ServerSocket socket = null;
    Socket client = null;
    String stringRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalCliente;
    DataOutputStream outVersoCliente;
    ArrayList<Socket> socketConnessi = new ArrayList<>();

    public ServerThread(Socket socket) {
        this.client = socket;
    }

    public void run() {
        try {
            comunica();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {
        inDalCliente = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoCliente = new DataOutputStream(client.getOutputStream());
        socketConnessi.add(client);
        for (;;) {
            stringRicevuta = inDalCliente.readLine();
            if (stringRicevuta.equals("fine")) {
                outVersoCliente.writeBytes(stringRicevuta + "server in chiusura" + "\n");
                System.out.println("Echo sul server in chiusura: " + stringRicevuta);
                break;
            }

            else if(stringRicevuta.equals("chiudi tutti i socket")) {
                this.chiusuraCompleta();
            }

            else {
                outVersoCliente.writeBytes(stringRicevuta + "(ricevuta e inviata nuovamente)" + "\n");
                System.out.println("6 echo sul server: " + stringRicevuta);
            }
        }
        outVersoCliente.close();
        inDalCliente.close();
        System.out.println("chiusura socket" + client);
        client.close();
    }

    public void chiusuraCompleta() throws IOException {
        for(int i=0; i<socketConnessi.size(); i++){
            socketConnessi.get(i).close();   
        }
    }
}
