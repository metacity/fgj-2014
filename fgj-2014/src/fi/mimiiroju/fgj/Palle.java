package fi.mimiiroju.fgj;

import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class Palle extends Rectangle {
	static final float WIDTH = 50;
	static final float HEIGHT = 100;
	static final int MOVEMENT_PER_SECOND = 400;


	public Palle(float x, float y) {
		super(x - WIDTH/2, y, WIDTH, HEIGHT);
	}

	public void update(float delta) {
		// NYI
	}
	
	private void preventOffscreen() {
		if (x < 0) {
			x = 0;
		} else if (x > World.WORLD_WIDTH - width) {
			x = World.WORLD_WIDTH - width;
		}
	}

}
