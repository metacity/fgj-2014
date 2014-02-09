package fi.mimiiroju.fgj;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import fi.mimiiroju.fgj.Coin.State;

@SuppressWarnings("serial")
public class ExitSign extends Rectangle {
	
	static float MOVEMENT_PER_SECOND_EXIT_SIGN = 300; 

	public void update(float delta) {
		if(x > 800) {
		x -= (MOVEMENT_PER_SECOND_EXIT_SIGN * delta);
		} else {
			x = 800;
		}
	}
	
	public void reset() {	
		x = World.WORLD_WIDTH + 2;
	}
}
