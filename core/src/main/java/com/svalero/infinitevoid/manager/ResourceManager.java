package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public class ResourceManager {

    private Texture playerTexture;
    private Texture asteroidTexture;
    private Texture kamikazeTexture;
    private Texture shieldShipTexture;
    private Texture explosionSheet;
    private Animation<TextureRegion> explosionAnimation;

    // CARGA EN MEMORIA LAS TEXTURAS
    public void loadAll() {
        playerTexture = new Texture(Gdx.files.internal("Ship2.png"));
        asteroidTexture = new Texture(Gdx.files.internal("asteroid.png"));
        kamikazeTexture = new Texture(Gdx.files.internal("kamikaze.png"));
        shieldShipTexture = new Texture(Gdx.files.internal("shieldship.png"));
        explosionSheet = new Texture(Gdx.files.internal("explosion.png"));


        //SPLIT PARA DIVIDIR LA FOTO
        TextureRegion[][] tmp = TextureRegion.split(explosionSheet,
            explosionSheet.getWidth() / 2,
            explosionSheet.getHeight() / 8);


        TextureRegion[] frames = new TextureRegion[10];

        //LLENAMOS EL FRAME CON LA PRIMER COLUMNA (para ponerlos primero en el array)
        for (int i = 0; i < 8; i++) {
            frames[i] = tmp[i][0];
        }

        // EXTRAEMOS LO QUE QUEDA DE LA SEGUNDA COLUMNA MANUALMENTE (los ponemos al final porque los de la otra columna son los otos
        frames[8] = tmp[0][1];  // fila uno columna dos
        frames[9] = tmp[1][1]; // fila dos columna dos

        //creamos la animacion con los argumentos (velocidad por frame y el array de frames
        explosionAnimation = new Animation<>(0.07f, frames);
    }

        // LIBERA DE LA MEMORIA SI YA NO HAY TEXTURA
        public void dispose () {
            if (playerTexture != null) playerTexture.dispose();
            if (asteroidTexture != null) asteroidTexture.dispose();
            if (kamikazeTexture != null) kamikazeTexture.dispose();
            if (shieldShipTexture != null) shieldShipTexture.dispose();
        }
    }
