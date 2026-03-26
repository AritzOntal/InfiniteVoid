package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;
import lombok.Data;

import static com.svalero.infinitevoid.Util.Constants.ASTEROID_SPEED;


public class Asteroid extends Character{


    public Asteroid(Texture texture, int x, int y) {
        super(texture, new Vector2(x, y), 1);
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void move(float delta)    {
        position.y -= ASTEROID_SPEED * delta;
        rectangle.y = position.y;
    }
}
