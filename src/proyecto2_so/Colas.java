package proyecto2_so;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import interfaz.Interfaz;

public class Colas {
    
    public int numBattles = 0;
    Queue<Object> cola1_RS = new LinkedList<>();
    Queue<Object> cola2_RS = new LinkedList<>();
    Queue<Object> cola3_RS = new LinkedList<>();
    Queue<Object> cola4_RS = new LinkedList<>();
    
    ArrayList<Integer> skillPoints_RS1 = new ArrayList<Integer>();
    ArrayList<Integer> skillPoints_RS2 = new ArrayList<Integer>();
    ArrayList<Integer> skillPoints_RS3 = new ArrayList<Integer>();
    ArrayList<Integer> skillPoints_RS4 = new ArrayList<Integer>();
    
    ArrayList<Integer> counters_RS2 = new ArrayList<Integer>();
    ArrayList<Integer> counters_RS3 = new ArrayList<Integer>();
    
    ArrayList<Integer> specialPoints_RS1 = new ArrayList<Integer>();
    ArrayList<Integer> specialPoints_RS2 = new ArrayList<Integer>();
    ArrayList<Integer> specialPoints_RS3 = new ArrayList<Integer>();
    ArrayList<Integer> specialPoints_RS4 = new ArrayList<Integer>();
    
    ArrayList<String> specialAbility_RS1 = new ArrayList<String>();
    ArrayList<String> specialAbility_RS2 = new ArrayList<String>();
    ArrayList<String> specialAbility_RS3 = new ArrayList<String>();
    ArrayList<String> specialAbility_RS4 = new ArrayList<String>();
    
    Queue<Object> cola1_A = new LinkedList<>();
    Queue<Object> cola2_A = new LinkedList<>();
    Queue<Object> cola3_A = new LinkedList<>();
    Queue<Object> cola4_A = new LinkedList<>();
    
    ArrayList<Integer> skillPoints_A1 = new ArrayList<Integer>();
    ArrayList<Integer> skillPoints_A2 = new ArrayList<Integer>();
    ArrayList<Integer> skillPoints_A3 = new ArrayList<Integer>();
    ArrayList<Integer> skillPoints_A4 = new ArrayList<Integer>();
    
    ArrayList<Integer> counters_A2 = new ArrayList<Integer>();
    ArrayList<Integer> counters_A3 = new ArrayList<Integer>();
    
    ArrayList<Integer> specialPoints_A1 = new ArrayList<Integer>();
    ArrayList<Integer> specialPoints_A2 = new ArrayList<Integer>();
    ArrayList<Integer> specialPoints_A3 = new ArrayList<Integer>();
    ArrayList<Integer> specialPoints_A4 = new ArrayList<Integer>();
    
    ArrayList<String> specialAbility_A1 = new ArrayList<String>();
    ArrayList<String> specialAbility_A2 = new ArrayList<String>();
    ArrayList<String> specialAbility_A3 = new ArrayList<String>();
    ArrayList<String> specialAbility_A4 = new ArrayList<String>();
    
    private Interfaz GUI;

    public Colas(Interfaz i){
        this.GUI = i;
    }
    
    //COLAS UN SHOW MAS---------------------------------------------------------
    public void updateColas_RS(String index, int skillPoints, int priority, String specialAbility, int specialPoints){
        if (priority == 1){
            cola1_RS.add(index);
            skillPoints_RS1.add(skillPoints);
            specialPoints_RS1.add(specialPoints);
            specialAbility_RS1.add(specialAbility);
            
        }
        else if (priority == 2){
            cola2_RS.add(index);
            skillPoints_RS2.add(skillPoints);
            counters_RS2.add(0);
            specialPoints_RS2.add(specialPoints);
            specialAbility_RS2.add(specialAbility);
        }
        else {
            cola3_RS.add(index);
            skillPoints_RS3.add(skillPoints);
            counters_RS3.add(0);
            specialPoints_RS3.add(specialPoints);
            specialAbility_RS3.add(specialAbility);
        }
    
    }
    
    //COLAS AVATAR--------------------------------------------------------------
    public void updateColas_A(String index, int skillPoints, int priority, String specialAbility, int specialPoints){
        if (priority == 1){
            cola1_A.add(index);
            skillPoints_A1.add(skillPoints);
            specialPoints_A1.add(specialPoints);
            specialAbility_A1.add(specialAbility);
            
        }
        else if (priority == 2){
            cola2_A.add(index);
            skillPoints_A2.add(skillPoints);
            counters_A2.add(0);
            specialPoints_A2.add(specialPoints);
            specialAbility_A2.add(specialAbility);
        }
        else {
            cola3_A.add(index);
            skillPoints_A3.add(skillPoints);
            counters_A3.add(0);
            specialPoints_A3.add(specialPoints);
            specialAbility_A3.add(specialAbility);
        }
    }
    
}
