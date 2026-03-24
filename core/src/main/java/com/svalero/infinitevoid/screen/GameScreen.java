package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.domain.Character;
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
    private Array<Effect> effects;


    @Override
    public void show() {
        levelManager = new LevelManager();

        player = new Player(new Texture(Gdx.files.internal("Ship2.png")));
        resourceManager = new ResourceManager();
        resourceManager.loadAll();

        //INICIALIZAMOS ARRAYS DE CLASES ABSTRACTAS
        characters = new Array<>();
        effects = new Array<>();

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

                Explosion exp = new Explosion(enemie.getRectangle().x - 100, enemie.getRectangle().y -100, resourceManager.getExplosionAnimation());

                effects.add(exp);

                Gdx.app.log("JUEGO", "¡Colisión! Vidas restantes: " + player.getLives());
                characters.removeValue(enemie, true);
            }

            if (enemie.getPosition().y < -enemie.getTexture().getHeight()) {
                characters.removeValue(enemie, true);
            }
        }

        //TODO RECORRE ARRAY DE EFECTOS PARA PINTARLOS
        for (int i = 0; i < effects.size; i++) {
            Effect effect = effects.get(i);
            effect.draw(batch, delta);

            if (effect.isFinished()) {
                effects.removeIndex(i);
                i--;
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
