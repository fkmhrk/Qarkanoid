package jp.fkmsoft.qarkanoid;

public class QObject {
    public float x;
    public float y;
    public float vX;
    public float vY;
    
    public void move1Frame() {
        x += vX;
        y += vY;
    }
}
