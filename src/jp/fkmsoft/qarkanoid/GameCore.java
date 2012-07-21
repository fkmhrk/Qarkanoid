package jp.fkmsoft.qarkanoid;

import android.graphics.Color;
import jp.fkmsoft.qarkanoid.ui.Block;

public class GameCore {
    private static GameCore instance = new GameCore();
    
    public static GameCore getInstance() {
        return instance;
    }
    
    private QObject ball = new QObject();
    private int width;
    private int height;
    private int unitWidth;
    private int unitHeight;
    private Block[] blocks = new Block[32];
    private Block pad = new Block();

    public void init() {
        int pt = 0;
        for (int y = 0 ; y < 4 ; ++y) {
            for (int x = 0 ; x < 8 ; ++x) {
                blocks[pt] = new Block();
                blocks[pt].x = x * unitWidth;
                blocks[pt].y = y * unitHeight;
                blocks[pt].width = unitWidth;
                blocks[pt].height = unitHeight;
                blocks[pt].setColor(((x + y) % 2 == 0) ? Color.RED : Color.GRAY);
                ++pt;
            }
        }
        
        // pad
        pad.x = 0;
        pad.y = unitHeight * 29;
        pad.width = unitWidth;
        pad.height = unitHeight;
        pad.setColor(Color.GRAY);
        
        // ball
        ball.x = 0;
        ball.y = unitHeight * 28;
        ball.vX = unitWidth / 4;
        ball.vY = -unitHeight / 4;
    }
    public void moveBall() {
        ball.move1Frame();
        // hit check
        
        if (ball.x < 0 || ball.x > width) {
            ball.vX = -ball.vX;
        }
        if (ball.y < 0) {
            ball.vY = -ball.vY;
        }
        
        for (int i = 0 ; i < blocks.length ; ++i) {
            if (blocks[i].isInner(ball.x, ball.y)) {
                blocks[i].enable = false;
                ball.vY = -ball.vY;
            }
        }
        // pad
        if (pad.isInner(ball.x, ball.y)) {
            ball.vY = -ball.vY;
        }
    }
    
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        unitWidth = width / 8;
        unitHeight = height / 32;
    }

    public QObject getBall() {
        return ball;
    }
    public Block[] getBlocks() {
        return blocks;
    }
    public Block getPad() {
        return pad;
    }
    
    
    
}
