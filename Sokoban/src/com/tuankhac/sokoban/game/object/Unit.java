package com.tuankhac.sokoban.game.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Unit extends AbstractGameObjects {

	private TextureRegion image;
	private int type;
	private Sprite sprite;

	public Unit(float x, float y, int type,TextureRegion image_unit) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.image = image_unit;
	}

	public int getType() {	return type;}

	public void setType(int type) {	this.type = type; }
	
	//lấy về kích thước của các unit
	public Rectangle getRectangle() { return new Rectangle(x, y, sizeX, sizeY); }

	// unit không cho phép đi qua
	public boolean notAllowPass() {
		if (type != 1)
			return true;
		return false;
	}

	// các unit cho phép player và hộp đi qua
	public boolean unitFollowPass() {
		if (type == 3)
			return true;
		return false;
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite = new Sprite(image);
		sprite.setSize(sizeX, sizeY);
		sprite.setOrigin(0, 0);
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}
}
