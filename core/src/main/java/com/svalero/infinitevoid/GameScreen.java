package com.svalero.infinitevoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture playerTexture, asteroidTexture;
    private Rectangle playerRectangle;
    private Vector2 playerPosition;
    private Array<Vector2> asteriodsPositions;
    private Array<Rectangle> asteroidRectangels;
    private float asteroidTimer;
    private float spawnInterval;


    @Override
    public void show() {
        playerTexture = new Texture(Gdx.files.internal("Ship2.png"));
        playerPosition = new Vector2(100, 100);
        //RECTANGULO DE COLISION
        playerRectangle = new Rectangle(
            playerPosition.x, playerPosition.y, playerTexture.getWidth(), playerTexture.getHeight());

        asteroidTexture = new Texture(Gdx.files.internal("asteroid.png"));

        //INICIALIZAMOS LOS ARRAYS
        asteriodsPositions = new Array<>();
        asteroidRectangels = new Array<>();

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
            //CREAMOS UNA POSICION CADA VEZ
            Vector2 asteroidPosition = new Vector2(MathUtils.random(0, 1024), 768);
            //Y LA AÑADIMOS AL ARRAY
            asteriodsPositions.add(asteroidPosition);

            //CREAMOS RECTANGLES CON LA POSICION (LA ANTERIOR) Y TAMAÑO DEL LA TEXTURA (DEL TEXTURE CREADO EN SHOW)
            asteroidRectangels.add(
                new Rectangle(asteroidPosition.x, asteroidPosition.y, asteroidTexture.getWidth(), asteroidTexture.getHeight()));

            asteroidTimer = 0;
        }

        batch.begin();

        batch.draw(playerTexture, playerPosition.x, playerPosition.y);

        //PINTAMOS LOS ASTEROIDES
        for (Vector2 asteriodPosition : asteriodsPositions) {
            batch.draw(asteroidTexture, asteriodPosition.x, asteriodPosition.y);
        }

        batch.end();

        //MOVIMIENTOS

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerPosition.x -= 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerPosition.x += 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerPosition.y += 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerPosition.y -= 10;
        }

        //ACTUALIZAMOS LA POSICION DEL RECTAGULO EN BASE LA DE LA TEXTURA
        playerRectangle.setPosition(playerPosition.x, playerPosition.y);

        //MOVEMOS ASTEROIDES JUNTO A RECTANGELS
        for (int i = 0; i < asteroidRectangels.size; i++) {
            asteriodsPositions.get(i).y -= 100 * delta;
            asteroidRectangels.get(i).y = asteriodsPositions.get(i).y;
        }


        //COMPROBAMOS SI A COLISIONADO (HACEMOS BUCLE HACIA ATRAS PARA QUE FUNCIONE) Y DE PASO BORRAMOS CUANDO SALGA DE LA PANTALLA EL ASTEROIDE)
        for (int i = asteroidRectangels.size - 1; i >= 0; i--) {
            //pillamos el ultimo rectangulo cada vez
            Rectangle asteroid = asteroidRectangels.get(i);

            if (asteroid.overlaps(playerRectangle) || asteroid.y < -asteroidTexture.getHeight()) {
                // Borramos el rectángulo de colisión
                asteroidRectangels.removeValue(asteroid, true);

                //BORRAMOS TAMBIEN LA POSICIÓN
                asteriodsPositions.removeIndex(i);
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
        asteroidTexture.dispose();
        batch.dispose();
        asteriodsPositions.clear();
    }
}
