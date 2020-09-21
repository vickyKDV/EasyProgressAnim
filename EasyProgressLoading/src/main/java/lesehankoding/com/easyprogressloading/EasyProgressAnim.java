/*
 *    Copyright 2015 Kaopiz Software Co., Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package lesehankoding.com.easyprogressloading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


public class EasyProgressAnim {


    private ProgressDialog mProgressDialog;
    private float mDimAmount;
    private int mWindowColor;
    private float mCornerRadius;
    private Context mContext;
    private boolean mIsAutoDismiss;

    private boolean mFinished;

    public EasyProgressAnim(Context context) {
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
        mDimAmount = 0;
        mWindowColor = context.getResources().getColor(R.color.easyprogressblack_color);
        mCornerRadius = 10;
        mIsAutoDismiss = true;
        mFinished = false;
    }


    public EasyProgressAnim setDimAmount(float dimAmount) {
        if (dimAmount >= 0 && dimAmount <= 1) {
            mDimAmount = dimAmount;
        }
        return this;
    }

    public EasyProgressAnim setSize(int width, int height) {
        mProgressDialog.setSize(width, height);
        return this;
    }

    @Deprecated
    public EasyProgressAnim setWindowColor(int color) {
        mWindowColor = color;
        return this;
    }

    public EasyProgressAnim setBackgroundColor(int color) {
        mWindowColor = color;
        return this;
    }

    public EasyProgressAnim setCornerRadius(float radius) {
        mCornerRadius = radius;
        return this;
    }


    public EasyProgressAnim setLabel(String label) {
        mProgressDialog.setLabel(label);
        return this;
    }

    public EasyProgressAnim setTitle(String label, int color) {
        mProgressDialog.setTitle(label, color);
        return this;
    }

    public EasyProgressAnim setFileName(String fileName) {
        mProgressDialog.setFileName(fileName);
        return this;
    }


    public EasyProgressAnim setDeskripsi(String detailsLabel) {
        mProgressDialog.setDeskripsi(detailsLabel);
        return this;
    }

    public EasyProgressAnim setDeskripsi(String detailsLabel, int color) {
        mProgressDialog.setDeksripsi(detailsLabel, color);
        return this;
    }


    public EasyProgressAnim setCustomBg(View view) {
        if (view != null) {
            mProgressDialog.setView(view);
        } else {
            throw new RuntimeException("View tidak boleh kosong!");
        }
        return this;
    }



    public EasyProgressAnim setCancellable(boolean isCancellable) {
        mProgressDialog.setCancelable(isCancellable);
        mProgressDialog.setOnCancelListener(null);
        return this;
    }

    public EasyProgressAnim setCancellable(DialogInterface.OnCancelListener listener) {
        mProgressDialog.setCancelable(null != listener);
        mProgressDialog.setOnCancelListener(listener);
        return this;
    }

    public EasyProgressAnim setAutoDismiss(boolean isAutoDismiss) {
        mIsAutoDismiss = isAutoDismiss;
        return this;
    }


    public EasyProgressAnim show() {
        if (!isShowing()) {
            mFinished = false;
            mProgressDialog.show();
        }
        return this;
    }

    public boolean isShowing() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    public void dismiss() {
        mFinished = true;
        if (mContext !=null && !((Activity)mContext).isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private class ProgressDialog extends Dialog {

        private View mView;
		private TextView txtTitle;
        private TextView txtDeskripsi;
        private String mTitle;
        private String mDeskripsi;
        private FrameLayout mCustomViewContainer;
        private LottieAnimationView mLottie;
        private BackgroundLayout mBackgroundLayout;
        private String mFileName;
        private int mWidth, mHeight;
        private int mLabelColor = Color.WHITE;
        private int mDetailColor = Color.WHITE;


        public ProgressDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.easy_progress_lay);
            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = mDimAmount;
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);

            setCanceledOnTouchOutside(false);

            initViews();
        }

        private void initViews() {
            mBackgroundLayout =  findViewById(R.id.background);
            mBackgroundLayout.setBaseColor(mWindowColor);
            mBackgroundLayout.setCornerRadius(mCornerRadius);
            if (mWidth != 0) {
                updateBackgroundSize();
            }

            mCustomViewContainer =  findViewById(R.id.container);
            addViewToFrame(mView);

            txtTitle =  findViewById(R.id.txtTitle);
            setTitle(mTitle, mLabelColor);
            txtDeskripsi =  findViewById(R.id.txtDeskripsi);
            setDeksripsi(mDeskripsi, mDetailColor);

            if(mFileName != null ) {
                mLottie = findViewById(R.id.lottie);
                Log.d("ProgressDialog", "initViews: "+mFileName);
                mLottie.setAnimation(mFileName);
                mLottie.enableMergePathsForKitKatAndAbove(true);
                mLottie.loop(true);
                mLottie.playAnimation();
                mLottie.setVisibility(View.VISIBLE);
            }else {
                mLottie.setVisibility(View.GONE);
            }

        }

        private void addViewToFrame(View view) {
            if (view == null) return;
            int wrapParam = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(wrapParam, wrapParam);
            mCustomViewContainer.addView(view, params);
        }

        private void updateBackgroundSize() {
            ViewGroup.LayoutParams params = mBackgroundLayout.getLayoutParams();
            params.width = Utils.dpToPixel(mWidth, getContext());
            params.height = Utils.dpToPixel(mHeight, getContext());
            mBackgroundLayout.setLayoutParams(params);
        }


        public void setView(View view) {
            if (view != null) {
                mView = view;
                if (isShowing()) {
                    mCustomViewContainer.removeAllViews();
                    addViewToFrame(view);
                }
            }
        }


        public void setFileName(String mFileName) {
            this.mFileName = mFileName;
        }

        public void setLabel(String label) {
            mTitle = label;
            if (txtTitle != null) {
                if (label != null) {
                    txtTitle.setText(label);
                    txtTitle.setVisibility(View.VISIBLE);
                } else {
                    txtTitle.setVisibility(View.GONE);
                }
            }
        }

        public void setDeskripsi(String detailsLabel) {
            mDeskripsi = detailsLabel;
            if (txtDeskripsi != null) {
                if (detailsLabel != null) {
                    txtDeskripsi.setText(detailsLabel);
                    txtDeskripsi.setVisibility(View.VISIBLE);
                } else {
                    txtDeskripsi.setVisibility(View.GONE);
                }
            }
        }

        public void setTitle(String label, int color) {
            mTitle = label;
            mLabelColor = color;
            if (txtTitle != null) {
                if (label != null) {
                    txtTitle.setText(label);
                    txtTitle.setTextColor(color);
                    txtTitle.setVisibility(View.VISIBLE);
                } else {
                    txtTitle.setVisibility(View.GONE);
                }
            }
        }

        public void setDeksripsi(String detailsLabel, int color) {
            mDeskripsi = detailsLabel;
            mDetailColor = color;
            if (txtDeskripsi != null) {
                if (detailsLabel != null) {
                    txtDeskripsi.setText(detailsLabel);
                    txtDeskripsi.setTextColor(color);
                    txtDeskripsi.setVisibility(View.VISIBLE);
                } else {
                    txtDeskripsi.setVisibility(View.GONE);
                }
            }
        }

        public void setSize(int width, int height) {
            mWidth = width;
            mHeight = height;
            if (mBackgroundLayout != null) {
                updateBackgroundSize();
            }
        }
    }
}
