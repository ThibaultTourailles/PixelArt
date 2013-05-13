package view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.pixelart.Tools;

public class MyToolsView extends View {

	private Paint gridPaint;
	private Paint squarePaint;

	private boolean initialized = false;

	private int width;
	private int height;
	private int squareWidth;
	private int squareHeight;

	private Point[][] centers;
	private String[][] colorList;

	public MyToolsView(Context context) {
		super(context);
	}	

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		if (!this.initialized) {
			this.init();
			this.initialized = true;
		}

		int currentCol = 0;
		Path squarePath;
		for (String[] colors : this.colorList) {
			for (int i = 0 ; i < 9 ; ++i) {
				squarePath = new Path();

				this.squarePaint.setColor(Color.parseColor("#" + colors[i]));
				this.squarePaint.setAntiAlias(true);
				this.squarePaint.setStyle(Paint.Style.FILL);
				this.squarePaint.setStrokeJoin(Paint.Join.MITER);
				this.squarePaint.setStrokeWidth(4f);


				int left = currentCol * this.squareWidth;
				int top = i * this.squareHeight;
				squarePath.addRect(left, top, left + this.squareWidth, top + this.squareHeight, Path.Direction.CW);
				canvas.drawPath(squarePath, this.squarePaint);
			}
			currentCol++;
		}
		for (int i = 0 ; i < this.width ; i += this.squareWidth)
			canvas.drawLine(i, 0, i, this.squareHeight * 9, gridPaint);
		for (float i = 0 ; i <= this.height ; i += this.squareHeight)
			canvas.drawLine(0, i, this.width, i, gridPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float pointX = event.getX();
		float pointY = event.getY();
		this.changeColor(new Point((int)pointX, (int)pointY));
		return true;
	}

	protected void changeColor(Point p) {
		if (p.y < this.squareHeight * 9)
			Tools.color = Color.parseColor("#" + this.colorList[p.x/this.squareWidth][p.y/this.squareHeight]);
	}

	public void init() {

		this.width = ((View)this.getParent()).getWidth();
		this.height = ((View)this.getParent()).getHeight();
		this.squareWidth = this.width / 6;
		this.squareHeight = this.height / 9;

		this.gridPaint = new Paint();
		this.squarePaint = new Paint();

		this.gridPaint.setAntiAlias(true);
		this.gridPaint.setColor(Color.BLACK);
		this.gridPaint.setStyle(Paint.Style.STROKE);
		this.gridPaint.setStrokeJoin(Paint.Join.MITER);
		this.gridPaint.setStrokeWidth(3f);

		this.colorList = new String[][] { {"822111", "A46A21", "AA8831", "076239", "1A764D", "1C4587", "41236D", "83334C", "000000" },
				{"AC2B16", "CF8933", "D5AE49", "0B804B", "2A9C68", "285BAC", "653E9B", "B65775", "434343" },
				{"CC3A21", "EAA041", "F2C960", "149E60", "3DC789", "3C78D8", "8E63CE", "E07798", "666666" },
				{"E66550", "FFBC6B", "FCDA83", "44B984", "68DFA9", "6D9EEB", "B694E8", "F7A7C0", "999999" },
				{"EFA093", "FFD6A2", "FCE8B3", "89D3B2", "A0EAC9", "A4C2F4", "D0BCF1", "FBC8D9", "CCCCCC" },
				{"F6C5BE", "FFE6C7", "FEF1D1", "B9E4D0", "C6F3DE", "C9DAF8", "E4D7F5", "FCDEE8", "FFFFFF" } };
		this.centers = new Point[9][6];
		for (int i = 0, y = this.squareHeight / 2; i < 9 ; ++i, y += this.squareHeight)
			for (int j = 0, x = this.squareWidth / 2 ; j < 6 ; ++j, x += this.squareWidth)
				this.centers[i][j] = new Point(x,y);
	}

}
