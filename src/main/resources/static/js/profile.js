async function updateUserDetails(user, firstName, lastName, email, ) {
    const payload = {
        orders: user.orders,
        id: user.id,
        firstName: firstName,
        lastName: lastName,
        email: email,
        pass: user.pass,
        active: user.active,
        role: user.role,
        shoppingCart: user.shoppingCart
    };

    try {
        const response = await fetch("/api/user/update/" + user.id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
        if(response.ok) {
            console.log("User was updated")
        }
    }catch (error) {
        console.log(error.message);
    }
}