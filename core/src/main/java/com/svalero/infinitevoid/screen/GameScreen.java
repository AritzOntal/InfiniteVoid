package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.domain.Enemy;
import com.svalero.infinitevoid.manager.LevelManager;
import com.svalero.infinitevoid.manager.LogicManager;
import com.svalero.infinitevoid.manager.RenderManager;
import com.svalero.infinitevoid.manager.ResourceManager;


public class GameScreen implements Screen {

    private SpriteBatch batch;
    private ResourceManager resourceManager;
    private LevelManager levelManager;
    private LogicManager logicManager;
    private RenderManager renderManager;
    private float timePlayed;
    private Array<Effect> effects;
    private Array<Enemy> characters;
    private Array<Shoot> shoots;
    private Player player;


    @Override
    public void show() {
        resourceManager = new ResourceManager();
        levelManager = new LevelManager(resourceManager);
        logicManager = new LogicManager(resourceManager, levelManager);
        batch = new SpriteBatch();
        renderManager = new RenderManager(batch, resourceManager, levelManager);
        characters = new Array<>();
        effects = new Array<>();
        shoots = new Array<>();

        //CARGAMOS RECURSOS
        resourceManager.loadAll();
        player = new Player(resourceManager.getShipAnimation());
        resourceManager.getMusic1().play();
    }

    @Override
    public void render(float delta) {
        timePlayed += delta;
        logicManager.handleInput(delta, player, shoots);
        levelManager.checkLevelUp(player.getScore());
        logicManager.spawnEnemies(characters, delta, player);
        logicManager.CheckColisions(characters, effects, delta, shoots, player);
        logicManager.updateEffects(effects);
        renderManager.render(player, characters, effects, shoots, delta);

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
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        resourceManager.dispose();
        characters.clear();
    }
}
