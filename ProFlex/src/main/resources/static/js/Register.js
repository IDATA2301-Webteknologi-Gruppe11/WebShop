
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

function showConfirmPassword() {
    const passwordTextBar = document.getElementById("password-confirm-text-bar");
    const buttonEye = document.getElementById("password-confirm-button-eye");

    if(passwordTextBar.type === "password") {
        passwordTextBar.type = "text";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
    }
    else {
        passwordTextBar.type = "password";
        buttonEye.innerHTML = '<i class="fa-regular fa-eye"></i>';
    }
}