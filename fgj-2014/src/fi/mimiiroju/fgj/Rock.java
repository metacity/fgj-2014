package fi.mimiiroju.fgj;

import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class Rock extends Rectangle {
	
	static final float WIDTH = 60;
	static final float HEIGHT = 40;
	static final int MOVEMENT_PER_SECOND = 500;

	public Rock() {
		super(0, 0, WIDTH, HEIGHT);
	}

	public void update(float delta) {
		
	}
}
