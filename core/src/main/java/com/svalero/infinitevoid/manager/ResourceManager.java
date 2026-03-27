package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.svalero.infinitevoid.Util.AnimationHelper;
import com.svalero.infinitevoid.domain.Player;
import lombok.Data;

@Data
public class ResourceManager {

    private Texture asteroidTexture;
    private Texture kamikazeTexture;
    private Texture enemieShip;
    private Texture shootTexture;

    private Texture playerShip;
    private Texture explosionSheet;
    private Texture asteroidSheet;
    private Texture kamikazeSheet;

    private Texture background;
    private Texture background2;


    private Animation<TextureRegion> explosionAnimation;
    private Animation<TextureRegion> asteroidAnimation;
    private Animation<TextureRegion> shipAnimation;
    private Animation<TextureRegion> kamikazeAnimation;

    private Player player;
    private Sound explosionSound;
    private Sound shootSound, damageSound, shootColision;
    private Music music1, music2;

    private BitmapFont fontLives, fontLevel, fontTitle;


    // CARGA EN MEMORIA LAS TEXTURAS
    public void loadAll() {
        background = new  Texture(Gdx.files.internal("textures/corona_ft.png"));
        background2 = new  Texture(Gdx.files.internal("textures/redeclipse_bk.png"));
        kamikazeTexture = new Texture(Gdx.files.internal("textures/kamikaze.png"));
        enemieShip = new Texture(Gdx.files.internal("textures/shieldship.png"));
        shootTexture = new Texture(Gdx.files.internal("textures/shoot.png"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
        shootColision = Gdx.audio.newSound(Gdx.files.internal("sounds/shootColision.mp3"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/level2.wav"));
        music1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/level1.mp3"));


        playerShip = new Texture(Gdx.files.internal("textures/tira_ships.png"));
        explosionSheet = new Texture(Gdx.files.internal("textures/boom.png"));
        asteroidSheet = new Texture(Gdx.files.internal("textures/tira_asteroides.png"));
        kamikazeSheet = new Texture(Gdx.files.internal("textures/tira_kamikazes.png"));

        //CREAMOS LAS ANIMACIONES
        explosionAnimation = AnimationHelper.createAnimation(explosionSheet, 11, 1, 0.07f, false);
        asteroidAnimation = AnimationHelper.createAnimation(asteroidSheet, 16, 1, 0.05f, true);
        shipAnimation = AnimationHelper.createAnimation(playerShip, 4, 1, 0.08f, true);
        kamikazeAnimation = AnimationHelper.createAnimation(kamikazeSheet, 10, 1, 0.07f, true);

        fontLives = new BitmapFont();
        fontLevel = new BitmapFont();
        fontTitle = new BitmapFont();

        //SETEA TAMAÑO
        fontLives.getData().setScale(2f);
        fontLevel.getData().setScale(2f);
        fontTitle.getData().setScale(4f);
        fontTitle.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


    }

        // LIBERA DE LA MEMORIA SI YA NO HAY TEXTURA
        public void dispose () {
            background.dispose();
            background2.dispose();
            playerShip.dispose();
            asteroidSheet.dispose();
            kamikazeTexture.dispose();
            enemieShip.dispose();
            shootTexture.dispose();
            explosionSheet.dispose();

            // Sonidos (Efectos cortos)
            explosionSound.dispose();
            shootSound.dispose();
            shootColision.dispose();
            damageSound.dispose();

            // Música (Archivos largos)
            music1.dispose();
            music2.dispose();
         }
    }
