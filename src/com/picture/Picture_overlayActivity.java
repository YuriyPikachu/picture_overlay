package com.picture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Picture_overlayActivity extends Activity {
	/** Called when the activity is first created. */
	ImageView image;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		image = (ImageView) findViewById(R.id.image);
	}

	public void first(View v) {

		// 防止出现Immutable bitmap passed to Canvas constructor错误
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.apple).copy(Bitmap.Config.ARGB_8888, true);
		Bitmap bitmap2 = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.go)).getBitmap();

		Bitmap newBitmap = null;

		newBitmap = Bitmap.createBitmap(bitmap1);
		Canvas canvas = new Canvas(newBitmap);
		Paint paint = new Paint();

		int w = bitmap1.getWidth();
		int h = bitmap1.getHeight();

		int w_2 = bitmap2.getWidth();
		int h_2 = bitmap2.getHeight();

		paint.setColor(Color.GRAY);
		paint.setAlpha(125);
		canvas.drawRect(0, 0, bitmap1.getWidth(), bitmap1.getHeight(), paint);

		paint = new Paint();
		canvas.drawBitmap(bitmap2, Math.abs(w - w_2) / 2,
				Math.abs(h - h_2) / 2, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// 存储新合成的图片
		canvas.restore();

		image.setImageBitmap(newBitmap);
	}

	public void second(View v) {

		Bitmap bitmap1 = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.apple)).getBitmap();
		Bitmap bitmap2 = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.go)).getBitmap();

		Drawable[] array = new Drawable[2];
		array[0] = new BitmapDrawable(bitmap1);
		array[1] = new BitmapDrawable(bitmap2);
		LayerDrawable la = new LayerDrawable(array);
		// 其中第一个参数为层的索引号，后面的四个参数分别为left、top、right和bottom
		la.setLayerInset(0, 0, 0, 0, 0);
		la.setLayerInset(1, 20, 20, 20, 20);
		image.setImageDrawable(la);
	}
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	//		
	// }
}