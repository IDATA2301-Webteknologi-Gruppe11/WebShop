/**
 * Updates the details of a user.
 *
 * @param {Object} user - The user object containing the current user details.
 * @param {string} firstName - The updated first name of the user.
 * @param {string} lastName - The updated last name of the user.
 * @param {string} email - The updated email of the user.
 * @returns {Promise<void>} - A Promise that resolves when the user details are updated.
 */
async function updateUserDetails(user, firstName, lastName, email) {
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
            console.log("User was updated");
        }
    } catch (error) {
        console.log(error.message);
    }
}