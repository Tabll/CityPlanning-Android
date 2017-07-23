package com.wts.sxgh.load;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.wts.sxgh.sxgh.MainActivity;
import com.wts.sxgh.sxgh.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoadActivity extends AppCompatActivity {

    public static int x[][];
    public static boolean J[][];
    public static int s[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*设置全屏*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toast.makeText(LoadActivity.this, "当前版本：0.7.1",Toast.LENGTH_LONG).show();

        x=new int[7][7];
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                x[i][j] = 0;
            }
            //Toast.makeText(LoadActivity.this, "数组初始化完成",0).show();
        }

        J = new boolean[5][5];

        reloadJ();

        s = new int[7][7];//当前位置上的总建筑个数
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                s[i][j] = 0;
            }
            //Toast.makeText(LoadActivity.this, "数组初始化完成",0).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 6000);
    }

    protected final int GOTO_MAIN_ACTIVITY = 0;
    Handler mHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(LoadActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    public static void reloadX(){
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7; j++){
                x[i][j] = 0;
            }
        }
    }

    public static void reloadJ(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                    J[i][j] = false;
            }
        }
    }

    public static void reloadS(){
        for(int i = 1; i < 7; i++){
            for(int j = 1; j < 7;j++){
                s[i][j] = 0;
            }
        }
    }
}
