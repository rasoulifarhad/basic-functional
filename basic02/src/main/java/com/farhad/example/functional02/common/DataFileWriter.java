package com.farhad.example.functional02.common;

import java.io.File;

public interface DataFileWriter {
    
    public File writeDataFile(String contents,String location) ;

    public static DataFileWriter defaultWriter() {
        return new FileDataFileWriter();
    }
}
