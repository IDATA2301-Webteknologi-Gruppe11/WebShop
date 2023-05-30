/**
 * Updates the details of a user.
 *
 * @param {Object} user - The user object containing the current user details.
 * @param {string} firstName - The updated first name of the user.
 * @param {string} lastName - The updated last name of the user.
 * @param {string} email - The updated email of the user.
 * @returns {Promise<void>} - A Promise that resolves when the user details are updated.
 */
async function updateUserDetails(user, firstName, lastName,role) {
    console.log(user.role)
    const payload = {
        orders: user.orders,
        id: user.id,
        firstName: firstName ? firstName : user.firstName,
        lastName: lastName ? lastName: user.lastName,
        email: user.email,
        pass: user.pass,
        active: user.active,
        shoppingCart: user.shoppingCart,
        role: role
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

async function saveChanges(sessionUser, role) {
    const firstNameInput = document.getElementById("firstName");
    const lastNameInput = document.getElementById("lastName");
    const firstName = firstNameInput.value;
    const lastName = lastNameInput.value;
    await updateUserDetails(sessionUser ,firstName, lastName, role);
}