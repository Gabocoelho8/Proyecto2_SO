package proyecto2_so;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaz.Interfaz;

public class IA extends Thread{
    
    private Semaphore mutex;
    private Colas colas;
    private int initCount = 0;
    private Interfaz GUI;
    private Integer totalVictories_A_Int = 0;
    private Integer totalVictories_RS_Int = 0;
    private String Status = "";
    Random rand = new Random();
        
    public IA(Semaphore m, Colas c, Interfaz i){
      this.mutex = m;
      this.colas = c;
      this.GUI = i;
    }

@Override
    public void run(){
        while(true) {
            try {
                if(initCount == 0){
                    Status = "Esperando";
                    this.GUI.setStatus_IA(Status);
                    sleep(2000);
                    initCount += 1;
                }
                this.mutex.acquire();
                Status = "Decidiendo";
                this.GUI.setStatus_IA(Status);
                sleep(500);
                battles();
                updateCounters();
                changeCola();
                removeCola4();
                Status = "Anunciando";
                this.GUI.setStatus_IA(Status);
                sleep(this.GUI.time * 1000);
                this.mutex.release();
                Status = "Esperando";
                this.GUI.setStatus_IA(Status);
                sleep(1250);
            } catch (InterruptedException ex) {
                Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateCounters(){
        for(int i = 0; i < this.colas.counters_A2.size(); i++){
            int k = this.colas.counters_A2.get(i) + 1;
            this.colas.counters_A2.remove(i);
            this.colas.counters_A2.add(i, k);
        }
        
        for(int i = 0; i < this.colas.counters_A3.size(); i++){
            int k = this.colas.counters_A3.get(i) + 1;
            this.colas.counters_A3.remove(i);
            this.colas.counters_A3.add(i, k);
        }
        
        for(int i = 0; i < this.colas.counters_RS2.size(); i++){
            int k = this.colas.counters_RS2.get(i) + 1;
            this.colas.counters_RS2.remove(i);
            this.colas.counters_RS2.add(i, k);
        }
        
        for(int i = 0; i < this.colas.counters_RS3.size(); i++){
            int k = this.colas.counters_RS3.get(i) + 1;
            this.colas.counters_RS3.remove(i);
            this.colas.counters_RS3.add(i, k);
        }
    }
    
    public void changeCola(){
        int removeThis_A2 = 0;
        int removeThis_A3 = 0;
        int removeThis_RS2 = 0;
        int removeThis_RS3 = 0;
        
        //PASAR DE LA COLA 3 A LA COLA 2 EN AVATAR (SI EL CONTADOR >= 8)--------
        for(int i = 0; i < this.colas.counters_A2.size(); i++){
            if(this.colas.counters_A2.get(i) >= 8){
                this.colas.cola1_A.add(this.colas.cola2_A.remove());
                this.colas.specialPoints_A1.add(this.colas.specialPoints_A2.getFirst());
                this.colas.skillPoints_A1.add(this.colas.skillPoints_A2.getFirst());
                this.colas.specialAbility_A1.add(this.colas.specialAbility_A2.getFirst());
                
                this.colas.specialPoints_A2.remove(0);
                this.colas.skillPoints_A2.remove(0);
                this.colas.specialAbility_A2.remove(0);
                removeThis_A2 ++;
            }
        }
        
        if(removeThis_A2 != 0){
            for(int i = 0; i < removeThis_A2; i++){
                this.colas.counters_A2.remove(0);
            }
        }
        
        //PASAR DE LA COLA 3 A LA COLA 2 EN AVATAR (SI EL CONTADOR >= 8)--------
        for(int i = 0; i < this.colas.counters_A3.size(); i++){
            if(this.colas.counters_A3.get(i) >= 8){
                this.colas.cola2_A.add(this.colas.cola3_A.remove());
                this.colas.specialPoints_A2.add(this.colas.specialPoints_A3.getFirst());
                this.colas.skillPoints_A2.add(this.colas.skillPoints_A3.getFirst());
                this.colas.specialAbility_A2.add(this.colas.specialAbility_A3.getFirst());
                
                this.colas.specialPoints_A3.remove(0);
                this.colas.skillPoints_A3.remove(0);
                this.colas.specialAbility_A3.remove(0);
                removeThis_A3 ++;
            }
        }
        
        if(removeThis_A3 != 0){
            for(int i = 0; i < removeThis_A3; i++){
                this.colas.counters_A2.add(1);
                this.colas.counters_A3.remove(0);
            }
        }
        
        //PASAR DE LA COLA 2 A LA COLA 1 EN REGULAR SHOW (SI EL CONTADOR >= 8)--
        for(int i = 0; i < this.colas.counters_RS2.size(); i++){
            if(this.colas.counters_RS2.get(i) >= 8){
                this.colas.cola1_RS.add(this.colas.cola2_RS.remove());
                this.colas.specialPoints_RS1.add(this.colas.specialPoints_RS2.getFirst());
                this.colas.skillPoints_RS1.add(this.colas.skillPoints_RS2.getFirst());
                this.colas.specialAbility_RS1.add(this.colas.specialAbility_RS2.getFirst());
                
                this.colas.specialPoints_RS2.remove(0);
                this.colas.skillPoints_RS2.remove(0);
                this.colas.specialAbility_RS2.remove(0);
                removeThis_RS2 ++;
            }
        }
        
        if(removeThis_RS2 != 0){
            for(int i = 0; i < removeThis_RS2; i++){
                this.colas.counters_RS2.remove(0);
            }
        }
        
        //PASAR DE LA COLA 3 A LA COLA 2 EN REGULAR SHOW (SI EL CONTADOR >= 8)--
        for(int i = 0; i < this.colas.counters_RS3.size(); i++){
            if(this.colas.counters_RS3.get(i) >= 8){
                this.colas.cola2_RS.add(this.colas.cola3_RS.remove());
                this.colas.specialPoints_RS2.add(this.colas.specialPoints_RS3.getFirst());
                this.colas.skillPoints_RS2.add(this.colas.skillPoints_RS3.getFirst());
                this.colas.specialAbility_RS2.add(this.colas.specialAbility_RS3.getFirst());
                
                this.colas.specialPoints_RS3.remove(0);
                this.colas.skillPoints_RS3.remove(0);
                this.colas.specialAbility_RS3.remove(0);
                removeThis_RS3 ++;
            }
        }
        
        if(removeThis_RS3 != 0){
            for(int i = 0; i < removeThis_RS3; i++){
                this.colas.counters_RS2.add(1);
                this.colas.counters_RS3.remove(0);
            }
        }
    }
    
    public void battles(){
        Object contestant_A = "";
        Object contestant_RS = "";
        Integer battlePoints_A = 0;
        Integer battlePoints_RS = 0;
        Integer specialPoints_A = 0;
        Integer skillPoints_A = 0;
        Integer specialPoints_RS = 0;
        Integer skillPoints_RS = 0;
        String specialAbility_A = "";
        String specialAbility_RS = "";
        
        int outcome = rand.nextInt(100 - 1 + 1) + 1;
        
        //ELEGIR COMBATIENTE AVATAR----------------------------------------------
        if(this.colas.cola1_A.size() != 0){
            contestant_A = this.colas.cola1_A.element();
            specialPoints_A = this.colas.specialPoints_A1.getFirst();
            skillPoints_A = this.colas.skillPoints_A1.getFirst();
            specialAbility_A = this.colas.specialAbility_A1.getFirst();
            battlePoints_A = specialPoints_A + skillPoints_A;
            this.colas.cola1_A.remove();
            this.colas.specialPoints_A1.remove(0);
            this.colas.skillPoints_A1.remove(0);
            this.colas.specialAbility_A1.remove(0);
            
        }
        
        else if(this.colas.cola2_A.size() != 0){
            contestant_A = this.colas.cola2_A.element();
            specialPoints_A = this.colas.specialPoints_A2.getFirst();
            skillPoints_A = this.colas.skillPoints_A2.getFirst();
            specialAbility_A = this.colas.specialAbility_A2.getFirst();
            battlePoints_A = specialPoints_A + skillPoints_A;
            this.colas.cola2_A.remove();
            this.colas.specialPoints_A2.remove(0);
            this.colas.skillPoints_A2.remove(0);
            this.colas.specialAbility_A2.remove(0);
            this.colas.counters_A2.remove(0);
        }
        
        else if (this.colas.cola3_A.size() != 0){
            contestant_A = this.colas.cola3_A.element();
            specialPoints_A = this.colas.specialPoints_A3.getFirst();
            skillPoints_A = this.colas.skillPoints_A3.getFirst();
            specialAbility_A = this.colas.specialAbility_A3.getFirst();
            battlePoints_A = specialPoints_A + skillPoints_A;
            this.colas.cola3_A.remove();
            this.colas.specialPoints_A3.remove(0);
            this.colas.skillPoints_A3.remove(0);
            this.colas.specialAbility_A3.remove(0);
            this.colas.counters_A3.remove(0);
            
        }
        
        //ELEGIR COMBATIENTE UN SHOW MAS----------------------------------------
        if(this.colas.cola1_RS.size() != 0){
            contestant_RS = this.colas.cola1_RS.element();
            specialPoints_RS = this.colas.specialPoints_RS1.getFirst();
            skillPoints_RS = this.colas.skillPoints_RS1.getFirst();
            specialAbility_RS = this.colas.specialAbility_RS1.getFirst();
            battlePoints_RS = specialPoints_RS + skillPoints_RS;
            this.colas.cola1_RS.remove();
            this.colas.specialPoints_RS1.remove(0);
            this.colas.skillPoints_RS1.remove(0);
            this.colas.specialAbility_RS1.remove(0);
        }
        
        else if(this.colas.cola2_RS.size() != 0){
            contestant_RS = this.colas.cola2_RS.element();
            specialPoints_RS = this.colas.specialPoints_RS2.getFirst();
            skillPoints_RS = this.colas.skillPoints_RS2.getFirst();
            specialAbility_RS = this.colas.specialAbility_RS2.getFirst();
            battlePoints_RS = specialPoints_RS + skillPoints_RS;
            this.colas.cola2_RS.remove();
            this.colas.specialPoints_RS2.remove(0);
            this.colas.skillPoints_RS2.remove(0);
            this.colas.specialAbility_RS2.remove(0);
            this.colas.counters_RS2.remove(0);
        }
        
        else if (this.colas.cola3_RS.size() != 0){
            contestant_RS = this.colas.cola3_RS.element();
            specialPoints_RS = this.colas.specialPoints_RS3.getFirst();
            skillPoints_RS = this.colas.skillPoints_RS3.getFirst();
            specialAbility_RS = this.colas.specialAbility_RS3.getFirst();
            battlePoints_RS = specialPoints_RS + skillPoints_RS;
            this.colas.cola3_RS.remove();
            this.colas.specialPoints_RS3.remove(0);
            this.colas.skillPoints_RS3.remove(0);
            this.colas.specialAbility_RS3.remove(0);
            this.colas.counters_RS3.remove(0);
        }
        this.colas.numBattles += 1;
        
        //RESULTADO BATALLA INTERFAZ--------------------------------------------
        this.GUI.setNumBattles(this.colas.numBattles);
        
        this.GUI.setCombatiente_A(contestant_A);
        this.GUI.setSkillPoints_A(skillPoints_A);
        this.GUI.setSpecialPoints_A(specialPoints_A);
        this.GUI.setTotalPoints_A(battlePoints_A);
        this.GUI.setPoder_A(specialAbility_A);
        
        this.GUI.setCombatiente_RS(contestant_RS);
        this.GUI.setSkillPoints_RS(skillPoints_RS);
        this.GUI.setSpecialPoints_RS(specialPoints_RS);
        this.GUI.setTotalPoints_RS(battlePoints_RS);
        this.GUI.setPoder_RS(specialAbility_RS);
        
        //SI NO HAY POR LO MENOS 1 PERSONAJE DE CADA LADO NO SE REALIZA BATALLAS
        if (battlePoints_A == 0 || battlePoints_RS == 0){
                System.out.println("No Hay Combatientes");
            }
        
        else if(outcome <= 40){
            //GANA EL QUE TENGA MAS PUNTOS COMBINADOS---------------------------
            if(battlePoints_A > battlePoints_RS){
                this.GUI.setWinner(contestant_A);
                totalVictories_A_Int += 1;
                this.GUI.setTotalVictories_A(totalVictories_A_Int);
            }
            else if (battlePoints_RS > battlePoints_A){
                this.GUI.setWinner(contestant_RS);
                totalVictories_RS_Int += 1;
                this.GUI.setTotalVictories_RS(totalVictories_RS_Int);
            }
            //EN CASO DE EMPATAR EN PUNTOS SE ELIGE UN GANADOR ALEATORIO--------
            else{ 
                int draw = rand.nextInt(1);
                if(draw == 0){
                    this.GUI.setWinner(contestant_A);
                    totalVictories_A_Int += 1;
                    this.GUI.setTotalVictories_A(totalVictories_A_Int);
                }
                else{
                    this.GUI.setWinner(contestant_RS);
                    totalVictories_RS_Int += 1;
                    this.GUI.setTotalVictories_RS(totalVictories_RS_Int);
                }
            }
            this.GUI.setOutcome("GANADOR");
        }
        //EMPATE SE DEVUELVAN A COLA 1------------------------------------------
        else if(outcome > 40 && outcome < 67){
            this.GUI.setOutcome("EMPATE");
            this.GUI.setWinner("");
            this.colas.cola1_A.add(contestant_A);
            this.colas.cola1_RS.add(contestant_RS);
            this.colas.specialPoints_A1.add(specialPoints_A);
            this.colas.skillPoints_A1.add(skillPoints_A);
            this.colas.specialPoints_RS1.add(specialPoints_RS);
            this.colas.skillPoints_RS1.add(skillPoints_RS);
            this.colas.specialAbility_A1.add(specialAbility_A);
            this.colas.specialAbility_RS1.add(specialAbility_RS);
        
        }
        //REFUERZO SE ENVIAN A COLA 4-------------------------------------------
        else{
            this.GUI.setOutcome("REFUERZO");
            this.GUI.setWinner("");
            this.colas.cola4_A.add(contestant_A);
            this.colas.cola4_RS.add(contestant_RS);
            this.colas.specialPoints_A4.add(specialPoints_A);
            this.colas.skillPoints_A4.add(skillPoints_A);
            this.colas.specialPoints_RS4.add(specialPoints_RS);
            this.colas.skillPoints_RS4.add(skillPoints_RS);
            this.colas.specialAbility_A4.add(specialAbility_A);
            this.colas.specialAbility_RS4.add(specialAbility_RS);
            
        
        }
        
        //UPDATE INTERFAZ--------------------------------------------------------------
        this.GUI.setCola1_A(this.colas.cola1_A);
        this.GUI.setCola2_A(this.colas.cola2_A);
        this.GUI.setCola3_A(this.colas.cola3_A);
        this.GUI.setRefuerzo_A(this.colas.cola4_A);
        
        this.GUI.setCola1_RS(this.colas.cola1_RS);
        this.GUI.setCola2_RS(this.colas.cola2_RS);
        this.GUI.setCola3_RS(this.colas.cola3_RS);
        this.GUI.setRefuerzo_RS(this.colas.cola4_RS);
    }
    
    public void removeCola4(){
        //SACAR DE LA COLA 4 AVATAR (REFUERZO)----------------------------------
        int removeCola4_A = rand.nextInt(100);
        
        if(this.colas.cola4_A.size() != 0){
            if(removeCola4_A < 40){
                this.colas.cola1_A.add(this.colas.cola4_A.element());
                this.colas.specialPoints_A1.add(this.colas.specialPoints_A4.getFirst());
                this.colas.skillPoints_A1.add(this.colas.skillPoints_A4.getFirst());
                this.colas.specialAbility_A1.add(this.colas.specialAbility_A4.getFirst());
                this.colas.cola4_A.remove();
                this.colas.specialPoints_A4.removeFirst();
                this.colas.skillPoints_A4.removeFirst();
                this.colas.specialAbility_A4.removeFirst();
            }
            else{
                this.colas.cola4_A.add(this.colas.cola4_A.element());
                this.colas.cola4_A.remove();
                this.colas.specialPoints_A4.addLast(this.colas.specialPoints_A4.getFirst());
                this.colas.specialPoints_A4.removeFirst();
                this.colas.skillPoints_A4.addLast(this.colas.skillPoints_A4.getFirst());
                this.colas.skillPoints_A4.removeFirst();
                this.colas.specialAbility_A4.addLast(this.colas.specialAbility_A4.getFirst());
                this.colas.specialAbility_A4.removeFirst();
            }
        }
        //SACAR DE LA COLA 4 UN SHOW MAS (REFUERZO)-----------------------------
        int removeCola4_RS = rand.nextInt(100);
        
        if(this.colas.cola4_RS.size() != 0){
            if(removeCola4_RS < 40){
                this.colas.cola1_RS.add(this.colas.cola4_RS.element());
                this.colas.specialPoints_RS1.add(this.colas.specialPoints_RS4.getFirst());
                this.colas.skillPoints_RS1.add(this.colas.skillPoints_RS4.getFirst());
                this.colas.specialAbility_RS1.add(this.colas.specialAbility_RS4.getFirst());
                this.colas.cola4_RS.remove();
                this.colas.specialPoints_RS4.removeFirst();
                this.colas.skillPoints_RS4.removeFirst();
                this.colas.specialAbility_RS4.removeFirst();
            }
            else{
                this.colas.cola4_RS.add(this.colas.cola4_RS.element());
                this.colas.cola4_RS.remove();
                this.colas.specialPoints_RS4.addLast(this.colas.specialPoints_RS4.getFirst());
                this.colas.specialPoints_RS4.removeFirst();
                this.colas.skillPoints_RS4.addLast(this.colas.skillPoints_RS4.getFirst());
                this.colas.skillPoints_RS4.removeFirst();
                this.colas.specialAbility_RS4.addLast(this.colas.specialAbility_RS4.getFirst());
                this.colas.specialAbility_RS4.removeFirst();
            }
        }
        
        //UPDATE INTERFAZ-------------------------------------------------------
        this.GUI.setCola1_A(this.colas.cola1_A);
        this.GUI.setCola2_A(this.colas.cola2_A);
        this.GUI.setCola3_A(this.colas.cola3_A);
        this.GUI.setRefuerzo_A(this.colas.cola4_A);
        
        this.GUI.setCola1_RS(this.colas.cola1_RS);
        this.GUI.setCola2_RS(this.colas.cola2_RS);
        this.GUI.setCola3_RS(this.colas.cola3_RS);
        this.GUI.setRefuerzo_RS(this.colas.cola4_RS);
    }
}
