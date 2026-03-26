package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;

@Data
public abstract class Character {

    protected Texture texture;
    protected Rectangle rectangle;
    protected Vector2 position;
    protected float spawnInterval;
    protected boolean doDamage;
    protected int lives;

    public Character(Texture texture, Vector2 position, int lives) {
        this.texture = texture;
        this.position = position;
        rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        doDamage = false;
        this.lives = lives;
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public abstract void move(float delta);

}
