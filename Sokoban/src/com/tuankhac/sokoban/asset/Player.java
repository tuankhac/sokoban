package com.tuankhac.sokoban.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Player {
	private TextureRegion teture;

	public Array<TextureRegion> PLAYER = new Array<TextureRegion>();
	public Player(TextureAtlas atlas) {
		PLAYER.add(set(atlas,"player").get());
		PLAYER.add(set(atlas,"player_change_color").get());
	}

	private Player set(TextureAtlas atlas,String name){
		teture = atlas.findRegion(name);
		return this;
	}
	private TextureRegion get(){
		return teture;
	}
}
