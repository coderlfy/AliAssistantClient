package com.cat.model;

public class ClawlerDisableTimes extends AbstractModel {
	
    public static String _KeyNameCrawlerDisableRuntimeMode1 = "CrawlerDisableRuntimeMode1";
    public static String _KeyNameCrawlerDisableRuntimeMode2 = "CrawlerDisableRuntimeMode2";

	private String _disableTime1;

    public String getDisableTime1() {
    	return this._disableTime1;
    }
    public void setDisableTime1(String disableTime1) {
        this._disableTime1 = disableTime1;
    }
    
	private String _disableTime2;

    public String getDisableTime2() {
    	return this._disableTime2;
    }
    public void setDisableTime2(String disableTime2) {
        this._disableTime2 = disableTime2;
    }
}
