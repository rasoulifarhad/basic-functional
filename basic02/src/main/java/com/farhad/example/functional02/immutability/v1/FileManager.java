package com.farhad.example.functional02.immutability.v1;

import java.util.HashMap;
import java.util.Map;

import com.farhad.example.functional02.lazy.v3.DataFileMetadata;

public class FileManager {
    
    private Map<Long,String> dataTable = new HashMap<>();

    public void process(DataFileMetadata metadata ) {

        this.dataTable.put(metadata.getCustomerId(), metadata.getContents());

    }
}
