document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('shipmentForm').addEventListener('submit', async function(event) {
        event.preventDefault();

        const shipment = {
            contractId: document.getElementById('contractId').value,
            trackingNumber: document.getElementById('trackingNumber').value,
            shipperName: document.getElementById('shipperName').value,
            shipperID: document.getElementById('shipperID').value,
            shipperAddress: document.getElementById('shipperAddress').value,
            consigneeName: document.getElementById('consigneeName').value,
            consigneeID: document.getElementById('consigneeID').value,
            consigneeAddress: document.getElementById('consigneeAddress').value,
            originPort: document.getElementById('originPort').value,
            destinationPort: document.getElementById('destinationPort').value,
            estimatedArrivalDate: document.getElementById('estimatedArrivalDate').value,
            shipmentStatus: document.getElementById('shipmentStatus').value,
            shipmentDate: document.getElementById('shipmentDate').value,
            deliveryDate: document.getElementById('deliveryDate').value,
        };

        const responseMessage = document.getElementById('responseMessage');

        try {
            const response = await fetch('/api/shipments/createShipment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(shipment),
            });

            if (response.ok) {
                const data = await response.json();
                responseMessage.textContent = `Shipment created successfully! ID: ${data.shipmentId.shipmentId}`;
                responseMessage.style.color = 'green';
            } else {
                const errorData = await response.json();
                responseMessage.textContent = `Error: ${errorData.message || 'Please try again.'}`;
                responseMessage.style.color = 'red';
            }
        } catch (error) {
            console.error('Network error:', error);
            responseMessage.textContent = 'Network error. Please try again.';
            responseMessage.style.color = 'red';
        }
    });
});
