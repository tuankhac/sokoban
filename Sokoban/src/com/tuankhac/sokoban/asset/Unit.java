package com.tuankhac.sokoban.asset;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Unit {
	//tạo các đối tượng unit để tạo dựng map
	private final TextureRegion unit01;
	private final TextureRegion unit02;
	private final TextureRegion unit03;
	private final TextureRegion unit04;
	private final TextureRegion unit05;
	
	public ArrayList<TextureRegion> UNIT;

	public Unit(TextureAtlas atlas) {
		unit01 = atlas.findRegion("unit01");
		unit02 = atlas.findRegion("unit02");
		unit03 = atlas.findRegion("balloon");
		unit04 = atlas.findRegion("unit01");
		unit05 = atlas.findRegion("unit02");
		UNIT = new ArrayList<TextureRegion>();
		UNIT.add(unit05);
		UNIT.add(unit01);
		UNIT.add(unit04);
		UNIT.add(unit03);
		UNIT.add(unit02);
	}

}
