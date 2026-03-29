package com.svalero.infinitevoid.Services;
import com.svalero.infinitevoid.manager.ConfigurationManager;
import com.svalero.infinitevoid.manager.ResourceManager;

public class AudioService {

    private ResourceManager res;
    private ConfigurationManager conf;

    public AudioService(ResourceManager resourceManager, ConfigurationManager conf) {
        this.res = resourceManager;
        this.conf = conf;
    }


    public void playPlayerDamage() {
        if (conf.isSoundEnabled()) {
            res.getDamageSound().play();
        }
    }

    public void playShootColision() {
        if (conf.isSoundEnabled()) {
            res.getShootColision().play();
        }
    }

    public void playExplosion() {
        if (conf.isSoundEnabled()) {
            res.getExplosionSound().play();
        }
    }

    public void playMusic(int level) {
        if (!conf.isMusicEnabled()) {
            stopAllMusic();
            return;
        }

        if (level > 4) {
            res.getMusic2().play();
            res.getMusic1().stop();
        } else {
            res.getMusic2().stop();
            res.getMusic1().play();
        }
    }

    public void playShootSound() {
        if (conf.isSoundEnabled()) {
            res.getShootSound().play();
        }
    }

    public void stopAllMusic() {
        res.getMusic1().stop();
        res.getMusic2().stop();
    }
}
