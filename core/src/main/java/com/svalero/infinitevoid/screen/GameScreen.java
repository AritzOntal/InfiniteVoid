package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.InfiniteVoid;
import com.svalero.infinitevoid.Services.AudioService;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.domain.Enemy;
import com.svalero.infinitevoid.manager.*;

import static com.svalero.infinitevoid.Util.Constants.TARGET_SCORE;


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
    private ConfigurationManager configurationManager;
    private InfiniteVoid game;
    private AudioService audioService;


    @Override
    public void show() {
        if (player == null) {
            //CASTEO
            InfiniteVoid game = (InfiniteVoid) Gdx.app.getApplicationListener();

            this.game = (InfiniteVoid) Gdx.app.getApplicationListener();
            this.resourceManager = game.getResourceManager();
            this.configurationManager = game.getConfigurationManager();
            this.audioService = game.getAudioService();

            configurationManager.update();

            levelManager = new LevelManager(resourceManager, configurationManager, audioService);
            logicManager = new LogicManager(resourceManager, levelManager, configurationManager, audioService);
            audioService.playMusic(levelManager.getCurrentLevel());

            batch = new SpriteBatch();
            renderManager = new RenderManager(batch, resourceManager, levelManager);
            characters = new Array<>();
            effects = new Array<>();
            shoots = new Array<>();

            player = new Player(resourceManager.getShipAnimation());
            audioService.playMusic(levelManager.getCurrentLevel());
        }
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

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            //Nos auto pasamos para recordar por donde iba la partida
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game, this));
        }

        if (player.getLives() < 1) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen(player, batch, resourceManager));
        }

        if (player.getScore() > TARGET_SCORE) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new VictoryScreen(player, resourceManager, batch));
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
        batch.dispose();
        resourceManager.dispose();
        characters.clear();
    }
}
