package com.tuankhac.sokoban.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tuankhac.sokoban.game.level.Level;
import com.tuankhac.sokoban.game.utils.Constants;

public class Player extends CShape {
	private byte count=0;
	boolean undo = true;

	//biến lấy vị trí của box trong mảng
	public Array<Integer> indexBox = new Array<Integer>();

	//khai báo các mảng để trả về vị trí trước đó player, box
	public Array<Vector2> pointPlayer = new Array<Vector2>();
	public Array<Vector2> pointBox = new Array<Vector2>();

	//khai báo các mảng trả về hướng trước đó của player, box
	public Array<Byte> orientPlayer = new Array<Byte>();
	public Array<Byte> orientBox = new Array<Byte>();

	public Player(byte id, float x, float y) {	
		this.x = x;
		this.y = y;
	}

	public boolean move(Level level) {
		float xt = x, yt = y;
		switch (orient) {
		case Constants.UP_ORIENT:
			yt += sizeY;
			break;
		case Constants.DOWN_ORIENT:
			yt -= sizeY;
			break;
		case Constants.LEFT_ORIENT:
			xt -= sizeX;
			break;
		case Constants.RIGHT_ORIENT:
			xt += sizeX;
			break;
		}
		for (Unit unit : level.getArrUnits()) {
			//kiểm tra nếu player chạm vào unit màu đỏ thì không cho qua nữa
			if (intersectRectangles(getRectangle(xt, yt), unit.getRectangle()) && !unit.notAllowPass()) {
				return false;
			}
			//kiểm tra nếu player nằm trên bóng thì chuyển màu sao cho giống
			if(intersectRectangles(getRectangle(xt, yt), unit.getRectangle())&&unit.unitFollowPass()){
				switch (orient) {
				case Constants.UP_ORIENT:
					orient=Constants.UP_ORIENT_CHANGE_COLOR;
					break;
				case Constants.DOWN_ORIENT:
					orient=Constants.DOWN_ORIENT_CHANGE_COLOR;
					break;
				case Constants.LEFT_ORIENT:
					orient=Constants.LEFT_ORIENT_CHANGE_COLOR;
					break;
				case Constants.RIGHT_ORIENT:
					orient=Constants.RIGHT_ORIENT_CHANGE_COLOR;
					break;
				}
				break;
			}
		}
		if (xt == x && yt == y)
			return false;
		for (Boxes box:level.getArrBoxes()) {
			if (intersectRectangles(box.getRectangle(), getRectangle(xt, yt))) {
				//nếu hộp đi được thì player cũng đi được
				if (box.move(level, this, orient,box)) {
					x = xt;
					y = yt;
				} 
				//nếu không thì cả hai không đi được
				else {
					return false;
				}
			}
		}
		//kiểm tra các cờ của các hộp
		for(Boxes boxs:level.getArrBoxes()){
			if(boxs.flag==false) count++;
		}

		//nếu mà các cờ đều false thì cho phép qua cửa
		if(count==level.getArrBoxes().size){
			//len level tai day
			System.out.println("qua cua");
		}

		//không thì sau mỗi vòng lặp gán count=0
		else{
			count=0;
		}
		x = xt;
		y = yt;

		return true;
	}
	//hàm lấy giao các đối tượng
	public boolean intersectRectangles(Rectangle a, Rectangle b) {
		return !(a.getX() > b.getX() + b.getWidth() - 1
				|| a.getX() + a.getWidth() - 1 < b.getX()
				|| a.getY() > b.getY() + b.getHeight() - 1 || a.getY()
				+ a.getHeight() - 1 < b.getY());
	}

	public boolean intersectRectangle(Rectangle a, Rectangle b) {
		return !(a.getX() >= b.getX() + b.getWidth()
				|| a.getX() + a.getWidth()  <= b.getX()
				|| a.getY() >= b.getY() + b.getHeight()  || a.getY()
				+ a.getHeight()  <= b.getY());
	}

	@Override
	public void render(SpriteBatch batch) {
		drawCShape(batch,arrImagesPlayer);
	}

}
