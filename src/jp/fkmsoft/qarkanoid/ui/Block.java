package jp.fkmsoft.qarkanoid.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Block {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean enable = true;
    
    public Paint paint = new Paint();
    
    public Block() {
        paint.setStyle(Style.FILL);
    }
    
    public void setColor(int color) {
        paint.setColor(color);
    }
    
    public void draw(Canvas canvas) {
        if (enable) {
            canvas.drawRect(x, y, x + width, y + height, paint);
        }
    }
    
    public boolean isInner(float x, float y) {
        if (!enable) return false;
        if (x < this.x || this.x + this.width < x) return false;
        if (y < this.y || this.y + this.height < y) return false;
        return true;
    }
}
