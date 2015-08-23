package me.croute.calendarexample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.croute.calendarexample.R;
import me.croute.calendarexample.domain.DayInfo;


/**
 * BaseAdapter를 상속받아 구현한 CalendarAdapter
 *
 * @author croute
 * @since 2011.03.08
 */
public class CalendarAdapter extends BaseAdapter {
    private static final String TAG = CalendarAdapter.class.getSimpleName();

    private List<DayInfo> mDayList;
    private Context mContext;
    private LayoutInflater mLiInflater;

    /**
     * Adpater 생성자
     *
     * @param context      컨텍스트
     * @param dayList      날짜정보가 들어있는 리스트
     */
    public CalendarAdapter(Context context, List<DayInfo> dayList) {
        this.mContext = context;
        this.mDayList = dayList;
        this.mLiInflater = LayoutInflater.from(mContext);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDayList.size();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public DayInfo getItem(int position) {
        // TODO Auto-generated method stub
        return mDayList.get(position);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    public int getPosition(DayInfo obj) {
        for (int i = 0, j = mDayList.size(); i < j; i++) {
            DayInfo d = mDayList.get(i);
            if (obj.getDay().equals(d.getDay()) && d.isInThisMonth())
                return i;
        }
        return -1;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DayInfo day = getItem(position);

        DayViewHolder dayViewHolder;

        if (convertView == null) {
            convertView = mLiInflater.inflate(R.layout.item_day, null);

//            if (position % 7 == 6) {
//                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP() + getRestCellWidthDP(), getCellHeightDP()));
//            } else {
//                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));
//            }

            dayViewHolder = new DayViewHolder();

            dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.tv_day);

            convertView.setTag(dayViewHolder);
        } else {
            dayViewHolder = (DayViewHolder) convertView.getTag();
        }

        if (day != null) {
            dayViewHolder.tvDay.setText(day.getDay());

            if (day.isInThisMonth()) {
                if (position % 7 == 0) {
                    dayViewHolder.tvDay.setTextColor(Color.RED);
                } else if (position % 7 == 6) {
                    dayViewHolder.tvDay.setTextColor(Color.BLUE);
                } else {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                }
            } else {
                dayViewHolder.tvDay.setTextColor(Color.GRAY);
            }

            if (day.isSelected()) {
                dayViewHolder.tvDay.setBackgroundColor(Color.RED);
            } else {
                dayViewHolder.tvDay.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        return convertView;
    }

    public class DayViewHolder {
        public TextView tvDay;
    }

    private int getCellWidthDP() {
//		int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 1080 / 7;

        return cellWidth;
    }

    private int getRestCellWidthDP() {
//		int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 1080 % 7;

        return cellWidth;
    }

    private int getCellHeightDP() {
//		int height = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellHeight = 1080 / 6;

        return cellHeight;
    }

    public void setDayList(List<DayInfo> dayList) {
        Log.i(TAG, "setDayList()");

        mDayList.clear();
        mDayList.addAll(dayList);

        notifyDataSetChanged();
    }

    public void setSelectedItem(int position) {
        Log.i(TAG, "setSelectedItem()");
        for (DayInfo day : mDayList) {
            day.setIsSelected(false);
        }

        mDayList.get(position).setIsSelected(true);

        notifyDataSetChanged();
    }
}
