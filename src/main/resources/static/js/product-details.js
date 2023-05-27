async function addProductToCart(shoppingCart, product) {
    const shoppingCartJson = JSON.stringify(shoppingCart);
    if(Object.values(shoppingCartJson.ciid === null)) {
        await createCartItem(shoppingCart, product)
    }
    else {
        for(const ciid of Object.values(shoppingCartJson.ciid)) {
            await checkProductInCartItem(ciid,product)
        }
    }
}

async function checkProductInCartItem(cartItem, product, shoppingCart) {
    if(cartItem.pid === product) {
        const payload = {
            ciid: cartItem.ciid,
            scid: cartItem.scid,
            pid: cartItem.pid,
            quantity: cartItem.quantity + 1
        };
        try {
            const response =  await fetch("/api/cartItems/update" + cartItem.ciid, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            });
            if(response.ok) {
                console.log("cartItem was updated");
            } else {
                console.log("Didnt update cart item.")
            }
        }catch (error) {
            console.log('Error updating cart item: ' + error.message);
        }
    } else {
        await createCartItem(cartItem.scid, product);
    }
}

async function createCartItem(shoppingCart, product) {
    const payload = {
        scid: shoppingCart,
        pid: product,
        quantity: 1
    }

    const response = await fetch("/api/cartItems/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });
    if(response.ok) {
        console.log("cartItem was created")
    } else {
        console.log("Error creating cartItem")
    }
}