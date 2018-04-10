import java.util.List;
import java.util.ArrayList;

public class irNGramCounter {

    List<nGram> list = new ArrayList<nGram>();

    public irNGramCounter(List<List<String>> ls) {
        nGramCounter(ls);
    }

    // دو لیست از کلمه ها می گیرد و مورد مقایسه قرار می دهد
    public boolean compare2List(List<String> ls1, List<String> ls2) {
        if (ls1.size() != ls2.size()) {
            return false;
        }
        for (int i = 0; i < ls1.size(); i++) {
            if (!ls1.get(i).equals(ls2.get(i))) {
                return false;
            }
        }
        return true;
    }

    // چک میکند که N-gramی با لیست ورودی وجود دارد یا نه
    public int nGramExist(List<String> ls) {
        for (int i = 0; i < list.size(); i++) {
            if (compare2List(ls, list.get(i).getList())) {
                return i;
            }
        }
        return -1;
    }

    //تعداد N-gramهای موجود را بدست می آورد
    public void nGramCounter(List<List<String>> ls) {
        int index;
        for (int i = 0; i < ls.size(); i++) {
            index = nGramExist(ls.get(i));
            if (index == -1) {
                list.add(new nGram(ls.get(i)));
            } else {
                list.get(index).countPlusPlus();
            }
        }
    }

    // لیست N-gramهای موجود را به همراه تکرار آن بر می گرداند
    public List<nGram> getList() {
        return list;
    }

}
