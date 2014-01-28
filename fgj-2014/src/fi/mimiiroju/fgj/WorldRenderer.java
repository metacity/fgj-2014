package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRenderer {
	
	static final float RUNNING_FRAME_DURATION = 0.07f;
	static final float EXPLOSION_FRAME_DURATION = 0.06f;
	// static final float COIN_TURNING_FRAME_DURATION = 0.0Xf;	Riippuu animaatiosta, TBA
	// static final float COIN_CAUGHT_FRAME_DURATION = 0.0Xf;  	Riippuu animaatiosta, TBA
	
	final SpriteBatch batch;
	final World world;
	final OrthographicCamera camera;
	
	final Animation palleAnimation;
	final Animation explosionAnimation;
	// final Animation coinAnimation;			Animaatio puuttuu
	// final Animation coinCaughtAnimation;		Animaatio puuttuu
	
	Sprite backgroundSprite;
	float scrollTimer = 0.0f;
	
	
	// For hitbox bound rendering..
	ShapeRenderer shapeRenderer;

	public WorldRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, World.WORLD_WIDTH, World.WORLD_HEIGHT);
		
		palleAnimation = new Animation(RUNNING_FRAME_DURATION, Assets.palleFrames);
		explosionAnimation = new Animation(EXPLOSION_FRAME_DURATION, Assets.explosionFrames);
		// coinCaughtAnimation = new Animation(COIN_CAUGHT_FRAME_DURATION, Assets.coinCaughtFrames); Ei oo vielä animaatioo
		// coinTurnAnimation = new Animation(COIN_TURNING_FRAME_DURATION, Assets.coinTurningFrames); Ei oo vielä animaatioo
		
		Assets.background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		backgroundSprite = new Sprite(Assets.background, 0, 0, World.WORLD_WIDTH, World.WORLD_HEIGHT);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setColor(Color.YELLOW);
	}
	
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderBackground();
		renderObjects();
		renderScore();
		batch.end();
		
		//renderHitBoxes();
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
		renderMushrooms();
		//renderCoins();
	}
	
	private void renderPalle() {
		float textureOffsetX = 5;
		float textureOffsetY = 5;
		
		Palle palle = world.palle;
		if (palle.state == Palle.State.JUMPING) {
			batch.draw(Assets.palleFrames[2], palle.x - Palle.HITBOX_RADIUS - textureOffsetX, palle.y - Palle.HITBOX_RADIUS - textureOffsetY);
		} else {
			batch.draw(palleAnimation.getKeyFrame(palle.stateTime, true), palle.x - Palle.HITBOX_RADIUS - textureOffsetX, palle.y - Palle.HITBOX_RADIUS - textureOffsetY);
		}
	}
	
	private void renderMushrooms() {
		int len = world.activeMushrooms.size;
		for (int i = 0; i < len; ++i) {
			Mushroom mushroom = world.activeMushrooms.get(i);
			if (mushroom.state == Mushroom.State.EXPLODING) {
				batch.draw(explosionAnimation.getKeyFrame(mushroom.stateTime), mushroom.x, mushroom.y);
			} else {
				batch.draw(Assets.mushroom, mushroom.x, mushroom.y);
			}
		}
	}
	
	/*
	 private void renderCoins() {
	 	TEHDÄÄN KUNHAN ANIMAATIOT JA SPAWNAUS PERIAATE SELVIÄÄ
	 }
	  
	 */
	
	private void renderScore() {
		Assets.font.draw(batch, String.format("Time: %.2f sec", (System.nanoTime() - world.startTime) / 1e9), 15, 40);
		Assets.font.draw(batch, "Health: " + world.palle.health + "%", 15, world.WORLD_HEIGHT - 15);
		//Assets.fon.draw(batch, String.format("Coins: " + world.coin.amount, vasenyläkulma xy));
	}
	
	private void renderHitBoxes() {
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.circle(world.palle.x, world.palle.y, world.palle.radius);
		for (Mushroom shroom : world.activeMushrooms) {
			shapeRenderer.rect(shroom.x, shroom.y, shroom.width, shroom.height);
		}
		shapeRenderer.end();
	}
}
