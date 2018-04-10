import java.util.List;
import java.util.ArrayList;

public class nGram {

    List<String> list = new ArrayList<String>();
    int count;

    public nGram(List<String> ls) {
        list = ls;
        count = 1;
    }

    // تعداد تکرار N-gram را بر می گرداند
    public int getCount() {
        return  count;
    }

    // مقدار لیست ثبت شده را بر میگرداند
    public List<String> getList() {
        return list;
    }

    // به تعداد تکرار لیست، یک واحد اضافه می کند
    public void countPlusPlus() {
        count ++;
    }

    // یک رشته مناسب از روی لیست و تعداد تکرارش می سازد و بر می گرداند
    public String toString() {
        String output = "list: [ ";
        for(int i = 0; i < list.size(); i ++) {
            output += list.get(i);
            if(i != list.size() - 1) {
                output+= ", ";
            }
        }
        output += "], count: " + count;
        return output;
    }
}
