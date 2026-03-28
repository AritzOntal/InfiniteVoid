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

public class ConfigurationScreen implements Screen {

    private Stage stage;

    @Override
    public void show() {


        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        stage = new Stage();
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        VisCheckBox checkSound = new VisCheckBox("Sound effects");
        checkSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //todo activar desactivar sonido
            }
        });

        VisCheckBox checkMusic = new VisCheckBox("Music");
        checkMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO activar desactivar musica de fondo
            }
        });

        VisTextButton backMainMenuButton = new VisTextButton("Back");
        backMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
            }
        });

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
