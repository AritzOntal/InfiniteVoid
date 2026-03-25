package com.svalero.infinitevoid.domain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import static com.svalero.infinitevoid.Util.Constants.SHOOT_SPEED;

public class Shoot extends Character {

    public Shoot(Texture texture, int x, int y) {
        super(texture, new Vector2(x, y));
    }

    @Override
    public void move(float delta) {
        position.y += SHOOT_SPEED * delta;
    }
}
