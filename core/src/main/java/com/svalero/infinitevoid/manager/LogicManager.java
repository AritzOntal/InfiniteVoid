package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.domain.*;
import com.svalero.infinitevoid.screen.MainMenuScreen;

import java.lang.module.Configuration;

import static com.svalero.infinitevoid.Util.Constants.PLAYER_SPEED;

public class LogicManager {

    private final ResourceManager resourceManager;
    private final LevelManager levelManager;
    private float asteroidTimer, kamikazeTimer, shieldTimer;
    private ConfigurationManager configurationManager;

    public LogicManager(ResourceManager resourceManager, LevelManager levelManager, ConfigurationManager configurationManager) {
        this.resourceManager = resourceManager;
        this.levelManager = levelManager;
        this.configurationManager = configurationManager;
    }

    public void spawnEnemies(Array<Enemy> characters, float delta, Player player) {
        asteroidTimer += delta;
        kamikazeTimer += delta;
        shieldTimer += delta;

        if (asteroidTimer >= levelManager.getAsteroidSpawnInterval()) {

            float asteroidWidth = resourceManager.getAsteroidAnimation().getKeyFrameIndex(0);

            characters.add(new Asteroid(resourceManager.getAsteroidAnimation(), MathUtils.random(0, Gdx.graphics.getWidth() - asteroidWidth), 768));
            asteroidTimer = 0;
        }

        if (shieldTimer >= levelManager.getShieldShipSpawnInterval()) {

            float enemyShip = resourceManager.getEnemieShip().getWidth();

            characters.add(new ShieldShip(resourceManager.getEnemieShip(), MathUtils.random(0, Gdx.graphics.getWidth() - enemyShip), 768));
            shieldTimer = 0;
        }

        if (kamikazeTimer >= levelManager.getKamikazeSpawnInterval()) {
            characters.add(new Kamikaze(resourceManager.getKamikazeAnimation(), MathUtils.random(0, 1024), 768));
            kamikazeTimer = 0;
        }


        for (Enemy enemie : characters) {
            if (enemie instanceof Kamikaze) {
                Kamikaze kamikaze = (Kamikaze) enemie;
                kamikaze.followPlayer(player.getPosition(), delta);
            }
            enemie.move(delta);
        }
    }

    public void CheckColisions(Array<Enemy> characters, Array<Effect> colisions, float delta, Array<Shoot> shoots, Player player) {

        for (int i = 0; i < characters.size; i++) {
            Enemy enemie = characters.get(i);

            if (enemie.getRectangle().overlaps(player.getRectangle())) {

                if (!enemie.isDoDamage()) {
                    player.takeDamage();
                    player.setLives(player.getLives() - 1);
                    if (configurationManager.isSoundEnabled()) {
                        resourceManager.getDamageSound().play();
                    }
                    enemie.setDoDamage(true);

                    if (enemie instanceof Kamikaze) {
                        characters.removeValue(enemie, true);
                    }
                }

            } else {
                enemie.setDoDamage(false);
            }

            if (enemie.getPosition().y < -enemie.getRectangle().getHeight()) {
                characters.removeValue(enemie, true);
            }

            for (int c = 0; c < shoots.size; c++) {
                Shoot shoot = shoots.get(c);

                if (shoot.getRectangle().overlaps(enemie.getRectangle())) {
                    if (enemie.getLives() > 1) {
                        enemie.setLives(enemie.getLives() - 1);
                        if (configurationManager.isSoundEnabled()) {
                            resourceManager.getShootColision().play();
                        }
                        shoots.removeValue(shoot, true);

                    } else {
                        Explosion exp = new Explosion(
                            enemie.getRectangle().x - 100,
                            enemie.getRectangle().y - 100,
                            resourceManager.getExplosionAnimation());
                        colisions.add(exp);
                        if (configurationManager.isSoundEnabled()) {
                            resourceManager.getExplosionSound().play();
                        }
                        characters.removeValue(enemie, true);
                        shoots.removeValue(shoot, true);

                        if (enemie.getClass().isAssignableFrom(ShieldShip.class)) {
                            player.setScore(player.getScore() + 50);
                        }
                    }
                }
            }
        }
    }

    public void updateEffects(Array<Effect> effects) {
        for (int i = 0; i < effects.size; i++) {
            if (effects.get(i).isFinished()) {
                effects.removeIndex(i);
                i--;
            }
        }
    }

    public void handleInput(float delta, Player player, Array<Shoot> shoots) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoots.add(new Shoot(resourceManager.getShootTexture(),
                player.getPosition().x + 6,
                player.getPosition().y, 1));
            if (configurationManager.isSoundEnabled()) {
                resourceManager.getShootSound().play();
            }
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

        if (player.getPosition().x < 0) {
            player.getPosition().x = 0;
        } else if (player.getPosition().x > Gdx.graphics.getWidth() - player.getRectangle().getWidth()) {
            player.getPosition().x = Gdx.graphics.getWidth() - player.getRectangle().getWidth();
        }

        if (player.getPosition().y < 0) {
            player.getPosition().y = 0;
        } else if (player.getPosition().y > Gdx.graphics.getHeight() - player.getRectangle().getHeight()) {
            player.getPosition().y = Gdx.graphics.getHeight() - player.getRectangle().getHeight();
        }

        player.getRectangle().setPosition(player.getPosition().x, player.getPosition().y);

        for (Shoot shoot : shoots) {
            shoot.move(delta);
            shoot.getRectangle().setPosition(shoot.getPosition().x, shoot.getPosition().y);
        }
    }
}
