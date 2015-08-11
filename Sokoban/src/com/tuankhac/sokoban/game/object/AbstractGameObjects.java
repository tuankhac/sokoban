package com.tuankhac.sokoban.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tuankhac.sokoban.game.utils.Constants;

public abstract class AbstractGameObjects {
	public float x;
	public float y;
	public float sizeX = Constants.SIZE ;
	public float sizeY = Constants.SIZE ;
	public AbstractGameObjects() {}

	public  void update(float deltaTime) {}

	public abstract void render(SpriteBatch batch);
	
	public float getX() {	return x;  }

	public float getY() {	return y; }

	public void setX(float x) {	this.x = x; }

	public void setY(float y) {	this.y = y;	}
	
}
