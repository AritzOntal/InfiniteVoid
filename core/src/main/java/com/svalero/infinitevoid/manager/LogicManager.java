package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.domain.Character;
import com.svalero.infinitevoid.domain.Shoot;

import static com.svalero.infinitevoid.Util.Constants.PLAYER_SPEED;

public class LogicManager {

    private final ResourceManager resourceManager;
    private final LevelManager levelManager;
    private float asteroidTimer, kamikazeTimer, shieldTimer;


    public LogicManager(ResourceManager resourceManager, LevelManager levelManager) {
        this.resourceManager = resourceManager;
        this.levelManager = levelManager;
    }



    public void spawnEnemies (Array<Character> characters, float delta) {

        //ACTUALIZAMOS TIMERS
        asteroidTimer += delta;
        kamikazeTimer += delta;
        shieldTimer += delta;

        if (asteroidTimer >= levelManager.getAsteroidSpawnInterval()) {
            characters.add(new Asteroid(resourceManager.getAsteroidTexture(), MathUtils.random(0, 1024), 768));
            asteroidTimer = 0;
        }

        if (kamikazeTimer >= levelManager.getKamikazeSpawnInterval()) {
            characters.add(new Kamikaze(resourceManager.getKamikazeTexture(), MathUtils.random(0, 1024), 768));
            kamikazeTimer = 0;
        }

        if (shieldTimer >= levelManager.getShieldShipSpawnInterval()) {
            characters.add(new ShieldShip(resourceManager.getShieldShipTexture(), MathUtils.random(0, 1024), 768));
            shieldTimer = 0;
        }

        for (Character enemie : characters) {
            enemie.move(delta);
        }
    }

    public void CheckColisions (Array<Character> characters, Array<Effect> colisions, float delta) {

        for (int i = 0; i < characters.size; i++) {
            Character enemie = characters.get(i);

            if (enemie.getRectangle().overlaps(resourceManager.getPlayer().getRectangle())) {
                resourceManager.getPlayer().setLives(resourceManager.getPlayer().getLives() - 1);

                Explosion exp = new Explosion(enemie.getRectangle().x - 100, enemie.getRectangle().y - 100, resourceManager.getExplosionAnimation());
                resourceManager.getPlayer().takeDamage();
                colisions.add(exp);
                resourceManager.getExplosionSound().play();

                characters.removeValue(enemie, true);
            }

            if (enemie.getPosition().y < -enemie.getTexture().getHeight()) {
                characters.removeValue(enemie, true);
            }
        }
    }


    public void updateEffects(float delta, Array<Effect> effects) {
        for (int i = 0; i < effects.size; i++) {
            // Solo comprobamos si ha terminado para borrarlo
            if (effects.get(i).isFinished()) {
                effects.removeIndex(i);
                i--;
            }
        }
    }

    public void handleInput(float delta, Player player) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.getPosition().x -= PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.getPosition().x += PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.getPosition().y += PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.getPosition().y -= PLAYER_SPEED * delta;
        }
        player.getRectangle().setPosition(player.getPosition().x, player.getPosition().y);
    }
}
