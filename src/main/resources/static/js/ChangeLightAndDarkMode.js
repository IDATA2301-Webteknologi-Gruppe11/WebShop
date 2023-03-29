function temp() {
    const styleSheet = document.getElementById("style-sheet");
    const image1 = document.getElementById("image7");
    const image2 = document.getElementById("image8");
    const image3 = document.getElementById("image9");
    const image4 = document.getElementById("image10");
    const image5 = document.getElementById("image11");
    const image6 = document.getElementById("image12");
    const imagelogo = document.getElementById("logo");
    if (styleSheet.getAttribute("href") === "../css/Products-Light.css") {
        styleSheet.setAttribute("href", "../css/Products-Dark.css");
        image1.setAttribute("src", "../images/os-icon-removebg-preview-kvit.png");
        image2.setAttribute("src", "../images/os-icon-removebg-preview-kvit.png");
        image3.setAttribute("src", "../images/os-icon-removebg-preview-kvit.png");
        image4.setAttribute("src", "../images/os-icon-removebg-preview-kvit.png");
        image5.setAttribute("src", "../images/os-icon-removebg-preview-kvit.png");
        image6.setAttribute("src", "../images/os-icon-removebg-preview-kvit.png");
        imagelogo.setAttribute("src", "../images/proflexlogo-kvit.png");
    } else {
        styleSheet.setAttribute("href", "../css/Products-Light.css");
        image1.setAttribute("src", "../images/os-icon-removebg-preview.png");
        image2.setAttribute("src", "../images/os-icon-removebg-preview.png");
        image3.setAttribute("src", "../images/os-icon-removebg-preview.png");
        image4.setAttribute("src", "../images/os-icon-removebg-preview.png");
        image5.setAttribute("src", "../images/os-icon-removebg-preview.png");
        image6.setAttribute("src", "../images/os-icon-removebg-preview.png");
        imagelogo.setAttribute("src", "../images/proflexlogo.png");
    }
}