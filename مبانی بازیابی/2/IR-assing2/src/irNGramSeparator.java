import java.util.List;
import java.util.ArrayList;

public class irNGramSeparator {

    int N;
    List<String> wordList = new ArrayList<String>();
    List<List<String>> nGramList = new ArrayList<List<String>>();

    public irNGramSeparator(int n, List<String> wl) {
        setN(n);
        setList(wl);
        N_gram();
    }

    // مقدار N را دریافت میکند و در متغیر محلی می ریزد
    private void setN(int n) {
        if (n > 0) {
            N = n;
        } else {
            N = 1;
        }
    }

    // لیست ورودی کلمات را دریافت کرده و در متغیر محلی می ریزد
    private void setList(List<String> wl) {
        wordList = wl;
    }

    // مقدار N را بر میگرداند
    public int getN() {
        return N;
    }

    // لیست تمامی N-gram های تولید شده را بر می گرداند
    public List<List<String>> getNGramList() {
        return nGramList;
    }

    // لیست کلمه های دریافت شده را بر میگرداند
    public List<String> getList() {
        return wordList;
    }

    // از روی لیست لغات ورودی و مقدار N تمامی N-gram ها را می سازد و در لیست nGramList می ریزد
    public void N_gram() {
        List<String> ls = getList();
        int n = getN();
        List<String> temp;

        for (int i = 0; i < ls.size() - n; i++) {
            temp = new ArrayList<String>();
            for (int j = i; j < i + n; j++) {
                temp.add(ls.get(j));
            }
            nGramList.add(temp);
        }
    }
}
