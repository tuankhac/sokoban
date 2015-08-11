package com.tuankhac.sokoban.game.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.tuankhac.sokoban.asset.Asset;

public abstract class CShape extends AbstractGameObjects {
	public Array<TextureRegion> arrImagesPlayer = Asset.instance.player.PLAYER;
	public Array<TextureRegion> arrImagesBox = Asset.instance.box.BOX;
	protected byte id;
	protected byte orient;
	Sprite sprite;

	public int getId() { return id; }

	public int getOrient() { return orient;	}

	public void drawCShape(SpriteBatch batch, Array<TextureRegion> arrTexture) {
		TextureRegion reg = null;
		if(orient < 4 )
			reg = arrTexture.get(0);
		else 
			reg = arrTexture.get(1);
		sprite = new Sprite(reg);
		sprite.setSize(sizeX, sizeY);
		sprite.setOrigin(0, 0);
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}

	public void changeOrient(byte orient) {
		if (this.orient != orient) {
			this.orient = orient;
		}
	}
	public Rectangle getRectangle() { return new Rectangle(x, y, sizeX, sizeY); 	}

	public Rectangle getRectangle(float x, float y) {	return new Rectangle(x, y, sizeX, sizeY);	}

}
