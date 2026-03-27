package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Enemy extends Entity {
    protected float spawnInterval;
    protected boolean doDamage;

    public Enemy(Animation<TextureRegion> animation, Vector2 position, int lives) {
        super(animation, position, lives); // Llama al constructor de Entity
        this.doDamage = false;
    }

    public Enemy (Texture texture, Vector2 position, int lives) {
        super(texture, position, lives);

    }

    public abstract void move(float delta);
}
