package org.iflab.wecenterandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import org.iflab.wecenterandroid.base.BaseActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeActivity extends BaseActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    protected void loadData() {

    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();
        if(result.startsWith("http://") || result.startsWith("https://")){
            ArticleActivity.startArticle(QRCodeActivity.this,-1,ArticleActivity.QRCODE_ARTICLE,result);
            finish();
        }else{
            showToast("链接格式不正确");
        }
        mScannerView.resumeCameraPreview(this);
    }

    public static void startQRCodeActivity(Context context){
        Intent intent = new Intent(context, QRCodeActivity.class);
        context.startActivity(intent);
    }
}
