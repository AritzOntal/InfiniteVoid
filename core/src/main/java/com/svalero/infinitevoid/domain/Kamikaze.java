package com.svalero.infinitevoid.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import static com.svalero.infinitevoid.Util.Constants.KAMIKAZE_SPEED;

public class Kamikaze extends Character {

    private float timer = 0;
    private int direccionX = 1;

    public Kamikaze(Texture texture, int x, int y) {
        super(texture, new Vector2(x, y), 1);
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void move(float delta) {
        /*timer += delta;
        if (timer >= 2.0F) {
            direccionX = -1;
            timer = 0;
        }

        position.y -= KAMIKAZE_SPEED * delta;
        position.x -= (KAMIKAZE_SPEED * direccionX) * delta;
        rectangle.y = position.y;
        rectangle.x = position.x;*/
    }

    public void followPlayer (Vector2 playerPos, float delta) {

        Vector2 vEnemigo = new Vector2(position.x, position.y);

        // Calcula la dirección y la normaliza en una sola línea
        Vector2 direccion = playerPos.cpy().sub(vEnemigo).nor();
        // Aplicamos el movimiento
        position.add(direccion.scl(KAMIKAZE_SPEED * delta));
        rectangle.setPosition(position.x, position.y);

    }


}
