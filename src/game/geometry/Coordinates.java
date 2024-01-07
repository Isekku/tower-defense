package game.geometry;

public class Coordinates implements Cloneable{
    private float x;
    public float y;

    public float speed = -2;
    public float speedMax = 0.45f;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinates(float x, float y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }
    public int getY() {
        return (int) y;
    }

    public float getXAsFloat(){return x;}
    public float getYAsFloat(){return y;}

    public void moveLeft(){
        y -= 1;
    }
    public void moveLeftWithSpeed(){
        y += speed;
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

    public Coordinates clone(){
        return new Coordinates(x, y);
    }
}
