var prevScrollpos = window.pageYOffset;

window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;

  if (prevScrollpos > currentScrollPos) {
    // Scrolling up, show the header
    document.querySelector(".header").style.display = "block";
  } else {
    // Scrolling down, hide the header
    document.querySelector(".header").style.display = "none";
  }

  prevScrollpos = currentScrollPos;
};
