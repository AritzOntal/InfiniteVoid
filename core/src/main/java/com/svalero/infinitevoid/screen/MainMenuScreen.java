package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MainMenuScreen implements Screen {
    private Stage stage;
    private GameScreen activeGame;

    public MainMenuScreen() {
        this.activeGame = null;
    }

    // Constructor para cuando esta pausado
    public MainMenuScreen(GameScreen gameScreen) {
        this.activeGame = gameScreen;
    }

    @Override
    public void show() {
        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        stage = new Stage();
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        if (activeGame != null) {
            VisTextButton resumeButton = new VisTextButton("Resume Game");
            resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Volvemos a la pantalla que guardamos
                    ((Game) Gdx.app.getApplicationListener()).setScreen(activeGame);
                    dispose();
                }
            });
            table.add(resumeButton).center().width(200).height(100).pad(5);
            table.row();
        }

        VisTextButton playButton = new VisTextButton("Play");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
                dispose();
            }
        });

        VisTextButton configurationButton = new VisTextButton("Configuration");
        configurationButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new ConfigurationScreen());
                dispose();
            }
        });

        VisTextButton quitButton = new VisTextButton("Quit");
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                dispose();
            }
        });


        table.row();
        table.add(playButton).center().width(200).height(100).pad(5);
        table.row();
        table.add(configurationButton).center().width(200).height(100).pad(5);
        table.row();
        table.add(quitButton).center().width(200).height(100).pad(5);

        //PARA QUE LEA LOS INPUTS
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
