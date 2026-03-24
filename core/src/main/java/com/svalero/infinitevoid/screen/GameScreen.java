package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.domain.Character;
import com.svalero.infinitevoid.manager.LevelManager;
import com.svalero.infinitevoid.manager.LogicManager;
import com.svalero.infinitevoid.manager.ResourceManager;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Player player;
    private Array<Character> characters;
    private LevelManager levelManager;
    private LogicManager logicManager;
    private float timePlayed;
    private ResourceManager resourceManager;
    private Array<Effect> effects;


    @Override
    public void show() {
        levelManager = new LevelManager();
        resourceManager = new ResourceManager();
        logicManager = new LogicManager(resourceManager, levelManager);
        batch = new SpriteBatch();
        characters = new Array<>();
        effects = new Array<>();
        //CARGAMOS RECURSOS
        resourceManager.loadAll();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelManager.checkLevelUp(timePlayed);
        logicManager.update(delta, characters);
        logicManager.spawnEnemies(characters);


        batch.begin();
        //PASAMOS BATCH
        resourceManager.getPlayer().draw(batch);
        //PINTAMOS ENEMIGOS
        for (Character enemie : characters) {
            enemie.draw(batch);
            enemie.move(delta);
        }

        //PARA SABER SI A COLISIONADO
        logicManager.CheckColisions(characters, effects);
        //PARA SABER SI HA TERMINADO
        logicManager.updateEffects(delta, effects);

        for (Effect effect : effects) {
            effect.draw(batch, delta);
        }

        batch.end();

        resourceManager.getPlayer().handleInput(delta);
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
