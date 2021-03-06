package org.academiadecodigo.jungleweed.sgfxgameobjects;

import org.academiadecodigo.jungleweed.gameobjectsframework.AbstractIndicator;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 1/29/17.
 */
public class SGFXGrabTotemIndicator extends AbstractIndicator {

    private Text text;

    public SGFXGrabTotemIndicator(int initialX, int initialY) {
        super.setX(initialX);
        super.setY(initialY);
    }

    @Override
    public void draw() {

        this.text = new Text(this.getX(), this.getY(), "Player " + super.getProperty() + " has grabbed the Totem!");
        this.text.setColor(Color.CYAN);

        this.text.grow(120,12);

        this.text.draw();
    }

    @Override
    public void hide() {
        if (this.text != null) {
            this.text.delete();
        }
    }
}
