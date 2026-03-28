package com.svalero.infinitevoid.manager;

import com.svalero.infinitevoid.Util.Constants;
import lombok.Data;

import static com.svalero.infinitevoid.Util.Constants.*;

@Data
public class LevelManager {

    private int currentLevel;
    private int targetScore;
    private ResourceManager res;
    private float asteroidSpawnInterval;
    private float kamikazeSpawnInterval;
    private float shieldShipSpawnInterval;
    private ConfigurationManager configurationManager;

    public LevelManager(ResourceManager res, ConfigurationManager configurationManager) {
        this.res = res;
        this.currentLevel = 1;
        this.targetScore = TARGET_SCORE_LEVEL;
        this.asteroidSpawnInterval = Constants.ASTEROID_INTERVAL;
        this.kamikazeSpawnInterval = Constants.KAMIKAZE_INTERVAL;
        this.shieldShipSpawnInterval = Constants.SHIELD_INTERVAL;
        this.configurationManager = configurationManager;
    }

    public boolean checkLevelUp(int score) {
        if (score >= targetScore) {
            levelUp();
            res.getMusic1().stop();
            if(currentLevel > 4) {
                res.getMusic2().setLooping(true);
            }
            if (configurationManager.isSoundEnabled()) {
                res.getMusic2().play();
            }
            return true;
        }
        return false;
    }

    private void levelUp() {

        currentLevel++;

            targetScore += TARGET_SCORE_LEVEL;

            asteroidSpawnInterval *= 0.7f;
            kamikazeSpawnInterval *= 0.7f;
            shieldShipSpawnInterval *= 0.8f;

            KAMIKAZE_SPEED += 30;
            SHIELD_SPEED += 20;
    }
}
