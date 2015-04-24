package com.cat.model;

public class AliRate extends AbstractModel {
	//是否获取成功利率
	//错误消息
	//获取利率时刻
	//各利率
	

    
    public static String KeyNameFetchTime = "FetchTime";
    public static String KeyNameMonth1To6 = "Month1To6";
    public static String KeyNameMonth6To12 = "Month6To12";
    public static String KeyNameMonth12To24 = "Month12To24";
    private String _fetchTime;

    public String getFetchTime() {
        return this._fetchTime;
    }
    public void setFetchTime(String fetchTime) {
        this._fetchTime = fetchTime;
    }

    private String _month1To6;

    public String getMonth1To6() {
    	return this._month1To6;
    }
    public void setMonth1To6(String month1To6) {
        this._month1To6 = month1To6;
    }
    
    private String _month6To12;

    public String getMonth6To12() {
    	return this._month6To12;
    }
    public void setMonth6To12(String month6To12) {
        this._month6To12 = month6To12;
    }
    
    private String _month12To24;

    public String getMonth12To24() {
    	return this._month12To24;
    }
    public void setMonth12To24(String month12To24) {
        this._month12To24 = month12To24;
    }
    
    
}
