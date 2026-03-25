package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.svalero.infinitevoid.Util.Constants.PLAYER_SPEED;

@Data
@AllArgsConstructor
public class Player implements Disposable {
    private Rectangle rectangle;
    private Vector2 position;
    private Texture texture;
    private int lives;
    private boolean isBlinking;
    private float blinkTimer;
    private float blinkDuration = 2.0f;

    public Player(Texture texture) {
        this.texture = texture;
        position = new Vector2(100, 100);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        lives = 5;
    }

    public void takeDamage() {
        isBlinking = true;
        blinkTimer = 0;
    }

    public void update(float delta) {
        if (isBlinking) {
            blinkTimer += delta;
            if (blinkTimer >= blinkDuration) {
                isBlinking = false;
            }
        }
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void handleInput(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= PLAYER_SPEED * delta;
        }

        rectangle.setPosition(position.x, position.y);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
