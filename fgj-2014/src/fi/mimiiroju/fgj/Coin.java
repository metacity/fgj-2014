package fi.mimiiroju.fgj;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;

import fi.mimiiroju.fgj.Mushroom.State;

public class Coin extends Rectangle implements Poolable{
	static final float MOVEMENT_PER_SECOND_COIN = 450;
	static final float WIDTH = 50;
	static final float HEIGHT = 70;
	
	float stateTime = 0.0f;
	State state;
	
	public Coin() {
		
	}
	
	void update(float delta) {
		x -= (MOVEMENT_PER_SECOND_COIN * delta);
		if (state == State.CAUGHT) {
			stateTime += delta;
		}
	}
	
	@Override
	public void reset() {	// World renderiin coin pool
		x = World.WORLD_WIDTH + 2;
		state = State.NORMAL;
		stateTime = 0.0f;
	}
	
	enum State {
		NORMAL,
		CAUGHT;
	}

}
