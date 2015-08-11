package com.tuankhac.sokoban.game.level;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.tuankhac.sokoban.asset.Asset;
import com.tuankhac.sokoban.game.object.Boxes;
import com.tuankhac.sokoban.game.object.Player;
import com.tuankhac.sokoban.game.object.Unit;
import com.tuankhac.sokoban.game.utils.Constants;
public class Level {
	private Array<Unit> arrUnits = new Array<Unit>();
	private Array<Boxes> arrBox = new Array<Boxes>();
	public Player player;

	boolean reset = true;
	
	
	
	public Level(int level) {
		init(level);
		
		
	}

	public void init(int level) {
		if(reset){
			arrBox.clear();
			arrBox = new Array<Boxes>();
		}
		setArrUnits("maps/map" + level);
	}

	public void render(SpriteBatch batch) {
		drawBottomUnit(batch);
		drawTopUnit(batch);
		drawAllBox(batch);
		player.render(batch);
	}

	
	
	private void drawAllBox(SpriteBatch batch) {
		for (int i = 0; i < arrBox.size; i++)
			arrBox.get(i).render(batch);
	}

	private void drawBottomUnit(SpriteBatch batch) {
		for (int i = arrUnits.size - 1; i >= 0; i--) {
			if (arrUnits.get(i).getType() != 1	|| arrUnits.get(i).getType() != 2)
				arrUnits.get(i).render(batch);
		}
	}

	private void drawTopUnit(SpriteBatch batch) {
		for (int i = arrUnits.size - 1; i >= 0; i--) {
			if (arrUnits.get(i).getType() == 1	|| arrUnits.get(i).getType() == 2)
				arrUnits.get(i).render(batch);
		}
	}

	public Array<Unit> getArrUnits() {	return arrUnits; }

	public Array<Boxes> getArrBoxes() {	return arrBox; }

	// tạo map bằng cách load file
	public void setArrUnits(String path) {
		try {
			FileHandle file = Gdx.files.internal(path);
			BufferedReader in = new BufferedReader(file.reader());
			String content;
			short j = 0;
			while ((content = in.readLine()) != null) {
				for (short i = 0; i < content.length(); i++) {
					short index = Short.parseShort(content.charAt(i) + "");
					float x = (i + 1)* Constants.SIZE + 5*Constants.NUM_BLOCK ;
					float y = (j + 1) * Constants.SIZE - Constants.NUM_BLOCK ;
					if (index > 0 && index < 5) {
						if (index != 2) {
							arrUnits.add(new Unit(x,y,index,Asset.instance.unit.UNIT.get(index)));
						} else if (index == 2) {
							arrBox.add(new Boxes(Constants.BOX_ID,x,y,	true));
						}
					} else if (index == 5) {
						player = new Player(Constants.PLAYER_ID,x,y);
					}
				}
				j++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
