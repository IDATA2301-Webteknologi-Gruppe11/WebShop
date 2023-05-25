
async function checkCartItemsInShoppingCart(scid, product) {
    const response = await fetch("/api/shoppingcart/" + scid);
    const shoppingCart = await response.json();
    for (const cartItem of shoppingCart.ciid) {
        await checkProductInCartItem(cartItem.ciid, product);
    }
}

async function checkProductInCartItem(ciid, product) {
    const response = await fetch("/api/cartItem/" + ciid);
    const cartItem = await response.json();
    if (cartItem.pid === product.pid) {
        const payload = {
            ciid: cartItem.ciid,
            quantity: cartItem.quantity + 1,
            scid: cartItem.scid,
            pid: cartItem.pid
        };
        const response = await fetch("/api/cartItems/update/" + cartItem.ciid, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        if (response.ok) {
            console.log("CartItem updated:", response);
        } else {
            console.log("Error updating cartItem:", response.status);
        }
    } else {
        const payload = {
            quantity: 1,
            scid: cartItem.scid,
            pid: product
        };
        const response = await fetch("/api/cartItems/add", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        if (response.ok) {
            console.log("CartItem added:", response);
        } else {
            console.log("Error adding cartItem:", response.status);
        }
    }
}
