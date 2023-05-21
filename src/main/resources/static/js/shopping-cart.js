
function redirectToProducts() {
    window.location.href = "/signup-form";
}

function calculateTotalPrice() {
    var quantityElements = document.getElementsByClassName("quantityNumber");
    var priceElements = document.getElementsByClassName("priceNumber");
    var totalPrice = 0;

    for (var i = 0; i < quantityElements.length; i++) {
        var quantity = parseInt(quantityElements[i].innerText);
        var price = parseFloat(priceElements[i].innerText);
        totalPrice += quantity * price;
    }

    var totalPriceElement = document.getElementById("total-price");
    totalPriceElement.innerText = "Total price: " + totalPrice;
}

// function quantityPlus(ciid) {
//     var quantity = parseInt($('.quantityNumber').text());
//     quantity++;
//     $('.quantityNumber').text(quantity);
//     updateQuantityInDatabase(ciid, quantity);
//     calculateTotalPrice();
// }
//
// function quantityMinus(ciid) {
//     var quantity = parseInt($('.quantityNumber').text());
//     if (quantity > 0) {
//         quantity--;
//         $('.quantityNumber').text(quantity);
//         updateQuantityInDatabase(ciid, quantity);
//         calculateTotalPrice();
//     }
// }
//
// function updateQuantityInDatabase(ciid, quantity) {
//     var cartItem = {
//         quantity: quantity
//     };
//
//     $.ajax({
//         url: '/api/cart-items/' + 1,
//         type: 'PUT',
//         contentType: 'application/json',
//         data: JSON.stringify(cartItem),
//         success: function(response) {
//             console.log('Quantity updated successfully.');
//         },
//         error: function(xhr, textStatus, errorThrown) {
//             console.error('Error updating quantity:', errorThrown);
//         }
//     });
// }

// Function to update the cart item quantity
function updateCartItemQuantity(id, increment) {
    // Find the quantity number element
    var quantityElement = document.querySelector('.quantityNumber');
    // Get the current quantity value
    var quantity = parseInt(quantityElement.textContent);

    // Calculate the new quantity based on the increment
    var newQuantity = increment ? quantity + 1 : quantity - 1;

    // Make the AJAX request to update the cart item in the database
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', '/api/cart-items/' + id, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 204) {
                // Update the quantity number element with the new quantity
                quantityElement.textContent = newQuantity;
            } else {
                console.log('Failed to update cart item:', xhr.responseText);
            }
        }
    };

    // Create the new cart item object with the updated quantity
    var cartItem = { quantity: newQuantity };

    // Send the updated cart item object in the request body
    xhr.send(JSON.stringify(cartItem));
}