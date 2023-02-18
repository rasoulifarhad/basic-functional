package com.farhad.example.functional02.remote;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.farhad.example.functional02.composition.v1.DataFileMetadata;

import cyclops.data.LazySeq; 

public class Transformer {
   
    
        /**
     * 
     * Let’s make it lazy!
     * 
     *  Using Cyclops
     * 
     * Refactoring the helper method
     * 
     * We can refactor buildCustomerRecords to return a LazySeq instead of an eager List
     * 
     * If our Records are stored in a Lazy data-structure we also need to update our entities to use LazySeq
     * 
     *   Data
     *   Record
     */
    public List<Data>  loadData(List<DataFileMetadata> fileInfo) {
        return 
            fileInfo.stream()
                        .flatMap(metadata -> Stream.of(metadata.getContents())
                                                    .map( this::buildCustomerRecords )              
                                                    .map( record -> Data.of( metadata.getCustomerId(), record )  ))
                        .collect( Collectors.toList() );
    }

    private LazySeq<String> splitContentsByLine(String contents) {
        return LazySeq.of( contents.split( "," ) );

    }

    private LazySeq<Record> buildCustomerRecords(String contents) {

        return splitContentsByLine( contents )
                    .map( recordStr -> recordStr.split( ":" ) )
                    .map( idAndData -> Record.of( Long.valueOf( idAndData[0] ) , idAndData[1] ) )
                    .lazySeq();

    }

}
