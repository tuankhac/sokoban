package com.tuankhac.sokoban.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tuankhac.sokoban.game.level.Level;
import com.tuankhac.sokoban.game.utils.Constants;

public class Boxes extends CShape {

	public boolean flag;
	private boolean check = false;
	
	//biến lấy vị trí của box trong mảng
		public Array<Integer> indexBox = new Array<Integer>();

	public Boxes(byte id,float x, float y,boolean flag) {
		this.x = x;
		this.y = y;
		this.flag=flag;
	}

	public boolean move(Level level, Player player, byte orient,Boxes box) {
		float xt = x,  yt = y;
		switch (orient) {
		case Constants.UP_ORIENT:
		case Constants.UP_ORIENT_CHANGE_COLOR:
			yt += sizeY;
			break;
		case Constants.DOWN_ORIENT:
		case Constants.DOWN_ORIENT_CHANGE_COLOR:
			yt -= sizeY;
			break;
		case Constants.LEFT_ORIENT:
		case Constants.LEFT_ORIENT_CHANGE_COLOR:
			xt -= sizeX;
			break;
		case Constants.RIGHT_ORIENT:
		case Constants.RIGHT_ORIENT_CHANGE_COLOR:
			xt += sizeX;
			break;
		}
		
		//kiểm tra các hộp không đi qua nhau
		for(Boxes b:level.getArrBoxes()){
			if(b.x==xt && b.y==yt) return false;
		}
		//kiểm tra nếu hộp không di chuyển thì trả về false
		if(xt==x && yt==y)
			return false;
		for (Unit unit : level.getArrUnits()) {
			//kiểm tra nếu hộp va vào thành unit 1 thì không cho qua tiếp
			if ((unit.getY() == yt && unit.getX() == getX() && !unit.notAllowPass())||
					(unit.getX() == xt && unit.getY() == getY() && !unit.notAllowPass())) {
				return false;
			} 
			else{
				//kiểm tra hộp có nằm trên bóng hay không
				if(player.intersectRectangle(box.getRectangle(xt,yt),unit.getRectangle())){
					if(unit.unitFollowPass()){
						check=true;
						switch (orient) {
						case Constants.UP_ORIENT:
							this.orient=Constants.UP_ORIENT_CHANGE_COLOR;
							break;
						case Constants.DOWN_ORIENT:
							this.orient=Constants.DOWN_ORIENT_CHANGE_COLOR;
							break;
						case Constants.LEFT_ORIENT:
							this.orient=Constants.LEFT_ORIENT_CHANGE_COLOR;
							break;
						case Constants.RIGHT_ORIENT:
							orient=Constants.RIGHT_ORIENT_CHANGE_COLOR;
							break;

						}
						break;
					}
					else{
						check=false;
					}
				}
				else{
					switch (orient) {
					case Constants.UP_ORIENT_CHANGE_COLOR:
						this.orient=Constants.UP_ORIENT;
						break;
					case Constants.DOWN_ORIENT_CHANGE_COLOR:
						this.orient=Constants.DOWN_ORIENT;
						break;
					case Constants.LEFT_ORIENT_CHANGE_COLOR:
						this.orient=Constants.LEFT_ORIENT;
						break;
					case Constants.RIGHT_ORIENT_CHANGE_COLOR:
						this.orient=Constants.RIGHT_ORIENT;
						break;	
					default:
						break;
					}
				}
			}
		}
		//kiểm tra hộp đang di chuyển mà intersect với bóng thì gán flag bằng false
		if(check == true){
			box.flag = false;
			check = false;
		}
		//nếu mà không intersect với bất kì bóng nào thì gán cờ bằng true
		else{
			box.flag = true;
		}

		//gán giá trị của các vị trí vào mảng để có thể quay lại
		player.pointPlayer.add(new Vector2(player.x, player.y));
		player.orientPlayer.add(orient);

		//gán giá trị của các vị trí vào mảng để có thể quay lại
		player.pointBox.add(new Vector2(x, y));
		player.indexBox.add(level.getArrBoxes().indexOf(box, true));
		player.orientBox.add(this.orient);

		//gán giá trị cho hộp nếu đúng để hộp di chuyển
		this.y = yt;
		this.x = xt;
		return true;
	}

	@Override
	public void render(SpriteBatch batch) {
		drawCShape(batch,arrImagesBox);
	}
}
