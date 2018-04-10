import java.io.*;

public class irReader {

    private String path, content;

    public irReader(String path) {
        setPath(path);
        readFile();
        getContent();
    }

    // مسیر قرار گیری فایل را تنظیم می کند
    private void setPath(String p) {
        path = p;
    }

    // مسیر فایل ورودی را بر می گرداند
    private String getPath() {
        return path;
    }

    // محتوای فایل را در متغیر content می ریزد
    private void setContent(String c) {
        content = c;
    }

    // محتوای فایل را بر می گرداند
    public String getContent() {
        return content;
    }

    // از آدرس تنظیم  شده فایل را می خواند و در متغیر content با استفاده تابع setContent  می ریزد
    private void readFile() {
        try {
            Reader reader = new InputStreamReader(new FileInputStream(getPath()), "UTF-8");
            BufferedReader fIn = new BufferedReader(reader);

            String s, output = "";

            while ((s = fIn.readLine()) != null) {
                output += s;
                output += "\n";
            }

            setContent(output);

            fIn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // این تابع برای ذخیره سازی محتوایی در فایل استفاده می شود که فعلا مورد استفاده قرار نگرفته است
    private void writeFile() {
        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8");
            BufferedWriter fOut = new BufferedWriter(writer);

            fOut.write(getContent());

            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
