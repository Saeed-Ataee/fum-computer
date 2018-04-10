package loader;


import java.io.IOException;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.util.BytesRef;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scot Computer
 */
public class MyIndexSearcher extends DefaultSimilarity{

  
      @Override
      public float tf(float freq) 
      {
        
        return (float) (1+Math.log(freq));
           //return (float)Math.sqrt(freq);
      }

       @Override
      public float idf(long docFreq, long numDocs) 
      {
        //return (float)(Math.log(numDocs/(double)(docFreq+1)) + 1.0);
        return (float)(Math.log(numDocs/(double)(docFreq)));
      } 
 

      
    /*
    @Override
    public float coord(int i, int i1)
    {
        return (float)(i / i1);
    }

    @Override
    public float queryNorm(float f) 
    {
        return (float) (1.0 / Math.sqrt(f)) ;
    }

    @Override
    public float tf(float f)
    {
        return (float)Math.sqrt(f); 
    }

    @Override
    public float idf(long l, long l1)
    {
        return (float) ( Math.log( l1 / (double)(l + 1) ) + 1.0);
    }

    @Override
    public float lengthNorm(FieldInvertState fis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float decodeNormValue(long l) {
        return 1;
    }

    @Override
    public long encodeNormValue(float f) {
        return 1;
    }

    @Override
    public float sloppyFreq(int i) 
    {
        return (float)(1.0 / i + 1);
    }

    @Override
    public float scorePayload(int i, int i1, int i2, BytesRef br) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */


    
    
    
}
