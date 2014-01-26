package fi.mimiiroju.fgj;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;

@SuppressWarnings("serial")
public class Mushroom extends Rectangle implements Poolable {

	static final float MOVEMENT_PER_SECOND = 380;
	static final float WIDTH = 70;
	static final float HEIGHT = 100;
	
	float stateTime = 0.0f;
	State state;

	public Mushroom() {
		super(World.WORLD_WIDTH + 2, 60, WIDTH, HEIGHT);
		state = State.NORMAL;
	}

	public void update(float delta) {
		x -= (MOVEMENT_PER_SECOND * delta);
		if (state == State.EXPLODING) {
			stateTime += delta;
		}
	}

	@Override
	public void reset() {
		x = World.WORLD_WIDTH + 2;
		state = State.NORMAL;
		stateTime = 0.0f;
	}
	
	enum State {
		NORMAL,
		EXPLODING
	}
}
