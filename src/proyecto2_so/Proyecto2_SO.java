package proyecto2_so;

import interfaz.Interfaz;
import java.util.concurrent.Semaphore;

public class Proyecto2_SO {

    public static void main(String[] args) {

        Interfaz GUI = new Interfaz();
        GUI.setVisible(true);
        GUI.setLocationRelativeTo(null);
        
        Semaphore mutex = new Semaphore(1);
        Colas colas = new Colas(GUI);
        Admin admin = new Admin(mutex, colas, GUI);
        IA ia = new IA(mutex, colas, GUI);
        
        admin.start();
        ia.start();
    }
    
}