package game.ui;

import java.io.Console;
import java.util.Scanner;

import game.View;
import game.map.Map;
import game.ui.Style;

import static game.ui.Style.*;

public class Terminal implements View{
    public String nom;
    public int chooseMode = -1;
    public int chooseMap = -1;
    public final int longueur = 9;
    public final int largeur = 5;
    public Terminal(){
        Scanner scanner = new Scanner(System.in);

        //Demande du nom :
        while(this.nom == null){
            System.out.print(stringBase +"Quel est votre nom ? : " + stringGras + stringCouleurPourpre);
            String nom = scanner.nextLine();
            nom = nom.toUpperCase();
            boolean valid = true;
            for(int i = 0; i < nom.length(); i++){
                if(!(nom.charAt(i) >= 'A' && nom.charAt(i) <= 'Z')) valid = false;
            }
            if(valid) this.nom = nom;
            else System.out.println(stringGras + stringCouleurRouge + "Le nom entré n'est pas correct !");
        }

        //Demande du mode choisi :
        while(this.chooseMode == -1){
            System.out.print(stringBase + "Quel mode de jeu souhaité vous ? " + stringGras + stringCouleurCyan + "(1) Normal " + stringBase + " | " + stringGras + stringCouleurRouge + "(2) Marathon " +  stringBase + ": ");
            String chooseMode = scanner.nextLine();
            boolean valid = true;
            if(!chooseMode.equals("1") && !chooseMode.equals("2")){
                valid = false;
            }
            if(valid) this.chooseMode = Integer.valueOf(chooseMode) - 1;
            else System.out.println(stringGras + stringCouleurRouge + "La valeur entré est incorrect !");
        }

        //Demande du choix de la map :
        while(this.chooseMap == -1){
            System.out.print(stringBase + "Quel est la map souhaité vous ? " + stringGras +stringCouleurVert + "(1) Plaine des Vertus " + stringBase + " | " + stringGras + stringCouleurJaune + "(2) La Voie Royale " +  stringBase + ": ");
            String chooseMap = scanner.nextLine();
            boolean valid = true;
            if(!chooseMap.equals("1") && !chooseMap.equals("2")){
                valid = false;
            }
            if(valid) this.chooseMap = Integer.valueOf(chooseMap) - 1;
            else System.out.println(stringGras + stringCouleurRouge + "La valeur entré est incorrect !" + stringCouleurRouge);
        }
    }

    public void update(Map map){
        // clear screen
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
        // print map
        System.out.println(map);
    }
    



}
