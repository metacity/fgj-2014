package fi.mimiiroju.fgj;

import java.sql.Time;
import java.util.Iterator;

import sun.java2d.Disposer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.xml.internal.messaging.saaj.util.LogDomainConstants;

public class World {

	FGJ2014 game;
	WorldRenderer wRenderer;
	static final int WORLD_WIDTH = 1280;
	static final int WORLD_HEIGHT = 720;
	static final float GRAVITY = 500;

	final Assets assets;
	final Palle palle;
	final Coin coin;
	final Array<Mushroom> activeMushrooms;
	final Pool<Mushroom> mushroomPool;
	final Array<Coin> activeCoins;
	final Pool<Coin> coinPool;
	boolean collided;
	boolean ended;
	boolean finished;

	int score = 0;
	long lasMushroomkSpawnTime;
	long nextMushroomAfterNs = 2000000000L;

	long startTime = System.nanoTime();
	long mapEndsTime = 10000000000L;

	long lastCoinSpawnTime;
	long nextCoinAfterNs = 2000000000L;

	int coins; // How many coins will be spawned

	public World() {
		assets = new Assets();
		coin = new Coin();
		palle = new Palle();
		activeCoins = new Array<Coin>(false, 8);
		activeMushrooms = new Array<Mushroom>(false, 8);
		coinPool = Pools.get(Coin.class);
		mushroomPool = Pools.get(Mushroom.class);
		ended = false;
		collided = false;
		finished = false;
	}

	public void update(float delta) {
		palle.update(delta);

		// Process rocks
		Iterator<Mushroom> iterMushroom = activeMushrooms.iterator();
		while (iterMushroom.hasNext()) {
			Mushroom mushroom = iterMushroom.next();
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

			if (mushroom.x + Mushroom.WIDTH < 0) {
				iterMushroom.remove();
				mushroomPool.free(mushroom);
			}


		}

		Iterator<Coin> iterCoin = activeCoins.iterator();
		while(iterCoin.hasNext()) {
			Coin coin = iterCoin.next();
			coin.update(delta);

			if(Intersector.overlaps(palle, coin)) {
				if(coin.state == coin.state.NORMAL) {
					palle.coinAmount += 1;
					Gdx.app.log("kolikko ?", String.valueOf(palle.coinAmount));
					coin.state = coin.state.CAUGHT;
				}


			}

			if(coin.x + Coin.WIDTH < 0) {
				iterCoin.remove();
				coinPool.free(coin);
			}	
		}
		if(ended = false) {
			if(palle.x < 10) {
				palle.x = 10;
			} else if(palle.x > WORLD_WIDTH) {
				palle.x = WORLD_WIDTH - 10;
			}
		}





		if (TimeUtils.nanoTime() - lasMushroomkSpawnTime > nextMushroomAfterNs) {
			// Spawn new obstacle..
			Gdx.app.log("Spawnataan kivi", "*SPAWNED*");
			lasMushroomkSpawnTime = TimeUtils.nanoTime();
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

		if(TimeUtils.nanoTime() - lastCoinSpawnTime > nextCoinAfterNs) {
			lastCoinSpawnTime = TimeUtils.nanoTime();

			long spawnVariation = 4000000000L;
			coins = (int) (Math.random() * 2);
			for(int i = 0; i < coins; i++) {
				coin.y = (float) (Math.random()*150+200);
				Gdx.app.log("coin y", String.valueOf(coin.y));
				activeCoins.add(coinPool.obtain());
			}
			nextCoinAfterNs = (long) (Math.random() * spawnVariation);
		}

		if(TimeUtils.nanoTime() - startTime > mapEndsTime) {
			ended = true;
			Gdx.app.log("mapsend", String.valueOf(TimeUtils.nanoTime() - startTime));
			if(palle.x > WORLD_WIDTH && ended == true) {
				finished = true;
			}
		} else {
			ended = false;
		}
	}
}
