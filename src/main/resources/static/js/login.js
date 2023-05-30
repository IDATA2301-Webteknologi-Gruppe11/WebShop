/**
 * Toggles the visibility of the password text in the password input field.
 */
function showPassword() {
    const passwordTextBar = document.getElementById("password");
    const buttonEye = document.getElementById("password-button-eye");

    if(passwordTextBar.type === "password") {
        passwordTextBar.type = "text";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye"></i>';
    }
    else {
        passwordTextBar.type = "password";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
    }
}

/**
 * Redirects the user to the sign-up form page.
 */
function redirectToSignUpForm() {
    window.location.href = "/signup-form";
}
