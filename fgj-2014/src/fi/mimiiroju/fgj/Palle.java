package fi.mimiiroju.fgj;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

@SuppressWarnings("serial")
public class Palle extends Rectangle {
	static final float WIDTH = 50;
	static final float HEIGHT = 100;
	static final int MOVEMENT_PER_SECOND = 400;
	
	final Vector2 acceleration;
	float stateTime = 0.0f;
	State state = State.RUNNING;

	public Palle(float x, float y) {
		super(x - WIDTH/2, y, WIDTH, HEIGHT);
		acceleration = new Vector2();
	}

	public void update(float delta) {
		stateTime += delta;
	}
	
	public void jump() {
		state = State.JUMPING;
	}
	
	private void preventOffscreen() {
		if (x < 0) {
			x = 0;
		} else if (x > World.WORLD_WIDTH - width) {
			x = World.WORLD_WIDTH - width;
		}
	}
	
	enum State {
		RUNNING,
		JUMPING
	}

}
