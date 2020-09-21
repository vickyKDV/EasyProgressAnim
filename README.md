# EasyProgressAnim
[![](https://jitpack.io/v/vickyKDV/EasyImagePicker.svg)](https://jitpack.io/#vickyKDV/EasyImagePicker)


![alt text](https://raw.githubusercontent.com/vickyKDV/EasyProgressAnim/master/EasyProgressAnim.gif)

   Cara implementasi


   Set pada build.gradle application

     allprojects {
          repositories {
             ...
             ...
             maven { url "https://jitpack.io" }

          }
      }

   Set pada build.gradle module

    dependencies {
        ...
        ...
	     implementation 'com.github.vickyKDV:EasyProgressAnim:0.1'
	}



Cara Menggunakan (Contoh : [Lihat Contoh](https://github.com/vickyKDV/EasyProgressAnim/blob/master/app/src/main/java/lesehankoding/com/exampleprogress/MainActivity.java))

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final EasyProgressAnim easyProgressAnim = new EasyProgressAnim(MainActivity.this);
                            easyProgressAnim.setLabel("Load")
                                    .setDeskripsi("Mohon menunggu...")
                                    .setCancellable(true)
                                    .setFileName("clock.json").setBackgroundColor(R.color.colorPrimary)
                                    .setDimAmount(0.5f)
                                    .show();
                }
            });
        }


    }
