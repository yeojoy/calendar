package me.croute.calendarexample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.croute.calendarexample.R;
import me.croute.calendarexample.adapter.CalendarAdapter;
import me.croute.calendarexample.domain.DayInfo;


/**
 * 그리드뷰를 이용한 달력 예제
 *
 * @author croute
 * @blog http://croute.me
 * @link http://croute.me/335
 * @since 2011.03.08
 */
public class CalendarActivity extends Activity implements OnClickListener {

    private static final String TAG = CalendarActivity.class.getSimpleName();

    public static int SUNDAY = 1;

    private TextView mTvCalendarTitle;

    private ViewPager mVpCalendar;

    private Calendar mThisMonthCalendar;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        findViewById(R.id.btn_last_month).setOnClickListener(this);
        findViewById(R.id.btn_next_month).setOnClickListener(this);

        mTvCalendarTitle = (TextView) findViewById(R.id.tv_title);

        mVpCalendar = (ViewPager) findViewById(R.id.vp_calendar);
        CalendarPagerAdapter adapter = new CalendarPagerAdapter();
        mVpCalendar.setAdapter(adapter);
        mVpCalendar.setCurrentItem(2);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();

        // 이번달 의 캘린더 인스턴스를 생성한다.
//        mThisMonthCalendar = Calendar.getInstance();
//        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
//        getCalendar(mThisMonthCalendar);
    }

    /**
     * 달력을 셋팅한다.
     *
     * @param calendar 달력에 보여지는 이번달의 Calendar 객체
     */
    private void getCalendar(CalendarAdapter adapter, Calendar calendar) {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;
        int MAX_DAY_OF_THIS_MONTH = 35;

        List<DayInfo> dayList = new ArrayList<>();

        // 이번달 시작일의 요일을 구한다. 시작일이 일요일인 경우 인덱스를 1(일요일)에서 8(다음주 일요일)로 바꾼다.)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH) + "");

        // 지난달의 마지막 일자를 구한다.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Log.d("지난 달 마지막 주 시작일", lastMonthStartDay + "");

        calendar.add(Calendar.MONTH, 1);
        Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH) + "");

        lastMonthStartDay -= (dayOfMonth - 1) - 1;

        Log.d("계산 후 지난 달 마지막 주 시작일", lastMonthStartDay + "");

        DayInfo day;

        Log.e("DayOfMonth", dayOfMonth + "");

        for (int i = 0; i < dayOfMonth - 1; i++) {
            int date = lastMonthStartDay + i;
            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInThisMonth(false);
            day.setIsSelected(false);
            day.setMonth(calendar.get(Calendar.MONTH) - 1);


            dayList.add(day);
        }
        for (int i = 1; i <= thisMonthLastDay; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInThisMonth(true);
            day.setIsSelected(false);
            day.setMonth(calendar.get(Calendar.MONTH));

            dayList.add(day);
        }

        if ((dayOfMonth == 6 && thisMonthLastDay > 30) ||
                (dayOfMonth == 7 && thisMonthLastDay > 29))
            MAX_DAY_OF_THIS_MONTH = 42;
        else if (dayOfMonth == 1 && thisMonthLastDay == 28)
            MAX_DAY_OF_THIS_MONTH = 28;

        for (int i = 1; i < MAX_DAY_OF_THIS_MONTH - (thisMonthLastDay + dayOfMonth - 1) + 1; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInThisMonth(false);
            day.setIsSelected(false);
            day.setMonth(calendar.get(Calendar.MONTH) + 1);
            dayList.add(day);
        }

        setTitle(calendar);

        adapter.setDayList(dayList);
    }

    /**
     * 지난달의 Calendar 객체를 반환합니다.
     *
     * @param calendar
     * @return LastMonthCalendar
     */
    private Calendar getLastMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);

        return calendar;
    }

    /**
     * 다음달의 Calendar 객체를 반환합니다.
     *
     * @param calendar
     * @return NextMonthCalendar
     */
    private Calendar getNextMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        return calendar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_last_month:
//                moveToLastMonth();
                break;
            case R.id.btn_next_month:
//                moveToNextMonth();
                break;
        }
    }

//    private void moveToLastMonth() {
//        mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
//        getCalendar(mThisMonthCalendar);
//    }
//
//    private void moveToNextMonth() {
//        mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
//        getCalendar(mThisMonthCalendar);
//    }
//
    private void moveToLastMonth(DayInfo dayInfo, Calendar cal, CalendarAdapter adapter) {
        getCalendar(adapter, cal);

        if (dayInfo != null) {

            int position = adapter.getPosition(dayInfo);
            Log.d(TAG, "last month Position : " + position);
            adapter.setSelectedItem(position);
        }
    }

    private void moveToNextMonth(DayInfo dayInfo, Calendar cal, CalendarAdapter adapter) {
        getCalendar(adapter, cal);
        if (dayInfo != null) {

            int position = adapter.getPosition(dayInfo);
            Log.d(TAG, "next month Position : " + position);
            adapter.setSelectedItem(position);
        }
    }

    public class CalendarPagerAdapter extends PagerAdapter
            implements OnItemClickListener {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View) object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            GridView gvCalendar = (GridView) LayoutInflater.from(CalendarActivity.this)
                    .inflate(R.layout.item_view_pager, null);
            gvCalendar.setOnItemClickListener(this);

            List<DayInfo> dayList = new ArrayList<>();

            CalendarAdapter calAdapter = new CalendarAdapter(CalendarActivity.this, dayList);
            gvCalendar.setAdapter(calAdapter);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, 1);

            switch (position) {
                case 0:
                    cal.add(Calendar.MONTH, -2);
                    break;
                case 1:
                    cal.add(Calendar.MONTH, -1);
                    break;
                case 3:
                    cal.add(Calendar.MONTH, 1);
                    break;
                case 4:
                    cal.add(Calendar.MONTH, 2);
                    break;

            }

            getCalendar(calAdapter, cal);

            container.addView(gvCalendar);

            return gvCalendar;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((GridView) object);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            ((CalendarAdapter) parent.getAdapter()).setSelectedItem(position);
            DayInfo day = ((CalendarAdapter) parent.getAdapter()).getItem(position);
            int page = mVpCalendar.getCurrentItem();
            if (!day.isInThisMonth()) {
                if (position > 15) {
                    mVpCalendar.setCurrentItem(page + 1);
                } else {
                    mVpCalendar.setCurrentItem(page - 1);
                }
            }

            Toast.makeText(CalendarActivity.this, day.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setTitle(Calendar cal) {
        // 캘린더 타이틀(년월 표시)을 세팅한다.
        mTvCalendarTitle.setText(cal.get(Calendar.YEAR) + "년 "
                + (cal.get(Calendar.MONTH) + 1) + "월");
    }
}