package com.bartolay.inventory.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class BarcodeEventHandler extends Pane implements EventHandler<KeyEvent> {
	
	private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean spacebar = false;
    private boolean shift = false;
    private boolean ctrl = false;

    private double mouseX;
    private double mouseY;
	
	public BarcodeEventHandler() {
		System.out.println("creating BarcodeEventHandler");
	}
	@Override
	public void handle(KeyEvent event) {
		System.out.println(event.getEventType().toString());

        if(event instanceof KeyEvent) {
            if(event.getSource() == getOnKeyPressed()) {
                keyPressed((KeyEvent) event);
            }
            if(event.getSource() == getOnKeyReleased()) {
                keyReleased((KeyEvent) event);
            }

            System.out.println(((KeyEvent) event).getCode());
        }
	}
	
	public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.UP)
            up = true;
        if (code == KeyCode.DOWN)
            down = true;
        if (code == KeyCode.LEFT)
            left = true;
        if (code == KeyCode.RIGHT)
            right = true;
        if (code == KeyCode.SPACE)
            spacebar = true;
        if (code == KeyCode.SHIFT)
            shift = true;
        if (code == KeyCode.CONTROL)
            ctrl = true;
    }

    public void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.UP)
            up = false;
        if (code == KeyCode.DOWN)
            down = false;
        if (code == KeyCode.LEFT)
            left = false;
        if (code == KeyCode.RIGHT)
            right = false;
        if (code == KeyCode.SPACE)
            spacebar = false;
        if (code == KeyCode.SHIFT)
            shift = false;
        if (code == KeyCode.CONTROL)
            ctrl = false;
    }
}
