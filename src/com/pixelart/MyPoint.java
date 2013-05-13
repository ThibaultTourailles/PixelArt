package com.pixelart;

import android.graphics.Paint;

public class MyPoint extends android.graphics.Point {
	
	public Paint paint;
	public int size;
	public int left;
	public int top;
	public int right;
	public int bottom;
	
	public MyPoint(int centerX, int centerY, int size, int color) {
		super(centerX, centerY);
		
		this.size = size;
		this.left = centerX - size/2;
		this.top = centerY - size/2;
		this.right = centerX + size/2;
		this.bottom = centerY + size/2;
		
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		this.paint.setColor(color);
		this.paint.setStyle(Paint.Style.FILL);
		this.paint.setStrokeJoin(Paint.Join.MITER);
		this.paint.setStrokeWidth(3f);
	}
}
