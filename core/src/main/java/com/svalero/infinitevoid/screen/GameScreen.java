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
import com.svalero.infinitevoid.domain.Character;
import com.svalero.infinitevoid.domain.Kamikaze;
import com.svalero.infinitevoid.domain.Player;
import com.svalero.infinitevoid.domain.ShieldShip;
import com.svalero.infinitevoid.manager.LevelManager;
import com.svalero.infinitevoid.manager.ResourceManager;

import static com.svalero.infinitevoid.Util.Constants.*;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Player player;
    private Array<Character> characters;
    private float asteroidTimer, kamikazeTimer, shieldTimer;
    private LevelManager levelManager;
    private float timePlayed;
    private ResourceManager resourceManager;


    @Override
    public void show() {
        levelManager = new LevelManager();

        player = new Player(new Texture(Gdx.files.internal("Ship2.png")));
        resourceManager = new ResourceManager();
        resourceManager.loadAll();

        //INICIALIZAMOS ARRAY DE CLASE ABSTRACTA
        characters = new Array<>();

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

        levelManager.checkLevelUp(timePlayed);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        asteroidTimer += delta;
        kamikazeTimer += delta;
        shieldTimer += delta;

        if (asteroidTimer >= levelManager.getAsteroidSpawnInterval()) {
            Asteroid asteroid = new Asteroid(resourceManager.getAsteroidTexture(), MathUtils.random(0, 1024), 768);
            characters.add(asteroid);
            asteroidTimer = 0;
        }

        if (kamikazeTimer >= levelManager.getKamikazeSpawnInterval()) {
            Kamikaze kamikaze = new Kamikaze(resourceManager.getKamikazeTexture(), MathUtils.random(0, 1024), 768);
            characters.add(kamikaze);
            kamikazeTimer = 0;
        }

        if (shieldTimer >= levelManager.getShieldShipSpawnInterval()) {
            ShieldShip shieldShip = new ShieldShip(resourceManager.getShieldShipTexture(), MathUtils.random(0, 1024), 768);
            characters.add(shieldShip);
            shieldTimer = 0;
        }

        batch.begin();

        //PASAMOS BATCH
        player.draw(batch);


        //PINTAMOS ENEMIGOS
        for (Character enemie : characters) {
            enemie.draw(batch);
            enemie.move(delta);

            if (enemie.getRectangle().overlaps(player.getRectangle())) {
                player.setLives(player.getLives() - 1);
                Gdx.app.log("JUEGO", "¡Colisión! Vidas restantes: " + player.getLives());


                characters.removeValue(enemie, true);
            }

            if (enemie.getPosition().y < -enemie.getTexture().getHeight()) {
                characters.removeValue(enemie, true);
            }
        }

        batch.end();

        player.handleInput(delta);
        timePlayed += delta;
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
        resourceManager.dispose();
        characters.clear();
    }
}
