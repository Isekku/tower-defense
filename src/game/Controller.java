package game;

public class Controller {
    private Model model;
    private View view;

    public Controller() {
        model = new Model();
        // view = view;
    }
    public void printMap(){
        model.printMap();
    }

}
