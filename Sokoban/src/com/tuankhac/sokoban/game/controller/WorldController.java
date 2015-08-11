package com.tuankhac.sokoban.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tuankhac.sokoban.MainMenu;
import com.tuankhac.sokoban.game.level.Level;
import com.tuankhac.sokoban.game.utils.Constants;

public class WorldController {
	public Level level;
	public HandleButton button;

	private Rectangle areInteractive;
	private float x,y,width,height;
	public WorldController() {
		super();
		level = new Level(Constants.LEVEL);	
		button = new HandleButton(level);

		initAreInteractive();
		areInteractive = new Rectangle(x, y, width, height);
	}
	private void initAreInteractive(){
		x =  Constants.SIZE + 5*Constants.NUM_BLOCK ;
		y = 0;
		width = 16* Constants.SIZE + 5*Constants.NUM_BLOCK ;
		height = Gdx.graphics.getHeight();
	}
	public void update(float deltaTime) {	handleDebugInput(deltaTime); }

	public void touchUp(Stage stage,float positionX,float positionY,int screenX, int screenY, int pointer, int button){
		if(areInteractive.contains(screenX, screenY)){
			float posX = stage.getCamera().getPickRay(screenX, screenY).origin.x;
			float posY = stage.getCamera().getPickRay(screenX, screenY).origin.y;
			float tempX = posX - positionX;
			float tempY = posY - positionY;
			if(tempX >= 0){
				if(tempY >= 0){
					if(tempY > tempX) 
						level.player.changeOrient(Constants.UP_ORIENT);
					else
						if(tempY < tempX)
							level.player.changeOrient(Constants.RIGHT_ORIENT);
				}
				else{
					if(tempY > -tempX)
						level.player.changeOrient(Constants.RIGHT_ORIENT);
					else
						if(tempY < tempX ) 
							level.player.changeOrient(Constants.DOWN_ORIENT);
				}
			}
			else{
				if(tempY > 0){
					if(tempY > tempX) 
						level.player.changeOrient(Constants.UP_ORIENT);
					else
						if(tempY < -tempX)
							level.player.changeOrient(Constants.LEFT_ORIENT);
				}
				else{
					if(tempY > tempX)
						level.player.changeOrient(Constants.LEFT_ORIENT);
					else
						if(tempY < tempX ) 
							level.player.changeOrient(Constants.DOWN_ORIENT);
				}
			}
			level.player.move(level);
		}
	}

	private void handleDebugInput(float deltaTime) {

		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			level.player.changeOrient(Constants.LEFT_ORIENT);
			level.player.move(level);
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			level.player.changeOrient(Constants.UP_ORIENT);
			level.player.move(level);
		}
		if (Gdx.input.isKeyPressed(Keys.D)|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
			level.player.changeOrient(Constants.RIGHT_ORIENT);
			level.player.move(level);
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			level.player.changeOrient(Constants.DOWN_ORIENT);
			level.player.move(level);
		}

		if (Gdx.input.isKeyPressed(Keys.P)) {
			MainMenu.paused = !MainMenu.paused;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
