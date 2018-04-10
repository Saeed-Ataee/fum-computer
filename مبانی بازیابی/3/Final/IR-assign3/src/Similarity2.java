import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.SmallFloat;

public class Similarity2 extends TFIDFSimilarity {

    private static final float[] NORM_TABLE = new float[256];
    protected boolean discountOverlaps = true;

    public float coord(int overlap, int maxOverlap) {
        return (float)overlap / (float)maxOverlap;
    }

    public float queryNorm(float sumOfSquaredWeights) {
        return (float)(1.0D / Math.sqrt((double)sumOfSquaredWeights));
    }

    public final long encodeNormValue(float f) {
        return (long)SmallFloat.floatToByte315(f);
    }

    public final float decodeNormValue(long norm) {
        return NORM_TABLE[(int)(norm & 255L)];
    }

    public float lengthNorm(FieldInvertState state) {
        int numTerms;
        if(this.discountOverlaps) {
            numTerms = state.getLength() - state.getNumOverlap();
        } else {
            numTerms = state.getLength();
        }

        return state.getBoost() * (float)(1.0D / Math.sqrt((double)numTerms));
    }

    public float tf(float freq) {
        return (float)Math.sqrt((double)freq);
    }

    public float sloppyFreq(int distance) {
        return 1.0F / (float)(distance + 1);
    }

    public float scorePayload(int doc, int start, int end, BytesRef payload) {
        return 1.0F;
    }

    // تغییر : حذف تعداد داکیومنت ها از صورت فرمول، به دلیل اینکه برای همه کوئری ها و عبارات ثابت است. لذا محاسبه آن تاثیری شاید نداشته باشد
    public float idf(long docFreq, long numDocs) {
        return (float)(Math.sqrt((double)numDocs / (double)(docFreq + 1L)) + 1.0D);
    }

    public void setDiscountOverlaps(boolean v) {
        this.discountOverlaps = v;
    }

    public boolean getDiscountOverlaps() {
        return this.discountOverlaps;
    }

    public String toString() {
        return "DefaultSimilarity";
    }

    static {
        for(int i = 0; i < 256; ++i) {
            NORM_TABLE[i] = SmallFloat.byte315ToFloat((byte)i);
        }

    }
}