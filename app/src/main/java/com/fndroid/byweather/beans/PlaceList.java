package com.fndroid.byweather.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */

public class PlaceList {

	/**
	 * errNum : 0
	 * errMsg : success
	 * retData : [{"province_cn":"广东","district_cn":"佛山","name_cn":"顺德","name_en":"shunde",
	 * "area_id":"101280801"}]
	 */

	private int errNum;
	private String errMsg;
	/**
	 * province_cn : 广东
	 * district_cn : 佛山
	 * name_cn : 顺德
	 * name_en : shunde
	 * area_id : 101280801
	 */

	private List<RetDataBean> retData;

	public int getErrNum() { return errNum;}

	public void setErrNum(int errNum) { this.errNum = errNum;}

	public String getErrMsg() { return errMsg;}

	public void setErrMsg(String errMsg) { this.errMsg = errMsg;}

	public List<RetDataBean> getRetData() { return retData;}

	public void setRetData(List<RetDataBean> retData) { this.retData = retData;}

}
