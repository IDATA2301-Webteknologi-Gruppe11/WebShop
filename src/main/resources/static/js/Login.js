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
function redirectToSignUpForm() {
    window.location.href = "/signup-form";
}