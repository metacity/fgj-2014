package fi.mimiiroju.fgj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import fi.mimiiroju.fgj.Mushroom.State;

public class Coin extends Rectangle implements Poolable{
	static final float MOVEMENT_PER_SECOND_COIN = 450;
	static final float WIDTH = 40;
	static final float HEIGHT = 60;
		
	float stateTime = 0.0f;
	State state;
	
	public Coin() {
		super(World.WORLD_WIDTH + 2, 60, WIDTH, HEIGHT );
		state = state.NORMAL;
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
