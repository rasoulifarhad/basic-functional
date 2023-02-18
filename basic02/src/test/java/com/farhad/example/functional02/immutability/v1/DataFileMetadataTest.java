package com.farhad.example.functional02.immutability.v1;


import org.junit.jupiter.api.Test;

import com.farhad.example.functional02.common.Customer;
import com.farhad.example.functional02.common.CustomerDAO;
import com.farhad.example.functional02.common.DataFileWriter;
import com.farhad.example.functional02.common.DataTypes;
import com.farhad.example.functional02.common.Store;
import com.farhad.example.functional02.common.User;
import com.farhad.example.functional02.common.UserService;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

@Slf4j
public class DataFileMetadataTest {

    @Test
    public void DataFileMetadata_Test() throws IOException{ 

        final Store store = new Store() ;
        final String contents = "test" ;
        final long customerId = Customer.ME.getCustomerId();
        final DataTypes dataType = ( contents == null ) ? DataTypes.EMPTY_TYPE : Store.extractType(contents);
        final File output = ( contents == null ) ? null : store.save(contents); 


        DataFileMetadata meta = new DataFileMetadata(customerId, dataType.toString(), output);
     }

     @Test
     public void DataFileMetadata_using_with_Test() throws IOException{ 
 
        final Store store = new Store() ;
        final String contents = "test" ;
        final long customerId = Customer.ME.getCustomerId();
        final DataTypes dataType = ( contents == null ) ? DataTypes.EMPTY_TYPE : Store.extractType(contents);
        final File output = ( contents == null ) ? null : store.save(contents); 


        DataFileMetadata meta = new DataFileMetadata(customerId, dataType.toString(), output);

        File newContents = createNewContents();
        DataFileMetadata  anotherMeta = meta.withFile(newContents);

        assertThat(anotherMeta.getCustomerId()).isEqualTo(meta.getCustomerId());
        assertThat(anotherMeta.getType()).isEqualTo(meta.getType());
      }

      private File createNewContents() throws IOException {
        final String contents = "another_test" ;
        final Store store = new Store() ;

        return store.save(contents) ;
      }
      @Test
      public void DataFileMetadata_immutable_Test() throws IOException{ 

        DataFileService dataFileService = new DataFileService( UserService.of( CustomerDAO.defaultDao() ), 
                                                               DataFileWriter.defaultWriter() );

        DataFileMetadata meta = dataFileService.createDataFileMetadata(User.of(10L, "farhad"), 
                                                                        DataTypes.EMPTY_TYPE.toString(), 
                                                                        "test_contents", 
                                                                        "test_location");

      }
 
}
