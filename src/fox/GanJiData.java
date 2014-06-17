package fox;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;




public class GanJiData {
	private GanJiFrame view;
	private Calendar calendar = Calendar.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");

	public void incMonth() {
		calendar.add(Calendar.MONTH, 1);
		updateCalendar();
	}

	public void decMonth() {
		calendar.add(Calendar.MONTH, -1);
		updateCalendar();
	}

	public GanJiData() {
		view = new GanJiFrame(this);
		updateCalendar();
	}

	private void updateCalendar() {
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int currentDay = -1;
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
				&& c.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
			currentDay = c.get(Calendar.DATE);
		}
		
		Map<String, String> dateMap=readProperties("/date.properties");
		String whichDayString=dateMap.get("whichDay");
		int whichDay=0;
		if(null!=whichDayString&&!"".equals(whichDayString)){
			whichDay=Integer.parseInt(whichDayString);
			System.out.println("--updateCalendar whichDay--"+whichDay);
		}
		c.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH), whichDay);
		int begin = c.get(Calendar.DAY_OF_WEEK);
		long quot = 0;
		quot=c.getTimeInMillis();
		quot = quot / 1000 / 60 / 60 / 24;
//		System.out.println("quot is:"+quot);
		int namesLenth=view.getNames().length;
		int firstName=(int) (quot%namesLenth);
//		System.out.println("firstName is:"+firstName);
		view.setCalendar(begin, maxDay, currentDay,firstName+1);
		view.setYearAndMonth(sdf.format(calendar.getTime()));
	}

	public void show() {
		view.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		GanJiData calendar = new GanJiData();
		calendar.show();
	}

	public void backToday() {
		calendar=Calendar.getInstance();
		updateCalendar();
	}
	
	/**
	 * 解析properties为Map
	 * @param path 读取properties的全部信息
	 * @return 解析生成的Map
	 */
	public Map<String, String> readProperties(String path) {
		Properties props = new Properties();
		Map<String, String> prop=new HashMap<String, String>();
		try {
			URL filePathURL = GanJiData.class.getResource(path);
			InputStream in = filePathURL.openStream();
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				prop.put(key, Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}