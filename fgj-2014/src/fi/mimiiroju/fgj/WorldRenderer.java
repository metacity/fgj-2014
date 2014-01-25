package fi.mimiiroju.fgj;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	final SpriteBatch batch;
	final World world;
	final OrthographicCamera camera; 

	public WorldRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, World.WORLD_WIDTH, World.WORLD_HEIGHT);
	}
	
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderBackground();
		renderObjects();
		Assets.font.draw(batch, "Score: " + world.score, 15, World.WORLD_HEIGHT - 15);
		batch.end();
	}
	
	private void renderBackground() {
		batch.disableBlending();
		batch.draw(Assets.background, 0, 0);
	}
	
	private void renderObjects() {
		batch.enableBlending();
		renderPalle();
		renderRocks();
	}
	
	private void renderPalle() {
		
	}
	
	private void renderRocks() {
		int len = world.activeRocks.size;
		for (int i = 0; i < len; ++i) {
			Rock rock = world.activeRocks.get(i);
			// Tähän render...
		}
	}
}
