package com.farhad.example.functional02.remote.inheritance;

import lombok.Getter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import com.farhad.example.functional02.common.DataFileException;

@Getter
public class URLDataFileMetadata extends DataFileMetadata {

    private final URL url ;

    public URLDataFileMetadata( long customerId, String type, URL url ) {

        super(customerId, type);
        this.url = url ;
    }

    @Override
    public String loadContents() {
        try {

            URLConnection conn = url.openConnection();

            try ( BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream(), StandardCharsets.UTF_8 ) ) ) {

                return in.lines().collect(Collectors.joining("\n"));

            }
                
        } catch (IOException ex) {
               //bad
            // return null;
           throw new DataFileException(ex)    ;   

        }    
    }
    
}
