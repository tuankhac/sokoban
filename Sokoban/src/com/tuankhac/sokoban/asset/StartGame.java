package com.tuankhac.sokoban.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Admin
 *
 */
public class StartGame {
	public TextureRegion background;
	public TextureRegion play;
	public TextureRegion option;
	
	public TextureRegion selectMode;
	public TextureRegion easyMode;
	public TextureRegion hardMode;
	public StartGame(TextureAtlas atlas) {
		background = atlas.findRegion("background");
		play = atlas.findRegion("play");
		option = atlas.findRegion("option");
		
		selectMode = atlas.findRegion("selectmode");
		easyMode = atlas.findRegion("easymode");
		hardMode = atlas.findRegion("hardmode");
	}

}
