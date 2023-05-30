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
async function updateUserRole(email, roleName) {
    const responseUser = await fetch("/api/user/email/" + email)
    const user = await responseUser.json();
    const responseRole = await fetch("/api/role/name/" + roleName)
    const role = await responseRole.json();
    const payload = {
        id: user.id,
        role: role,
        firstName: user.firstName,
        lastName: user.lastName,
        pass: user.pass,
        email: user.email,
        orders: user.orders,
        active: user.active,
        shoppingCart: user.shoppingCart

    }
    const response = await fetch("/api/user/update/" + user.id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
}

async function saveChangesRole() {
    const emailInput = document.getElementById("email-input");
    const roleInput = document.getElementById("role-input");
    const email = emailInput.value;
    const role = roleInput.value;
    await updateUserRole(email, role);
}

async function fetchProductByName(name) {
    const response = await fetch("/api/product/name/" + name);
    if(response.ok) {
        console.log("Product found")
        return await response.json();
    }else {
        console.log("didnt find product")
    }
}

async function updateProduct(name, changeName, price, newOreNot, description, shortDescription) {
    const product = await fetchProductByName(name)

    const payload = {
        id: product.id,
        description: description ? description : product.description,
        image: product.image,
        name: name ? name : product.name,
        newProduct: newOreNot ? newProduct : product.newProduct,
        price: price ? price : product.price,
        shortDescription: shortDescription ? shortDescription : product.shortDescription
    }

    const response = await fetch("/api/product/update/" + product.id, {
        method: 'PUT',
        headers: {
           'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });
    if (response.ok) {
        console.log("Product was updated")
    }else {
        console.log("Error updateing product")
    }
}

async function saveProductChanges() {
    const nameOfProductToChangeInput = document.getElementById("nameOfProductYouWantToChange");
    const nameChangeInput = document.getElementById("changeName");
    const priceInput = document.getElementById("changePrice");
    const newOrNotInput = document.getElementById("changeNewOrNote");
    const descriptionInput = document.getElementById("changeDescription");
    const shortDescriptionInput = document.getElementById("changeShortDescription");

    const nameOfProductToChange = nameOfProductToChangeInput.value;
    const nameChange = nameChangeInput.value;
    const price = priceInput.value;
    const newOrNot = newOrNotInput.value;
    const description = descriptionInput.value;
    const shortDescription = shortDescriptionInput.value;

    await updateProduct(nameOfProductToChange, nameChange, price, newOrNot, description, shortDescription);
}
