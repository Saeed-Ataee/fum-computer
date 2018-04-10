import java.util.List;

public class TaskRunner {

    public void run(String path) {
        // آدرس فایل داده شده را بگیریم و محتوای آن را استخراج کنیم
        irReader reader = new irReader(path);
        String fileContent = reader.getContent();

        // از محتوای فایل لیست کلمات را بدست می آوریم
        irWordSeparator wordSeparator = new irWordSeparator(fileContent);
        List<String> wordsList = wordSeparator.getList();

        // تعداد هر کلمه را از لیست کل کلمات محتوای فایل بدست آوریم
        irWordCounter wordCounter = new irWordCounter(wordsList);
        List<Word> output = wordCounter.getList();

        // در حال حاضر به عنوان خروجی چیزی برگشت داده نمیشود. ولی قرار است که خروجی به شکل <fileID, token, tf> در بیاید
        for(int i = 0; i < output.size(); i ++) {
            // System.out.println(output.get(i).toString());
        }

        irNGramSeparator nGramSeparator = new irNGramSeparator(5, wordsList);
        List<List<String>> nGramsList = nGramSeparator.getNGramList();

        irNGramCounter nGramCounter = new irNGramCounter(nGramsList);
        List<nGram> output2 = nGramCounter.getList();

        // در حال حاضر به عنوان خروجی چیزی برگشت داده نمی شود. ولی قرار است که خروجی به شکل <fileId, n-gram, freq> در بیاید
        for(int i = 0; i < output2.size(); i ++) {
            System.out.println(output2.get(i).toString());
        }
    }

}
