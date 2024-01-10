package game.geometry;

public class Coordinates implements Cloneable{
    private float x;
    public float y;

    public float speed;
    public float speedMax;

    public Coordinates(int x, int y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.speedMax = speed;
    }

    public Coordinates(float x, float y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.speedMax = speed;
    }

    public int getX() {
        return (int) x;
    }
    public int getY() {
        return Math.round(y);
    }

    public float getXAsFloat(){return x;}
    public float getYAsFloat(){return y;}

    public void setSpeed(float speed){
        this.speed = speed;
        this.speedMax = speed;
    }

    public void moveLeft(){
        y -= speed;
    }

    public void moveRight(){
        y += speed;
    }

    public static Coordinates coordonateToPoint(String c){
        if(c.length() != 2) return null;
        int x = (c.charAt(0) - 'A');
        int y = Integer.valueOf(c.charAt(1) + "") - 1;
        return new Coordinates(x, y, 0.23f);
    }

    public String toString(){
        return "(" + getX() + "," + getY() + ")";
    }

    public Coordinates clone(){
        return new Coordinates(x, y, speed);
    }
}
