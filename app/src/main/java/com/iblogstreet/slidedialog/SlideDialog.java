package com.iblogstreet.slidedialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 项目名称：UI
 * 类描述：
 * 创建人：王军
 * 创建时间：2016/11/21 10:48
 * 修改人：Administrator
 * 修改时间：2016/11/21 10:48
 * 修改备注：
 */
public class SlideDialog
        extends Dialog
        implements DialogInterface
{

    private static final int DISMISS = 0;


    FrameLayout flModel;

    private             int wifiState       = WifiManager.WIFI_STATE_UNKNOWN;
    public static final int SPLASH_DURATION = 3000;

    public SlideDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_slide);
        initView();
        initSet();
        initEvent();

    }

    private void initView() {
        flModel = (FrameLayout) findViewById(R.id.fl_model);
    }

    /**
     * 创建人：王军
     * 创建时间：2016/11/21 16:31
     * 描述：初始化事件
     */
    private void initEvent() {
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mHandler.sendEmptyMessageDelayed(DISMISS, SPLASH_DURATION);
            }
        });
        flModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeMessages(DISMISS);
                dismiss();
            }
        });

    }

    /**
     * 创建人：王军
     * 创建时间：2016/11/21 16:31
     * 描述：初始化设置
     */
    private void initSet() {
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setBackgroundDrawableResource(R.color.slide_dialog_bg_translucent);
        params.x = 0;//设置x坐标
        params.y = 0;//设置y坐标
        params.gravity = Gravity.LEFT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = (int) dp2px(2000);
        window.setAttributes(params);
        window.setWindowAnimations(R.style.SlideDialogAnim);
    }

    /**
     * 将dp转成px
     *
     * @param dp
     * @return
     */
    public float dp2px(float dp) {
        float dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                                                    dp,
                                                    getContext().getResources()
                                                                .getDisplayMetrics());
        return dimension;
    }

    @Override
    public void onDetachedFromWindow() {
        dismiss();
        mHandler.removeMessages(DISMISS);
        super.onDetachedFromWindow();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DISMISS:
                    dismiss();
                    break;
            }
        }
    };

}
