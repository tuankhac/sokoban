package com.tuankhac.sokoban.game.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tuankhac.sokoban.game.controller.WorldController;

public class WorldRenderer extends Actor {
	public WorldController controller;

	public WorldRenderer(WorldController controller) {
		this.controller = controller;
	}

	@Override public void draw(SpriteBatch batch,float parentAlpha) {
		super.draw(batch, parentAlpha);
		controller.level.render(batch);
		//		controller.renderButton(batch);

	}
}
