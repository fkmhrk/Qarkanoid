package jp.fkmsoft.qarkanoid.ui;

import jp.fkmsoft.qarkanoid.GameCore;
import jp.fkmsoft.qarkanoid.QObject;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainSurface extends SurfaceView {

    
    // pad
    private float centerX;
    
    private boolean showTitle = true;
    
    private Paint textPaint = new Paint();
    private Paint ballPaint = new Paint();
    
    
    public MainSurface(Context context) {
        super(context);
        
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        
        ballPaint.setColor(Color.WHITE);
        ballPaint.setStyle(Style.FILL);
        
    }
    
    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void update() {
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas == null) return;
        
        GameCore core = GameCore.getInstance();
        QObject ball = core.getBall();
        Block[] blocks = core.getBlocks();
        Block pad = core.getPad();
        
        canvas.drawColor(Color.BLACK);
        if (showTitle) {
//            canvas.drawText("Qarkanoid", width / 2f, height / 2f, textPaint);
        }
        
        for (int i = 0 ; i < blocks.length ; ++i) {
            blocks[i].draw(canvas);
        }
        pad.draw(canvas);
        canvas.drawCircle(ball.x, ball.y, 12, ballPaint);
        holder.unlockCanvasAndPost(canvas);
        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
            centerX = event.getX();
            return true;
        case MotionEvent.ACTION_MOVE:
            float diff = event.getX() - centerX;
            GameCore.getInstance().getPad().x += diff;
            centerX = event.getX();
            return true;
        default:
            return super.onTouchEvent(event);
        }
    }

    public void movePad(int value) {
        GameCore.getInstance().getPad().x += value;
    }
}
