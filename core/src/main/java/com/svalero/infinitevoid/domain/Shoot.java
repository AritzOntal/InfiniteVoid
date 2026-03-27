package com.svalero.infinitevoid.domain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import static com.svalero.infinitevoid.Util.Constants.SHOOT_SPEED;

public class Shoot extends Entity {

    public Shoot(Texture texture, float x, float y, int lives) {
        super(texture, new Vector2(x, y), lives);
    }

    public void move(float delta) {
        position.y += SHOOT_SPEED * delta;
    }
}
