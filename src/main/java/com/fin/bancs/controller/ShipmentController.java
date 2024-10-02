package com.fin.bancs.controller;

import java.util.Optional;

import com.fin.bancs.common.AccountsConstants;
import com.fin.bancs.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.mapper.RequestWrapperShipment;
import com.fin.bancs.services.ShipmentService;
import com.fin.bancs.services.TradeServices;
import com.fin.bancs.trade.Shipment;


@Tag(name = "Omega Bank Trade Finance Controller",
        description = "This Swagger containing CRUD operation for Trade Finance Management")
@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
  
  @Autowired
  private ShipmentService shipmentService;
  @Autowired
  private TradeServices tfserv;

  @Operation(summary = "Create a Trade with this API",
          description = "REST API to Create a Trade in Omega Bank")
  @ApiResponse(
          responseCode = "200",
          description = "Http Request processed successfully"
  )
  @PostMapping("/createTrade")
  public ResponseEntity<ResponseDto> createTrade(@RequestBody RequestWrapperShipment tradeDetails){
	  tfserv.createTrade(tradeDetails.getTradeFinance(),
              tradeDetails.getSenderDetails(),
              tradeDetails.getReceiverDetails());
	  
	  return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));

  }
  @PostMapping("/createShipment")
  public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
    Shipment shipmentOut = shipmentService.createShipment(shipment);
    return ResponseEntity.status(HttpStatus.CREATED).body(shipmentOut);
  }
  
  @GetMapping("/trackingNumber")
  public Optional<Shipment> getShipmentByTrackingNumber(@RequestParam String trackingNumber) {
    return shipmentService.getShipmentByTrackingNumber(trackingNumber);
  }
  
  @PutMapping("/id")
  public Shipment updateShipment( @RequestBody Shipment shipment) {
    return shipmentService.updateShipment(shipment);
  }
  
  @DeleteMapping("/id")
  public void deleteShipment(@RequestParam Long id) {
    shipmentService.deleteShipment(id);
  }
  
}
