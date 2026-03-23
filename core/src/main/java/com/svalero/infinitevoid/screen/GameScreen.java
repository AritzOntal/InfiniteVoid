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

import static com.svalero.infinitevoid.Util.Constants.*;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture asteroidTexture;
    private Texture kamikazeTexture;
    private Texture shieldShipTexture;
    private Player player;
    private Array<Character> characters;
    private float asteroidTimer, kamikazeTimer, shieldTimer;
    private LevelManager levelManager;
    private float timePlayed;


    @Override
    public void show() {
        levelManager = new LevelManager();

        player = new Player(new Texture(Gdx.files.internal("Ship2.png")));
        asteroidTexture = new Texture(Gdx.files.internal("asteroid.png"));
        kamikazeTexture = new Texture(Gdx.files.internal("kamikaze.png"));
        shieldShipTexture = new Texture(Gdx.files.internal("shieldship.png"));

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
            Asteroid asteroid = new Asteroid(asteroidTexture, MathUtils.random(0, 1024), 768);
            characters.add(asteroid);
            asteroidTimer = 0;
        }

        if (kamikazeTimer >= levelManager.getKamikazeSpawnInterval()) {
            Kamikaze kamikaze = new Kamikaze(kamikazeTexture, MathUtils.random(0, 1024), 768);
            characters.add(kamikaze);
            kamikazeTimer = 0;
        }

        if (shieldTimer >= levelManager.getShieldShipSpawnInterval()) {
            ShieldShip shieldShip = new ShieldShip(shieldShipTexture, MathUtils.random(0, 1024), 768);
            characters.add(shieldShip);
            shieldTimer = 0;
        }

        batch.begin();

        //PASO BATCH
        player.draw(batch);

        //PINTAMOS NPCs
        for (Character enemie : characters) {
            enemie.draw(batch);
            enemie.move(delta);

            if (enemie.getRectangle().overlaps(player.getRectangle()) || enemie.getPosition().y < -enemie.getTexture().getHeight()) {
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
        asteroidTexture.dispose();
        characters.clear();
    }
}
