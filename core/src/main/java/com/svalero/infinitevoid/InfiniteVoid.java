package com.svalero.infinitevoid;

import com.badlogic.gdx.Game;
import com.svalero.infinitevoid.Services.AudioService;
import com.svalero.infinitevoid.manager.ConfigurationManager;
import com.svalero.infinitevoid.manager.ResourceManager;
import com.svalero.infinitevoid.screen.MainMenuScreen;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class InfiniteVoid extends Game {
    private ResourceManager resourceManager;
    private ConfigurationManager configurationManager;
    private AudioService audioService;

    @Override
    public void create() {
        resourceManager = new ResourceManager();
        resourceManager.loadAll();
        configurationManager = new ConfigurationManager(resourceManager);

        audioService = new AudioService(resourceManager, configurationManager);

        setScreen(new MainMenuScreen(this, null));
    }

    @Override
    public void dispose() {
        super.dispose();
        resourceManager.dispose();
    }
}
