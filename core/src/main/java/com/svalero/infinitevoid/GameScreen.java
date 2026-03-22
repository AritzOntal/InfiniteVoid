package com.svalero.infinitevoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture player;
    private Texture asteroid;
    private Vector2 playerPosition;
    private Array<Vector2> asteriods;


    @Override
    public void show() {
        player = new Texture(Gdx.files.internal("Ship2.png"));
        asteroid = new Texture(Gdx.files.internal("asteroid.png"));
        batch = new SpriteBatch();
        playerPosition = new Vector2(100, 100);
        asteriods = new Array<>();

        //METEMOS TODOS LOS ASTEROIDES EN EL ARRAY
        for (int i = 0; i < 8; i++) {
            Vector2 asteroidPosition = new Vector2(MathUtils.random(0, 1024), MathUtils.random( 768));
            asteriods.add(asteroidPosition);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (Vector2 asteriodPosition : asteriods) {}
        batch.draw(player, playerPosition.x, playerPosition.y);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            playerPosition.x -= 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
            playerPosition.x += 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) ) {
            playerPosition.y += 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) ) {
            playerPosition.y -= 10;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
