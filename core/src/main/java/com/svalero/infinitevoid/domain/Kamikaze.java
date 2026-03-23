package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.svalero.infinitevoid.Util.Constants.KAMIKAZE_SPEED;

public class Kamikaze extends Character {

    private float timer = 0;
    private int direccionX = 1;

    public Kamikaze(Texture texture, int x, int y) {
        super(texture, new Vector2(x, y));
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void move(float delta) {
        timer += delta;
        if (timer >= 2.0F) {
            direccionX = -1;
            timer = 0;
        }

        position.y -= KAMIKAZE_SPEED * delta;
        position.x -= (KAMIKAZE_SPEED * direccionX) *  delta;
        rectangle.y = position.y;
        rectangle.x = position.x;
    }
}
