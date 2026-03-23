package com.svalero.infinitevoid.manager;

import com.svalero.infinitevoid.Util.Constants;
import lombok.Data;

@Data
public class LevelManager {

    private int currentLevel;
    private float targetTime;

    private float asteroidSpawnInterval = 1;
    private float kamikazeSpawnInterval = 1;
    private float shieldShipSpawnInterval = 1;

    public LevelManager() {
        this.currentLevel = 1;
        this.targetTime = 100;

        this.asteroidSpawnInterval = Constants.ASTEROID_INTERVAL;
        this.kamikazeSpawnInterval = Constants.KAMIKAZE_INTERVAL;
        this.shieldShipSpawnInterval = Constants.SHIELD_INTERVAL;
    }

    public boolean checkLevelUp(float timePlayed) {
        if (timePlayed >= targetTime) {
            levelUp();
            return true;
        }
        return false;
    }

    private void levelUp() {
        currentLevel++;

        if (currentLevel == 2) {
            targetTime = 300;
            asteroidSpawnInterval = 0.8f;
            kamikazeSpawnInterval = 0.2f;
            shieldShipSpawnInterval = 0.1f;
        }
    }
}
