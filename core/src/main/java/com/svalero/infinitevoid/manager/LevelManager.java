package com.svalero.infinitevoid.manager;

import com.svalero.infinitevoid.Util.Constants;
import lombok.Data;

@Data
public class LevelManager {

    private int currentLevel;
    private int targetScore;
    private ResourceManager res;
    private float asteroidSpawnInterval;
    private float kamikazeSpawnInterval;
    private float shieldShipSpawnInterval;

    public LevelManager(ResourceManager res) {
        this.res = res;
        this.currentLevel = 1;
        this.targetScore = 400;
        this.asteroidSpawnInterval = Constants.ASTEROID_INTERVAL;
        this.kamikazeSpawnInterval = Constants.KAMIKAZE_INTERVAL;
        this.shieldShipSpawnInterval = Constants.SHIELD_INTERVAL;
    }

    public boolean checkLevelUp(int score) {
        if (score >= targetScore) {
            levelUp();
            res.getMusic1().stop();
            res.getMusic2().setLooping(true);
            res.getMusic2().play();
            return true;
        }
        return false;
    }

    private void levelUp() {

        currentLevel++;

            targetScore += 400;

            asteroidSpawnInterval *= 0.7f;
            kamikazeSpawnInterval *= 0.6f;
            shieldShipSpawnInterval *= 0.9f;
    }
}
