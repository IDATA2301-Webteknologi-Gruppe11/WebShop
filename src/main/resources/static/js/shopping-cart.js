
function redirectToProducts() {
    window.location.href = "/signup-form";
}

function calculateTotalPrice() {
    var quantityElements = document.getElementsByClassName("quantityNumber");
    var priceElements = document.getElementsByClassName("priceNumber");
    var totalPrice = 0;
    console.log(quantityElements);
    console.log(priceElements);
    for (var i = 0; i < quantityElements.length; i++) {
        var quantity = parseInt(quantityElements[i].innerText);
        console.log(priceElements)
        var price = parseFloat(priceElements[i].innerText);
        totalPrice += quantity * price;
    }
    var totalPriceElement = document.getElementById("total-price");
    totalPriceElement.innerText = "Total price: " + totalPrice;
}

async function updateCartItemQuantity(isIncrease, cartItemId, scid, pid) {
    var quantityElement = document.getElementById("quantityNumber-" + cartItemId);
    var currentQuantity = parseInt(quantityElement.textContent);
    var newQuantity = isIncrease ? currentQuantity + 1 : currentQuantity - 1;
    var payload = {
        ciid: cartItemId,
        quantity: newQuantity,
        scid: scid,
        pid: pid
    };
    try {
        const response = await fetch(`/api/cartItems/update/` + cartItemId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        console.log("im here")
        if (response.ok) {
            quantityElement.textContent = newQuantity;
            currentQuantity = newQuantity; // Update currentQuantity after each button press
        } else {
            console.log('Error updating cart item: ' + response.status);
        }
    } catch (error) {
        console.log('Error updating cart item: ' + error.message);
    }
}