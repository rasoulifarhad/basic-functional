package com.farhad.example.functional02.remote.option;

import lombok.Getter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import cyclops.control.Option;

@Getter
public class FileDataFileMetadata extends DataFileMetadata {

    private final File file;

    public FileDataFileMetadata( long customerId, String type, File file ) {
        super(customerId, type);
        this.file = file ;
    }


    @Override
    public Option<String> loadContents() {

        try {
            return Option.some(  new String(Files.readAllBytes(file.toPath())) );
        } catch (IOException ex) {
            return Option.none();
        }

    }
    
}
