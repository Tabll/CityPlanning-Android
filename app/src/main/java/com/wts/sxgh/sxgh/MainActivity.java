package com.wts.sxgh.sxgh;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.views.PgyerDialog;
import com.wts.sxgh.load.LoadActivity;
import com.wts.sxgh.view.SlidingMenu;

import java.util.Random;

import static com.wts.sxgh.load.LoadActivity.s;

public class MainActivity extends Activity {

    public static boolean isprograming = false;
    public static int buildings = 0;
    public static int insidebuildings = 0;
    public static int firststep = 10;
    public static int allsum = 0;
    private SlidingMenu mLeftMenu;
    int sj[];
    private Vibrator vibrator;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    setpged();
                    break;
                case 1:
                    setpgeded();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("数学规划大作业")
                .setContentText("团队：陈西西 陈旭贞 魏天述")
                .setSmallIcon(R.drawable.icon_top).setContentIntent(pi)
                .build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        nm.notify(0, notification);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        //nm.cancel(0);关闭通知
        PgyCrashManager.register(this);
        PgyerDialog.setDialogTitleBackgroundColor("#ff0000");
        PgyerDialog.setDialogTitleTextColor("#ffffff");
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
        ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
        ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
        ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
        ImageButton b15 = (ImageButton)findViewById(R.id.ib15);
        ImageButton b16 = (ImageButton)findViewById(R.id.ib16);
        ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
        ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
        ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
        ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
        ImageButton b25 = (ImageButton)findViewById(R.id.ib25);
        ImageButton b26 = (ImageButton)findViewById(R.id.ib26);
        ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
        ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
        ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
        ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
        ImageButton b35 = (ImageButton)findViewById(R.id.ib35);
        ImageButton b36 = (ImageButton)findViewById(R.id.ib36);
        ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
        ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
        ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
        ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
        ImageButton b45 = (ImageButton)findViewById(R.id.ib45);
        ImageButton b46 = (ImageButton)findViewById(R.id.ib46);
        ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
        ImageButton b52 = (ImageButton)findViewById(R.id.ib52);
        ImageButton b53 = (ImageButton)findViewById(R.id.ib53);
        ImageButton b54 = (ImageButton)findViewById(R.id.ib54);
        ImageButton b55 = (ImageButton)findViewById(R.id.ib55);
        ImageButton b56 = (ImageButton)findViewById(R.id.ib56);
        ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
        ImageButton b62 = (ImageButton)findViewById(R.id.ib62);
        ImageButton b63 = (ImageButton)findViewById(R.id.ib63);
        ImageButton b64 = (ImageButton)findViewById(R.id.ib64);
        ImageButton b65 = (ImageButton)findViewById(R.id.ib65);
        ImageButton b66 = (ImageButton)findViewById(R.id.ib66);

        Button start = (Button)findViewById(R.id.button);
        start.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(200);
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(isprograming){
                        Toast.makeText(MainActivity.this,"已规划，请重新规划",Toast.LENGTH_SHORT).show();
                    }else{
                        isprograming = true;
                        //Toast.makeText(MainActivity.this, "正在规划...请稍等",Toast.LENGTH_SHORT).show();
                        prepare();
                    }
                }
                return false;
            }
        });
        Button restart = (Button)findViewById(R.id.rbutton);
        restart.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(100);
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    reset();
                    Toast.makeText(MainActivity.this, "请重新选择规划区域",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });



        b11.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[1][1] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[1][1] = 1;
                    }else if(LoadActivity.x[1][1] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[1][1] = 0;
                    }
                }
                return false;
            }
        });

        b12.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[1][2] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[1][2] = 1;
                    }else if(LoadActivity.x[1][2] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[1][2] = 0;
                    }
                }
                return false;
            }
        });

        b13.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[1][3] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[1][3] = 1;
                    }else if(LoadActivity.x[1][3] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[1][3] = 0;
                    }
                }
                return false;
            }
        });

        b14.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[1][4] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[1][4] = 1;
                    }else if(LoadActivity.x[1][4] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[1][4] = 0;
                    }
                }
                return false;
            }
        });

        b15.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[1][5] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[1][5] = 1;
                    }else if(LoadActivity.x[1][5] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[1][5] = 0;
                    }
                }
                return false;
            }
        });

        b16.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[1][6] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[1][6] = 1;
                    }else if(LoadActivity.x[1][6] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[1][6] = 0;
                    }
                }
                return false;
            }
        });


        b21.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[2][1] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[2][1] = 1;
                    }else if(LoadActivity.x[2][1] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[2][1] = 0;
                    }
                }
                return false;
            }
        });

        b22.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[2][2] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[2][2] = 1;
                    }else if(LoadActivity.x[2][2] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[2][2] = 0;
                    }
                }
                return false;
            }
        });

        b23.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[2][3] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[2][3] = 1;
                    }else if(LoadActivity.x[2][3] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[2][3] = 0;
                    }
                }
                return false;
            }
        });

        b24.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[2][4] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[2][4] = 1;
                    }else if(LoadActivity.x[2][4] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[2][4] = 0;
                    }
                }
                return false;
            }
        });

        b25.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[2][5] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[2][5] = 1;
                    }else if(LoadActivity.x[2][5] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[2][5] = 0;
                    }
                }
                return false;
            }
        });

        b26.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[2][6] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[2][6] = 1;
                    }else if(LoadActivity.x[2][6] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[2][6] = 0;
                    }
                }
                return false;
            }
        });


        b31.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[3][1] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[3][1] = 1;
                    }else if(LoadActivity.x[3][1] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[3][1] = 0;
                    }
                }
                return false;
            }
        });

        b32.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[3][2] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[3][2] = 1;
                    }else if(LoadActivity.x[3][2] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[3][2] = 0;
                    }
                }
                return false;
            }
        });

        b33.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[3][3] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[3][3] = 1;
                    }else if(LoadActivity.x[3][3] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[3][3] = 0;
                    }
                }
                return false;
            }
        });

        b34.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[3][4] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[3][4] = 1;
                    }else if(LoadActivity.x[3][4] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[3][4] = 0;
                    }
                }
                return false;
            }
        });

        b35.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[3][5] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[3][5] = 1;
                    }else if(LoadActivity.x[3][5] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[3][5] = 0;
                    }
                }
                return false;
            }
        });

        b36.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[3][6] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[3][6] = 1;
                    }else if(LoadActivity.x[3][6] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[3][6] = 0;
                    }
                }
                return false;
            }
        });


        b41.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[4][1] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[4][1] = 1;
                    }else if(LoadActivity.x[4][1] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[4][1] = 0;
                    }
                }
                return false;
            }
        });

        b42.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[4][2] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[4][2] = 1;
                    }else if(LoadActivity.x[4][2] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[4][2] = 0;
                    }
                }
                return false;
            }
        });

        b43.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[4][3] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[4][3] = 1;
                    }else if(LoadActivity.x[4][3] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[4][3] = 0;
                    }
                }
                return false;
            }
        });

        b44.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[4][4] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[4][4] = 1;
                    }else if(LoadActivity.x[4][4] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[4][4] = 0;
                    }
                }
                return false;
            }
        });

        b45.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[4][5] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[4][5] = 1;
                    }else if(LoadActivity.x[4][5] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[4][5] = 0;
                    }
                }
                return false;
            }
        });

        b46.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[4][6] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[4][6] = 1;
                    }else if(LoadActivity.x[4][6] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[4][6] = 0;
                    }
                }
                return false;
            }
        });


        b51.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[5][1] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[5][1] = 1;
                    }else if(LoadActivity.x[5][1] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[5][1] = 0;
                    }
                }
                return false;
            }
        });

        b52.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[5][2] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[5][2] = 1;
                    }else if(LoadActivity.x[5][2] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[5][2] = 0;
                    }
                }
                return false;
            }
        });

        b53.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[5][3] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[5][3] = 1;
                    }else if(LoadActivity.x[5][3] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[5][3] = 0;
                    }
                }
                return false;
            }
        });

        b54.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[5][4] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[5][4] = 1;
                    }else if(LoadActivity.x[5][4] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[5][4] = 0;
                    }
                }
                return false;
            }
        });

        b55.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[5][5] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[5][5] = 1;
                    }else if(LoadActivity.x[5][5] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[5][5] = 0;
                    }
                }
                return false;
            }
        });

        b56.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[5][6] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[5][6] = 1;
                    }else if(LoadActivity.x[5][6] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[5][6] = 0;
                    }
                }
                return false;
            }
        });


        b61.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[6][1] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[6][1] = 1;
                    }else if(LoadActivity.x[6][1] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[6][1] = 0;
                    }
                }
                return false;
            }
        });

        b62.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[6][2] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[6][2] = 1;
                    }else if(LoadActivity.x[6][2] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[6][2] = 0;
                    }
                }
                return false;
            }
        });

        b63.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[6][3] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[6][3] = 1;
                    }else if(LoadActivity.x[6][3] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[6][3] = 0;
                    }
                }
                return false;
            }
        });

        b64.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[6][4] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[6][4] = 1;
                    }else if(LoadActivity.x[6][4] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[6][4] = 0;
                    }
                }
                return false;
            }
        });

        b65.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[6][5] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[6][5] = 1;
                    }else if(LoadActivity.x[6][5] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[6][5] = 0;
                    }
                }
                return false;
            }
        });

        b66.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    vibrator.vibrate(50);
                }else if(!isprograming && motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(LoadActivity.x[6][6] == 0) {
                        view.setBackgroundResource(R.drawable.is_building);
                        LoadActivity.x[6][6] = 1;
                    }else if(LoadActivity.x[6][6] == 1) {
                        view.setBackgroundResource(R.drawable.empty);
                        LoadActivity.x[6][6] = 0;
                    }
                }
                return false;
            }
        });

        // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());

        RelativeLayout setting = (RelativeLayout) findViewById(R.id.setting);
        setting.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    view.setBackgroundColor(Color.TRANSPARENT);
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    view.setBackgroundColor(Color.parseColor("#F5F5DC"));
                }
                return false;
            }
        });
        RelativeLayout update = (RelativeLayout) findViewById(R.id.update);
        update.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    view.setBackgroundColor(Color.TRANSPARENT);
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    view.setBackgroundColor(Color.parseColor("#F5F5DC"));
                }
                return false;
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    //判断所选住宅区是否符合规划条件
    private void prepare(){
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                if(LoadActivity.x[i][j] == 1) {
                    buildings++;
                }
            }
        }
        if(buildings >= 36 - firststep){
            Toast.makeText(MainActivity.this, "所选"+buildings+"幢住宅区过多，请重新选择",Toast.LENGTH_SHORT).show();
            reset();
        }else if(buildings == 0) {
            Toast.makeText(MainActivity.this, "请选择住宅区", Toast.LENGTH_SHORT).show();
            reset();
        }else if(buildings <= 10){
            Toast.makeText(MainActivity.this, "所选择的住宅区过少，请重新选择", Toast.LENGTH_SHORT).show();
            reset();
        }else{
            Toast.makeText(MainActivity.this, "即将规划"+buildings+"幢住宅区的周边",Toast.LENGTH_SHORT).show();
            beforepg();
        }
    }

    //判断所选内围住宅区个数是否符合规划条件
    private void beforepg(){
        for(int i = 2; i < 6; i++){
            for(int j = 2;j < 6; j++){
                if(LoadActivity.x[i][j] == 1){
                    insidebuildings++;
                }
            }
        }
        if(insidebuildings >= 4 && insidebuildings <=10){
            setwillpg();
        }else{
            Toast.makeText(MainActivity.this, "所选"+insidebuildings+"幢内围住宅区不满足规划条件，请重新选择",Toast.LENGTH_SHORT).show();
            reset();
        }
    }

    //正式规划前，画出将要接受规划的地区
    private void setwillpg(){
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                if(LoadActivity.x[i][j] == 0){
                    if(i == 2){
                        if(j == 1){
                        }else if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.will_pg);
                        }
                    }else if(i == 3){
                        if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.will_pg);
                        }
                    }else if(i == 4){
                        if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 5){
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.will_pg);
                        }
                    }else if(i == 5){
                        if(j == 2){
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 3){
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.will_pg);
                        }else if(j == 4){
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.will_pg);
                        }else if (j == 5){
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.will_pg);
                        }
                    }
                }
            }
        }
        //Toast.makeText(MainActivity.this, "即将规划",Toast.LENGTH_SHORT).show();
        program();
    }

    //重置
    private void reset(){
        ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
        ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
        ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
        ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
        ImageButton b15 = (ImageButton)findViewById(R.id.ib15);
        ImageButton b16 = (ImageButton)findViewById(R.id.ib16);
        ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
        ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
        ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
        ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
        ImageButton b25 = (ImageButton)findViewById(R.id.ib25);
        ImageButton b26 = (ImageButton)findViewById(R.id.ib26);
        ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
        ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
        ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
        ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
        ImageButton b35 = (ImageButton)findViewById(R.id.ib35);
        ImageButton b36 = (ImageButton)findViewById(R.id.ib36);
        ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
        ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
        ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
        ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
        ImageButton b45 = (ImageButton)findViewById(R.id.ib45);
        ImageButton b46 = (ImageButton)findViewById(R.id.ib46);
        ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
        ImageButton b52 = (ImageButton)findViewById(R.id.ib52);
        ImageButton b53 = (ImageButton)findViewById(R.id.ib53);
        ImageButton b54 = (ImageButton)findViewById(R.id.ib54);
        ImageButton b55 = (ImageButton)findViewById(R.id.ib55);
        ImageButton b56 = (ImageButton)findViewById(R.id.ib56);
        ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
        ImageButton b62 = (ImageButton)findViewById(R.id.ib62);
        ImageButton b63 = (ImageButton)findViewById(R.id.ib63);
        ImageButton b64 = (ImageButton)findViewById(R.id.ib64);
        ImageButton b65 = (ImageButton)findViewById(R.id.ib65);
        ImageButton b66 = (ImageButton)findViewById(R.id.ib66);
        TextView sum = (TextView)findViewById(R.id.sum);
        b11.setBackgroundResource(R.drawable.empty);
        b12.setBackgroundResource(R.drawable.empty);
        b13.setBackgroundResource(R.drawable.empty);
        b14.setBackgroundResource(R.drawable.empty);
        b15.setBackgroundResource(R.drawable.empty);
        b16.setBackgroundResource(R.drawable.empty);
        b21.setBackgroundResource(R.drawable.empty);
        b22.setBackgroundResource(R.drawable.empty);
        b23.setBackgroundResource(R.drawable.empty);
        b24.setBackgroundResource(R.drawable.empty);
        b25.setBackgroundResource(R.drawable.empty);
        b26.setBackgroundResource(R.drawable.empty);
        b31.setBackgroundResource(R.drawable.empty);
        b32.setBackgroundResource(R.drawable.empty);
        b33.setBackgroundResource(R.drawable.empty);
        b34.setBackgroundResource(R.drawable.empty);
        b35.setBackgroundResource(R.drawable.empty);
        b36.setBackgroundResource(R.drawable.empty);
        b41.setBackgroundResource(R.drawable.empty);
        b42.setBackgroundResource(R.drawable.empty);
        b43.setBackgroundResource(R.drawable.empty);
        b44.setBackgroundResource(R.drawable.empty);
        b45.setBackgroundResource(R.drawable.empty);
        b46.setBackgroundResource(R.drawable.empty);
        b51.setBackgroundResource(R.drawable.empty);
        b52.setBackgroundResource(R.drawable.empty);
        b53.setBackgroundResource(R.drawable.empty);
        b54.setBackgroundResource(R.drawable.empty);
        b55.setBackgroundResource(R.drawable.empty);
        b56.setBackgroundResource(R.drawable.empty);
        b61.setBackgroundResource(R.drawable.empty);
        b62.setBackgroundResource(R.drawable.empty);
        b63.setBackgroundResource(R.drawable.empty);
        b64.setBackgroundResource(R.drawable.empty);
        b65.setBackgroundResource(R.drawable.empty);
        b66.setBackgroundResource(R.drawable.empty);
        sum.setText("尚未规划");
        LoadActivity.reloadX();
        buildings = 0;
        isprograming = false;
        insidebuildings = 0;
        allsum = 0;
        LoadActivity.reloadJ();
        LoadActivity.reloadS();
    }

    //规划最起点
    private void program(){
        //Toast.makeText(MainActivity.this, "开始递归自增",Toast.LENGTH_SHORT).show();
        add(1,4,4);
    }

    //递归自增加并开始规划
    private void add(int a, int b, int c) {
        if (LoadActivity.J[b][c] == false) {
            LoadActivity.J[b][c] = true;
            //结束add
            if (isbuilding()) {
                add(1, 4, 4);
            } else {
                addnext();
            }
        } else {
            LoadActivity.J[b][c] = false;
            if (c == 1) {
                if (b == 1) {
                    //结束add了
                    if (isbuilding()) {
                        add(1, 4, 4);
                    } else {
                        addnext();
                    }
                } else {
                    c = 4;
                    b = b - 1;
                    add(a, b, c);
                }
            } else {
                c = c - 1;
                add(a, b, c);
            }
        }
    }

    //确保每个地区只有一幢建筑
    private void addnext(){
        boolean next = true;
        for(int i = 1; i < 5 ; i++){
            for(int j = 1; j < 5; j++){
                if(LoadActivity.J[i][j]){
                    s[i][j]++;
                }
                if(s[i][j] > 1){
                    next = false;
                    break;
                }
                if(!next){
                    break;
                }
            }
            if(!next){
                break;
            }
        }
        if(next){
            //继续规划
            Toast.makeText(MainActivity.this, "继续规划",Toast.LENGTH_SHORT).show();
            //addnextnext();
            //addnext4();
            addf();
        }else{
            add(1,4,4);
        }
    }

//    private void addnextnext(){
//        int m = 0, n = 0;
//        int xf = 0, jc = 0, yy = 0, gy = 0;
//        int sk = 0;
//        boolean isbreak = false;
//        for(int i = 1; i < 7; i++){
//            for(int j = 1; j < 7; j++){
//                if(LoadActivity.x[i][j] == 1){
//                    xf = 0; jc = 0; yy = 0 ;gy = 0;
//
////i=3,i=4
//                    if(i >= 3 && i <= 4){
//                        if(j >= 3 && j <=4){
//
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf > 3 || jc > 3 || yy > 3 || gy > 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= i+2; m++){
//                                for(n = j-2; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf > 3 || jc > 3 || yy > 3 || gy > 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++){
//                                for(n = 1; n < 7; n++){
//                                    if(LoadActivity.J[3][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[6][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[8][m][n]){
//                                        yy++;
//                                    }
//                                    if(LoadActivity.J[10][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//
//
//                        }else if(j == 2){
//
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= i+2; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 5){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= i+2; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 1){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = 1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= i+2; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else{
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= 6; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= i+2; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }
//
////i=2
//                    }else if(i == 2){
//                        if(j >= 3 && j <=4){
//
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = j-2; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++){
//                                for(n = 1; n < 7; n++){
//                                    if(LoadActivity.J[3][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[6][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[8][m][n]){
//                                        yy++;
//                                    }
//                                    if(LoadActivity.J[10][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 2){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 5){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 1){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = 1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else{
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= 6; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }
//
////i=5
//                    }else if(i == 5){
//                        if(j >= 3 && j <=4){
//
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = j-2; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++){
//                                for(n = 1; n < 7; n++){
//                                    if(LoadActivity.J[3][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[6][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[8][m][n]){
//                                        yy++;
//                                    }
//                                    if(LoadActivity.J[10][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 2){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//
//                        }else if(j == 5){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//
//                        }else if(j == 1){
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = 1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else{
//                            for(m = i-1; m <= i+1; m++){
//                                for(n = j-1; n <= 6; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }
//
////i=1
//                    }else if(i == 1){
//                        if(j >= 3 && j <=4){
//
//                            for(m = 1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = j-2; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++){
//                                for(n = 1; n < 7; n++){
//                                    if(LoadActivity.J[3][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[6][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[8][m][n]){
//                                        yy++;
//                                    }
//                                    if(LoadActivity.J[10][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 2){
//                            for(m = 1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 5){
//                            for(m = 1; m <= i+1; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 1){
//                            for(m = 1; m <= i+1; m++){
//                                for(n = 1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else{
//                            for(m = 1; m <= i+1; m++){
//                                for(n = j-1; n <= 6; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m <= i+2; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }
//
////i=6
//                    }else if(i == 6){
//                        if(j >= 3 && j <=4){
//
//                            for(m = i-1; m <= 6; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = j-2; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++){
//                                for(n = 1; n < 7; n++){
//                                    if(LoadActivity.J[3][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[6][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[8][m][n]){
//                                        yy++;
//                                    }
//                                    if(LoadActivity.J[10][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 2){
//                            for(m = i-1; m <= 6; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 5){
//                            for(m = i-1; m <= 6; m++){
//                                for(n = j-1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else if(j == 1){
//                            for(m = i-1; m <= 6; m++){
//                                for(n = 1; n <= j+1; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = 1; n <= j+2; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }else{
//                            for(m = i-1; m <= 6; m++){
//                                for(n = j-1; n <= 6; n++){
//                                    if(LoadActivity.J[1][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[4][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[7][m][n]){
//                                        yy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = i-2; m <= 6; m++){
//                                for(n = j-2; n <= 6; n++){
//                                    if(LoadActivity.J[2][m][n]){
//                                        xf++;
//                                    }
//                                    if(LoadActivity.J[5][m][n]){
//                                        jc++;
//                                    }
//                                    if(LoadActivity.J[9][m][n]){
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                            for(m = 1; m < 7; m++) {
//                                for (n = 1; n < 7; n++) {
//                                    if (LoadActivity.J[3][m][n]) {
//                                        xf++;
//                                    }
//                                    if (LoadActivity.J[6][m][n]) {
//                                        jc++;
//                                    }
//                                    if (LoadActivity.J[8][m][n]) {
//                                        yy++;
//                                    }
//                                    if (LoadActivity.J[10][m][n]) {
//                                        gy++;
//                                    }
//                                    if(xf >= 3 || jc >= 3 || yy >= 3 || gy >= 3){
//                                        isbreak = true;
//                                    }
//                                    if(isbreak){
//                                        break;
//                                    }
//                                }
//                                if(isbreak){
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    if(xf >= 1 && jc >= 1 && yy >= 1 && gy >= 1 ){
//                        //Toast.makeText(MainActivity.this, "规划完成",Toast.LENGTH_SHORT).show();
//                        //isprograming = false;
//                        //break;
//                        sk++;
//                    }else{
//                        isbreak = true;
//                        break;
//                    }
//
//
//                }
//                if(isbreak){
//                    break;
//                }
//            }
//            if(isbreak){
//                break;
//            }
//        }
//        if(sk == buildings){
//            Toast.makeText(MainActivity.this, "规划完成",Toast.LENGTH_SHORT).show();
//        }else{
//            add(10,6,6);
//        }
//    }

    //判断递归自增的地区是否和已有建筑重合
    private static boolean isbuilding(){
        boolean istrue = false;
            for(int i = 1; i < 5; i++){
                if(istrue){
                    break;
                }
                for(int j = 1; j < 5; j++){
                    if(LoadActivity.x[i][j] == 1){
                        if(LoadActivity.J[i][j]){
                            istrue = true;
                            break;
                        }
                    }
                }
            }
        if(istrue){
            istrue = false;
            return true;
        }else{
            istrue = false;
            return false;
        }
    }

    //判断规划后的建筑是否过多
    private static boolean istoomuch(){
        int sum = 0;
            for(int i = 1; i < 5; i++){
                for(int j = 1; j < 5; j++){
                    if(LoadActivity.J[i][j]){
                        sum++;
                    }
                }
                if(sum >= 5){
                    break;
                }
            if(sum >= 5){
                break;
            }
        }
        if(sum >= 5){
            sum = 0;
            return true;
        }else{
            sum = 0;
            return false;
        }
    }

    //在4*4的区域中递归枚举规划
    private void addnext4(){
        int m = 0, n = 0;
        int xf = 0, jc = 0, yy = 0, gy = 0;
        int sk = 0;
        boolean isbreak = false;
        for(int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                if (LoadActivity.x[i][j] == 1) {
                    xf = 0;jc = 0;yy = 0;gy = 0;
                    if(i < 3){
                        if(j < 3){
                            for(m = 1; m < i+2; m++){
                                for(n = 1; n < j+2; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }else if(j < 5){
                            for(m = 1; m < i+2; m++){
                                for(n = 1; n < 5; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }else{
                            for(m = 1; m < i+2; m++){
                                for(n = j-2; n < 5; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }
//i=3,4
                    }else if(i < 5){
                        if(j < 3){
                            for(m = 1; m < 5; m++){
                                for(n = 1; n < j+2; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }else if(j < 5){
                            for(m = 1; m < 5; m++){
                                for(n = 1; n < 5; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }else{
                            for(m = 1; m < 5; m++){
                                for(n = j-2; n < 5; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }
 //i=5,6
                    }else{
                        if(j < 3){
                            for(m = i-2; m < 5; m++){
                                for(n = 1; n < j+2; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }else if(j < 5){
                            for(m = i-2; m < 5; m++){
                                for(n = 1; n < 5; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }else{
                            for(m = i-2; m < 5; m++){
                                for(n = j-2; n < 5; n++){
                                    if(LoadActivity.J[m][n]){
                                        xf++;
                                    }
                                }
                            }
                        }
                    }
                    if(xf > 0 && xf < 3){
                        sk++;
                    }
                }
            }
        }
        if(sk == buildings){
            Toast.makeText(MainActivity.this, "规划完成",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "规划失败",Toast.LENGTH_SHORT).show();
            add(1,4,4);
        }
    }

    //第一步规划线程
    private void addf(){
        Thread newThread; //子线程
        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                sj = new int[6];
                for(int i = 2; i <= 5; i++){
                    for(int j = 2; j <= 5; j++){
                        if(LoadActivity.x[i][j] == 0){
                            Random random = new Random();
                            int q = random.nextInt(6);
                            if(sj[q] >= 2){
                                q = random.nextInt(6);
                                if(sj[q] >= 2){
                                    q = random.nextInt(6);
                                    if(sj[q] >= 2){
                                        q = random.nextInt(6);
                                        if(sj[q] >= 2){
                                            q = random.nextInt(6);
                                            if(sj[q] >= 2){
                                                q = random.nextInt(6);
                                                if(sj[q] >= 2){
                                                    q = random.nextInt(6);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if(q != 1){
                                LoadActivity.x[i][j] = q;
                                sj[q] ++;
                            }else{
                                LoadActivity.x[i][j] = 0;
                            }
                        }
                    }
                }
                handler.sendEmptyMessage(0);
            }
        });
        newThread.start(); //启动线程
    }

    //画出接下来待规划区域
    private void setnextwillpg(){
        for(int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                if (LoadActivity.x[i][j] == 0) {
                    if (i == 1) {
                        if (j == 1) {
                            ImageButton b11 = (ImageButton) findViewById(R.id.ib11);
                            b11.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 2) {
                            ImageButton b12 = (ImageButton) findViewById(R.id.ib12);
                            b12.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 3) {
                            ImageButton b13 = (ImageButton) findViewById(R.id.ib13);
                            b13.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 4) {
                            ImageButton b14 = (ImageButton) findViewById(R.id.ib14);
                            b14.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 5) {
                            ImageButton b15 = (ImageButton) findViewById(R.id.ib15);
                            b15.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 6) {
                            ImageButton b16 = (ImageButton) findViewById(R.id.ib16);
                            b16.setBackgroundResource(R.drawable.will_pg);
                        }
                    } else if (i == 2) {
                        if (j == 1) {
                            ImageButton b21 = (ImageButton) findViewById(R.id.ib21);
                            b21.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 2) {
                            ImageButton b22 = (ImageButton) findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 3) {
                            ImageButton b23 = (ImageButton) findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 4) {
                            ImageButton b24 = (ImageButton) findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 6) {
                            ImageButton b26 = (ImageButton) findViewById(R.id.ib26);
                            b26.setBackgroundResource(R.drawable.will_pg);
                        }
                    } else if (i == 3) {
                        if (j == 1) {
                            ImageButton b31 = (ImageButton) findViewById(R.id.ib31);
                            b31.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 2) {
                            ImageButton b32 = (ImageButton) findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 3) {
                            ImageButton b33 = (ImageButton) findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 4) {
                            ImageButton b34 = (ImageButton) findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 6) {
                            ImageButton b36 = (ImageButton) findViewById(R.id.ib36);
                            b36.setBackgroundResource(R.drawable.will_pg);
                        }
                    } else if (i == 4) {
                        if (j == 1) {
                            ImageButton b41 = (ImageButton) findViewById(R.id.ib41);
                            b41.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 2) {
                            ImageButton b42 = (ImageButton) findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 3) {
                            ImageButton b43 = (ImageButton) findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 4) {
                            ImageButton b44 = (ImageButton) findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 5) {
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 6) {
                            ImageButton b46 = (ImageButton) findViewById(R.id.ib46);
                            b46.setBackgroundResource(R.drawable.will_pg);
                        }
                    } else if (i == 5) {
                        if (j == 1) {
                            ImageButton b51 = (ImageButton) findViewById(R.id.ib51);
                            b51.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 2) {
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 3) {
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 4) {
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 5) {
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 6) {
                            ImageButton b56 = (ImageButton) findViewById(R.id.ib56);
                            b56.setBackgroundResource(R.drawable.will_pg);
                        }
                    } else if (i == 6) {
                        if (j == 1) {
                            ImageButton b61 = (ImageButton) findViewById(R.id.ib61);
                            b61.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 2) {
                            ImageButton b62 = (ImageButton) findViewById(R.id.ib62);
                            b62.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 3) {
                            ImageButton b63 = (ImageButton) findViewById(R.id.ib63);
                            b63.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 4) {
                            ImageButton b64 = (ImageButton) findViewById(R.id.ib64);
                            b64.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 5) {
                            ImageButton b65 = (ImageButton) findViewById(R.id.ib65);
                            b65.setBackgroundResource(R.drawable.will_pg);
                        } else if (j == 6) {
                            ImageButton b66 = (ImageButton) findViewById(R.id.ib66);
                            b66.setBackgroundResource(R.drawable.will_pg);
                        }
                    }
                }
            }
        }
        addfnext();
    }

    //第二步规划线程
    private void addfnext(){
        Thread Thread2; //线程2
        Thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sj = new int[15];
                for(int i = 1; i <= 6; i++){
                    for(int j = 1; j <= 6; j++){
                        if(LoadActivity.x[i][j] == 0){
                            Random random = new Random();
                            int q = random.nextInt(8) + 6;
                            if(sj[q] >= 2){
                                q = random.nextInt(8) + 6;
                                if(sj[q] >= 2){
                                    q = random.nextInt(8) + 6;
                                    if(sj[q] >= 2){
                                        q = random.nextInt(8) + 6;
                                        if(sj[q] >= 2){
                                            q = random.nextInt(8) + 6;
                                            if(sj[q] >= 2){
                                                q = random.nextInt(8) + 6;
                                                if(sj[q] >= 2){
                                                    q = random.nextInt(8) + 6;
                                                    if(sj[q] >= 2){
                                                        q = random.nextInt(8) + 6;
                                                        if(sj[q] >= 2){
                                                            q = random.nextInt(8) + 6;
                                                            if(sj[q] >= 2){
                                                                q = random.nextInt(8) + 6;
                                                                if(sj[q] >= 2){
                                                                    q = random.nextInt(8) + 6;
                                                                    if(sj[q] >= 2){
                                                                        q = random.nextInt(8) + 6;
                                                                        if(sj[q] >= 2){
                                                                            q = random.nextInt(8) + 6;
                                                                            if(sj[q] >= 2){
                                                                                q = random.nextInt(8) + 6;
                                                                                if(sj[q] >= 2){
                                                                                    q = random.nextInt(8) + 6;
                                                                                    if(sj[q] >= 2){
                                                                                        q = random.nextInt(8) + 6;
                                                                                        if(sj[q] >= 2){
                                                                                            q = random.nextInt(8) + 6;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if(q == 6 || q == 7 || q == 8 || q == 9){
                                LoadActivity.x[i][j] = q;
                                sj[q] ++;
                            }else{
                                LoadActivity.x[i][j] = 0;
                            }
                        }
                    }
                }
                handler.sendEmptyMessage(1);
            }
        });
        Thread2.start(); //启动线程
    }

    //画出第一步规划结果
    public void setpged(){
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                if(LoadActivity.x[i][j] == 0){
                    if(i == 2){
                        if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 3){
                        if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 4){
                        if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5) {
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 5) {
                        if (j == 2) {
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.empty);
                        } else if (j == 3) {
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.empty);
                        } else if (j == 4) {
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.empty);
                        } else if (j == 5) {
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.empty);
                        }
                    }


                }else if(LoadActivity.x[i][j] == 2){
                    if(i == 2){
                        if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_xf);
                        }
                    }else if(i == 3){
                        if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_xf);
                        }
                    }else if(i == 4){
                        if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_xf);
                        }else if(j == 5) {
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_xf);
                        }
                    }else if(i == 5) {
                        if (j == 2) {
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_xf);
                        } else if (j == 3) {
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_xf);
                        } else if (j == 4) {
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_xf);
                        } else if (j == 5) {
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_xf);
                        }
                    }


                }else if(LoadActivity.x[i][j] == 3){
                    if(i == 2){
                        if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_jc);
                        }
                    }else if(i == 3){
                        if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_jc);
                        }
                    }else if(i == 4){
                        if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_jc);
                        }else if(j == 5) {
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_jc);
                        }
                    }else if(i == 5) {
                        if (j == 2) {
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_jc);
                        } else if (j == 3) {
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_jc);
                        } else if (j == 4) {
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_jc);
                        } else if (j == 5) {
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_jc);
                        }
                    }


                }else if(LoadActivity.x[i][j] == 4){
                    if(i == 2){
                        if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_yy);
                        }
                    }else if(i == 3){
                        if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_yy);
                        }
                    }else if(i == 4){
                        if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_yy);
                        }else if(j == 5) {
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_yy);
                        }
                    }else if(i == 5) {
                        if (j == 2) {
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_yy);
                        } else if (j == 3) {
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_yy);
                        } else if (j == 4) {
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_yy);
                        } else if (j == 5) {
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_yy);
                        }
                    }
                }else if(LoadActivity.x[i][j] == 5){
                    if(i == 2){
                        if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 5) {
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_gy);
                        }
                    }else if(i == 3){
                        if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 5) {
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_gy);
                        }
                    }else if(i == 4){
                        if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_gy);
                        }else if(j == 5) {
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_gy);
                        }
                    }else if(i == 5) {
                        if (j == 2) {
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_gy);
                        } else if (j == 3) {
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_gy);
                        } else if (j == 4) {
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_gy);
                        } else if (j == 5) {
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_gy);
                        }
                    }
                }
            }
        }
        Toast.makeText(MainActivity.this, "第一步规划已完成",Toast.LENGTH_SHORT).show();
        setnextwillpg();
    }

    //画出第二部规划结果
    public void setpgeded(){
        for(int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                if(LoadActivity.x[i][j] == 0){
                    if(i == 1){
                        if(j == 1){
                            ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
                            b11.setBackgroundResource(R.drawable.empty);
                        }else if(j == 2){
                            ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
                            b12.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
                            b13.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
                            b14.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5){
                            ImageButton b15 = (ImageButton) findViewById(R.id.ib15);
                            b15.setBackgroundResource(R.drawable.empty);
                        }else if(j == 6){
                            ImageButton b16 = (ImageButton) findViewById(R.id.ib16);
                            b16.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 2){
                        if(j == 1){
                            ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
                            b21.setBackgroundResource(R.drawable.empty);
                        }else if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5){
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.empty);
                        }else if(j == 6){
                            ImageButton b26 = (ImageButton) findViewById(R.id.ib26);
                            b26.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 3){
                        if(j == 1){
                            ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
                            b31.setBackgroundResource(R.drawable.empty);
                        }else if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5){
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.empty);
                        }else if(j == 6){
                            ImageButton b36 = (ImageButton) findViewById(R.id.ib36);
                            b36.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 4){
                        if(j == 1){
                            ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
                            b41.setBackgroundResource(R.drawable.empty);
                        }else if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.empty);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.empty);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.empty);
                        }else if(j == 5){
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.empty);
                        }else if(j == 6){
                            ImageButton b46 = (ImageButton) findViewById(R.id.ib46);
                            b46.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 5){
                        if(j == 1){
                            ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
                            b51.setBackgroundResource(R.drawable.empty);
                        }else if (j == 2){
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.empty);
                        }else if (j == 3){
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.empty);
                        }else if (j == 4){
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.empty);
                        }else if (j == 5){
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.empty);
                        }else if(j == 6){
                            ImageButton b56 = (ImageButton) findViewById(R.id.ib56);
                            b56.setBackgroundResource(R.drawable.empty);
                        }
                    }else if(i == 6){
                        if(j == 1){
                            ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
                            b61.setBackgroundResource(R.drawable.empty);
                        }else if (j == 2){
                            ImageButton b62 = (ImageButton) findViewById(R.id.ib62);
                            b62.setBackgroundResource(R.drawable.empty);
                        }else if (j == 3){
                            ImageButton b63 = (ImageButton) findViewById(R.id.ib63);
                            b63.setBackgroundResource(R.drawable.empty);
                        }else if (j == 4){
                            ImageButton b64 = (ImageButton) findViewById(R.id.ib64);
                            b64.setBackgroundResource(R.drawable.empty);
                        }else if (j == 5){
                            ImageButton b65 = (ImageButton) findViewById(R.id.ib65);
                            b65.setBackgroundResource(R.drawable.empty);
                        }else if(j == 6){
                            ImageButton b66 = (ImageButton) findViewById(R.id.ib66);
                            b66.setBackgroundResource(R.drawable.empty);
                        }
                    }
                }else if(LoadActivity.x[i][j] == 6){
                    if(i == 1){
                        if(j == 1){
                            ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
                            b11.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 2){
                            ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
                            b12.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 3){
                            ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
                            b13.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 4){
                            ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
                            b14.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 5){
                            ImageButton b15 = (ImageButton) findViewById(R.id.ib15);
                            b15.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 6){
                            ImageButton b16 = (ImageButton) findViewById(R.id.ib16);
                            b16.setBackgroundResource(R.drawable.is_dt);
                        }
                    }else if(i == 2){
                        if(j == 1){
                            ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
                            b21.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 5){
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 6){
                            ImageButton b26 = (ImageButton) findViewById(R.id.ib26);
                            b26.setBackgroundResource(R.drawable.is_dt);
                        }
                    }else if(i == 3){
                        if(j == 1){
                            ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
                            b31.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 5){
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 6){
                            ImageButton b36 = (ImageButton) findViewById(R.id.ib36);
                            b36.setBackgroundResource(R.drawable.is_dt);
                        }
                    }else if(i == 4){
                        if(j == 1){
                            ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
                            b41.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 5){
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 6){
                            ImageButton b46 = (ImageButton) findViewById(R.id.ib46);
                            b46.setBackgroundResource(R.drawable.is_dt);
                        }
                    }else if(i == 5){
                        if(j == 1){
                            ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
                            b51.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 2){
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 3){
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 4){
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 5){
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 6){
                            ImageButton b56 = (ImageButton) findViewById(R.id.ib56);
                            b56.setBackgroundResource(R.drawable.is_dt);
                        }
                    }else if(i == 6){
                        if(j == 1){
                            ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
                            b61.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 2){
                            ImageButton b62 = (ImageButton) findViewById(R.id.ib62);
                            b62.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 3){
                            ImageButton b63 = (ImageButton) findViewById(R.id.ib63);
                            b63.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 4){
                            ImageButton b64 = (ImageButton) findViewById(R.id.ib64);
                            b64.setBackgroundResource(R.drawable.is_dt);
                        }else if (j == 5){
                            ImageButton b65 = (ImageButton) findViewById(R.id.ib65);
                            b65.setBackgroundResource(R.drawable.is_dt);
                        }else if(j == 6){
                            ImageButton b66 = (ImageButton) findViewById(R.id.ib66);
                            b66.setBackgroundResource(R.drawable.is_dt);
                        }
                    }
                }else if(LoadActivity.x[i][j] == 7){
                    if(i == 1){
                        if(j == 1){
                            ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
                            b11.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 2){
                            ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
                            b12.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 3){
                            ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
                            b13.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 4){
                            ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
                            b14.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 5){
                            ImageButton b15 = (ImageButton) findViewById(R.id.ib15);
                            b15.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 6){
                            ImageButton b16 = (ImageButton) findViewById(R.id.ib16);
                            b16.setBackgroundResource(R.drawable.is_dyy);
                        }
                    }else if(i == 2){
                        if(j == 1){
                            ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
                            b21.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 5){
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 6){
                            ImageButton b26 = (ImageButton) findViewById(R.id.ib26);
                            b26.setBackgroundResource(R.drawable.is_dyy);
                        }
                    }else if(i == 3){
                        if(j == 1){
                            ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
                            b31.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 5){
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 6){
                            ImageButton b36 = (ImageButton) findViewById(R.id.ib36);
                            b36.setBackgroundResource(R.drawable.is_dyy);
                        }
                    }else if(i == 4){
                        if(j == 1){
                            ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
                            b41.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 5){
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 6){
                            ImageButton b46 = (ImageButton) findViewById(R.id.ib46);
                            b46.setBackgroundResource(R.drawable.is_dyy);
                        }
                    }else if(i == 5){
                        if(j == 1){
                            ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
                            b51.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 2){
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 3){
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 4){
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 5){
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 6){
                            ImageButton b56 = (ImageButton) findViewById(R.id.ib56);
                            b56.setBackgroundResource(R.drawable.is_dyy);
                        }
                    }else if(i == 6){
                        if(j == 1){
                            ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
                            b61.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 2){
                            ImageButton b62 = (ImageButton) findViewById(R.id.ib62);
                            b62.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 3){
                            ImageButton b63 = (ImageButton) findViewById(R.id.ib63);
                            b63.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 4){
                            ImageButton b64 = (ImageButton) findViewById(R.id.ib64);
                            b64.setBackgroundResource(R.drawable.is_dyy);
                        }else if (j == 5){
                            ImageButton b65 = (ImageButton) findViewById(R.id.ib65);
                            b65.setBackgroundResource(R.drawable.is_dyy);
                        }else if(j == 6){
                            ImageButton b66 = (ImageButton) findViewById(R.id.ib66);
                            b66.setBackgroundResource(R.drawable.is_dyy);
                        }
                    }
                }else if(LoadActivity.x[i][j] == 8){
                    if(i == 1){
                        if(j == 1){
                            ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
                            b11.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 2){
                            ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
                            b12.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 3){
                            ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
                            b13.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 4){
                            ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
                            b14.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 5){
                            ImageButton b15 = (ImageButton) findViewById(R.id.ib15);
                            b15.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 6){
                            ImageButton b16 = (ImageButton) findViewById(R.id.ib16);
                            b16.setBackgroundResource(R.drawable.is_sc);
                        }
                    }else if(i == 2){
                        if(j == 1){
                            ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
                            b21.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 5){
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 6){
                            ImageButton b26 = (ImageButton) findViewById(R.id.ib26);
                            b26.setBackgroundResource(R.drawable.is_sc);
                        }
                    }else if(i == 3){
                        if(j == 1){
                            ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
                            b31.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 5){
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 6){
                            ImageButton b36 = (ImageButton) findViewById(R.id.ib36);
                            b36.setBackgroundResource(R.drawable.is_sc);
                        }
                    }else if(i == 4){
                        if(j == 1){
                            ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
                            b41.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 5){
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 6){
                            ImageButton b46 = (ImageButton) findViewById(R.id.ib46);
                            b46.setBackgroundResource(R.drawable.is_sc);
                        }
                    }else if(i == 5){
                        if(j == 1){
                            ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
                            b51.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 2){
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 3){
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 4){
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 5){
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 6){
                            ImageButton b56 = (ImageButton) findViewById(R.id.ib56);
                            b56.setBackgroundResource(R.drawable.is_sc);
                        }
                    }else if(i == 6){
                        if(j == 1){
                            ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
                            b61.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 2){
                            ImageButton b62 = (ImageButton) findViewById(R.id.ib62);
                            b62.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 3){
                            ImageButton b63 = (ImageButton) findViewById(R.id.ib63);
                            b63.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 4){
                            ImageButton b64 = (ImageButton) findViewById(R.id.ib64);
                            b64.setBackgroundResource(R.drawable.is_sc);
                        }else if (j == 5){
                            ImageButton b65 = (ImageButton) findViewById(R.id.ib65);
                            b65.setBackgroundResource(R.drawable.is_sc);
                        }else if(j == 6){
                            ImageButton b66 = (ImageButton) findViewById(R.id.ib66);
                            b66.setBackgroundResource(R.drawable.is_sc);
                        }
                    }
                }else if(LoadActivity.x[i][j] == 9){
                    if(i == 1){
                        if(j == 1){
                            ImageButton b11 = (ImageButton)findViewById(R.id.ib11);
                            b11.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 2){
                            ImageButton b12 = (ImageButton)findViewById(R.id.ib12);
                            b12.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 3){
                            ImageButton b13 = (ImageButton)findViewById(R.id.ib13);
                            b13.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 4){
                            ImageButton b14 = (ImageButton)findViewById(R.id.ib14);
                            b14.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 5){
                            ImageButton b15 = (ImageButton) findViewById(R.id.ib15);
                            b15.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 6){
                            ImageButton b16 = (ImageButton) findViewById(R.id.ib16);
                            b16.setBackgroundResource(R.drawable.is_xx);
                        }
                    }else if(i == 2){
                        if(j == 1){
                            ImageButton b21 = (ImageButton)findViewById(R.id.ib21);
                            b21.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 2){
                            ImageButton b22 = (ImageButton)findViewById(R.id.ib22);
                            b22.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 3){
                            ImageButton b23 = (ImageButton)findViewById(R.id.ib23);
                            b23.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 4){
                            ImageButton b24 = (ImageButton)findViewById(R.id.ib24);
                            b24.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 5){
                            ImageButton b25 = (ImageButton) findViewById(R.id.ib25);
                            b25.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 6){
                            ImageButton b26 = (ImageButton) findViewById(R.id.ib26);
                            b26.setBackgroundResource(R.drawable.is_xx);
                        }
                    }else if(i == 3){
                        if(j == 1){
                            ImageButton b31 = (ImageButton)findViewById(R.id.ib31);
                            b31.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 2){
                            ImageButton b32 = (ImageButton)findViewById(R.id.ib32);
                            b32.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 3){
                            ImageButton b33 = (ImageButton)findViewById(R.id.ib33);
                            b33.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 4){
                            ImageButton b34 = (ImageButton)findViewById(R.id.ib34);
                            b34.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 5){
                            ImageButton b35 = (ImageButton) findViewById(R.id.ib35);
                            b35.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 6){
                            ImageButton b36 = (ImageButton) findViewById(R.id.ib36);
                            b36.setBackgroundResource(R.drawable.is_xx);
                        }
                    }else if(i == 4){
                        if(j == 1){
                            ImageButton b41 = (ImageButton)findViewById(R.id.ib41);
                            b41.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 2){
                            ImageButton b42 = (ImageButton)findViewById(R.id.ib42);
                            b42.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 3){
                            ImageButton b43 = (ImageButton)findViewById(R.id.ib43);
                            b43.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 4){
                            ImageButton b44 = (ImageButton)findViewById(R.id.ib44);
                            b44.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 5){
                            ImageButton b45 = (ImageButton) findViewById(R.id.ib45);
                            b45.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 6){
                            ImageButton b46 = (ImageButton) findViewById(R.id.ib46);
                            b46.setBackgroundResource(R.drawable.is_xx);
                        }
                    }else if(i == 5){
                        if(j == 1){
                            ImageButton b51 = (ImageButton)findViewById(R.id.ib51);
                            b51.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 2){
                            ImageButton b52 = (ImageButton) findViewById(R.id.ib52);
                            b52.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 3){
                            ImageButton b53 = (ImageButton) findViewById(R.id.ib53);
                            b53.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 4){
                            ImageButton b54 = (ImageButton) findViewById(R.id.ib54);
                            b54.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 5){
                            ImageButton b55 = (ImageButton) findViewById(R.id.ib55);
                            b55.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 6){
                            ImageButton b56 = (ImageButton) findViewById(R.id.ib56);
                            b56.setBackgroundResource(R.drawable.is_xx);
                        }
                    }else if(i == 6){
                        if(j == 1){
                            ImageButton b61 = (ImageButton)findViewById(R.id.ib61);
                            b61.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 2){
                            ImageButton b62 = (ImageButton) findViewById(R.id.ib62);
                            b62.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 3){
                            ImageButton b63 = (ImageButton) findViewById(R.id.ib63);
                            b63.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 4){
                            ImageButton b64 = (ImageButton) findViewById(R.id.ib64);
                            b64.setBackgroundResource(R.drawable.is_xx);
                        }else if (j == 5){
                            ImageButton b65 = (ImageButton) findViewById(R.id.ib65);
                            b65.setBackgroundResource(R.drawable.is_xx);
                        }else if(j == 6){
                            ImageButton b66 = (ImageButton) findViewById(R.id.ib66);
                            b66.setBackgroundResource(R.drawable.is_xx);
                        }
                    }
                }
            }
        }
        Toast.makeText(MainActivity.this, "第二步规划已完成",Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this, "规划已完成",Toast.LENGTH_SHORT).show();
        sum();
    }

    public void update(View view){
        switch (view.getId()){
            case R.id.update:
                Toast.makeText(MainActivity.this, "正在检查更新",Toast.LENGTH_SHORT).show();
                PgyUpdateManager.register(this);
                Toast.makeText(MainActivity.this, "已是最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(MainActivity.this, "它还在测试中",Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "敬请期待",Toast.LENGTH_SHORT).show();
                Intent itent=new Intent();
                itent.setClass(MainActivity.this, SettingsActivity.class);
                startActivity(itent);
                //MainActivity.this.finish();
                break;
            case R.id.name:
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://www.pgyer.com/LLdI");
                intent.setData(content_url);
                intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void version(View view){
        Toast.makeText(MainActivity.this, "当前版本：0.7.1",Toast.LENGTH_SHORT).show();
    }

    private void flashlayout(){
        Thread flash; //线程2
        flash = new Thread(new Runnable() {

            @Override
            public void run() {

            }
        });
        flash.start(); //启动线程
    }

    private void sum(){
        final int XF = 11000;//消防2
        final int JC = 18800;//警察3
        final int YY = 64200;//医院4
        final int GY = 20000;//公园5
        final int DT = 100000;//地铁6
        final int DY = 35000;//电影院7
        final int SC = 70000;//商场8
        final int XX = 55000;//学校9
        int xf = 0, jc = 0, yy = 0, gy = 0, dt = 0,dy = 0, sc = 0, xx = 0;
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                if(LoadActivity.x[i][j] == 2){
                   allsum = allsum + XF;
                    xf++;
                }else if(LoadActivity.x[i][j] == 3){
                    allsum = allsum + JC;
                    jc++;
                }else if(LoadActivity.x[i][j] == 4){
                    allsum = allsum + YY;
                    yy++;
                }else if(LoadActivity.x[i][j] == 5){
                    allsum = allsum + GY;
                    gy++;
                }else if(LoadActivity.x[i][j] == 6){
                    allsum = allsum + DT;
                    dt++;
                }else if(LoadActivity.x[i][j] == 7){
                    allsum = allsum +DY;
                    dy++;
                }else if(LoadActivity.x[i][j] == 8){
                    allsum = allsum + SC;
                    sc++;
                }else if(LoadActivity.x[i][j] == 9){
                    allsum = allsum + XX;
                    xx++;
                }
            }
        }
        if(xf!=0&&jc!=0&&yy!=0&&gy!=0) {
            TextView sum = (TextView) findViewById(R.id.sum);
            sum.setText("当前造价为\n" + allsum + "\n\n\n\n\n\n\n共规划了\n" + "消防站 " + xf + "个\n" + "警察局 " + jc + "个\n" + "医院   " + yy + "个\n" + "公园   " + gy + "个\n" + "地铁站 " + dt + "个\n" + "电影院 " + dy + "个\n" + "商场   " + sc + "个\n" + "学校   " + xx + "个\n");
            Toast.makeText(MainActivity.this, "规划已完成",Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 1; i < 7; i++) {
                for (int j = 1; j < 7; j++) {
                    if(LoadActivity.x[i][j] != 1){
                        LoadActivity.x[i][j] = 0;
                    }
                }
            }
            buildings = 0;
            isprograming = true;
            insidebuildings = 0;
            allsum = 0;
            LoadActivity.reloadJ();
            LoadActivity.reloadS();
            addf();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // 摇一摇的灵敏度(数值越小灵敏度越高)
        PgyFeedbackShakeManager.setShakingThreshold(1000);
        PgyFeedbackShakeManager.register(MainActivity.this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        PgyFeedbackShakeManager.unregister();
    }
}