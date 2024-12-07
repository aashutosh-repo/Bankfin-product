package com.fin.bancs.services;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.mapper.JSONMapper;
import com.fin.bancs.repository.TempModifiedEntityRepository;
import com.fin.bancs.utils.TempModifiedEntity;

import jakarta.transaction.Transactional;

@Service
public class TempModifiedEntityServices {
	
	@Autowired
    private TempModifiedEntityRepository tempModifiedRepository;

    @Transactional
    public <T> boolean storeTransactionInTemp(String modifiedKey, String entityName, T modifiedData) {
        try {
            // Serialize the modified data into a BLOB
        	String jsondata = JSONMapper.convertToJSON(modifiedData);
            byte[] jsonBytes = jsondata.getBytes(StandardCharsets.UTF_8);
        	Blob blob = new SerialBlob(jsonBytes);
        	// Create a new TempModified entry
            TempModifiedEntity tempTransaction = new TempModifiedEntity();
            tempTransaction.setModifiedKey(modifiedKey);
            tempTransaction.setEntityName(entityName);
            tempTransaction.setModifiedData(blob);
            tempTransaction.setModifiedDate(LocalDate.now());
            tempTransaction.setStatus("PENDING");

            tempModifiedRepository.save(tempTransaction);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Transactional
    public <T> T retrieveTransactionFromTemp(String modifiedKey, String entityName, Class<T> clazz) {
        try {
            // Retrieve the TempModifiedEntity from the database using the repository
            TempModifiedEntity tempTransaction = tempModifiedRepository.findByModifiedKeyAndEntityName(modifiedKey, entityName);
            
            if (tempTransaction != null) {
                // Extract the BLOB data from the entity
                Blob blob = tempTransaction.getModifiedData();
                if (blob != null) {
                    // Convert the BLOB to a byte array
                    byte[] jsonBytes = blob.getBytes(1, (int) blob.length());
                    String jsonData = new String(jsonBytes, StandardCharsets.UTF_8);
                    
                    // Deserialize the JSON data into the Java object
                    T object = JSONMapper.convertFromJSON(jsonData, clazz);
                    tempModifiedRepository.deleteById(modifiedKey);
                    return object;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null or handle as needed
    }


}
