package me.croute.calendarexample.domain;

/**
 * 하루의 날짜정보를 저장하는 클래스
 *
 * @author croute
 * @since 2011.03.08
 */
public class DayInfo {
    private String day;
    private boolean inThisMonth;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * 날짜를 반환한다.
     *
     * @return item_day 날짜
     */
    public String getDay() {
        return day;
    }

    /**
     * 날짜를 저장한다.
     *
     * @param day 날짜
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * 이번달의 날짜인지 정보를 반환한다.
     *
     * @return inThisMonth(true/false)
     */
    public boolean isInThisMonth() {
        return inThisMonth;
    }

    /**
     * 이번달의 날짜인지 정보를 저장한다.
     *
     * @param inThisMonth(true/false)
     */
    public void setInThisMonth(boolean inThisMonth) {
        this.inThisMonth = inThisMonth;
    }

    @Override
    public String toString() {
        return "DayInfo{" +
                "day='" + day + '\'' +
                ", inThisMonth=" + inThisMonth +
                ", isSelected=" + isSelected +
                '}';
    }
}