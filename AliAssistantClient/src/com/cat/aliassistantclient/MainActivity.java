package com.cat.aliassistantclient;

import com.cat.common.AliCache;
import com.cat.service.AliRemoteService;
import com.example.aliassistantclient.R;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AliCache.Init();
		setContentView(R.layout.activity_main);

		getControl();
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */
	private TextView _month1to6 = null;
	private TextView _month6to12 = null;
	private TextView _month12to24 = null;
	private TextView _refreshTime = null;

	private void getControl() {
		_month1to6 = (TextView) findViewById(R.id.tv_Month1To6);
		_month6to12 = (TextView) findViewById(R.id.tv_Month6To12);
		_month12to24 = (TextView) findViewById(R.id.tv_Month12To24);
		_refreshTime = (TextView) findViewById(R.id.tv_FetchTime);
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case 1:
				if (AliCache._AliRate.getIsSuccess()) {
					_month1to6.setText(String.format("半年：%s",
							AliCache._AliRate.getMonth1To6()));
					_month6to12.setText(String.format("一年：%s",
							AliCache._AliRate.getMonth6To12()));
					_month12to24.setText(String.format("两年：%s",
							AliCache._AliRate.getMonth12To24()));
					_refreshTime.setText(String.format("最新刷新：%s",
							AliCache._AliRate.getFetchTime()));

					/*
					 * Toast.makeText( MainActivity.this,
					 * String.format("半年：%s,一年：%s,两年：%s",
					 * AliCache._AliRate.getMonth1To6(),
					 * AliCache._AliRate.getMonth6To12(),
					 * AliCache._AliRate.getMonth12To24()),
					 * Toast.LENGTH_LONG).show();
					 */
				} else {
					Toast.makeText(
							MainActivity.this,
							String.format("错误消息：%s",
									AliCache._AliRate.getErrorMsg()),
							Toast.LENGTH_LONG).show();
				}
				break;
			}
		}
	};

	public void btnRefressRateClick(View v) {
		new Thread(runnable).start();
	}

	public void btnSetDisableClawlerTimeClick(View v) {

		// 创建需要对应于目标Activity的Intent
		Intent intent = new Intent(MainActivity.this,
				SaveDisableTimeActivity.class);
		// 启动指定Activity并等待返回的结果，其中0是请求码，用于标识该请求
		startActivityForResult(intent, 0);
	}

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {

			AliCache._AliRate = (new AliRemoteService()).GetRate();

			Message message = handler.obtainMessage();
			message.what = 1;
			handler.sendMessage(message);

		}

	};
}
