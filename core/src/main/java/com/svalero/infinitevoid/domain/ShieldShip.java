package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.svalero.infinitevoid.Util.Constants.SHIELD_LIVES;
import static com.svalero.infinitevoid.Util.Constants.SHIELD_SPEED;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShieldShip extends Enemy {

    public ShieldShip(Texture texture, float x, float y) {
        super(texture, new Vector2(x, y), SHIELD_LIVES);
    }

    @Override
    public void move(float delta) {
        position.y -= SHIELD_SPEED * delta;
        rectangle.y = position.y;
    }
}
