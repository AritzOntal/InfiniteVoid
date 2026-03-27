package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.svalero.infinitevoid.Util.Constants.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends Entity {
    private boolean isBlinking;
    private boolean isShooting;
    private float blinkTimer;
    private float blinkDuration = PLAYER_BLINKING_TIME;
    private int score;

    public Player(Animation<TextureRegion> animation) {
        super(animation, new Vector2(100, 100), PLAYER_LIVES);
        this.score = PLAYER_SCORE;
    }

    public void takeDamage() {
        isBlinking = true;
        blinkTimer = 0;
    }

    @Override
    public void update(float delta) {
        super.update(delta); // Suma el delta al stateTime de la animación en Entity

        if (isBlinking) {
            blinkTimer += delta;
            if (blinkTimer >= blinkDuration) {
                isBlinking = false;
            }
        }
    }
}
