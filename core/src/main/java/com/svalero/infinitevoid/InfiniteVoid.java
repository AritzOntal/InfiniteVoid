package com.svalero.infinitevoid.screen;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class InfiniteVoid extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
