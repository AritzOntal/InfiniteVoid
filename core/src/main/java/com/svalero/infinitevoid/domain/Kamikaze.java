package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.svalero.infinitevoid.Util.Constants.KAMIKAZE_LIVES;
import static com.svalero.infinitevoid.Util.Constants.KAMIKAZE_SPEED;

@Data
@EqualsAndHashCode(callSuper = true)
public class Kamikaze extends Enemy {

    Vector2 playerPos;
    //Variable desechable para no guardar una cada frame. Se reutiliza.
    private final Vector2 tmpVector = new Vector2();

    public Kamikaze(Animation<TextureRegion> animation, float x, float y) {
        super(animation, new Vector2(x, y), KAMIKAZE_LIVES);
    }

    @Override
    public void move(float delta, Vector2 playerPos) {
        if (playerPos.y < position.y) {
            this.followPlayer(playerPos, delta);
        } else {

            position.y -= KAMIKAZE_SPEED * delta;
        }
        rectangle.setPosition(position.x, position.y);
    }

    public void followPlayer(Vector2 playerPos, float delta) {
        this.playerPos = playerPos;

        if (playerPos.y < position.y) {
            tmpVector.set(playerPos).sub(position).nor().scl(KAMIKAZE_SPEED * delta);

            position.add(tmpVector);
        }
    }
}
