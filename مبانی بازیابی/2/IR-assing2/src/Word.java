public class Word {

    String value;
    int count;

    public Word(String val) {
        value = val;
        count = 1;
    }

    // مقدار تنظیم شده برای کلمه را بر میگرداند
    public String getValue() {
        return  value;
    }

    // هر بار که این تابع صدا زده می شود بر تعداد تکرار کلمه یک واحد افزوده می شود
    public void countPlusPlus() {
        count ++;
    }

    // تعداد تکرار کلمه را بر می گرداند
    public int getCount() {
        return count;
    }

    // یک مقدار رشته را بر اساس مشخصه های این کلاس بر میگرداند
    public String toString() {
        return getValue() + ", " + getCount();
    }
}
