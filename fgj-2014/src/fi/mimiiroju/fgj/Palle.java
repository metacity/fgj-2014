package fi.mimiiroju.fgj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import fi.mimiiroju.fgj.Mushroom.State;

@SuppressWarnings("serial")
public class Palle extends Circle {

	static final float HITBOX_RADIUS = 105;
	static final float minY = 50;
	static final int MOVEMENT_PER_SECOND_PALLE = 300;
	
	public int health = 90;
	
	public int coinAmount = 0;
	
	final Vector2 velocity;
	float stateTime = 0.0f;
	State state = State.RUNNING;
	Game game;
	

	public Palle() {
		super(World.WORLD_WIDTH/4, minY + HITBOX_RADIUS, HITBOX_RADIUS);
		velocity = new Vector2();
	}

	public void update(float delta) {
		if(Gdx.input.getAccelerometerX() < 0) {
			jump(1.0f);
			}
		
		if(state == state.RUNNING && Gdx.input.isTouched() && y < 160) {
			if(Gdx.input.getX() > 0 && Gdx.input.getX() < 400) {
				 x -= 15; //-> vasen puoli näytöstä liikuttaa pallee vasemmalle päin
			} else if (Gdx.input.getX() > 800 && Gdx.input.getX() < 1200) {
				 x += 15; //-> oikea puoli näytöstä liikuttaa pallea oikealle päin
			}
		}
		
		stateTime += delta;
		y += (velocity.y * delta);
		velocity.y -= (World.GRAVITY * delta);
		preventOffscreen();
	}
	
	public synchronized void jump(float intensityMultiplier) {
		if (state == State.RUNNING) {
			state = State.JUMPING;
			if (intensityMultiplier < 1f) {
				intensityMultiplier = 1f;
			} else if (intensityMultiplier > 2f) {
				intensityMultiplier = 2f;
			}
			velocity.y = 425 * intensityMultiplier;
		}
	}
	
	private void preventOffscreen() {
		if (y <  minY + HITBOX_RADIUS) {
			y = minY + HITBOX_RADIUS;
			velocity.y = 0;
			state = State.RUNNING;
		}
	}
	
	enum State {
		RUNNING,
		JUMPING
	}



}
