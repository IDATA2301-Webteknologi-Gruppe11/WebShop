/**
 * Adds a product to the shopping cart.
 *
 * @param {string} shoppingCartId - The ID of the shopping cart.
 * @param {object} product - The product object to add to the cart.
 * @returns {Promise<void>} - A Promise that resolves when the operation is complete.
 */
async function addProductToCart(shoppingCartId, product) {
    try {
        const responseShoppingCart = await fetch("/api/shoppingcart/" + shoppingCartId);
        if (!responseShoppingCart.ok) {
            console.log("Failed to fetch shopping cart");
            return;
        }
        const shoppingCartData = await responseShoppingCart.json();
        if (shoppingCartData.cartItems === null) {
            await createCartItem(shoppingCartData, product);
            redirectToShoppingCart();
        } else {
            let productFound = false;
            for (const cartItem of Object.values(shoppingCartData.cartItems)) {
                if (await checkProductInCartItem(cartItem, product, shoppingCartData)) {
                    productFound = true;
                    redirectToShoppingCart();
                    break;
                }
            }
            if (!productFound) {
                await createCartItem(shoppingCartData, product);
                redirectToShoppingCart();
            }
        }
    } catch (error) {
        console.log('Error: ' + error.message);
    }
}

/**
 * Checks if a product exists in a cart item and updates its quantity if found.
 *
 * @param {object} cartItem - The cart item to check.
 * @param {object} product - The product to compare with the cart item.
 * @param {object} shoppingCart - The shopping cart object.
 * @returns {Promise<boolean>} - A Promise that resolves to `true` if the cart item was updated, or `undefined` otherwise.
 */
async function checkProductInCartItem(cartItem, product, shoppingCart) {
    if (cartItem.product.id === product.id) {
        const payload = {
            id: cartItem.id,
            shoppingCart: shoppingCart,
            product: cartItem.product,
            quantity: cartItem.quantity + 1
        };
        try {
            const response = await fetch("/api/cartItems/update/" + cartItem.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            });
            if (response.ok) {
                console.log("cartItem was updated");
                cartItem.quantity = cartItem.quantity + 1; // Update the quantity in the cartItem object
                return true;
            } else {
                console.log("Didn't update cart item.");
            }
        } catch (error) {
            console.log('Error updating cart item: ' + error.message);
        }
    }
}

/**
 * Creates a new cart item in the shopping cart.
 *
 * @param {object} shoppingCart - The shopping cart object.
 * @param {object} product - The product object to add to the cart.
 * @returns {Promise<void>} - A Promise that resolves when the operation is complete.
 */
async function createCartItem(shoppingCart, product) {
    const payload = {
        shoppingCart: shoppingCart,
        product: product,
        quantity: 1
    };
    const response = await fetch("/api/cartItems/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });
    if (response.ok) {
        console.log("cartItem was created");
    } else {
        console.log("Error creating cartItem");
    }
}

/**
 * Rederect to shopping cart page
 */
function redirectToShoppingCart() {
    window.location.href = "/shoppingcart";
}