package com.farhad.example.functional02.composition.lazy;

import java.io.File;
import java.io.IOException;
import  lombok.With;
import java.nio.file.Files;

import com.farhad.example.functional02.common.DataFileException;

import cyclops.control.Eval;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  We have our DataFileMetadata class that represents a Data File stored in some location for a customer.
 * 
 *   DataFileMetadata meta = new DataFileMetadata( customerId, type, file )
 *   Eval<Status> processContents = meta.getContents()
 *                                    .map( this::process )
 *                                    .map( this::save ) ;
 * 
 *   public Data process( String contents );
 *   
 *   public Status save( Data data ) ;
 * 
 *   processContents.get() ;// terminal op
 * 
 * We can use the getContents() method that returns an Eval to tee a set of lazy operations, these won’t be executed until 
 * a ‘terminal’ operator on the Eval instance (such as ‘get’) is called.
 * 
 * Running the code
 * 
 *  If we setup a test DataFileMetadata instance
 * 
 *    //  "10:hello,20:world"
 *    meta = new DataFileMetadata(10l,"type",file);
 * 
 * Running The Original Imperative / Eager version
 * 
 * And run a transform against the original Eager implementation
 * 
 *     System.out.println(transformer.loadData(Arrays.asList(meta1)));
 * 
 * Running Lazily with ReactiveSeq version
 * 
 * If we run with the Lazy version instead, swithing our input data type to a ReactiveSeq instance 
 * rather than an ArrayList
 * 
 *      System.out.println(transformer.loadData(ReactiveSeq.of(meta)));
 * 
 * If we refactor our execution code slightly to eagerly resolve the ReactiveSeq dataflow to a List
 * 
 *      System.out.println(transformer.loadData(ReactiveSeq.of(meta))
 *                                                             .toList());
 * 
 */
@Getter
@AllArgsConstructor
public class DataFileMetadata {
    
    private final long customerId ;
    private final String type ;
    @With
    private final File file ;
    
    private final Eval<String> contents = Eval.later( this::loadContents );

    public String getContents() {
        return contents.get();
    }
    private String loadContents() {

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
           throw new DataFileException(ex)    ;   
        }
    }
}
