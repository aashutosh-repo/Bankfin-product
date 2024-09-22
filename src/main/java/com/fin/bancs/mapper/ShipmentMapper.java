package com.fin.bancs.mapper;

import com.fin.bancs.dto.ShipmentDTO;
import com.fin.bancs.trade.Shipment;

public class ShipmentMapper {

    public static ShipmentDTO shipmentToShipmentDTO(Shipment shipment) {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setShipperName(shipment.getShipperName());
        shipmentDTO.setShipperID(shipment.getShipperID());
        shipmentDTO.setShipperAddress(shipment.getShipperAddress());
        shipmentDTO.setConsigneeName(shipment.getConsigneeName());
        shipmentDTO.setConsigneeID(shipment.getConsigneeID());
        shipmentDTO.setConsigneeAddress(shipment.getConsigneeAddress());
        shipmentDTO.setShipmentStatus(shipment.getShipmentStatus());
        shipmentDTO.setShipmentDate(shipment.getShipmentDate());
        shipmentDTO.setDeliveryDate(shipment.getDeliveryDate());
        return shipmentDTO;
    }

    public static Shipment shipmentDTOToShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();
        shipment.setShipperName(shipmentDTO.getShipperName());
        shipment.setShipperID(shipmentDTO.getShipperID());
        shipment.setShipperAddress(shipmentDTO.getShipperAddress());
        shipment.setConsigneeName(shipmentDTO.getConsigneeName());
        shipment.setConsigneeID(shipmentDTO.getConsigneeID());
        shipment.setConsigneeAddress(shipmentDTO.getConsigneeAddress());
        shipment.setShipmentStatus(shipmentDTO.getShipmentStatus());
        shipment.setShipmentDate(shipmentDTO.getShipmentDate());
        shipment.setDeliveryDate(shipmentDTO.getDeliveryDate());
        return shipment;
    }
}