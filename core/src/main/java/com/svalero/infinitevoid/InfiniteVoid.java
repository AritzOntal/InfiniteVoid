package com.svalero.infinitevoid;

import com.badlogic.gdx.Game;
import com.svalero.infinitevoid.screen.GameScreen;
import com.svalero.infinitevoid.screen.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class InfiniteVoid extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen());
    }
}
