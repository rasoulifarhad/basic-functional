package com.farhad.example.functional02.immutability.v1;

import lombok.RequiredArgsConstructor;
import com.farhad.example.functional02.common.UserService;
import com.farhad.example.functional02.common.DataFileWriter;
import com.farhad.example.functional02.common.User;

@RequiredArgsConstructor
public class DataFileService {
    
    private final UserService userService ; 
    private final DataFileWriter dataFileWriter;

    public DataFileMetadata createDataFileMetadata(final User user,
                                                    final String type,
                                                    final String contents,
                                                    final String location)  {

        return new DataFileMetadata(
                            userService.attachCustomerInfo(user, type), 
                            type, 
                            dataFileWriter.writeDataFile(contents, location)) ;

    }
}
