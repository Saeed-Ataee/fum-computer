
public class Index {

    public static void main(String[] args) {
        try {
            Task task = new Task();

            //--------------------------------------------بخش ایندکس کردن داکیومنت ها----------------------------------------------

            // خواندن فایل حاوی تمامی داکیومنت ها
            String docsContent = task.fileReader("dataset/cran.all.1400");

            // جدا کردن داکیومنت ها از هم
            task.docSeparator("docs/", docsContent, ".I ");

            // اضافه کردن داکیومنت ها به ایندکس
            task.index("docs", null);

            // بستن نویسنده ایندکس
            task.close();

            //--------------------------------------------بخش جستجوی کوئری ها------------------------------------------------

            String queriesContent = task.fileReader("dataset/cran.qry");

            task.docSeparator("query/", queriesContent, ".I ");

            String[] array = queriesContent.split(".I ");

            for (int i = 1; i < array.length; i++) {
                int j = 1;
                while (array[i].charAt(j - 1) != '.' && array[i].charAt(j) != 'W') {
                    j++;
                }
                String query = array[i].substring(j + 2, array[i].length());

                task.search(i, query);
            }
            task.resultWriter(3);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
