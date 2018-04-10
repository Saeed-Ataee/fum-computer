import java.util.List;
import java.util.ArrayList;

// در حال حاضر این کلاس برای حروف عربی و فارسی هیچ همگام سازی انجام نمی دهد و هر کدام را یک کلمه جدا می شمارد

public class irWordCounter {

    List<Word> list = new ArrayList<Word>();

    public irWordCounter(List<String> wl) {
        wordCounter(wl);
    }

    // در آرایه کلمه ها به دنبال رشته ورودی می گردد و در صورت پیدا شدن مکان آن و در غیر این صورت -1 را بر میگرداند
    public int existWord(String v) {
        for(int i = 0; i < list.size(); i ++) {
            if(list.get(i).getValue().equals(v)) {
                return i;
            }
        }
        return -1;
    }

    // یک آرایه از رشته ها را دریافت میکند و آرایه ای از کلمه ها و تعدادشان را می سازد
    public void wordCounter(List<String> ls) {
        int index;
        for (int i = 0; i < ls.size(); i++) {
            index = existWord(ls.get(i));
            if(index == -1) {
                Word w = new Word(ls.get(i));
                list.add(w);
            }
            else {
                list.get(index).countPlusPlus();
            }
        }
    }

    // لیست کلمه ها را بر میگرداند
    public List<Word> getList() {
        return list;
    }
}
