package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

	private final static int PALLE_FRAME_COLS = 5;
	private final static int PALLE_FRAME_ROWS = 2;
	private final static int EXPLOSION_FRAME_COLS = 5;
	private final static int EXPLOSION_FRAME_ROWS = 5;
	//private final static int COIN_CAUGHT_FRAME_COLS = X;	Puuttuu animaatio, TBA
	//private final static int COIN_CAUGHT_FRAME_ROWS = X;	Puuttuu animaatio, TBA
	//private final static int COIN_TURN_FRAME_COLS = X;	Puuttuu animaatio, TBA
	//private final static int COIN_TURN_FRAME_ROWS = X;	Puuttuu animaatio, TBA

	static Texture coin;		
	static Texture background;
	static Texture mushroom;
	static BitmapFont menuFont;
	static BitmapFont font;
	static Music music;
	static Texture exitSign;

	static Texture palleTexture;
	static TextureRegion[] palleFrames;

	static Texture explosionTexture;
	static TextureRegion[] explosionFrames;

	public static void load() {
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		music.setLooping(true);
		
		coin = new Texture(Gdx.files.internal("data/coin.png"));
		mushroom = new Texture(Gdx.files.internal("data/mushroom.png"));
		background = new Texture(Gdx.files.internal("data/background2.png"));
		exitSign = new Texture(Gdx.files.internal("data/exitSign_remake.png"));
		
		loadRunningAnimation();
		loadExplosionAnimation();

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/Roboto-Bold.ttf"));
		font = generator.generateFont(Gdx.graphics.getHeight() / 25);
		font.setColor(Color.WHITE);
		menuFont = generator.generateFont(Gdx.graphics.getHeight() / 10);
		menuFont.setColor(Color.WHITE);
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}
	
	private static void loadRunningAnimation() {
		palleTexture = new Texture(Gdx.files.internal("data/palle2.png"));
		palleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmp = TextureRegion.split(
				palleTexture, 
				palleTexture.getWidth() / PALLE_FRAME_COLS, 
				palleTexture.getHeight() / PALLE_FRAME_ROWS
				);
		palleFrames = new TextureRegion[PALLE_FRAME_ROWS * PALLE_FRAME_COLS];
		int index = 0;
		for (int i = 0; i < PALLE_FRAME_ROWS; i++) {
			for (int j = 0; j < PALLE_FRAME_COLS; j++) {
				palleFrames[index++] = tmp[i][j];
			}
		}
	}
	
	private static void loadExplosionAnimation() {
		explosionTexture = new Texture(Gdx.files.internal("data/explosion.png"));
		explosionTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmp = TextureRegion.split(
				explosionTexture, 
				explosionTexture.getWidth() / EXPLOSION_FRAME_COLS, 
				explosionTexture.getHeight() / EXPLOSION_FRAME_ROWS
				);
		explosionFrames = new TextureRegion[EXPLOSION_FRAME_COLS * EXPLOSION_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < EXPLOSION_FRAME_ROWS; i++) {
			for (int j = 0; j < EXPLOSION_FRAME_COLS; j++) {
				explosionFrames[index++] = tmp[i][j];
			}
		}
	}

	public static void dispose() {
		coin.dispose();
		background.dispose();
		mushroom.dispose();
		exitSign.dispose();
		music.dispose();
		menuFont.dispose();
		font.dispose();
		palleTexture.dispose();
		explosionTexture.dispose();
	}
	
}
