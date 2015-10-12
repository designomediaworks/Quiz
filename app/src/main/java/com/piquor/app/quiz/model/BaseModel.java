package com.piquor.app.quiz.model;

public class BaseModel {
	
private int resultCode = 0;

public int getResultCode() {
	return resultCode;
}

public void setResultCode(int resultCode) {
	this.resultCode = resultCode;
}

public boolean isError() {
	return resultCode != -1;

}


}
