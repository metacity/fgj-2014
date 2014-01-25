package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

public class WorldRenderer {
	
	final SpriteBatch batch;
	final World world;
	final OrthographicCamera camera; 
	
	Animation palleAnimation;
	TextureRegion currentFrame;
	
	float stateTime;

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
		palleAnimation = new Animation(0.025f, Assets.palleFrames);
		stateTime += Gdx.graphics.getDeltaTime();	
		currentFrame = palleAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, 20, 5, Palle.WIDTH, Palle.HEIGHT);
	}
	
	public void renderRocks() {
		int len = world.activeRocks.size;
		for (int i = 0; i < len; ++i) {
			Rock rock = world.activeRocks.get(i);
			// Tähän render...
			batch.draw(Assets.rock, 700, 5, Rock.WIDTH, Rock.HEIGHT);
		}
	}
}
