package com.fin.bancs.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Response",
        description = "Contains Details of Response Status"
)
@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status Code",
            example = "200"
    )
    private String statusCode;
    @Schema(
            description = "Status Message",
            example = "Request Processed Successfully"
    )
    private String statusMsg;
    @Schema(
            description = "reponse Object",
            example = "Object"
    )
    private Object responseObject;
    //Default constructor for StatusCode and message
	public ResponseDto(String statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}
	//This method use to get response with object containing important info for User
    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus respStatus, Object responseObj){
    	Map<String, Object> response = new HashMap<String, Object>();
    	response.put("Message", message);
    	response.put("HttpStatus", respStatus);
    	response.put("Data", responseObj);
    	return new ResponseEntity<>(response, respStatus);
    }
    
}
