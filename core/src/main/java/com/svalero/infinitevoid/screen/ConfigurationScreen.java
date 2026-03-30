package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.infinitevoid.InfiniteVoid;
import com.svalero.infinitevoid.manager.ConfigurationManager;

public class ConfigurationScreen implements Screen {

    private Stage stage;
    private ConfigurationManager configurationManager;
    private GameScreen activeGame;

    public ConfigurationScreen(GameScreen activeGame) {
        this.activeGame = activeGame;
    }

    @Override
    public void show() {
        InfiniteVoid game = (InfiniteVoid) Gdx.app.getApplicationListener();
        this.configurationManager = game.getConfigurationManager();

        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisCheckBox checkMusic = new VisCheckBox("Música");
        //Comprueba el valor anterior
        checkMusic.setChecked(configurationManager.isMusicEnabled());
        checkMusic.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                configurationManager.setMusic(checkMusic.isChecked());
            }
        });

        VisCheckBox checkSound = new VisCheckBox("Efectos de sonido");
        //Comprueba el valor anterior
        checkSound.setChecked(configurationManager.isSoundEnabled());
        checkSound.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                configurationManager.setSounds(checkSound.isChecked());
            }
        });

        VisTextButton backMainMenuButton = new VisTextButton("Atrás");
        checkSound.setChecked(configurationManager.isSoundEnabled());
        backMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game, activeGame));
            }
        });

        stage = new Stage();
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        table.row();
        table.add(checkSound).center().width(300).height(100).pad(2);
        table.row();
        table.add(checkMusic).center().width(300).height(100).pad(2);
        table.row();
        table.add(backMainMenuButton).center().width(300).height(100).pad(2);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (width > 0 && height > 0) {
            stage.getViewport().update(width, height, true);
        }
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
