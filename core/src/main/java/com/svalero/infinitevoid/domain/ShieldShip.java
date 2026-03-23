package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.svalero.infinitevoid.Util.Constants.SHIELD_SPEED;

public class ShieldShip extends Character {

    public ShieldShip(Texture texture, int x, int y) {
        super(texture, new Vector2(x, y));
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void move(float delta) {
        position.y -= SHIELD_SPEED * delta;
        rectangle.y = position.y;
    }
}
