package lesehankoding.com.exampleprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import lesehankoding.com.easyprogressloading.EasyProgressAnim;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingShow();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingShow2();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingShow3();
            }
        });
    }

    void loadingShow(){
        final EasyProgressAnim easyProgressAnim = new EasyProgressAnim(MainActivity.this);
        easyProgressAnim.setLabel("Load")
                .setDeskripsi("Mohon menunggu...")
                .setCancellable(true)
                .setFileName("clock.json").setBackgroundColor(R.color.colorPrimary)
                .setDimAmount(0.5f)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                easyProgressAnim.dismiss();
            }
        },3000);


    }
    void loadingShow2(){
        final EasyProgressAnim easyProgressAnim = new EasyProgressAnim(MainActivity.this);
        easyProgressAnim.setLabel("Loading")
                .setCancellable(true)
                .setFileName("load.json").setBackgroundColor(R.color.colorPrimary)
                .setDimAmount(0.5f)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                easyProgressAnim.dismiss();
            }
        },3000);


    }

    void loadingShow3(){
        final EasyProgressAnim easyProgressAnim = new EasyProgressAnim(MainActivity.this);
        easyProgressAnim
                .setDeskripsi("Mohon Menunggu...")
                .setCancellable(true)
                .setFileName("loading_default.json").setBackgroundColor(R.color.colorPrimary)
                .setDimAmount(0.5f)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                easyProgressAnim.dismiss();
            }
        },3000);


    }

}