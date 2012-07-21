package jp.fkmsoft.qarkanoid;

import jp.fkmsoft.qarkanoid.ui.MainSurface;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

public class QarkanoidActivity extends Activity {
    /** Called when the activity is first created. */
    private MainThread thread;
    private MainSurface surface;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // get window size
        Display display = getWindowManager().getDefaultDisplay();
        GameCore core = GameCore.getInstance();
        core.setSize(display.getWidth(), display.getHeight());
        core.init();
        
        surface = new MainSurface(this);
        
        setContentView(surface);
        
        thread = new MainThread(surface);
        thread.begin();
        thread.start();
    }
    
    
    
    @Override
    protected void onDestroy() {
        if (thread != null) {
            thread.end();
            thread = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
        case KeyEvent.KEYCODE_VOLUME_UP:
            surface.movePad(10);
            break;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            surface.movePad(-10);
            break;
//		case KeyEvent.KEYCODE_VOLUME_MUTE:
//			break;
        case KeyEvent.KEYCODE_BACK:
            finish();
            break;
		}
		return true;
	}


    private static class MainThread extends Thread {
        private boolean running;
        private MainSurface surface;
        
        public MainThread(MainSurface surface) {
            this.surface = surface;
        }

        public void begin() {
            running = true;
        }
        
        public void end() {
            running = false;
        }
        @Override
        public void run() {
            GameCore core = GameCore.getInstance();
            while(running) {
                core.moveBall();
                surface.update();
                try {
                    sleep(32);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}