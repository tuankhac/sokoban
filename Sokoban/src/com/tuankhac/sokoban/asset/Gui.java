package com.tuankhac.sokoban.asset;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Gui {
	private final AtlasRegion level;
	public ArrayList<TextureRegion> renderGUI;

	public Gui(TextureAtlas atlas) {
		level = atlas.findRegion("level");
		renderGUI = new ArrayList<TextureRegion>();
		renderGUI.add(level);
	}

}
