package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.svalero.infinitevoid.domain.Player;
import lombok.Data;

@Data
public class ResourceManager {

    private Texture playerTexture;
    private Texture asteroidTexture;
    private Texture kamikazeTexture;
    private Texture shieldShipTexture;
    private Texture explosionSheet;
    private Texture background;
    private Texture background2;
    private Texture shootTexture;

    private Animation<TextureRegion> explosionAnimation;

    private Player player;
    private Sound explosionSound;
    private Sound shootSound, damageSound, shootColision;
    private Music music1, music2;

    private BitmapFont fontLives, fontLevel, fontTitle;


    // CARGA EN MEMORIA LAS TEXTURAS
    public void loadAll() {
        background = new  Texture(Gdx.files.internal("textures/corona_ft.png"));
        background2 = new  Texture(Gdx.files.internal("textures/redeclipse_bk.png"));
        player = new Player(new Texture(Gdx.files.internal("textures/Ship2.png")));
        playerTexture = new Texture(Gdx.files.internal("textures/Ship2.png"));
        asteroidTexture = new Texture(Gdx.files.internal("textures/asteroid.png"));
        kamikazeTexture = new Texture(Gdx.files.internal("textures/kamikaze.png"));
        shieldShipTexture = new Texture(Gdx.files.internal("textures/shieldship.png"));
        shootTexture = new Texture(Gdx.files.internal("textures/shoot.png"));
        explosionSheet = new Texture(Gdx.files.internal("textures/explosion.png"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
        shootColision = Gdx.audio.newSound(Gdx.files.internal("sounds/shootColision.mp3"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));
        music1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/level1.mp3"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/level2.wav"));



        fontLives = new BitmapFont();
        fontLevel = new BitmapFont();
        fontTitle = new BitmapFont();

        //setea tamaño
        fontLives.getData().setScale(2f);
        fontLevel.getData().setScale(2f);
        fontTitle.getData().setScale(4f);
        fontTitle.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


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
