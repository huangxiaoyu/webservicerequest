package com.thinfreely.app.webservicerequest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thinfreely.app.webservicerequest.R;
import com.thinfreely.app.webservicerequest.network.Callback;
import com.thinfreely.app.webservicerequest.tool.SoapEnvelopeUtil;
import com.thinfreely.app.webservicerequest.tool.SoapUtil;

import org.ksoap2.SoapEnvelope;

public class MainActivity extends AppCompatActivity {
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_text = findViewById(R.id.tv_text);
        findViewById(R.id.btn_Send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                SoapUtil.getInstance().getSupportCity("河南", new Callback() {
                SoapUtil.getInstance().sendAsync("http://www.WebXml.com.cn/WebServices/WeatherWebService.asmx", "http://WebXml.com.cn/", "getSupportCity", "byProvinceName", "河南", new Callback() {
                    @Override
                    public void onResponse(SoapEnvelope envelope) {
                        final String response = SoapEnvelopeUtil.getTextFromResponse(envelope);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_text.setText(response);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Object o) {

                    }
                });
            }
        });
    }
}
