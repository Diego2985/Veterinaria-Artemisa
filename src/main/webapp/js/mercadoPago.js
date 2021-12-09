window.onload = function(e){

    // Handle call to backend and generate preference.
    var botones = document.getElementsByClassName("checkout-btn");

    for (let i=0; i < botones.length; i++) {
        botones[i].addEventListener("click", function() {
            pagar(i);
        });
    }
}

function pagar(index) {
    $('.checkout-btn').attr("disabled", true);

    createCheckoutButton("130399654-7b0b9506-3a98-4e59-840f-a0db0a4476a3", index);

    // const orderData = {
    //     quantity: 1,
    //     title: "Alimento",
    //     description: "Para perro",
    //     price: 500.0
    // };
    //
    // fetch("http://localhost:8080/Veterinaria_Artemisa_war_exploded/create_preference", {
    //     method: "POST",
    //     headers: {
    //         "Content-Type": "application/json",
    //     },
    //     body: JSON.stringify(orderData),
    // })
    //     .then(function(response) {
    //         return "130399654-5f50f497-ac90-46c7-8d76-7fb5d04285a5"; //response.json();
    //     })
    //     .then(function(preference) {
    //         console.log(preference);
    //         createCheckoutButton(preference);
    //     })
    //     .catch(function(e) {
    //         console.log(e);
    //         alert("Unexpected error" + e.message);
    //         $('#checkout-btn').attr("disabled", false);
    //     });
}

// Create preference when click on checkout button
function createCheckoutButton(preferenceId, index) {
    // REPLACE WITH YOUR PUBLIC KEY AVAILABLE IN: https://developers.mercadopago.com/panel
    const mercadopago = new MercadoPago('TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d', {
        locale: 'es-AR' // The most common are: 'pt-BR', 'es-AR' and 'en-US'
    });

    // Initialize the checkout
    mercadopago.checkout({
        preference: {
            id: preferenceId
        },
        render: {
            container: '.cho-container-' + index, // Class name where the payment button will be displayed
            label: 'Pagar', // Change the payment button text (optional)
        }
    });
}