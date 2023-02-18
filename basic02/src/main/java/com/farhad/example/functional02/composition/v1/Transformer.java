package com.farhad.example.functional02.composition.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cyclops.data.LazySeq; 

public class Transformer {
    
    // 
    // public List<Data>  loadData(List<DataFileMetadata> fileInfo) {

    //     List<Data> result = new ArrayList<>() ;
    //     for (DataFileMetadata metadata : fileInfo ) {
    //         List<String> contentLines = Arrays.asList( metadata.getContents().split(",") );

    //         List<Record> records = new ArrayList<>() ;

    //         for (String recordLine : contentLines ) {
    //             String[] idAndData = recordLine.split(":");
    //             long recordId = Long.valueOf(idAndData[0]);
    //             Record record = Record.of(recordId, idAndData[1]);
    //             records.add(record);
    //         }
    //         result.add(Data.of(metadata.getCustomerId(), records));
    //     }
    //     return result ;
    // }

    // /**
    //  * Let’s refactor to a more functional style
    //  */
    // public List<Data>  loadData(List<DataFileMetadata> fileInfo) {
    //     return 
    //         fileInfo.stream()
    //                     .flatMap(metadata -> Stream.of(metadata.getContents())
    //                                                 .map( contents -> Arrays.stream( contents.split(",") ) )              
    //                                                 .map( contentLines -> contentLines.map( recordStr -> recordStr.split(":") ) 
    //                                                                                     .map( idAndData -> Record.of(Long.valueOf(idAndData[0]), idAndData[1]) )
    //                                                                                     .collect(Collectors.toList()))
    //                                                 .map( record -> Data.of( metadata.getCustomerId(), record )  ))
    //                     .collect( Collectors.toList() );
    // }

    /**
     * Fixing the challenge of mixed abstraction levels
     * 
     * factor out some of the lower level tasks into their own Functions that we can reference in our flow.
     * 
     * We can create two helper functions, the fist should split the contents String out by commas. 
     * 
     * contents String :
     * 
     *   "10:hello,20:world"
     * 
     * Or in a more general form
     * 
     *    "<customer_id>:<record_data>,<customer_id>:<record_data>..."
     * 
     * Let’s strip away the Stream infrastructure code to examine the sequence of verbs (functions) we apply :
     * 
     *    - getContents()
     *    - splitContentsByLine()
     *    - buildRecords() 
     *    - record -> Data.of( metadata.getCustomerId(), record )
     * 
     * still mixing abstraction levels.
     *   
     */
    //  public List<Data>  loadData(List<DataFileMetadata> fileInfo) {
    //     return 
    //         fileInfo.stream()
    //                     .flatMap(metadata -> Stream.of(metadata.getContents())
    //                                                 .map( this::splitContentsByLine )              
    //                                                 .map( this::buildRecords )
    //                                                 .map( record -> Data.of( metadata.getCustomerId(), record )  ))
    //                     .collect( Collectors.toList() );
    // }

    // private Stream<String> splitContentsByLine(String contents) {
    //     return Arrays.stream( contents.split( "," ) );

    // }

    // private List<Record> buildRecords(Stream<String> contentLines) {

    //     return contentLines
    //                 .map( recordStr -> recordStr.split( ":" ) )
    //                 .map( idAndData -> Record.of( Long.valueOf( idAndData[0] ) , idAndData[1] ) )
    //                 .collect( Collectors.toList() );

    // }

    /**
     * 
     * Building Customer Records and Customer related Data Objects, from contents, do seem to be abstractions 
     * of a similar level. 
     * 
     * splitContentsByLine looks like it a could be a sub-function inside buildRecords. 
     * 
     * Let’s refactor buildRecords (and give it a better name while we are at it) .
     * 
     * Let’s strip away the Stream infrastructure code to examine the sequence of verbs (functions) we apply :
     * 
     *    - getContents()
     *    - buildCustomerRecords()
     *    - new Data
     */
    public List<Data>  loadData(List<DataFileMetadata> fileInfo) {
        return 
            fileInfo.stream()
                        .flatMap(metadata -> Stream.of(metadata.getContents())
                                                    .map( this::buildCustomerRecords )              
                                                    .map( record -> Data.of( metadata.getCustomerId(), record )  ))
                        .collect( Collectors.toList() );
    }

    private Stream<String> splitContentsByLine(String contents) {
        return Arrays.stream( contents.split( "," ) );

    }

    private List<Record> buildCustomerRecords(String contents) {

        return splitContentsByLine( contents )
                    .map( recordStr -> recordStr.split( ":" ) )
                    .map( idAndData -> Record.of( Long.valueOf( idAndData[0] ) , idAndData[1] ) )
                    .collect( Collectors.toList() );

    }

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
     * 
     */

}
