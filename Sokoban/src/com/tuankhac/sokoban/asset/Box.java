package com.tuankhac.sokoban.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Box {

	private TextureRegion teture;
	
	public Array<TextureRegion> BOX = new Array<TextureRegion>();
	public Box(TextureAtlas atlas) {

		BOX.add(set(atlas,"box").get());
		BOX.add(set(atlas,"box_change_color").get());
	}
	
	private Box set(TextureAtlas atlas,String name){
		teture = atlas.findRegion(name);
		return this;
	}
	private TextureRegion get(){
		return teture;
	}

}
