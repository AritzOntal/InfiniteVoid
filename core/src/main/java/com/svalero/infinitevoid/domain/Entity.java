package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;

@Data
public abstract class Entity {
    protected Vector2 position;
    protected Rectangle rectangle;
    protected int lives;
    protected Animation<TextureRegion> animation;
    protected float stateTime;

    public Entity(Animation<TextureRegion> animation, Vector2 position, int lives) {
        this.animation = animation;
        this.position = position;
        this.lives = lives;
        this.stateTime = 0f;
        // Obtenemos las dimensiones del primer frame para calcular el Rectangle
        TextureRegion firstFrame = animation.getKeyFrame(0);
        this.rectangle = new Rectangle(position.x, position.y, firstFrame.getRegionWidth(), firstFrame.getRegionHeight());
    }

    public Entity(Texture texture, Vector2 position, int lives) {
        this.position = position;
        this.stateTime = 0f;
        this.rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

        TextureRegion singleFrame = new TextureRegion(texture);
        this.animation = new Animation<>(1f, singleFrame);
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void draw(Batch batch) {
        if (animation != null) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime);
            batch.draw(currentFrame, position.x, position.y);
        }
    }
}
