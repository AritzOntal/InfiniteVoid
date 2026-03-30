package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.svalero.infinitevoid.Services.AudioService;
import com.svalero.infinitevoid.domain.*;

import static com.svalero.infinitevoid.Util.Constants.PLAYER_SPEED;

public class LogicManager {

    private final ResourceManager res;
    private final LevelManager levelManager;
    private float asteroidTimer, kamikazeTimer, shieldTimer;
    private AudioService audioService;

    public LogicManager(ResourceManager resourceManager, LevelManager levelManager, AudioService audioService) {
        this.res = resourceManager;
        this.levelManager = levelManager;
        this.audioService = audioService;
    }

    public void spawnEnemies(Array<Enemy> enemies, float delta, Player player) {
        asteroidTimer += delta;
        kamikazeTimer += delta;
        shieldTimer += delta;

        if (asteroidTimer >= levelManager.getAsteroidSpawnInterval()) {
            float asteroidWidth = res.getAsteroidAnimation().getKeyFrameIndex(0);

            enemies.add(new Asteroid(res.getAsteroidAnimation(), MathUtils.random(0, Gdx.graphics.getWidth() - asteroidWidth), 768));

            asteroidTimer = 0;
        }

        if (shieldTimer >= levelManager.getShieldShipSpawnInterval()) {
            float enemyShip = res.getEnemyTexture().getWidth();

            enemies.add(new ShieldShip(res.getEnemyTexture(), MathUtils.random(0, Gdx.graphics.getWidth() - enemyShip), 768));

            shieldTimer = 0;
        }

        if (kamikazeTimer >= levelManager.getKamikazeSpawnInterval()) {
            enemies.add(new Kamikaze(res.getKamikazeAnimation(), MathUtils.random(0, 1024), 768));


            kamikazeTimer = 0;
        }

        for (Enemy enemy : enemies) {
            enemy.move(delta, player.getPosition());
        }
    }


    public void cleanOutEntities(Array<Enemy> enemies, Array<Shoot> shoots) {
        for (int i = enemies.size - 1; i >= 0; i--) {
            if (enemies.get(i).getPosition().y < -enemies.get(i).getRectangle().getHeight()) {
                enemies.removeIndex(i);
                System.out.println("Enemigo borrado");

            }
        }

        for (int i = shoots.size - 1; i >= 0; i--) {
            if (shoots.get(i).getPosition().y > Gdx.graphics.getHeight()) {
                shoots.removeIndex(i);
                System.out.println("Shoot borrado");
            }
        }
    }

    public void checkColisions(Array<Enemy> enemies, Array<Effect> effects, Array<Shoot> shoots, Player player) {
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);

            if (enemy.getRectangle().overlaps(player.getRectangle())) {
                if (!enemy.isDoDamage()) {
                    player.takeDamage();
                    player.setLives(player.getLives() - 1);
                    audioService.playPlayerDamage();
                    enemy.setDoDamage(true);

                    if (enemy instanceof Kamikaze) {
                        enemies.removeIndex(i);
                        --i;
                        continue;
                    }
                }
            } else {
                enemy.setDoDamage(false);
            }

            for (int j = 0; j < shoots.size; j++) {
                Shoot shoot = shoots.get(j);

                if (shoot.getRectangle().overlaps(enemy.getRectangle())) {
                    if (enemy.getLives() > 1) {
                        enemy.setLives(enemy.getLives() - 1);
                        audioService.playShootColision();
                        shoots.removeIndex(j);
                    } else {
                        Explosion exp = new Explosion(enemy.getRectangle().x - 100, enemy.getRectangle().y - 100, res.getExplosionAnimation());
                        effects.add(exp);
                        audioService.playExplosion();

                        if (enemy instanceof ShieldShip) {
                            player.setScore(player.getScore() + 50);
                        }

                        enemies.removeIndex(i);
                        shoots.removeIndex(j);
                        --i;
                        break;
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
        float playerWidth = player.getRectangle().getWidth();
        float playerHeight = player.getRectangle().getHeight();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float currentX = player.getPosition().x;
        float currentY = player.getPosition().y;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoots.add(new Shoot(res.getShootTexture(), currentX + 6, currentY, 1));
            audioService.playShootSound();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentX -= PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentX += PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentY += PLAYER_SPEED * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentY -= PLAYER_SPEED * delta;
        }

        if (currentX < 0) {
            currentX = 0;
        } else if (currentX > screenWidth - playerWidth) {
            currentX = screenWidth - playerWidth;
        }

        if (currentY < 0) {
            currentY = 0;
        } else if (currentY > screenHeight - playerHeight) {
            currentY = screenHeight - playerHeight;
        }

        player.getRectangle().setPosition(currentX, currentY);
        player.getPosition().x = currentX;
        player.getPosition().y = currentY;

        for (Shoot shoot : shoots) {
            shoot.move(delta);
            shoot.getRectangle().setPosition(shoot.getPosition().x, shoot.getPosition().y);
        }
    }
}
