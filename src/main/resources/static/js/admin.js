/**
 * Creates a new product.
 *
 * @param {string} name - The name of the product.
 * @param {number} price - The price of the product.
 * @param {string} image - The URL or path of the product image.
 * @param {string[]} categories - An array of categories that the product belongs to.
 * @param {string} description - The description of the product.
 * @param {string} shortDescription - A short description of the product.
 * @returns {Promise<void>} - A Promise that resolves when the product creation is complete.
 */
async function createProduct(name, price, image, categories, description, shortDescription) {
    const payload = {
        name: name,
        price: price,
        image: image,
        shortDescription: shortDescription,
        categories: categories,
    };
    try {
        const response = await fetch("/api/product/add", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        if(response.ok) {
            console.log("Product was created");
        }
    } catch (error) {
        console.log(error.message);
    }
}

/**
 * Updates a product.
 *
 * @param {string} id - The ID of the product to update.
 * @param {string} name - The updated name of the product.
 * @param {number} price - The updated price of the product.
 * @param {string} image - The updated URL or path of the product image.
 * @param {string[]} categories - The updated array of categories that the product belongs to.
 * @param {string} description - The updated description of the product.
 * @param {string} shortDescription - The updated short description of the product.
 * @returns {Promise<void>} - A Promise that resolves when the product update is complete.
 */
async function updateProduct(id, name, price, image, categories, description, shortDescription) {
    const payload = {
        id: id,
        name: name,
        price: price,
        image: image,
        shortDescription: shortDescription,
        description: description,
        categories: categories,
        newProduct: 1
    };
    try {
        const response = await fetch("/api/product/update/" + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        if(response.ok) {
            console.log("Product was updated");
        }
    } catch (error) {
        console.log(error.message);
    }
}

/**
 * Changes the role of a user.
 *
 * @param {string} id - The ID of the user to update.
 * @param {string} role - The updated role of the user.
 * @returns {Promise<void>} - A Promise that resolves when the user role change is complete.
 */
async function changeUserRole(id, role) {
    try {
        const responseUser = await fetch("/api/user/" + id)
        if(responseUser.ok) {
            console.log("user was found")
            const user = responseUser.json();
            const userObj = user.parse(user);

            const payload = {
                orders: userObj.orders,
                id: id,
                firstName: userObj.firstName,
                lastName: userObj.lastName,
                email: userObj.email,
                pass: userObj.pass,
                active: userObj.active,
                role: role,
                shoppingCart: userObj.shoppingCart
            };
            const responseUpdateProfile = await fetch("/api/user/update/" + id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            });
            if(responseUser.ok) {
                console.log("User was updated")
            }
        }
    } catch (error) {
        console.log(error.message);
    }
}
