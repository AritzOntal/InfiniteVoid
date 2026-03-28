package com.svalero.infinitevoid.manager;


import com.kotcrab.vis.ui.widget.VisCheckBox;
import lombok.Data;

@Data
public class ConfigurationManager {

    private ResourceManager resourceManager;

    private boolean musicEnabled = true;
    private boolean soundEnabled = true;

    public ConfigurationManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setSounds(boolean enabled) {
        this.soundEnabled = enabled;
    }

    public void setMusic(boolean enabled) {
        this.musicEnabled = enabled;
        if (musicEnabled) {
            if (!resourceManager.getMusic1().isPlaying()) {
                resourceManager.getMusic1().play();
            }
        } else {
            resourceManager.getMusic1().stop();
            resourceManager.getMusic2().stop();
        }
    }

    public void update () {
        musicEnabled = true;
        soundEnabled = true;
    }

}
