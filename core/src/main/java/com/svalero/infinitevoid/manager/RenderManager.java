package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.Effect;
import com.svalero.infinitevoid.domain.Character;
import com.svalero.infinitevoid.domain.Player;


public class RenderManager {

    private final SpriteBatch batch;
    private final ResourceManager res;
    private final LevelManager lev;


    public RenderManager(SpriteBatch spriteBatch, ResourceManager resourceManager, LevelManager levelManager) {
        this.batch = spriteBatch;
        this.res = resourceManager;
        this.lev = levelManager;
    }

    public void render(Player player, Array<Character> characters, Array<Effect> effects, float delta) {
        batch.begin();

        if(lev.getCurrentLevel() < 2) {
            batch.draw(res.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            batch.draw(res.getBackground2(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (player.getLives() > 1) {
            res.getFontLives().setColor(Color.WHITE);
        } else {
            res.getFontLives().setColor(Color.RED);
        }

        player.draw(batch);

        for (Character enemie : characters) {
            enemie.draw(batch);
            enemie.move(delta);
        }

        for (Effect effect : effects) {
            effect.draw(batch, delta);
        }

        res.getFontLives().draw(batch, "Lives: " + player.getLives(), 20, Gdx.graphics.getHeight() - 10);
        res.getFontLevel().draw(batch, "Level: " + lev.getCurrentLevel(), 20, Gdx.graphics.getHeight() - 45);

        batch.end();
    }
}
