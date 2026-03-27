package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.svalero.infinitevoid.Util.Constants.KAMIKAZE_LIVES;

@Data
@EqualsAndHashCode(callSuper = true)
public class Kamikaze extends Enemy {

    private float speed = 150f;

    public Kamikaze(Animation<TextureRegion> animation, float x, float y) {
        super(animation, new Vector2(x, y), KAMIKAZE_LIVES);
    }

    @Override
    public void move(float delta) {
        position.y -= speed * delta;
        rectangle.y = position.y;
    }

    public void followPlayer(Vector2 playerPos, float delta) {
        if (position.x < playerPos.x) {
            position.x += (speed - 50) * delta;
        } else if (position.x > playerPos.x) {
            position.x -= (speed - 50) * delta;
        }
        rectangle.x = position.x;
    }
}
