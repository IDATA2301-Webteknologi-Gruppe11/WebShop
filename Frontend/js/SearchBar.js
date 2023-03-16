var header = document.querySelector("header");
var searchBar = document.getElementById("search-bar");

searchBar.addEventListener("focus", function() {
    header.classList.add("header-expanded");
});

searchBar.addEventListener("blur", function() {
    header.classList.remove("header-expanded");
});