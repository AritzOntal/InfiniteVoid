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

    public Kamikaze(Animation<TextureRegion> animation, float x, float y) {
        super(animation, new Vector2(x, y), KAMIKAZE_LIVES);
    }

    @Override
    public void move(float delta) {
        if (playerPos.y > position.y) {
            position.y -= KAMIKAZE_SPEED * delta;
            rectangle.y = position.y;
        }
    }

    public void followPlayer(Vector2 playerPos, float delta) {
        this.playerPos = playerPos;
        if (playerPos.y < position.y) {
            Vector2 direccion = new Vector2(playerPos.x, playerPos.y);
            direccion.sub(position);
            direccion.nor();
            direccion.scl(KAMIKAZE_SPEED * delta);
            position.add(direccion);

            rectangle.setPosition(position.x, position.y);

        }
    }
}
