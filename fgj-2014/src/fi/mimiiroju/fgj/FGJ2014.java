package fi.mimiiroju.fgj;
 
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
 
public class FGJ2014 extends Game implements ApplicationListener, GestureListener, InputProcessor {
	
	enum State {
		IDLE, RUN, JUMP
	}
 
    private static final int        FRAME_COLS = 6;         // #1
    private static final int        FRAME_ROWS = 5;         // #2
    
    // JUMP
    private static final long 	LONG_JUMP_PRESS = 150l;
    private static final float 	ACCELERATION = 20f;
    private static final float	GRAVITY = -20f;
    private static final float 	MAX_JUMP_SPEED = 7f;
    private static final float	DAMP = 0.90f;
    private static final float MAX_VEL = 4f;
    
    private static final float WIDTH = 10f;
    
    private World world;
    
    
    // JUMP
    private long jumpPressedTime;
    private boolean jumpingPressed;
    private FGJ2014 bob;
    private boolean jump;
   
   
    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    Texture                                                     standSheet;
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7
   
    int width;
    int height;
    
    
    int times = 1;
   
    private OrthographicCamera cam;
    Rectangle rect;
   
    float stateTime;                                        // #8
   
    /*
    public void jumpReleased() {
    	Keys.get(keys.put(State.JUMP, false));
    	
    }
    */
    
   
    @Override
    public void create() {
        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);
        im.addProcessor(gd);
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
       
       
        cam = new OrthographicCamera(50,50);
        cam.position.set(0, 48/2, 0);
            walkSheet = new Texture(Gdx.files.internal("data/animation_sheet.png"));     // #9
            TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);                                // #10
            walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
            int index = 0;
            for (int i = 0; i < FRAME_ROWS; i++) {
                    for (int j = 0; j < FRAME_COLS; j++) {
                            walkFrames[index++] = tmp[i][j];
                    }
            }
            walkAnimation = new Animation(0.025f, walkFrames);              // #11
            spriteBatch = new SpriteBatch();                             // #12
            rect = new Rectangle();
            rect.x = 0;
            rect.y = 0;
            rect.width = 10;
            rect.height = 10;
            stateTime = 0f;                                                 // #13
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getWidth();
    }
    
    
 
    @Override
    public void render() {
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);                                            // #14
            stateTime += Gdx.graphics.getDeltaTime();                       // #15
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);      // #16
            cam.update();
            spriteBatch.getProjectionMatrix().set(cam.combined);
            spriteBatch.begin();
            spriteBatch.draw(currentFrame, rect.x, rect.y, rect.width, rect.height);
               
           
            if(Gdx.input.justTouched()) {
                        rect.x = rect.x + 2;
                }
                else {
                        rect.x = (float) (rect.x - 0.1);
                }
           
            if(jump == true) {
            	Jump();
            }
           
                   /* if(Gdx.input.getAccelerometerX() < -3){
                        rect.y = rect.y + 1;
                    }else if (Gdx.input.getAccelerometerX() > 3){
                        rect.y = rect.y - 1;
                    }else if(Gdx.input.getAccelerometerY() > 3){
                        rect.x = rect.x + 1;
                    }else if(Gdx.input.getAccelerometerY() < -3){
                        rect.x = rect.x - 1;
                    }*/
                   
                    // make sure the ship is inside the stage
                    if( rect.x < -25) {
                        Gdx.app.exit();
                    } else if(rect.x > 15) {
                        rect.x = 15;
                    }
                    if( rect.y < 0 ) {
                        rect.y = 0;
                    } else if(rect.y > 37) {
                        rect.y = 37;
                    }
                 
                    // update the ship's actual position
                    rect.x = rect.x;
                    rect.y = rect.y;
            spriteBatch.end();
    }
    
    	public void Jump() {
    		if(times == 1) {
    			rect.x = rect.x + 1;
    			rect.y = rect.y + 3;
    			times++;
    		} else if (times == 2) {
    			rect.x = rect.x + 1;
    			rect.y = rect.y + 3;
    			times++;
    			
			} else if (times == 3) {
    			rect.x = rect.x + 1;
    			rect.y = rect.y + 3;
    			times++;
			} else if (times == 4) {
    			rect.x = rect.x + 1;
    			rect.y = rect.y + 3;
    			times++;
			} else if (times == 5) {
				rect.x = rect.x + 1;
				rect.y = rect.y + 0;
				times++;
			} else if (times == 6) {
				rect.x = rect.x + 1;
				rect.y = rect.y - 3;
				times++;
			} else if (times == 7) {
				rect.x = rect.x + 1;
				rect.y = rect.y - 3;
				times++;
			} else if (times == 8) {
				rect.x = rect.x + 1;
				rect.y = rect.y - 3;
				times++;
			} else if (times == 9) {
				rect.x = rect.x + 1;
				rect.y = rect.y - 3;
				times = 1;
				jump = false;
			}
    	}
 
        @Override
        public void resize(int width, int height) {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public void pause() {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public void resume() {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public void dispose() {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
                //Gdx.app.log("Message", "TOUCH DOWN!");
                return true;
        }
 
        @Override
        public boolean tap(float x, float y, int count, int button) {
                //Gdx.app.log("Message", "TAP!");
                return true;
        }
 
        @Override
        public boolean longPress(float x, float y) {
                jump = true;
                return true;
        }
 
        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean zoom(float initialDistance, float distance) {
                String message = "Zoom performed, initial Distance:" + Float.toString(initialDistance) +
                        " Distance: " + Float.toString(distance);
            //Gdx.app.log("Message", message);
                return true;
        }
 
        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
                        Vector2 pointer1, Vector2 pointer2) {
        		return false;
        }
 
        @Override
        public boolean keyDown(int keycode) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean keyUp(int keycode) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean keyTyped(char character) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean mouseMoved(int screenX, int screenY) {
                // TODO Auto-generated method stub
                return false;
        }
 
        @Override
        public boolean scrolled(int amount) {
                // TODO Auto-generated method stub
                return false;
        }
}