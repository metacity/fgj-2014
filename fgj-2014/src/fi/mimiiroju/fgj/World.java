package fi.mimiiroju.fgj;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.TimeUtils;

public class World {
	static final int WORLD_WIDTH = 1280;
	static final int WORLD_HEIGHT = 720;
	static final float GRAVITY = 500;

	final Palle palle;
	final Array<Mushroom> activeMushrooms;
	final Pool<Mushroom> mushroomPool;

	int score = 0;
	long lastRockSpawnTime;
	long nextMushroomAfterNs = 2000000000L;

	public World() {
		palle = new Palle();
		activeMushrooms = new Array<Mushroom>(false, 8);
		mushroomPool = Pools.get(Mushroom.class);
	}

	public void update(float delta) {
		palle.update(delta);

		// Process rocks
		Iterator<Mushroom> iter = activeMushrooms.iterator();
		while (iter.hasNext()) {
			Mushroom mushroom = iter.next();
			mushroom.update(delta);
			
			if (Intersector.overlaps(palle, mushroom)) {
				//Gdx.app.log("T�rm�ys!", "T�rm�sit kiveen");
				Gdx.input.vibrate(10);
				mushroom.state = Mushroom.State.EXPLODING;
			}
			
			if (mushroom.x + Mushroom.WIDTH < 0) {
				iter.remove();
				mushroomPool.free(mushroom);
			}
		}

		if (TimeUtils.nanoTime() - lastRockSpawnTime > nextMushroomAfterNs) {
			// Spawn new obstacle..
			Gdx.app.log("Spawnataan kivi", "*SPAWNED*");
			lastRockSpawnTime = TimeUtils.nanoTime();
			activeMushrooms.add(mushroomPool.obtain());
			nextMushroomAfterNs = (long) (Math.random() * 4000000000L + 2000000000L);
		}
	}
}
