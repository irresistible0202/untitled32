package manage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    boolean[] keys = new boolean[256];

    public boolean isUP, isDown, isLeft, isRight;

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        setVariable();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        setVariable();
    }

    private void setVariable() {
        isRight = keys[KeyEvent.VK_D];
        isLeft = keys[KeyEvent.VK_A];
        isUP = keys[KeyEvent.VK_W];
        isDown = keys[KeyEvent.VK_S];
    }
}
