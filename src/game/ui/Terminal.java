package game.ui;

import java.io.Console;
import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

import game.Entity.Mobs.Mob;
import game.Entity.towers.Tower;
import game.Game;
import game.Model;
import game.View;
import game.geometry.Coordinates;
import game.map.Map;
import game.ui.Style;

import static game.ui.Style.*;

public class Terminal implements View{
    public String nom;
    public boolean win = false;
    public boolean lose = false;
    private final Model model = Model.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    public Terminal(){
        clearScreen();

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
            else System.out.println(stringErrorMessage);
        }

        //Demande du mode choisi :
        while(model.getMode() == -1){
            System.out.print(stringBase + "Quel mode de jeu souhaitez-vous ? " + stringGras + stringCouleurCyan + "(1) Normal " + stringBase + " | " + stringGras + stringCouleurRouge + "(2) Marathon " +  stringBase + ": ");
            String chooseMode = scanner.nextLine();
            boolean valid = true;
            if(!chooseMode.equals("1") && !chooseMode.equals("2")) valid = false;
            if(valid) model.setMode(Integer.valueOf(chooseMode) - 1);
            else System.out.println(stringErrorMessage);
        }

        //Demande du choix de la map :
        while(model.getMapType() == -1){
            System.out.print(stringBase + "Quel map souhaitez-vous ? " + stringGras +stringCouleurVert + "(1) Plaine des Vertus " + stringBase + " | " + stringGras + stringCouleurJaune + "(2) La Voie Royale " +  stringBase + ": ");
            String chooseMap = scanner.nextLine();
            boolean valid = true;
            if(!chooseMap.equals("1") && !chooseMap.equals("2")) valid = false;
            if(valid)  model.setMapType(Integer.valueOf(chooseMap) - 1);
            else System.out.println(stringErrorMessage);
        }

        //Demande du choix de la difficulté :
        while(model.getDifficulty() == -1){
            System.out.print(stringBase + "Quel est le niveau de difficulté souhaité ? " + stringGras +stringCouleurVert + "(1) Facile " + stringBase + " | " + stringGras + stringCouleurJaune + "(2) Moyen " +  stringBase + " | " + stringGras + stringCouleurRouge + "(3) Difficile "+ stringBase + ": ");
            String chooseMap = scanner.nextLine();
            boolean valid = true;
            if(!chooseMap.equals("1") && !chooseMap.equals("2") && !chooseMap.equals("3")) valid = false;
            if(valid) model.setDifficulty(Integer.valueOf(chooseMap));
            else System.out.println(stringErrorMessage);
        }
    }

    public void play(){
        if(model.getMode() == 0) playNormalMode();
        else playMarathonMode();
    }

    private void playNormalMode(){
        System.out.println("Vous êtes en mode Normal :");
        while(!win && !lose){

            update();
            int choix = choixNormalAction();

            //Le joueur veut placer une tour
            if(choix == 1){
                peutPlacerTour();
            }

            //Le joueur veut commencer la vague
            if(choix == 2){
                cheat();
            }

            //Le joueur veut entrer dans l'état Option
            if(choix == 3){
                //Créer une méthode qui met pause au jeu
                quitOrRestart();
            }
        }
        if(win) System.out.println("Vous avez gagné. Félicitation !");
        if(lose) System.out.println("Vous avez perdu :(");
        System.exit(0);
    }

    private void playMarathonMode(){
        System.out.println("Vous êtes en mode marathon");
        while (!lose){
            int choix = -1;
        }
        if(lose) System.out.println("Vous avez tenu " + "Mettre le nombre de vague ici à prendre dans le model" + " vagues !");
        System.exit(0);
    }

    private void quitOrRestart(){
        int valid = -1;
        while(valid == -1){
            System.out.print(stringBase + "Voulez vous arreter le jeu ? " + stringCouleurRouge + "(1) Oui " + stringBase + " | " + stringCouleurVert + "(2) Non : ");
            String value = scanner.nextLine();
            if(!value.equals("1") && !value.equals("2")) System.out.println(stringErrorMessage);
            else valid = Integer.valueOf(value);
        }
        if(valid == 2){
            //Créer une méthode pour revenir dans le le jeu. Pas besoin finalement
        }
        else{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.exit(0);
        }
    }

    //Méthode regroupant les choix possible dans les 2 modes :
    private int choixNormalAction(){
        int choix = -1;
        while(choix == -1){
            System.out.print(stringBase + "Quel action souhaité vous réaliser ? : " + stringCouleurVert + "(1) Placer une tour" + stringBase + " | " + stringCouleurRouge + "(2) Commencer le tour" + stringBase + " | " + stringCouleurJaune + "(3) Pause" + stringBase + ": " );
            String value = scanner.nextLine();
            if(!value.equals("1") && !value.equals("2") && !value.equals("3")) System.out.println(stringErrorMessage);
            else choix = Integer.valueOf(value);
        }
        return choix;
    }

    private int choixMarathonMode(){
        return -1;
    }

    //Méthode permettant de placer les tours :
    private void peutPlacerTour(){
        model.makeBreak();
        int choix = -1;
        clearScreen();
        while(choix == -1) {
            model.printWithoutMap();
            //Toutes les tours disponibles avec leur prix :
            int val = 1;
            for(int i = 0; i < model.towerExample.size(); i++){
                Tower tower = model.towerExample.get(i);
                System.out.println(stringBase + " (" + (val++) + ") " + tower.couleur + tower.getNom() + ": " + tower.getCost() + " pieces" + stringBase);
            }

            System.out.println('\n' + "(" + val + ") " + "Revenir au jeu" + '\n');

            System.out.print(stringBase + "Quel tour souhaité vous placez ? : ");
            String value = scanner.nextLine();
            //Actions possible :
            boolean valid = false;
            for(int i = 1; i <= val; i++){
                if (value.equals(i + "")) {
                    valid = true;
                    break;
                }
            }
            if(!valid) {
                clearScreen();
                System.out.println(stringErrorMessage + ": " + value);
            }
            else if(value.equals(val + "")) choix = val;
            else{
                int temp = Integer.valueOf(value);
                Tower example = model.towerExample.get(temp-1);
                clearScreen();
                if(model.getMoney() < example.getCost()) System.out.println(stringNotEnoughMoney);
                else choix = temp;
            }
        }
        if(choix == model.towerExample.size()+1){}
        else placerTour(choix);
        model.restartWave();
    }

    private void placerTour(int choix){
        boolean valid = false;
        Tower tower = model.towerExample.get(choix-1);
        while(!valid){
            model.printMap();
            System.out.print(stringBase + stringGras + "Où voulez vous placer la tours ? : ");
            Coordinates value = Coordinates.coordonateToPoint(scanner.nextLine());
            if(value == null) System.out.println(stringErrorMessage);
            else valid = model.setTower(value, tower.clone(value));
            if(!valid){
                clearScreen();
                System.out.println(stringTowerAlreadyHere);
            }
        }
    }

    public void update(){
        clearScreen();

        //Affichage d'information utile :
        System.out.print(stringBase + "Joueur " + stringGras + stringCouleurPourpre + nom + stringBase + " : ");
        model.print();
        System.out.println(stringBase + '\n');
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void cheat(){
        clearScreen();
        boolean valid = false;
        while(!valid){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try{
                        model.startWave();
                    }
                    catch (InterruptedException e){
                        System.out.println("La vague est interrompu !");
                    }
                }
            };
            Thread playThread = new Thread(runnable);
            playThread.start();

            while(!model.winWave()){
                Scanner temp = new Scanner(System.in);
                String answer = temp.nextLine();
                if(answer.equals("1")){
                    peutPlacerTour();
                }
            }
            valid = true;
        }

        /*
            System.out.print("Bonjour mon " + stringCouleurJaune + "roi. " +stringBase + stringGras + "Que voulez-vous faire ? : ");
            String value = scanner.nextLine();
            if(value.equals("add mob")){
                boolean temp = false;
                model.printMap();
                System.out.print("Dans quel hauteur souhaité vous ? : ");
                Coordinates c = Coordinates.coordonateToPoint(scanner.nextLine());
                System.out.print("Quel mob souhaité vous placer ? (0-3) : ");
                temp = model.setMob(c, (model.mobExample.get(scanner.nextInt())).clone(c));
                if(!temp){
                    clearScreen();
                    System.out.println(stringCouleurRouge + "Mon roi, je n'aime pas vous dérangez pour ça mais je penses que vous avez fait une erreur sur les coordonnées" + stringBase);
                }
                else model.printMap();
            }

            if(value.equals("mob on way")){
                model.printMap();
                System.out.print("Quel sont les coordonnées à vérifier ? : ");
                Coordinates c = Coordinates.coordonateToPoint(scanner.nextLine());
                System.out.println(model.mobOnWay(c));
            }

            if(value.equals("tower in front")){
                model.printMap();
                System.out.print("Quel sont les coordonnées à vérifier ? : ");
                Coordinates c = Coordinates.coordonateToPoint(scanner.nextLine());
                System.out.println(model.towerInFront(c));
            }

            if(value.equals("valid")) valid = true;

         */
    }

}
