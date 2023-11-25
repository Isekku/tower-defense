package game;

import game.ui.Terminal;
import game.ui.Window;

public class View {
    private Terminal terminal;
    private Window window;
    
    public View(Terminal terminal, Window window) {
        this.terminal = terminal;
        this.window = window;
    }
    public View(Terminal terminal) {
        this.terminal = terminal;
    }
    public View(Window window) {
        this.window = window;
    }

}
