package fi.mimiiroju.fgj;

import java.util.HashMap;
import java.util.Map;

import net.obviam.starassault.controller.BobController.Keys;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

import fi.mimiiroju.fgj.FGJ2014.State;

public class palleController implements GestureListener {

		enum Keys {
			JUMP, RUN
		}
		
		private static final long LONG_JUMP_PRESS = 150l;
		private static final float ACCELERATION = 20f;
		private static final float GRAVITY = -20f;
		private static final float MAX_JUMP_SPEED = 7f;
		private static final float DAMP = 0.90f;
		private static final float MAX_VEL = 4f;
		
		private static final float WIDTH = 10f;
		
		private World world;
		private Palle palle;
		private long jumpPressedTime;
		private boolean jumpingPressed;
		
		static Map<Keys, Boolean> keys = new HashMap<palleController.Keys, Boolean>();
		
		static {
			keys.put(Keys.JUMP, false);
			keys.put(Keys.RUN, false);
		};
		
		public palleController(World world) {
			this.world = world;
			this.palle = world.getpalle();
		}

		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			keys.get(keys.put(Keys.RUN, true));
			return true;
		}

		@Override
		public boolean longPress(float x, float y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			keys.get(keys.put(Keys.JUMP, true));
			return true;
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
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
				Vector2 pointer1, Vector2 pointer2) {
			// TODO Auto-generated method stub
			return false;
		}
		
		public void update(float delta) {
			processInput();
			
			palle.getAcceleration().y = GRAVITY;
			palle.getAcceleration().mul(delta);
			palle.getVelocity().add(palle.getAcceleration().x, palle.getAcceleration().y);
			if(palle.getAcceleration().x == 0) {
				palle.getVelocity().x *= DAMP;
			}
			if(palle.getVelocity().x > MAX_VEL) {
				palle.getVelocity().x = MAX_VEL;
			}
			
			palle.update(delta);
			if(palle.getPosition().y < 0) {
				palle.getPosition().y = 0f;
				palle.setPosition(palle.getPosition());
				if(palle.getState().equals(State.JUMP)) {
					palle.setState(State.IDLE);
				}
			}
			if(palle.getPosition().x > WIDTH - palle.getBounds().width) {
				palle.getPosition().x = WIDTH - palle.getBounds().width;
				palle.setPosition(palle.getPosition());
				if(!palle.getState().equals(State.JUMP)) {
					palle.setState(State.IDLE);
				}
			}
		}

		private boolean processInput() {
			if (keys.get(Keys.JUMP)) {
				if (!palle.getState().equals(State.JUMP)) {
					jumpingPressed = true;
					jumpPressedTime = System.currentTimeMillis();
					palle.setState(State.JUMP);
					palle.getVelocity().y = MAX_JUMP_SPEED; 
				} else {
					if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
						jumpingPressed = false;
					} else {
						if (jumpingPressed) {
							palle.getVelocity().y = MAX_JUMP_SPEED;
						}
					}
				}
			}
			if(keys.get(Keys.RUN)) {
				if (!palle.getState().equals(State.JUMP)) {
					palle.setState(State.RUN);
				}
				palle.getAcceleration().x = ACCELERATION;
			} else {
				if(!palle.getState().equals(State.JUMP)) {
					palle.setState(State.IDLE);
				}
				palle.getAcceleration().x = 0;
			}
			return false;
		}
}