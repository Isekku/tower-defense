package game.geometry;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
        System.out.println(this);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void moveLeft(){
        y -= 1;
    }

    public void moveRight(){
        y += 1;
    }

    public static Coordinates coordonateToPoint(String c){
        if(c.length() != 2) return null;
        int x = (c.charAt(0) - 'A');
        int y = Integer.valueOf(c.charAt(1) + "") - 1;
        return new Coordinates(x, y);
    }

    public String toString(){
        return "(" + getX() + "," + getY() + ")";
    }
}
