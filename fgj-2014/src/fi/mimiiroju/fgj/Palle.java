package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

@SuppressWarnings("serial")
public class Palle extends Circle {
	static final float HITBOX_RADIUS = 100;
	static final float minY = Gdx.graphics.getWidth() / 20;
	
	final Vector2 velocity;
	float stateTime = 0.0f;
	State state = State.RUNNING;

	public Palle() {
		super(World.WORLD_WIDTH/4, minY + HITBOX_RADIUS, HITBOX_RADIUS);
		velocity = new Vector2();
	}

	public void update(float delta) {
		if (Gdx.input.justTouched()) {
			jump();
		}
		
		stateTime += delta;
		y += (velocity.y * delta);
		velocity.y -= (World.GRAVITY * delta);
		preventOffscreen();
	}
	
	public synchronized void jump() {
		if (state == State.RUNNING) {
			state = State.JUMPING;
			velocity.y = 425;
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
