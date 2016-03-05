package com.example.uploadImage;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="http://192.168.56.1:8080/Upload/MySevlet";
				File file=Environment.getExternalStorageDirectory();
				File img_path=new File(file, "src.png");
				String fileName=img_path.getAbsolutePath();
				new UploadHttpThread(url, fileName).start();
			}
		});
	}

}
