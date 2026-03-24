package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.Effect;
import com.svalero.infinitevoid.domain.Character;
import com.svalero.infinitevoid.domain.Player;



public class RenderManager {

    private final SpriteBatch batch;


    public RenderManager(SpriteBatch spriteBatch, ResourceManager resourceManager) {
        this.batch = spriteBatch;
    }

    public void render(Player player, Array<Character> characters, Array<Effect> effects, float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        player.draw(batch);

        for (Character enemie : characters) {
            enemie.draw(batch);
            enemie.move(delta);
        }

        for (Effect effect : effects) {
            effect.draw(batch, delta);
        }

        batch.end();
    }
}
