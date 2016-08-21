package com.fndroid.byweather.beans;

/**
 * Created by Administrator on 2016/7/7.
 */

public class CityPinyin {

	/**
	 * errNum : 0
	 * errMsg : success
	 * retData : {"city":"顺德","pinyin":"foshan","citycode":"101280801","date":"16-07-07",
	 * "time":"08:00","postCode":"528300","longitude":113.167,"latitude":22.828,"altitude":"13",
	 * "weather":"晴","temp":"36","l_tmp":"27","h_tmp":"36","WD":"无持续风向","WS":"微风(<10m/h)",
	 * "sunrise":"05:48","sunset":"19:16"}
	 */

	private int errNum;
	private String errMsg;
	/**
	 * city : 顺德
	 * pinyin : foshan
	 * citycode : 101280801
	 * date : 16-07-07
	 * time : 08:00
	 * postCode : 528300
	 * longitude : 113.167
	 * latitude : 22.828
	 * altitude : 13
	 * weather : 晴
	 * temp : 36
	 * l_tmp : 27
	 * h_tmp : 36
	 * WD : 无持续风向
	 * WS : 微风(<10m/h)
	 * sunrise : 05:48
	 * sunset : 19:16
	 */

	private RetDataBean retData;

	public int getErrNum() { return errNum;}

	public void setErrNum(int errNum) { this.errNum = errNum;}

	public String getErrMsg() { return errMsg;}

	public void setErrMsg(String errMsg) { this.errMsg = errMsg;}

	public RetDataBean getRetData() { return retData;}

	public void setRetData(RetDataBean retData) { this.retData = retData;}

	public static class RetDataBean {
		private String city;
		private String pinyin;
		private String citycode;
		private String date;
		private String time;
		private String postCode;
		private double longitude;
		private double latitude;
		private String altitude;
		private String weather;
		private String temp;
		private String l_tmp;
		private String h_tmp;
		private String WD;
		private String WS;
		private String sunrise;
		private String sunset;

		public String getCity() { return city;}

		public void setCity(String city) { this.city = city;}

		public String getPinyin() { return pinyin;}

		public void setPinyin(String pinyin) { this.pinyin = pinyin;}

		public String getCitycode() { return citycode;}

		public void setCitycode(String citycode) { this.citycode = citycode;}

		public String getDate() { return date;}

		public void setDate(String date) { this.date = date;}

		public String getTime() { return time;}

		public void setTime(String time) { this.time = time;}

		public String getPostCode() { return postCode;}

		public void setPostCode(String postCode) { this.postCode = postCode;}

		public double getLongitude() { return longitude;}

		public void setLongitude(double longitude) { this.longitude = longitude;}

		public double getLatitude() { return latitude;}

		public void setLatitude(double latitude) { this.latitude = latitude;}

		public String getAltitude() { return altitude;}

		public void setAltitude(String altitude) { this.altitude = altitude;}

		public String getWeather() { return weather;}

		public void setWeather(String weather) { this.weather = weather;}

		public String getTemp() { return temp;}

		public void setTemp(String temp) { this.temp = temp;}

		public String getL_tmp() { return l_tmp;}

		public void setL_tmp(String l_tmp) { this.l_tmp = l_tmp;}

		public String getH_tmp() { return h_tmp;}

		public void setH_tmp(String h_tmp) { this.h_tmp = h_tmp;}

		public String getWD() { return WD;}

		public void setWD(String WD) { this.WD = WD;}

		public String getWS() { return WS;}

		public void setWS(String WS) { this.WS = WS;}

		public String getSunrise() { return sunrise;}

		public void setSunrise(String sunrise) { this.sunrise = sunrise;}

		public String getSunset() { return sunset;}

		public void setSunset(String sunset) { this.sunset = sunset;}
	}
}
