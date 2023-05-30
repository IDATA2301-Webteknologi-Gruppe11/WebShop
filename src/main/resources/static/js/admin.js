

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
    if(response.ok) {
        vaildateRole();
        updateShoppingCartPage();
    }
}

/**
 * Saves the changes made to a user's role.
 */
async function saveChangesRole() {
    const emailInput = document.getElementById("email-input");
    const roleInput = document.getElementById("role-input");
    const email = emailInput.value;
    const role = roleInput.value;
    await updateUserRole(email, role);
}

/**
 * Fetches a product by its name.
 *
 * @param name The name of the product to fetch.
 * @returns The fetched product if found, or undefined otherwise.
 */
async function fetchProductByName(name) {
    const response = await fetch("/api/product/name/" + name);
    if(response.ok) {
        console.log("Product found")
        return await response.json();
    }else {
        console.log("didnt find product")
    }
}

/**
 * Updates a product with new information.
 *
 * @param name              The name of the product to update.
 * @param changeName        The new name for the product.
 * @param price             The new price for the product.
 * @param newOreNot         Flag indicating whether the product is new or not.
 * @param description       The new description for the product.
 * @param shortDescription  The new short description for the product.
 */
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
        vaildateProduct();
        updateShoppingCartPage();
    }else {
        console.log("Error updateing product")
    }
}

/**
 * Saves the changes made to a product.
 */
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

/**
 * Reload page
 */
function updateShoppingCartPage() {
    location.reload();
}

/**
 * response when updating product or role
 */
function vaildateProduct() {
    alert(" product was updated")
}

function vaildateRole() {
    alert("User was updated")
}

