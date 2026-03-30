package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.infinitevoid.domain.Player;
import com.svalero.infinitevoid.manager.ResourceManager;

public class GameOverScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    private ResourceManager res;

    public GameOverScreen(SpriteBatch batch, ResourceManager resourceManager) {
        this.batch = batch;
        this.res = resourceManager;
    }

    @Override
    public void show() {
        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisTextButton playButton = new VisTextButton("Volver a Jugar");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
                dispose();
            }
        });

        stage = new Stage();
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        table.row();
        table.add(playButton).center().width(300).height(100).pad(2);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        batch.begin();

        res.getFontWin().getData().setScale(3f);
        res.getFontWin().draw(batch, "HAS MUERTO", 0, Gdx.graphics.getHeight() / 2f + 150, Gdx.graphics.getWidth(), Align.center, false
        );

        res.getFontWin().getData().setScale(1f);

        batch.end();

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

    }
}
