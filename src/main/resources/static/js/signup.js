
/**
 * Toggles the visibility of the password input field and the associated eye button.
 *
 * @function showPassword
 */
function showPassword() {
    const passwordTextBar = document.getElementById("password-text-bar");
    const buttonEye = document.getElementById("password-button-eye");

    if(passwordTextBar.type === "password") {
        passwordTextBar.type = "text";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye"></i>'
    }
    else {
        passwordTextBar.type = "password";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
    }
}

/**
 * Toggles the visibility of the confirm password input field and the associated eye button.
 *
 * @function showConfirmPassword
 */
function showConfirmPassword() {
    const passwordTextBar = document.getElementById("password-confirm-text-bar");
    const buttonEye = document.getElementById("password-confirm-button-eye");

    if(passwordTextBar.type === "password") {
        passwordTextBar.type = "text";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye"></i>'
    }
    else {
        passwordTextBar.type = "password";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
    }
}

/**
 * Validates the registration form to ensure that the "Confirm Password" field matches the "Password" field.
 * Displays an alert if the passwords do not match.
 * 
 * @returns {boolean} Returns true if the form is valid, otherwise false.
 */
function validateForm() {
    var password = document.getElementById("password-text-bar").value;
    var confirmPassword = document.getElementById("password-confirm-text-bar").value;
    
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }
    
    return true;
}