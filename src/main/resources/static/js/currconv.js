async function convertCurrency() {
    const inputCurrency = document.getElementById('inputCurrency').value;
    const amount = parseFloat(document.getElementById('amount').value);
    const outputCurrency = document.getElementById('outputCurrency').value;

    if (isNaN(amount) || amount <= 0) {
        alert("Please enter a valid amount");
        return;
    }

    // Replace with your actual API endpoint
    const apiUrl = 'http://localhost:9999/instrument/currency/conversion';

    const requestData = {
        inputCurrency: inputCurrency,
        amount: amount,
        outputCurrency: outputCurrency
    };

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        const convertedAmount = data.convertedAmount;  // Adjust based on your API response structure

        document.getElementById('result').innerText = `Converted Amount: ${convertedAmount} ${outputCurrency}`;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
        alert('Failed to convert currency. Please try again.');
    }
}
