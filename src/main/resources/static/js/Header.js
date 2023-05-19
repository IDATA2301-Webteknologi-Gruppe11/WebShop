var prevScrollpos = window.pageYOffset;

window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;

  if (prevScrollpos >= currentScrollPos || currentScrollPos === 0) {
    // Scrolling up or at the top, show the header
    document.querySelector(".header").style.display = "block";
  } else {
    // Scrolling down, hide the header
    document.querySelector(".header").style.display = "none";
  }

  prevScrollpos = currentScrollPos;
};
