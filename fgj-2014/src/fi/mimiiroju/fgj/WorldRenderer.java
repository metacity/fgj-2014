package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	static final float RUNNING_FRAME_DURATION = 0.07f;
	
	final SpriteBatch batch;
	final World world;
	final OrthographicCamera camera;
	
	final Animation palleAnimation;
	
	Sprite backgroundSprite;
	float scrollTimer = 0.0f;

	public WorldRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, World.WORLD_WIDTH, World.WORLD_HEIGHT);
		
		palleAnimation = new Animation(RUNNING_FRAME_DURATION, Assets.palleFrames);
		
		
		Assets.background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		backgroundSprite = new Sprite(Assets.background, 0, 0, World.WORLD_WIDTH, World.WORLD_HEIGHT);
		backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		
		scrollTimer += Gdx.graphics.getDeltaTime() * 0.3f;
		if (scrollTimer > 1.0f) {
			scrollTimer = 0.0f;
		}
		backgroundSprite.setU(scrollTimer);
		backgroundSprite.setU2(scrollTimer + 1);
		backgroundSprite.draw(batch);
	}
	
	private void renderObjects() {
		batch.enableBlending();
		renderPalle();
		renderRocks();
	}
	
	private void renderPalle() {
		Palle palle = world.palle;
		batch.draw(palleAnimation.getKeyFrame(palle.stateTime, true), palle.x, palle.y);
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
