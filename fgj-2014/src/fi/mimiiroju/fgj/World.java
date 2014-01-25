package fi.mimiiroju.fgj;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.TimeUtils;

public class World {
	static final int WORLD_WIDTH = 1280;
	static final int WORLD_HEIGHT = 720;
	static final float GRAVITY = 3;
	static final long ROCK_SPAWN_INTERVAL_NS = 5000000000L; 
	
	final Palle palle;
	final Array<Rock> activeRocks;
	final Pool<Rock> rockPool;
	
	int score = 0;
	long lastRockSpawnTime;
	
	public World() {
		palle = new Palle(World.WORLD_WIDTH / 2, 60);
		activeRocks = new Array<Rock>(false, 8);
		rockPool = Pools.get(Rock.class);
	}
	
	public void update(float delta) {
		palle.update(delta);
		
		// Process rocks
		Iterator<Rock> iter = activeRocks.iterator();
		while (iter.hasNext()) {
			Rock rock = iter.next();
			rock.update(delta);
			// Tähän törmäystestaus..
		}
		
		if (TimeUtils.nanoTime() - lastRockSpawnTime > ROCK_SPAWN_INTERVAL_NS) {
			// Spawn new obstacle..
		}
	}
}
