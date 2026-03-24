package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import lombok.Getter;

@Getter
public class ResourceManager {

    private Texture playerTexture;
    private Texture asteroidTexture;
    private Texture kamikazeTexture;
    private Texture shieldShipTexture;

    // CARGA EN MEMORIA LAS TEXTURAS
    public void loadAll() {
        playerTexture = new Texture(Gdx.files.internal("Ship2.png"));
        asteroidTexture = new Texture(Gdx.files.internal("asteroid.png"));
        kamikazeTexture = new Texture(Gdx.files.internal("kamikaze.png"));
        shieldShipTexture = new Texture(Gdx.files.internal("shieldship.png"));
    }

    // LIBERA DE LA MEMORIA SI YA NO HAY TEXTURA
    public void dispose() {
        if (playerTexture != null) playerTexture.dispose();
        if (asteroidTexture != null) asteroidTexture.dispose();
        if (kamikazeTexture != null) kamikazeTexture.dispose();
        if (shieldShipTexture != null) shieldShipTexture.dispose();
    }
}
