package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends Effect {

    Animation<TextureRegion> animation;

    public Explosion (float x, float y, Animation animation) {
        super(x, y);
        this.animation = animation;
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        stateTime += delta;

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, x, y);
    }

    //BORRA DE LA MEMORIA
    @Override
    public boolean isFinished() {
        return animation.isAnimationFinished(stateTime);    }
}
