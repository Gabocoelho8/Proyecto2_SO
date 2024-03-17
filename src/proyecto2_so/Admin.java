package proyecto2_so;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import interfaz.Interfaz;


public class Admin extends Thread{
    
        private Semaphore mutex;
        private Colas colas;
        private String[] names_RS = {"Hi-Five Ghost", "Mordecai", "Benson", "Pops Maellard", "Skips", "Eileen", "Muscle Man", "Rigby"};
        private String[] specialAbility_RS = {"Hi-Fives", "Amistad", "Gumballs", "Clase Alta", "Fuerza", "Inteligencia", "Ira", "Caos"};
        private Integer[] specialPoints_RS = {4, 8, 12, 16, 20, 24, 28, 32};
        private String[] names_A = {"Appa", "Momo", "Sokka", "Katara", "Zuko", "Toph", "Iroh", "Aang"};
        private String[] specialAbility_A = {"Volar", "Supervivencia", "Ingenio", "Agua", "Fuego", "Tierra", "Experiencia", "Elementos"};
        private Integer[] specialPoints_A = {4, 8, 12, 16, 20, 24, 28, 32};
        private int count_A = 1;
        private int count_RS = 1;
        private int initCount = 0;
        private Interfaz GUI;
        Random rand = new Random();
        
        
    public Admin(Semaphore m, Colas c, Interfaz i){
      this.mutex = m;
      this.colas = c;
      this.GUI = i;
    }
    
@Override
    public void run(){
        while(true) {
            try {
                if(initCount == 0){
                    initCharacters();
                }
                else{
                    this.mutex.acquire();
                    if(this.colas.numBattles % 2 == 0 && this.colas.numBattles != 0){
                        createCharacters();
                        sleep(1000);
                    }
                    this.mutex.release();
                    sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void initCharacters(){
        for (int i = 0; i < 20; i++) {
            int nameIndex = rand.nextInt(8);
            int ability = rand.nextInt(10 - 1 + 1) + 1;
            int exp = rand.nextInt(10 - 1 + 1) + 1;
            int strength = rand.nextInt(10 - 1 + 1) + 1;
            int agility = rand.nextInt(10 - 1 + 1) + 1;
            int skillPoints = ability + exp + strength + agility;
            int priority = 3;
            String index = "RS" + (count_RS);
            
            if (skillPoints > 27){
                priority = 1;
            }
            else if (skillPoints > 15){
                priority = 2;
            }
            
            colas.updateColas_RS(index + " " + names_RS[nameIndex], skillPoints, priority, specialAbility_RS[nameIndex], specialPoints_RS[nameIndex]);
            count_RS += 1;
        }
        
        for (int i = 0; i < 20; i++) {
            int nameIndex = rand.nextInt(8);
            int ability = rand.nextInt(10 - 1 + 1) + 1;
            int exp = rand.nextInt(10 - 1 + 1) + 1;
            int strength = rand.nextInt(10 - 1 + 1) + 1;
            int agility = rand.nextInt(10 - 1 + 1) + 1;
            int skillPoints = ability + exp + strength + agility;
            int priority = 3;
            String index = "A" + (count_A);
            
            if (skillPoints > 27){
                priority = 1;
            }
            else if (skillPoints > 15){
                priority = 2;
            }
            
            colas.updateColas_A(index + " " + names_A[nameIndex], skillPoints, priority, specialAbility_A[nameIndex], specialPoints_A[nameIndex]);
            count_A += 1;
        }

        initCount += 1;
    }
    
    public void createCharacters(){
        int newCharacters = rand.nextInt(100);
        
        if(newCharacters < 80 && this.colas.numBattles != 0 && this.colas.numBattles % 2 == 0){
                int nameIndex_RS = rand.nextInt(8);
                int ability_RS = rand.nextInt(10 - 1 + 1) + 1;
                int exp_RS = rand.nextInt(10 - 1 + 1) + 1;
                int strength_RS = rand.nextInt(10 - 1 + 1) + 1;
                int agility_RS = rand.nextInt(10 - 1 + 1) + 1;
                int skillPoints_RS = ability_RS + exp_RS + strength_RS + agility_RS;
                int priority_RS = 3;
                String index_RS = "RS" + (count_RS);
            
                if (skillPoints_RS > 27){
                    priority_RS = 1;
                }
                else if (skillPoints_RS > 15){
                    priority_RS = 2;
                }
            
                colas.updateColas_RS(index_RS + " " + names_RS[nameIndex_RS], skillPoints_RS, priority_RS, specialAbility_RS[nameIndex_RS], specialPoints_RS[nameIndex_RS]);
                count_RS += 1;
        
                int nameIndex_A = rand.nextInt(8);
                int ability_A = rand.nextInt(10 - 1 + 1) + 1;
                int exp_A = rand.nextInt(10 - 1 + 1) + 1;
                int strength_A = rand.nextInt(10 - 1 + 1) + 1;
                int agility_A = rand.nextInt(10 - 1 + 1) + 1;
                int skillPoints_A = ability_A + exp_A + strength_A + agility_A;
                int priority_A = 3;
                String index_A = "A" + (count_A);
            
                if (skillPoints_A > 27){
                    priority_A = 1;
                }
                else if (skillPoints_A > 15){
                    priority_A = 2;
                }
            
                colas.updateColas_A(index_A + " " + names_A[nameIndex_A], skillPoints_A, priority_A, specialAbility_A[nameIndex_A], specialPoints_A[nameIndex_A]);
                count_A += 1;
            }
            
        }
    }
