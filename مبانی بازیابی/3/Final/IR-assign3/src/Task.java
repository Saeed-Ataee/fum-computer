import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;


public class Task {

    // نویسنده ی ایندکس
    private IndexWriter writer;
    private String finalResult = "";

    // مکان قرارگیری ایندکس در سیستم
    private static final String INDEX_DIRECTORY = "INDEX_DIR";

    public Task() throws IOException {


        Directory indexDir = FSDirectory.open(Paths.get(INDEX_DIRECTORY));

        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);

        cfg.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        writer = new IndexWriter(indexDir, cfg);

    }

    // آدرس فایل داکیومنت ها را دریافت میکند و لیست فایل های درون آن را ایندکس میکند
    public int index(String dataDir, FileFilter filter) throws Exception {
        File[] files = new File(dataDir).listFiles();
        for (File f : files) {
            if (filter == null || filter.accept(f)) {
                indexFile(f);
            }
        }
        return writer.numDocs();
    }

    // فیلد های هر داکیومنت را بر می گرداند
    protected Document getDocument(File f) throws Exception {

        String content = fileReader(f.getPath());

        int I = 0;
        int T = 0;
        int A = 0;
        int B = 0;
        int W = 0;

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '.' && i + 1 < content.length() && content.charAt(i + 1) == 'I') {
                I = i;
            } else if (content.charAt(i) == '.' && i + 1 < content.length() && content.charAt(i + 1) == 'T') {
                T = i;
            } else if (content.charAt(i) == '.' && i + 1 < content.length() && content.charAt(i + 1) == 'A') {
                A = i;
            } else if (content.charAt(i) == '.' && i + 1 < content.length() && content.charAt(i + 1) == 'B') {
                B = i;
            } else if (content.charAt(i) == '.' && i + 1 < content.length() && content.charAt(i + 1) == 'W') {
                W = i;
            }
        }

        Document doc = new Document();

        doc.add(new StringField("I", content.substring(I + 3, T - 1), Field.Store.YES));
        try {
            doc.add(new StringField("T", content.substring(T + 3, A - 1), Field.Store.YES));
        } catch (Exception e) {
            doc.add(new StringField("T", "Bad Format", Field.Store.YES));
        }
        try {
            doc.add(new StringField("A", content.substring(A + 3, B - 1), Field.Store.YES));
        } catch (Exception e) {
            doc.add(new StringField("A", "Bad Format", Field.Store.YES));
        }
        try {
            doc.add(new StringField("B", content.substring(B + 3, W - 1), Field.Store.YES));
        } catch (Exception e) {
            doc.add(new StringField("B", "Bad Format", Field.Store.YES));
        }
        doc.add(new TextField("W", new FileReader(f)));
        //doc.add(new StringField("W", content.substring(W + 3, content.length() - 1), Field.Store.YES));

        return doc;
    }

    // به کمک writer فایل را اضافه می کند به ایندکس
    private void indexFile(File f) throws Exception {
        Document doc = getDocument(f);
        writer.addDocument(doc);
    }

    // آدرس فایل را دریافت کرده و محتوای آن را برمی گرداند
    public String fileReader(String dataDir) throws Exception {
        String filetext = null;
        BufferedReader reader = null;
        //BufferedReader namesReader; //reader for followers
        //Extractor extractor = new Extractor();
        File inFile = new File(dataDir);
        //File namesFile = new File(args[1]); //get followers file
        //File userFile = new File(args[1]);

        //READING FROM USERS FILE
        reader = new BufferedReader(new FileReader(inFile));
        String line = null;

        int numLine = 0;


        while ((line = reader.readLine()) != null) {
            // numLine++;
            filetext = filetext + " " + line;

            // System.out.println(line);
        }

        reader.close();
        return filetext;
    }

    // یک رشته دریافت میکند و بر اساس پارامتر جداساز آن را بخش بخش میکند
    public void docSeparator(String dir, String text, String separateStr) {
        String[] result = text.split(separateStr);

        try {
            for (int i = 1; i < result.length; i++) {
                if (result[i] != null) {
                    fileWriter(dir, "inp-" + i, ".I " + result[i]);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // آدرس و نام و محتوای فایل را دریافت کرده و ذخیره می کند
    private void fileWriter(String dir, String name, String text) {
        try {
            PrintWriter pWriter = new PrintWriter(dir + name + ".txt", "UTF-8");
            pWriter.print(text);
            pWriter.close();
        } catch (IOException e) {
            System.out.println("Error in Doc " + name + " While writing to file");
        }
    }

    // نویسنده ی ایندکس را می بندد
    public void close() throws IOException {
        writer.close();
    }

    // یک رشته را به عنوان ورودی دریافت می کند و در ایندکس به دنبال داکیومنت های مرتبط می گردد
    public void search(int queryNumber, String q) throws IOException, ParseException {
        IndexReader rdr = DirectoryReader.open(FSDirectory.open(Paths.get(INDEX_DIRECTORY)));
        IndexSearcher is = new IndexSearcher(rdr);

        // is.setSimilarity(new DefaultSimilarity());
        // is.setSimilarity(new Similarity1());
        is.setSimilarity(new Similarity2());

        QueryParser parser = new QueryParser("W", new StandardAnalyzer());
        parser.setAllowLeadingWildcard(true);

        Query query = parser.parse(q);

        TopDocs hits = is.search(query, 100);

        int i = 1;
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            finalResult += queryNumber + " \tQ0\t" + doc.get("I") + "\t" + (i ++) + "\t" + scoreDoc.score + "\tIR-assign3\n";
            // topic_id  \t Q0 \t document_id \t rank \t score \t your_login
        }
    }

    public void resultWriter(int i) {
        fileWriter("output/", "test-" + i, finalResult);
    }

}