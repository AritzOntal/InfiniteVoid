package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Effect {
    protected float x, y;
    protected float stateTime;


    public Effect(float x, float y) {
        this.x = x;
        this.y = y;
        this.stateTime = 0f;
    }

    public abstract void draw(SpriteBatch batch, float delta);
    public abstract boolean isFinished();
}
