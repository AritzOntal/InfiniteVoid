package com.svalero.infinitevoid.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationHelper {

    public static Animation<TextureRegion> createAnimation(Texture sheet, int columns, int rows, float frameDuration) {

        TextureRegion[][] tmp = TextureRegion.split(sheet,
            sheet.getWidth() / columns,
            sheet.getHeight() / rows);

        Array<TextureRegion> frames = new Array<>(columns * rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frames.add(tmp[i][j]);
            }
        }
        return new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
    }
}
