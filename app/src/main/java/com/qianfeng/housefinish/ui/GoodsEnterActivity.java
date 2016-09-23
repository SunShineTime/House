package com.qianfeng.housefinish.ui;

import android.app.Service;
import android.content.Context;
import android.media.Image;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.housefinish.R;
import com.qianfeng.housefinish.utils.ClearEditText;

public class GoodsEnterActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = GoodsEnterActivity.class.getSimpleName();
    public TextView mEnterBack;
    public Button mLogin;
    public ClearEditText mEdt1;
    public ClearEditText mEdt2;


//    private TextWatcher watcher=new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            Log.e(TAG, "beforeTextChanged: " );
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.e(TAG, "onTextChanged: " );
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            Log.e(TAG, "afterTextChanged: " );
//            if (s.toString().length()==0) {
//                mClear1.setVisibility(View.INVISIBLE);
//
//            }else {
//                mClear1.setVisibility(View.VISIBLE);
//
//            }
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_enter);
        initView();
    }

    private void initView() {

        mEnterBack = (TextView) findViewById(R.id.day_day_enter_back);
        mEnterBack.setOnClickListener(this);
        mLogin = (Button) findViewById(R.id.my_login);
        mLogin.setOnClickListener(this);
        mEdt1 = (ClearEditText) findViewById(R.id.enter_edt1);
        mEdt2 = (ClearEditText) findViewById(R.id.enter_edt2);


//        mEdt1.addTextChangedListener(watcher);
//        mEdt2.addTextChangedListener(watcher);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day_day_enter_back:
                finish();
                break;
            case R.id.my_login:
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.edt_shark);
                Log.e(TAG, "onClick: "+mEdt1.getText() );
                if (mEdt1.getText().length()==0) {
                    mEdt1.startAnimation(shake);
                }else {
                    mEdt1.setAnimation(null);
                    if (mEdt2.getText().length()==0) {
                        mEdt2.startAnimation(shake);
                    }else {
                        mEdt2.setAnimation(null);
                    }
                }
                break;
//            case R.id.enter_edt1_clear:
//                mEdt1.getText().clear();
//                break;
//            case R.id.enter_edt2_clear:
//                mEdt2.getText().clear();
//                break;
                }
        }
}
