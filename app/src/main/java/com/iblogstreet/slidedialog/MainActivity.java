package com.iblogstreet.slidedialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

public class MainActivity
        extends Activity
{
    SlideDialog mSlideDialg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mSlideDialg = new SlideDialog(this);
    }

    public void showDialog(View view) {
        mSlideDialg.show();
    }
}
