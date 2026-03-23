package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.Asteroid;
import com.svalero.infinitevoid.domain.Player;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture asteroidTexture;
    private Player player;
    private Array<Asteroid> asteroids;
    private float asteroidTimer;
    private float spawnInterval;


    @Override
    public void show() {
        player = new Player(new Texture(Gdx.files.internal("Ship2.png")));
        asteroidTexture = new Texture(Gdx.files.internal("asteroid.png"));
        //INICIALIZAMOS ARRAY
        asteroids = new Array<>();

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        asteroidTimer += delta;
        spawnInterval = 1;

        //TODO CONTROLAR VELOCIDAD QUE SE GENERAN LOS ASTEROIDES
        if(asteroidTimer >= spawnInterval) {
            //CREAMOS UN ASTEROIDE CADA VEZ
            Asteroid asteroid = new Asteroid(asteroidTexture, MathUtils.random(0, 1024), 768);
            //Y LA AÑADIMOS AL ARRAY
            asteroids.add(asteroid);
            asteroidTimer = 0;
        }

        batch.begin();

        //PASO BATCH
        player.draw(batch);

        //PINTAMOS LOS ASTEROIDES
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(batch);
        }
        batch.end();
        //LE PASO EL DELTA A SUS MOVIMIENTOS
        player.handleInput(delta);

        //MOVEMOS ASTEROIDES JUNTO A RECTANGELS
        for (Asteroid asteroid : asteroids) {
            asteroid.move(delta);

            //AQUI COMPROBAMOS SI HAN COINCIDIDO
            if (asteroid.getRectangle().overlaps(player.getRectangle()) || asteroid.getPosition().y < -asteroidTexture.getHeight()) {
                asteroids.removeValue(asteroid, true);
            }
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
        player.dispose();
        batch.dispose();
        asteroidTexture.dispose();
        asteroids.clear();
    }
}
