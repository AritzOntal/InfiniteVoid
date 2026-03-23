package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;
import lombok.Data;


@Data
public class Asteroid  {
    private Texture texture;
    private Rectangle rectangle;
    private Vector2 position;


    public Asteroid(Texture texture, int x, int y) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void move(float delta) {
        position.y -= 10 * delta;
        rectangle.y = position.y;

    }
}
