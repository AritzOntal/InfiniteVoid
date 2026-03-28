package com.svalero.infinitevoid;

import com.badlogic.gdx.Game;
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

    @Override
    public void create() {
        resourceManager = new ResourceManager();
        resourceManager.loadAll();
        configurationManager = new ConfigurationManager(resourceManager);

        setScreen(new MainMenuScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        resourceManager.dispose();
    }
}
