/*
 * 
 */
package com.ab.view.wheel;


/**
 * The Class AbSample.
 */
public class AbSample {
	
	/*//时间选择可以这样实现
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);
	int day = calendar.get(Calendar.DATE);
	// 添加大小月月份并将其转换为list,方便之后的判断
	String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
	String[] months_little = { "4", "6", "9", "11" };
	final int START_YEAR = 1990, END_YEAR = 2100;

	final List<String> list_big = Arrays.asList(months_big);
	final List<String> list_little = Arrays.asList(months_little);
	
	
	final AbWheelView mWheelView41 = (AbWheelView)mTimeView.findViewById(R.id.wheelView1);
	final AbWheelView mWheelView42 = (AbWheelView)mTimeView.findViewById(R.id.wheelView2);
	final AbWheelView mWheelView43 = (AbWheelView)mTimeView.findViewById(R.id.wheelView3);
	
	// 年
	mWheelView41.setAdapter(new AbNumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
	mWheelView41.setCyclic(true);// 可循环滚动
	mWheelView41.setLabel("年");  // 添加文字
	mWheelView41.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
	
	// 月
	mWheelView42.setAdapter(new AbNumericWheelAdapter(1, 12));
	mWheelView42.setCyclic(true);
	mWheelView42.setLabel("月");
	mWheelView42.setCurrentItem(month);
	
	// 日
	// 判断大小月及是否闰年,用来确定"日"的数据
	if (list_big.contains(String.valueOf(month + 1))) {
		mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 31));
	} else if (list_little.contains(String.valueOf(month + 1))) {
		mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 30));
	} else {
		// 闰年
		if (AbDateUtil.isLeapYear(year)){
			mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 29));
		}else{
			mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 28));
		}
	}
	mWheelView43.setCyclic(true);
	mWheelView43.setLabel("日");
	mWheelView43.setCurrentItem(day - 1);
	
	// 添加"年"监听
	AbOnWheelChangedListener wheelListener_year = new AbOnWheelChangedListener() {

		public void onChanged(AbWheelView wheel, int oldValue, int newValue) {
			int year_num = newValue + START_YEAR;
			// 判断大小月及是否闰年,用来确定"日"的数据
			if (list_big.contains(String.valueOf(mWheelView42.getCurrentItem() + 1))) {
				mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 31));
			} else if (list_little.contains(String.valueOf(mWheelView42.getCurrentItem() + 1))) {
				mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 30));
			} else {
				if (AbDateUtil.isLeapYear(year_num))
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 29));
				else
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 28));
			}
		}
	};
	// 添加"月"监听
	AbOnWheelChangedListener wheelListener_month = new AbOnWheelChangedListener() {

		public void onChanged(AbWheelView wheel, int oldValue, int newValue) {
			int month_num = newValue + 1;
			// 判断大小月及是否闰年,用来确定"日"的数据
			if (list_big.contains(String.valueOf(month_num))) {
				mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 31));
			} else if (list_little.contains(String.valueOf(month_num))) {
				mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 30));
			} else {
				int year_num = mWheelView41.getCurrentItem() + START_YEAR;
				if (AbDateUtil.isLeapYear(year_num))
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 29));
				else
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 28));
			}
		}
	};
	mWheelView41.addChangingListener(wheelListener_year);
	mWheelView42.addChangingListener(wheelListener_month);*/
	
	
	
	
	
	
	
	
	
	
	
	
}
