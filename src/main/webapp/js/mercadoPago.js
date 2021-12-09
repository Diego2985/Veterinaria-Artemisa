window.document.onload = function(e){
    // Add SDK credentials
// REPLACE WITH YOUR PUBLIC KEY AVAILABLE IN: https://developers.mercadopago.com/panel
    const mercadopago = new MercadoPago('TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d', {
        locale: 'es-AR' // The most common are: 'pt-BR', 'es-AR' and 'en-US'
    });

// Handle call to backend and generate preference.
    document.getElementById("checkout-btn").addEventListener("click", function() {
        pagar();
    });
}

function pagar() {
    $('#checkout-btn').attr("disabled", true);

    const orderData = {
        quantity: 1,
        title: "Alimento",
        description: "Para perro",
        price: 500.0
    };

    fetch("http://localhost:8080/Veterinaria_Artemisa_war_exploded/create_preference", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(orderData),
    })
        .then(function(response) {
            return response.json();
        })
        .then(function(preference) {
            console.log('createCheckoutButton');
            createCheckoutButton(preference.id);
        })
        .catch(function(e) {
            console.log(e);
            alert("Unexpected error" + e.message);
            $('#checkout-btn').attr("disabled", false);
        });
}

// Create preference when click on checkout button
function createCheckoutButton(preferenceId) {
    // Initialize the checkout
    console.log(preferenceId);
    mercadopago.checkout({
        preference: {
            id: preferenceId
        },
        render: {
            container: '.cho-container', // Class name where the payment button will be displayed
            label: 'Pagar', // Change the payment button text (optional)
        }
    });
}