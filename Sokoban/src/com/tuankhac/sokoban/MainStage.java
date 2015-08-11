package com.tuankhac.sokoban;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tuankhac.sokoban.game.controller.WorldController;
import com.tuankhac.sokoban.game.render.StartGame;
import com.tuankhac.sokoban.game.render.WorldRenderer;
import com.tuankhac.sokoban.game.utils.Constants;

public class MainStage extends Stage {
	private WorldController controller;
	private WorldRenderer renderer;

	static final float width = Gdx.graphics.getWidth();
	static final float height = Gdx.graphics.getHeight();
	private StartGame start;
	public MainStage() {
		super(width, height ,true);

		start = new StartGame();
		controller = new WorldController();
		renderer = new WorldRenderer(controller);

		Gdx.input.setInputProcessor(this);
	}

	private void addStartActor(){
		addActor(start);
		addActor(start.play);
		addActor(start.option);
	}

	private void addRunningActor(){
		addActor(renderer);
		controller.button.addAllButton(this);
	}

	private void addModeActor(){
		addActor(start.easyMode);
		addActor(start.hardMode);
	}

	@Override public void act(){
		switch (Constants.state) {
		case BEGIN:
			addStartActor();
			break;
		case MODE:
			start.option.remove();
			addModeActor();
			break;
		case RUNNING:
			start.remove();
			start.hardMode.remove();
			addRunningActor();
			break;
		default:
			break;
		}
		controller.update(Gdx.graphics.getDeltaTime());
		super.act();
	}

	@Override public void draw() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f,0f,1f,1f);		
		super.draw();
	}

	float positionX;
	float positionY;
	@Override   public boolean touchDragged(int screenX, int screenY, int pointer) { 
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		positionX = getCamera().getPickRay(screenX, screenY).origin.x;
		positionY = getCamera().getPickRay(screenX, screenY).origin.y;
		return super.touchDown(screenX, screenY, pointer, button);
	}
	@Override	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		controller.touchUp(this, positionX, positionY, screenX, screenY, pointer, button);
		return true;
	}

	@Override public void dispose(){ start.dispose();}
}