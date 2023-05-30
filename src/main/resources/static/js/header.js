 var prevScrollpos = window.pageYOffset;

window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;

  if (prevScrollpos >= currentScrollPos || currentScrollPos === 0) {
    // Scrolling up or at the top, show the header with sliding effect
    document.querySelector(".header").style.top = "0";
  } else {
    // Scrolling down, hide the header with sliding effect
    document.querySelector(".header").style.top = "-100px";
  }

  prevScrollpos = currentScrollPos;
};
