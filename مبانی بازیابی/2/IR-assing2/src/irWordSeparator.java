import java.util.List;
import java.util.ArrayList;

public class irWordSeparator {

    List<String> list = new ArrayList<String>();

    public irWordSeparator(String t) {
        textAnalyser(t);
    }

    // یک متن را به عنوان ورودی می گیرد و لیست کلمات آن را بدست می آورد
    private void textAnalyser(String text) {

        String word = "";

        for (int i = 0; i < text.length(); i++) {
            Character charAt = text.charAt(i);
            if (Character.isAlphabetic(charAt) || Character.isDigit(charAt)) {
                word = word + charAt;
            } else {
                if (!word.isEmpty()) list.add(word);
                word = "";
            }
        }

    }

    // لیست کل کلمات متن پاس داده شده را بر می گرداند
    public List<String> getList() {
        return list;
    }

}
