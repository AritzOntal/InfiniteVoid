package com.svalero.infinitevoid.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.svalero.infinitevoid.Util.AnimationHelper;
import lombok.Data;

@Data
public class ResourceManager {

    //TEXTURES
    private Texture EnemyTexture;
    private Texture shootTexture;
    // SHEETS
    private Texture playerSheet;
    private Texture explosionSheet;
    private Texture asteroidSheet;
    private Texture kamikazeSheet;
    // BACKGROUND
    private Texture background;
    private Texture background2;
    //ANIMATIONS
    private Animation<TextureRegion> explosionAnimation;
    private Animation<TextureRegion> asteroidAnimation;
    private Animation<TextureRegion> shipAnimation;
    private Animation<TextureRegion> kamikazeAnimation;
    //SOUNDS
    private Sound explosionSound;
    private Sound shootSound;
    private Sound shootColision;
    private Sound damageSound;
    //MUSIC
    private Music music1;
    private Music music2;
    //FONTS
    private BitmapFont fontLives, fontLevel, fontTitle, fontWin;

    // CARGA EN MEMORIA LAS TEXTURAS
    public void loadAll() {
        background = new  Texture(Gdx.files.internal("textures/corona_ft.png"));
        background2 = new  Texture(Gdx.files.internal("textures/redeclipse_bk.png"));
        EnemyTexture = new Texture(Gdx.files.internal("textures/shieldship.png"));
        shootTexture = new Texture(Gdx.files.internal("textures/shoot.png"));

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
        shootColision = Gdx.audio.newSound(Gdx.files.internal("sounds/shootColision.mp3"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));

        music1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/level1.mp3"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/level2.wav"));
        music2.setLooping(true);

        playerSheet = new Texture(Gdx.files.internal("textures/tira_ships.png"));
        explosionSheet = new Texture(Gdx.files.internal("textures/boom.png"));
        asteroidSheet = new Texture(Gdx.files.internal("textures/tira_asteroides.png"));
        kamikazeSheet = new Texture(Gdx.files.internal("textures/tira_kamikazes.png"));

        //CREAMOS LAS ANIMACIONES
        explosionAnimation = AnimationHelper.createAnimation(explosionSheet, 11, 1, 0.07f, false);
        asteroidAnimation = AnimationHelper.createAnimation(asteroidSheet, 16, 1, 0.07f, true);
        shipAnimation = AnimationHelper.createAnimation(playerSheet, 4, 1, 0.08f, true);
        kamikazeAnimation = AnimationHelper.createAnimation(kamikazeSheet, 10, 1, 0.07f, true);

        fontLives = new BitmapFont();
        fontLevel = new BitmapFont();
        fontTitle = new BitmapFont();
        fontWin = new BitmapFont();

        //SETEA TAMAÑO
        fontLives.getData().setScale(2f);
        fontLevel.getData().setScale(2f);
        fontTitle.getData().setScale(4f);
        fontWin.getData().setScale(10f);

        fontTitle.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

        // LIBERA DE LA MEMORIA SI YA NO HAY TEXTURA
        public void dispose () {
            shootTexture.dispose();
            EnemyTexture.dispose();

            explosionSheet.dispose();
            playerSheet.dispose();
            kamikazeSheet.dispose();
            asteroidSheet.dispose();

            background.dispose();
            background2.dispose();

            explosionSound.dispose();
            shootSound.dispose();
            shootColision.dispose();
            damageSound.dispose();

            music1.dispose();
            music2.dispose();
         }
    }
