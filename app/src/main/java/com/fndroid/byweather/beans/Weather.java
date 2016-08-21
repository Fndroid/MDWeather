package com.fndroid.byweather.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */

public class Weather {

	/**
	 * errNum : 0
	 * errMsg : success
	 * retData : {"city":"北京","cityid":"101010100","today":{"date":"2016-07-06","week":"星期三",
	 * "curTemp":"33℃","aqi":"116","fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃",
	 * "lowtemp":"22℃","type":"晴","index":[{"name":"感冒指数","code":"gm","index":"",
	 * "details":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","otherName":""},{"code":"fs",
	 * "details":"属强紫外辐射天气，外出时应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。","index":"强","name":"防晒指数",
	 * "otherName":""},{"code":"ct","details":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","index":"炎热",
	 * "name":"穿衣指数","otherName":""},{"code":"yd","details":"天气较好，户外运动请注意防晒。推荐您进行室内运动。",
	 * "index":"较适宜","name":"运动指数","otherName":""},{"code":"xc",
	 * "details":"不宜洗车，未来24小时内有霾，如果在此期间洗车，会弄脏您的爱车。","index":"不宜","name":"洗车指数","otherName":""},
	 * {"code":"ls","details":"天气不错，极适宜晾晒。抓紧时机把久未见阳光的衣物搬出来晒晒太阳吧！","index":"极适宜","name":"晾晒指数",
	 * "otherName":""}]},"forecast":[{"date":"2016-07-07","week":"星期四","fengxiang":"无持续风向",
	 * "fengli":"微风级","hightemp":"32℃","lowtemp":"23℃","type":"霾"},{"date":"2016-07-08",
	 * "week":"星期五","fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃","lowtemp":"24℃",
	 * "type":"多云"},{"date":"2016-07-09","week":"星期六","fengxiang":"无持续风向","fengli":"微风级",
	 * "hightemp":"33℃","lowtemp":"25℃","type":"多云"},{"date":"2016-07-10","week":"星期天",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"34℃","lowtemp":"25℃","type":"晴"}],
	 * "history":[{"date":"2016-06-29","week":"星期三","aqi":"148","fengxiang":"无持续风向",
	 * "fengli":"微风级","hightemp":"29℃","lowtemp":"22℃","type":"雷阵雨"},{"date":"2016-06-30",
	 * "week":"星期四","aqi":"108","fengxiang":"无持续风向","fengli":"微风级","hightemp":"31℃",
	 * "lowtemp":"22℃","type":"多云"},{"date":"2016-07-01","week":"星期五","aqi":"58",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃","lowtemp":"21℃","type":"晴"},
	 * {"date":"2016-07-02","week":"星期六","aqi":"108","fengxiang":"无持续风向","fengli":"微风级",
	 * "hightemp":"33℃","lowtemp":"21℃","type":"晴"},{"date":"2016-07-03","week":"星期天","aqi":"124",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"31℃","lowtemp":"23℃","type":"雷阵雨"},
	 * {"date":"2016-07-04","week":"星期一","aqi":"132","fengxiang":"无持续风向","fengli":"微风级",
	 * "hightemp":"30℃","lowtemp":"21℃","type":"霾"},{"date":"2016-07-05","week":"星期二","aqi":"124",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃","lowtemp":"22℃","type":"多云"}]}
	 */

	private int errNum;
	private String errMsg;
	/**
	 * city : 北京
	 * cityid : 101010100
	 * today : {"date":"2016-07-06","week":"星期三","curTemp":"33℃","aqi":"116","fengxiang":"无持续风向",
	 * "fengli":"微风级","hightemp":"32℃","lowtemp":"22℃","type":"晴","index":[{"name":"感冒指数",
	 * "code":"gm","index":"","details":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","otherName":""},
	 * {"code":"fs","details":"属强紫外辐射天气，外出时应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。","index":"强",
	 * "name":"防晒指数","otherName":""},{"code":"ct","details":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。",
	 * "index":"炎热","name":"穿衣指数","otherName":""},{"code":"yd",
	 * "details":"天气较好，户外运动请注意防晒。推荐您进行室内运动。","index":"较适宜","name":"运动指数","otherName":""},
	 * {"code":"xc","details":"不宜洗车，未来24小时内有霾，如果在此期间洗车，会弄脏您的爱车。","index":"不宜","name":"洗车指数",
	 * "otherName":""},{"code":"ls","details":"天气不错，极适宜晾晒。抓紧时机把久未见阳光的衣物搬出来晒晒太阳吧！","index":"极适宜",
	 * "name":"晾晒指数","otherName":""}]}
	 * forecast : [{"date":"2016-07-07","week":"星期四","fengxiang":"无持续风向","fengli":"微风级",
	 * "hightemp":"32℃","lowtemp":"23℃","type":"霾"},{"date":"2016-07-08","week":"星期五",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃","lowtemp":"24℃","type":"多云"},
	 * {"date":"2016-07-09","week":"星期六","fengxiang":"无持续风向","fengli":"微风级","hightemp":"33℃",
	 * "lowtemp":"25℃","type":"多云"},{"date":"2016-07-10","week":"星期天","fengxiang":"无持续风向",
	 * "fengli":"微风级","hightemp":"34℃","lowtemp":"25℃","type":"晴"}]
	 * history : [{"date":"2016-06-29","week":"星期三","aqi":"148","fengxiang":"无持续风向",
	 * "fengli":"微风级","hightemp":"29℃","lowtemp":"22℃","type":"雷阵雨"},{"date":"2016-06-30",
	 * "week":"星期四","aqi":"108","fengxiang":"无持续风向","fengli":"微风级","hightemp":"31℃",
	 * "lowtemp":"22℃","type":"多云"},{"date":"2016-07-01","week":"星期五","aqi":"58",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃","lowtemp":"21℃","type":"晴"},
	 * {"date":"2016-07-02","week":"星期六","aqi":"108","fengxiang":"无持续风向","fengli":"微风级",
	 * "hightemp":"33℃","lowtemp":"21℃","type":"晴"},{"date":"2016-07-03","week":"星期天","aqi":"124",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"31℃","lowtemp":"23℃","type":"雷阵雨"},
	 * {"date":"2016-07-04","week":"星期一","aqi":"132","fengxiang":"无持续风向","fengli":"微风级",
	 * "hightemp":"30℃","lowtemp":"21℃","type":"霾"},{"date":"2016-07-05","week":"星期二","aqi":"124",
	 * "fengxiang":"无持续风向","fengli":"微风级","hightemp":"32℃","lowtemp":"22℃","type":"多云"}]
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
		private String cityid;
		/**
		 * date : 2016-07-06
		 * week : 星期三
		 * curTemp : 33℃
		 * aqi : 116
		 * fengxiang : 无持续风向
		 * fengli : 微风级
		 * hightemp : 32℃
		 * lowtemp : 22℃
		 * type : 晴
		 * index : [{"name":"感冒指数","code":"gm","index":"",
		 * "details":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","otherName":""},{"code":"fs",
		 * "details":"属强紫外辐射天气，外出时应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。","index":"强","name":"防晒指数",
		 * "otherName":""},{"code":"ct","details":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","index":"炎热",
		 * "name":"穿衣指数","otherName":""},{"code":"yd","details":"天气较好，户外运动请注意防晒。推荐您进行室内运动。",
		 * "index":"较适宜","name":"运动指数","otherName":""},{"code":"xc",
		 * "details":"不宜洗车，未来24小时内有霾，如果在此期间洗车，会弄脏您的爱车。","index":"不宜","name":"洗车指数",
		 * "otherName":""},{"code":"ls","details":"天气不错，极适宜晾晒。抓紧时机把久未见阳光的衣物搬出来晒晒太阳吧！",
		 * "index":"极适宜","name":"晾晒指数","otherName":""}]
		 */

		private TodayBean today;
		/**
		 * date : 2016-07-07
		 * week : 星期四
		 * fengxiang : 无持续风向
		 * fengli : 微风级
		 * hightemp : 32℃
		 * lowtemp : 23℃
		 * type : 霾
		 */

		private List<ForecastBean> forecast;
		/**
		 * date : 2016-06-29
		 * week : 星期三
		 * aqi : 148
		 * fengxiang : 无持续风向
		 * fengli : 微风级
		 * hightemp : 29℃
		 * lowtemp : 22℃
		 * type : 雷阵雨
		 */

		private List<HistoryBean> history;

		public String getCity() { return city;}

		public void setCity(String city) { this.city = city;}

		public String getCityid() { return cityid;}

		public void setCityid(String cityid) { this.cityid = cityid;}

		public TodayBean getToday() { return today;}

		public void setToday(TodayBean today) { this.today = today;}

		public List<ForecastBean> getForecast() { return forecast;}

		public void setForecast(List<ForecastBean> forecast) { this.forecast = forecast;}

		public List<HistoryBean> getHistory() { return history;}

		public void setHistory(List<HistoryBean> history) { this.history = history;}

		public static class TodayBean {
			private String date;
			private String week;
			private String curTemp;
			private String aqi;
			private String fengxiang;
			private String fengli;
			private String hightemp;
			private String lowtemp;
			private String type;
			/**
			 * name : 感冒指数
			 * code : gm
			 * index :
			 * details : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
			 * otherName :
			 */

			private List<IndexBean> index;

			public String getDate() { return date;}

			public void setDate(String date) { this.date = date;}

			public String getWeek() { return week;}

			public void setWeek(String week) { this.week = week;}

			public String getCurTemp() { return curTemp;}

			public void setCurTemp(String curTemp) { this.curTemp = curTemp;}

			public String getAqi() { return aqi;}

			public void setAqi(String aqi) { this.aqi = aqi;}

			public String getFengxiang() { return fengxiang;}

			public void setFengxiang(String fengxiang) { this.fengxiang = fengxiang;}

			public String getFengli() { return fengli;}

			public void setFengli(String fengli) { this.fengli = fengli;}

			public String getHightemp() { return hightemp;}

			public void setHightemp(String hightemp) { this.hightemp = hightemp;}

			public String getLowtemp() { return lowtemp;}

			public void setLowtemp(String lowtemp) { this.lowtemp = lowtemp;}

			public String getType() { return type;}

			public void setType(String type) { this.type = type;}

			public List<IndexBean> getIndex() { return index;}

			public void setIndex(List<IndexBean> index) { this.index = index;}

			public static class IndexBean {
				private String name;
				private String code;
				private String index;
				private String details;
				private String otherName;

				public String getName() { return name;}

				public void setName(String name) { this.name = name;}

				public String getCode() { return code;}

				public void setCode(String code) { this.code = code;}

				public String getIndex() { return index;}

				public void setIndex(String index) { this.index = index;}

				public String getDetails() { return details;}

				public void setDetails(String details) { this.details = details;}

				public String getOtherName() { return otherName;}

				public void setOtherName(String otherName) { this.otherName = otherName;}
			}
		}

		public static class ForecastBean {
			private String date;
			private String week;
			private String fengxiang;
			private String fengli;
			private String hightemp;
			private String lowtemp;
			private String type;

			public String getDate() { return date;}

			public void setDate(String date) { this.date = date;}

			public String getWeek() { return week;}

			public void setWeek(String week) { this.week = week;}

			public String getFengxiang() { return fengxiang;}

			public void setFengxiang(String fengxiang) { this.fengxiang = fengxiang;}

			public String getFengli() { return fengli;}

			public void setFengli(String fengli) { this.fengli = fengli;}

			public String getHightemp() { return hightemp;}

			public void setHightemp(String hightemp) { this.hightemp = hightemp;}

			public String getLowtemp() { return lowtemp;}

			public void setLowtemp(String lowtemp) { this.lowtemp = lowtemp;}

			public String getType() { return type;}

			public void setType(String type) { this.type = type;}
		}

		public static class HistoryBean {
			private String date;
			private String week;
			private String aqi;
			private String fengxiang;
			private String fengli;
			private String hightemp;
			private String lowtemp;
			private String type;

			public String getDate() { return date;}

			public void setDate(String date) { this.date = date;}

			public String getWeek() { return week;}

			public void setWeek(String week) { this.week = week;}

			public String getAqi() { return aqi;}

			public void setAqi(String aqi) { this.aqi = aqi;}

			public String getFengxiang() { return fengxiang;}

			public void setFengxiang(String fengxiang) { this.fengxiang = fengxiang;}

			public String getFengli() { return fengli;}

			public void setFengli(String fengli) { this.fengli = fengli;}

			public String getHightemp() { return hightemp;}

			public void setHightemp(String hightemp) { this.hightemp = hightemp;}

			public String getLowtemp() { return lowtemp;}

			public void setLowtemp(String lowtemp) { this.lowtemp = lowtemp;}

			public String getType() { return type;}

			public void setType(String type) { this.type = type;}

			@Override
			public String toString() {
				return "HistoryBean{" +
						"date='" + date + '\'' +
						", week='" + week + '\'' +
						", aqi='" + aqi + '\'' +
						", fengxiang='" + fengxiang + '\'' +
						", fengli='" + fengli + '\'' +
						", hightemp='" + hightemp + '\'' +
						", lowtemp='" + lowtemp + '\'' +
						", type='" + type + '\'' +
						'}';
			}
		}

		@Override
		public String toString() {
			return "RetDataBean{" +
					"city='" + city + '\'' +
					", cityid='" + cityid + '\'' +
					", today=" + today +
					", forecast=" + forecast +
					", history=" + history +
					'}';
		}
	}

	@Override
	public String toString() {
		return "Weather{" +
				"errNum=" + errNum +
				", errMsg='" + errMsg + '\'' +
				", retData=" + retData +
				'}';
	}
}
