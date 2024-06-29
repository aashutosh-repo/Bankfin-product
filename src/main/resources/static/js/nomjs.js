document.addEventListener('DOMContentLoaded', function () {
    const nomineeForm = document.getElementById('nomineeForm');

    nomineeForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const nominee = {
            nominee_id: document.getElementById('nomineeId').value,
            owner_id: document.getElementById('ownerId').value,
            //owner_type: document.getElementById('ownerType').value,
            owner_type:12,
            nom_type: document.getElementById('nomType').value,
            nominee_first_name: document.getElementById('nomineeFirstName').value,
            nominee_middle_name: document.getElementById('nomineeMiddleName').value,
            nominee_last_name: document.getElementById('nomineeLastName').value,
            rtln_type: document.getElementById('relationType').value,
            date_of_birth: document.getElementById('dateOfBirth').value,
           	nom_doc_id: document.getElementById('nomDocId').value,
           	nom_share: document.getElementById('nomShare').value
        };

        // Send the nominee object to the REST endpoint
        fetch('/nominee/insert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nominee)
        })
        .then(response => {
            if (response.ok) {
                console.log('Nominee data sent successfully.');
                console.log(nominee);
                // Do something after successful submission
            } else {
                console.error('Failed to send nominee data.');
                // Handle error
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle network errors
        });
    });
});
        