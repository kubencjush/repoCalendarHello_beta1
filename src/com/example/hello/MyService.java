package com.example.hello;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int x;
	private Toast myToast;
	private Timer updatingTimer;
	private TimerTask notify = new TimerTask() {
		@Override
		public void run() {
			
			x = (int) (Math.random() * 11 + 4);

		
			
			Calendar rightNow1 = Calendar.getInstance();
			
			myToast.setText(" --- first day of week="+rightNow1.getFirstDayOfWeek()+
					"  first day of month>>="+rightNow1.getMinimalDaysInFirstWeek()+
					" --second - "+rightNow1.getActualMinimum(Calendar.DAY_OF_WEEK)+
				//	" day of week month="+rightNow1.get(Calendar.DAY_OF_WEEK_IN_MONTH)+""+
				//				"    Calendar.DAY_OF_MONTH="+Calendar.DAY_OF_MONTH+
				//				"    Calendar.DAY_OF_WEEK_IN_MONTH="+Calendar.DAY_OF_WEEK_IN_MONTH+
								"    Calendar.DAY_OF_YEAR="+Calendar.DAY_OF_YEAR+
				//				 maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
								
								" get actual maximum ="+rightNow1.getActualMaximum(Calendar.DAY_OF_MONTH)
				//				+
				//				" get actual min day of week="+rightNow1.getActualMinimum(Calendar.DAY_OF_WEEK)+
				//				" get min days in first week="+rightNow1.getMinimalDaysInFirstWeek()+
				//				" get min days week="+rightNow1.get(Calendar.DAY_OF_WEEK_IN_MONTH)
								);
			
			myToast.show();
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		updatingTimer = new Timer();
		
		myToast = Toast.makeText(getApplicationContext(),
				"Usługa została uruchomiona", Toast.LENGTH_SHORT);

		myToast.show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		updatingTimer.scheduleAtFixedRate(notify, 5 * 1000, 5 * 1000);	
		createWallper1();

	}

	@Override
	public void onDestroy() {
		updatingTimer.cancel();
		myToast.setText("Usługa została zatrzymana");
		myToast.show();
		super.onDestroy();
	}

	
	public void ekran(){
		
	//	Display display = getWindowManager().getDefaultDisplay(); 
	//	int width = display.getWidth();
	//	int height = display.getHeight();
		
	//	DisplayMetrics metrics = new DisplayMetrics();
	//	getWindowManager().getDefaultDisplay().getMetrics(metrics);

	//	metrics.heightPixels;
	//	metrics.widthPixels;
	}
	
	public void createWallper1() {
		
		// / File externalDir = Environment.getExternalStorageDirectory();
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res,
				R.drawable.chicas_polar_pilsen_04);
		Paint paint = new Paint();
		Paint curPaint=new Paint();
		Bitmap newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		Calendar rightNow = Calendar.getInstance();
		Calendar firstDayOfMonth = Calendar.getInstance();
		Canvas canvas = new Canvas(newBitmap);

		//canvas.getHeight();
		//and canvas.getWidth()
		
		int tablica[] = new int[rightNow.getActualMaximum(Calendar.DAY_OF_MONTH)];
		for (int i = 0; i < rightNow.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

			tablica[i] = i+1 ;
		}

		paint.setStyle(Paint.Style.FILL);
		paint.setAlpha(0);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		
		
		canvas.drawRect(4, 200, 332, 330, paint);
		paint.setTextSize(16);
		paint.setColor(Color.BLACK);
	
		curPaint.setColor(Color.YELLOW);
		curPaint.setFakeBoldText(true);
		
		/**
		 * calculating first day of month
		 *  eg. friday
		 */
	
		int delayDays=0;
			
		firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
		delayDays= firstDayOfMonth.get(Calendar.DAY_OF_WEEK)-2;
	
		int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
	
		paint.setAlpha(255);

		//Service.getSystemService(WINDOW_SERVICE);
	//	Display display = ((WindowManager) Service.getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
	//	   int width = display.getWidth();
	//	   int height = display.getHeight();
		
		
	//	Display display = ((this) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	//	int width = display.getWidth(); 
		
		/**
		 * printing calendar board
		 */
		try {			
			int counter = 0;
			for (int y = 0; y < 6; y++) {
				for (int x = 0; x < 7; x++) {
					if(delayDays!=0 && x==0 && y==0){
						x=x+delayDays;
					}
					if(counter<rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) && tablica[counter]==currentDay ){
						Rect rect = new Rect();
						paint.setTypeface(Typeface.SERIF);
						paint.setFakeBoldText(true); 
						paint.setTextSize(16);
						paint.setColor(Color.RED);
						canvas.drawRect((72 + 30 * x)-5, (220 + 20 * y)+2, (72 + 30 * x)+22, (220 + 20 * y)-15, curPaint);

						canvas.drawText(""+currentDay + "", 72 + 30 * x,220 + 20 * y, paint);
						counter++;
					}
					else if(counter<rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) ){
						paint.setTypeface(Typeface.SERIF);
						paint.setFakeBoldText(false); 
						paint.setTextSize(16);
						paint.setColor(Color.BLACK);
						canvas.drawText(tablica[counter] + "", 72 + 30 * x,
								220 + 20 * y, paint);
						counter++;
					}
					else {
						paint.setTypeface(Typeface.SERIF);
						paint.setFakeBoldText(false); 
						paint.setTextSize(16);
						paint.setColor(Color.BLACK);
						canvas.drawText(  ""/*canvas.getHeight()+"-"+canvas.getWidth()*/, 72 + 30 * x,
								220 + 20 * y, paint);
					}	
			}
			}		
			getApplicationContext().setWallpaper(newBitmap);
		} catch (IOException e) {
		}

		}

	
	// TODO  delete this in the future
	public void tapeta() {
		WallpaperManager myWallpaperManager = WallpaperManager
				.getInstance(getApplicationContext());
		try {

			if (x == 14) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_14);
			} else if (x == 4) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_04);
			} else if (x == 5) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_05);
			} else if (x == 6) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_06);
			} else if (x == 7) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_07);
			} else if (x == 8) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_08);
			} else if (x == 9) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_09);
			} else if (x == 10) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_10);
			} else if (x == 11) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_11);
			} else if (x == 12) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_12);
			} else if (x == 13) {
				myWallpaperManager
						.setResource(R.drawable.chicas_polar_pilsen_13);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//TODO stop deleting here

}
