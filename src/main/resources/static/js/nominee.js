document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('nomineeForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        
        const nomineeDetails = {
            id: document.getElementById('id').value,
            name: document.getElementById('name').value,
            age: document.getElementById('age').value
        };

        fetch('/api/nominee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nomineeDetails)
        })
        .then(response => {
            if (response.ok) {
                console.log('Nominee data sent successfully.');
            } else {
                console.error('Failed to send nominee data.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
