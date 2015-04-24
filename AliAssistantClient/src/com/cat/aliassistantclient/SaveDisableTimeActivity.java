package com.cat.aliassistantclient;

import com.cat.common.AliCache;
import com.cat.service.AliRemoteService;
import com.example.aliassistantclient.R;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.*;
import android.view.*;
import android.widget.*;

public class SaveDisableTimeActivity extends ActionBarActivity {
	private EditText _disableTimes1 = null;
	private EditText _disableTimes2 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AliCache.Init();
		setContentView(R.layout.set_clawer_disabletime);

		getControl();
		
		new Thread(getdisableparams).start();
	}
	
	private void getControl() {
		_disableTimes1 = (EditText) findViewById(R.id.et_DisableTime1);
		_disableTimes2 = (EditText) findViewById(R.id.et_DisableTime2);
	}
	
	public void btnSaveDisableTimeClick(View v) {
		new Thread(savedisableparams).start();
		
	}
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case 1:
				if (AliCache._ClawlerDisableTimes.getIsSuccess()) {
					_disableTimes1.setText(
							AliCache._ClawlerDisableTimes.getDisableTime1());
					_disableTimes2.setText(
							AliCache._ClawlerDisableTimes.getDisableTime2());

					
				} else {
					Toast.makeText(
							SaveDisableTimeActivity.this,
							String.format("错误消息：%s",
									AliCache._ClawlerDisableTimes.getErrorMsg()),
							Toast.LENGTH_LONG).show();
				}
				break;
			case 2:
				if (AliCache._SoapResult.getIsSuccess()) {
					Toast.makeText(
							SaveDisableTimeActivity.this,
							"保存成功！",
							Toast.LENGTH_LONG).show();
					
				} else {
					Toast.makeText(
							SaveDisableTimeActivity.this,
							String.format("错误消息：%s",
									AliCache._SoapResult.getErrorMsg()),
							Toast.LENGTH_LONG).show();
				}
				break;
			}
		}
	};
	private Runnable getdisableparams = new Runnable() {

		@Override
		public void run() {

			AliCache._ClawlerDisableTimes = (new AliRemoteService()).GetClawlerDisableTimes();

			Message message = handler.obtainMessage();
			message.what = 1;
			handler.sendMessage(message);

		}

	};
	private Runnable savedisableparams = new Runnable() {

		@Override
		public void run() {

			AliCache._SoapResult = (new AliRemoteService())
					.SaveClawlerDisableTimes(
							_disableTimes1.getText().toString(),
							_disableTimes2.getText().toString());

			Message message = handler.obtainMessage();
			message.what = 2;
			handler.sendMessage(message);

		}

	};
}
