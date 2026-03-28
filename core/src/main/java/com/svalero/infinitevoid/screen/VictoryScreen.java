package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.infinitevoid.domain.Player;
import com.svalero.infinitevoid.manager.ResourceManager;

public class VictoryScreen implements Screen {

    private Player player;
    private Stage stage;
    private ResourceManager res;
    private Batch batch;

    public VictoryScreen(Player player, ResourceManager res, SpriteBatch batch) {
        this.player = player;
        this.res = res;
        this.batch = batch;
    }

    @Override
    public void show() {

        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisTextButton playButton = new VisTextButton("Play");
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
        stage.act(delta);
        stage.draw();

        batch.begin();

        res.getFontWin().getData().setScale(3f);
        res.getFontWin().draw(batch, "HAS GANADO!", 0, Gdx.graphics.getHeight() / 2f + 150, Gdx.graphics.getWidth(), Align.center, false
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
