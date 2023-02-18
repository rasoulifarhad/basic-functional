package com.farhad.example.functional02.lazy.v1;

import java.util.HashMap;
import java.util.Map;

public class FileManager {
    
    private Map<Long,String> dataTable = new HashMap<>();

    public void process(DataFileMetadata metadata ) {

        // bad
        if (metadata.getContents() == null)
                metadata.loadContents();
        this.dataTable.put(metadata.getCustomerId(), metadata.getContents());

    }
}
