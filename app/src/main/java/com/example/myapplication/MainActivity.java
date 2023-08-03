package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1= this.findViewById(R.id.button);

        Bitmap saveBm =  BitmapFactory.decodeResource(getResources(), R.drawable.snapchat_2128188078);
        NumberPicker nm=this.findViewById(R.id.numberpicker);
        nm.setMinValue(0);
        nm.setMaxValue(100);

        ImageView tv1;
        final Bitmap[] bm = new Bitmap[1];
        tv1= (ImageView) findViewById(R.id.imageView3);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quality= nm.getValue();
                String root = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
                Log.d("jashwantfre","root-"+root);
                File myDir = new File(root + "/saved_images");
                Log.d("jashwant","myDir-"+myDir);
                myDir.mkdirs();
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                String fname = "Image-"+ n +".jpg";
                File file = new File (myDir, fname);

                if (file.exists ()) file.delete ();
                try {
                    Log.d("jashwant","inside try");
                    FileOutputStream out = new FileOutputStream(file);
                    Log.d("jashwant","inside try out-"+out);
                    saveBm.compress(Bitmap.CompressFormat.JPEG, quality,out);
                    out.flush();
                    out.close();
                    bm[0] = BitmapFactory.decodeFile(root+"/"+fname);
                    Log.d("jashwant","inside bm-"+root+"/"+fname);


                } catch (Exception e) {
                    Log.d("jashwant","inside catch");
                    e.printStackTrace();
                }
                Bitmap bm = BitmapFactory.decodeFile(root+"/"+fname);
                tv1.setImageBitmap(bm);

            }
        });


    }
}