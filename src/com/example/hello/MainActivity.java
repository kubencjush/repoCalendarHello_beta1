package com.example.hello;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;



@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	private Button btnStartService;
    private Button btnStopService;
	Bitmap bitmap;
	int lastImageRef;
	    
	Display display = getWindowManager().getDefaultDisplay();
	Point size = new Point();
	//display.getSize(size);
	int width = 0;//size.x;
	int height =0;// size.y;
	
	
	private OnClickListener startServiceListener = new OnClickListener() {       
		
		
        public void onClick(View arg0) {
        	startService(new Intent(MainActivity.this, MyService.class));
        	
        }
    };
    private OnClickListener stopServiceListener = new OnClickListener() {
        public void onClick(View arg0) {
        	stopService(new Intent(MainActivity.this, MyService.class));
        }
    };
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     /*
      *    
      	display.getSize(size);
        width = size.x;
    	height = size.y;
    	Intent intentToService=new Intent(MainActivity.this, MyService.class);
    	intentToService.putExtra("size_x", size.x);
    	intentToService.putExtra("size_y", value);
    */	
        Button setWallper2= (Button)findViewById(R.id.setWallperKalendar);
        ImageView imagePreview2 = (ImageView)findViewById(R.id.imageView01);
        imagePreview2.setImageResource(R.drawable.chicas_polar_pilsen_05);
        
        btnStartService = (Button)findViewById(R.id.btnStartService);
        btnStopService  = (Button)findViewById(R.id.btnStopService);
        btnStartService.setOnClickListener( startServiceListener);
        btnStopService.setOnClickListener( stopServiceListener);
        
        setWallper2.setOnClickListener(new Button.OnClickListener(){
        	   @Override
        	   public void onClick(View arg0) {
        	    // TODO Auto-generated method stub
        	    WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        	    try {
        	     myWallpaperManager.setResource(R.drawable.chicas_polar_pilsen_07);
        	    } catch (IOException e) {
        	     // TODO Auto-generated catch block
        	     e.printStackTrace();
        	    }
        	    
        	   }}
        );
   	   
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
