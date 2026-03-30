package com.svalero.infinitevoid.manager;

import com.svalero.infinitevoid.Services.AudioService;
import com.svalero.infinitevoid.Util.Constants;
import lombok.Data;

import static com.svalero.infinitevoid.Util.Constants.*;

@Data
public class LevelManager {
    private int currentLevel;
    private int targetScore;
    private float asteroidSpawnInterval;
    private float kamikazeSpawnInterval;
    private float shieldShipSpawnInterval;
    private AudioService audioService;

    public LevelManager(AudioService audioService) {
        this.currentLevel = CURRENT_LEVEL;
        this.targetScore = TARGET_SCORE_LEVEL;
        this.asteroidSpawnInterval = Constants.ASTEROID_INTERVAL;
        this.kamikazeSpawnInterval = Constants.KAMIKAZE_INTERVAL;
        this.shieldShipSpawnInterval = Constants.SHIELD_INTERVAL;
        this.audioService = audioService;
    }

    public boolean checkLevelUp(int score) {
        if (score >= targetScore) {
            levelUp();
            audioService.playMusic(currentLevel);
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
