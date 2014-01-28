package fi.mimiiroju.fgj;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.TimeUtils;

public class World {
	
	FGJ2014 game;
	static final int WORLD_WIDTH = 1280;
	static final int WORLD_HEIGHT = 720;
	static final float GRAVITY = 500;

	final Palle palle;
	final Array<Mushroom> activeMushrooms;
	final Pool<Mushroom> mushroomPool;
	boolean collided;

	int score = 0;
	long lastRockSpawnTime;
	long nextMushroomAfterNs = 2000000000L;
	long startTime = System.nanoTime();
	
	public World() {
		palle = new Palle();
		activeMushrooms = new Array<Mushroom>(false, 8);
		mushroomPool = Pools.get(Mushroom.class);
		collided = false;
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
				
				if(mushroom.state == mushroom.state.NORMAL) {
					Gdx.input.vibrate(200);
					palle.health -= 30;
					mushroom.state = Mushroom.State.EXPLODING;
					}
				
				if(palle.health <= 0)
					collided = true;
			}
			if(palle.x < 10) {
				palle.x = 10;
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
			
			long minAfter = 2000000000L;
			long spawnVariation = 4000000000L;
			if (System.nanoTime() - startTime > 6000000000L) { // After 60s
				spawnVariation = 3500000000L;
			} else if (System.nanoTime() - startTime > 120000000000L) { // After 120s
				minAfter = 1750000000L;
				spawnVariation = 3000000000L;
			}
			nextMushroomAfterNs = (long) (Math.random() * spawnVariation + minAfter);
		}
	}
}
