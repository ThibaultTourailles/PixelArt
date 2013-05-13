package view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.pixelart.MyPoint;
import com.pixelart.Tools;

@SuppressLint("DrawAllocation")
public class MyTouchView extends View{

	private Paint gridPaint;
	private Paint clearPaint;

	private MyPoint[][] centers;
	private int width;
	private int height;
	private final int SIZE = Tools.size;

	/*	public Button btnReset;
	public LayoutParams params;*/

	@SuppressLint("NewApi")
	public MyTouchView(Context context) {
		super(context);
		this.clearPaint = new Paint();
		clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

		/* Getting the size of the screen */
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		this.width = size.x;
		this.height = size.y;

		this.gridPaint = new Paint();

		this.gridPaint.setAntiAlias(true);
		this.gridPaint.setColor(Color.BLACK);
		this.gridPaint.setStyle(Paint.Style.STROKE);
		this.gridPaint.setStrokeJoin(Paint.Join.MITER);
		this.gridPaint.setStrokeWidth(3f);

		this.centers = new MyPoint[this.height/SIZE][this.width/SIZE];
		for (int i = 0, y = SIZE/2; i < this.centers.length ; ++i, y += SIZE)
			for (int j = 0, x = SIZE/2 ; j < this.centers[i].length ; ++j, x += SIZE)
				this.centers[i][j] = new MyPoint(x,y, SIZE,  Color.WHITE);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
	
		setDrawingCacheEnabled(true);
		setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		
		canvas.drawRect(0, 0, this.width, this.height, this.clearPaint);
		for (MyPoint[] points : this.centers)
			for (MyPoint p : points)
				canvas.drawRect(p.left, p.top, p.right, p.bottom, p.paint);
	
		Tools.savedBitmap = getDrawingCache();
	
		for (int i = 0 ; i < this.width ; i += SIZE)
			canvas.drawLine(i, 0, i, this.height, gridPaint);
		for (int i = 0 ; i < this.height ; i += SIZE)
			canvas.drawLine(0, i, this.width, i, gridPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		Point position = this.getCenter(new Point((int)event.getX(), (int)event.getY()));
		int x = position.x;
		int y = position.y;

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			this.centers[x][y].paint.setColor(Tools.color);
			return true;

		case MotionEvent.ACTION_MOVE:
			this.centers[x][y].paint.setColor(Tools.color);
			break;

		case MotionEvent.ACTION_UP:
			this.centers[x][y].paint.setColor(Tools.color);
			break;
		default:
			return false;
		}
		
		postInvalidate();
		return true;
	}



	protected Point getCenter(Point p) {
		return new Point(p.y/SIZE,p.x/SIZE);
	}

	public void clean() {
		for (MyPoint[] points : this.centers)
			for (MyPoint p : points)
				p.paint.setColor(Color.WHITE);
	}
}
