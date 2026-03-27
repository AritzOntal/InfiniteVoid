package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.Effect;
import com.svalero.infinitevoid.domain.Enemy;
import com.svalero.infinitevoid.domain.Player;
import com.svalero.infinitevoid.domain.Shoot;


public class RenderManager {

    private final SpriteBatch batch;
    private final ResourceManager res;
    private final LevelManager lev;


    public RenderManager(SpriteBatch spriteBatch, ResourceManager resourceManager, LevelManager levelManager) {
        this.batch = spriteBatch;
        this.res = resourceManager;
        this.lev = levelManager;
    }

    public void render(Player player, Array<Enemy> enemies, Array<Effect> effects, Array<Shoot> shoots, float delta) {

        batch.begin();
        player.update(delta);

        if (lev.getCurrentLevel() < 2) {
            batch.draw(res.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            batch.draw(res.getBackground2(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (player.getLives() > 1) {
            res.getFontLives().setColor(Color.WHITE);
        } else {
            res.getFontLives().setColor(Color.RED);
        }

        //Renderizado de parpadeo
        if (player.isBlinking()) {
            if ((player.getBlinkTimer() % 0.2f) < 0.1f) {
                batch.setColor(1, 1, 1, 0);
            } else {
                batch.setColor(1, 1, 1, 1); // Visible
            }
        }

        player.draw(batch);
        batch.setColor(Color.WHITE);

        for (Enemy enemie : enemies) {
            enemie.update(delta);
            enemie.draw(batch);
        }

        for (Effect effect : effects) {
            effect.draw(batch, delta);
        }

        for (Shoot shoot : shoots) {
            shoot.update(delta);
            shoot.draw(batch);
        }

        res.getFontLives().draw(batch, "Lives: " + player.getLives(), 20, Gdx.graphics.getHeight() - 10);
        res.getFontLevel().draw(batch, "Level: " + lev.getCurrentLevel(), 20, Gdx.graphics.getHeight() - 45);
        res.getFontLevel().draw(batch, "Score: " + player.getScore(), 20, Gdx.graphics.getHeight() - 80);



        batch.end();
    }
}
