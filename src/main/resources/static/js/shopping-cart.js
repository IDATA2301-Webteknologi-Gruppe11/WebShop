
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

async function removeACartItem(ciid) {
    const response = await fetch("/api/cartItems/remove/" + ciid, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    });
    if(response.ok) {
        updateShoppingCartPage()
        console.log('CartItem was deleted' + response)
    }
    else {
        console.log('Error deleting cartItem' + response.status)
    }
}

async function createOrder(uid) {
    const payload = {
        date: new Date(),
        uid: uid,
    }
    const response = await fetch("/api/order/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });
    if(response.ok) {
        console.log("Order was created")
        const order = await response.json();
        return order;
    } else {
        console.log("Error creating order")
    }
}

async function createOrderProduct(scid, uid) {
    const order = await createOrder(uid);
    const response = await fetch("/api/shoppingcart/" + scid);
    if(response.ok) {
        const shoppingCart = await response.json();
        for (const ciid in shoppingCart) {
            console.log(ciid)
            await createOrderProductFromCartItem(uid, ciid, order);
        }
    }
    else {
        console.log("Error creating order")
    }
}
async function createOrderProductFromCartItem(uid, cartItem, order) {
    const payload = {
        oid: order,
        pid: cartItem.pid,
        quantity: cartItem.quantity,
        lisensKey: generateRandomString(10)
    }
    const responseOrderProduct = await fetch("/api/orderproduct/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });
    if (responseOrderProduct.ok) {
        console.log("OrderProduct was created")
    }else {
        console.log("Error creating orderProduct")
    }
}

function generateRandomString(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}

function updateShoppingCartPage() {
    location.reload()
}