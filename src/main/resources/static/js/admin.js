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
    }catch (error) {
        console.log(error.message);
    }
}

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
            console.log("product was updated");
        }
    } catch (error) {
        console.log(error.message);
    }
}

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
