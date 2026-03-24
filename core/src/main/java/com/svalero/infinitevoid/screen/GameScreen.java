package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.domain.Character;
import com.svalero.infinitevoid.manager.LevelManager;
import com.svalero.infinitevoid.manager.LogicManager;
import com.svalero.infinitevoid.manager.RenderManager;
import com.svalero.infinitevoid.manager.ResourceManager;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Array<Character> characters;
    private LevelManager levelManager;
    private LogicManager logicManager;
    private RenderManager renderManager;
    private float timePlayed;
    private ResourceManager resourceManager;
    private Array<Effect> effects;


    @Override
    public void show() {
        levelManager = new LevelManager();
        resourceManager = new ResourceManager();
        logicManager = new LogicManager(resourceManager, levelManager);
        batch = new SpriteBatch();
        renderManager = new RenderManager(batch, resourceManager);
        characters = new Array<>();
        effects = new Array<>();

        //CARGAMOS RECURSOS
        resourceManager.loadAll();
    }

    @Override
    public void render(float delta) {
        timePlayed += delta;

        resourceManager.getPlayer().handleInput(delta);
        levelManager.checkLevelUp(timePlayed);
        logicManager.spawnEnemies(characters, delta);
        logicManager.CheckColisions(characters, effects, delta);
        renderManager.render(resourceManager.getPlayer(), characters, effects, delta);
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
        batch.dispose();
        resourceManager.dispose();
        characters.clear();
    }
}
