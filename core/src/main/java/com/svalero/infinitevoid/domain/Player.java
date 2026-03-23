package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player implements Disposable {
    private Rectangle rectangle;
    private Vector2 position;
    private Texture texture;

    public Player(Texture texture) {
        this.texture = texture;
        position = new Vector2(100, 100);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= 250 * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += 250 * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += 250 * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= 250 * delta;
        }

        rectangle.setPosition(position.x, position.y);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
