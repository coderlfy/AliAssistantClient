package com.cat.model;

public class AbstractModel {
	private Boolean _isSuccess = false;
    public Boolean getIsSuccess() {
        return _isSuccess;
    }
    public void setIsSuccess(Boolean isSuccess) {
        this._isSuccess = isSuccess;
    }

	private String _errorMsg;
    public String getErrorMsg() {
        return _errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this._errorMsg = errorMsg;
    }
}
