
/**
 * Redirects to the products page.
 */
function redirectToProducts() {
    window.location.href = "/products";
}

/**
 * Calculates the total price based on the quantities and prices of the products.
 */
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

/**
 * Updates the quantity of a cart item.
 *
 * @param {boolean} isIncrease - Indicates whether to increase the quantity (true) or decrease it (false).
 * @param {string} cartItemId - The ID of the cart item to update.
 * @param {string} scid - The shopping cart ID associated with the cart item.
 * @param {string} pid - The product ID associated with the cart item.
 */
async function updateCartItemQuantity(isIncrease, cartItemId, shoppingCart, product) {
    var quantityElement = document.getElementById("quantityNumber-" + cartItemId);
    var currentQuantity = parseInt(quantityElement.textContent);
    var newQuantity = isIncrease ? currentQuantity + 1 : currentQuantity - 1;
    if(newQuantity < 0) {
        return;
    }
    var payload = {
        id: cartItemId,
        quantity: newQuantity,
        shoppingCart: shoppingCart,
        product: product
    };

    try {
        const response = await fetch(`/api/cartItems/update/` + cartItemId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        if (response.ok) {
            quantityElement.textContent = newQuantity;
            currentQuantity = newQuantity;
        } else {
            console.log('Error updating cart item: ' + response.status);
        }
    } catch (error) {
        console.log('Error updating cart item: ' + error.message);
    }
}

/**
 * Removes a cart item.
 *
 * @param {string} ciid - The ID of the cart item to remove.
 */
async function removeACartItem(cartItemId) {
    const response = await fetch("/api/cartItems/remove/" + cartItemId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    });

    if (response.ok) {
        console.log('CartItem was deleted: ' + response);
        return true;
    } else {
        console.log('Error deleting cartItem: ' + response.status);
        return false;
    }
}

async function runRemoveCartItem(cartItemId) {
    if( await removeACartItem(cartItemId) === true) {
        updateShoppingCartPage();
    }
}

/**
 * Creates an order.
 *
 * @param {string} uid - The ID of the user associated with the order.
 * @returns {Promise<Object>} - A Promise that resolves to the created order.
 */
async function createOrder(uid) {
    const payload = {
        date: new Date(),
        user: uid,
    };

    const response = await fetch("/api/order/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });

    if (response.ok) {
        console.log("Order was created");
        const order = await response.json();
        return order;
    } else {
        console.log("Error creating order");
    }
}

/**
 * Creates order products from cart items and removes the cart items.
 *
 * @param {string} scid - The shopping cart ID.
 * @param {string} uid - The user ID associated with the order.
 */
async function createOrderProduct(scid, uid) {
    const order = await createOrder(uid);
    const response = await fetch("/api/shoppingcart/" + scid);

    if (response.ok) {
        const shoppingCart = await response.json();
        for (const cartItems of Object.values(shoppingCart.cartItems)) {
            console.log(cartItems);
            await createOrderProductFromCartItem(uid, cartItems, order);
            console.log(cartItems.id);
            await removeACartItem(cartItems.id);
        }
        updateShoppingCartPage()
    } else {
        console.log("Error creating order");
    }
}

/**
 * Creates an order product from a cart item.
 *
 * @param {string} uid - The user ID associated with the order product.
 * @param {Object} ciid - The cart item object.
 * @param {Object} order - The order object.
 */
async function createOrderProductFromCartItem(uid, ciid, order) {
    const payload = {
        order: order,
        product: ciid.product,
        quantity: ciid.quantity,
        lisensKey: generateRandomString(10)
    };

    const responseOrderProduct = await fetch("/api/orderproduct/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });

    if (responseOrderProduct.ok) {
        console.log("OrderProduct was created");
    } else {
        console.log("Error creating orderProduct");
    }
}

/**
 * Generates a random string of the specified length.
 *
 * @param {number} length - The length of the random string to generate.
 * @returns {string} - The generated random string.
 */
function generateRandomString(length) {
    console.log("im here");
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;

    for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }

    console.log(result);
    return result;
}

/**
 * Reloads the shopping cart page.
 */
function updateShoppingCartPage() {
    location.reload();
}