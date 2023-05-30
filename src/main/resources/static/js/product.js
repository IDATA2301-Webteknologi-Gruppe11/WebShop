
/**
 * Asynchronously sends a request to retrieve product data.
 * If the product data is already available in the state, it shows the products.
 * Otherwise, it retrieves the product data from the database and updates the state.
 */
async function sendProductDataRequest() {
    console.log("Loading product data...");
        const response = await fetch("/api/product/getAll");
    if (response.ok) {
        const productJson = await response.json();
        showProducts(productJson);
    } else {
        console.error("Failed to retrieve products");
    }
}

/**
 * Asynchronously retrieves all products.
 * @returns {Promise<void>}
 */
async function fetchProductRequest() {
    const response = await fetch("/api/product/getAll");
    if(response.ok) {
        const productJosn = await response.json();
        showProducts(productJosn)
    }
}

/**
 * Asynchronously retrieves products based on the given category.
 *
 * @param {string} category - The category to filter the products.
 */
async function getProductsByCategory(category) {
    const response = await fetch("/api/product/getByCategory/" + category)

    if (response.ok) {
        const products = await response.json();
        showProducts(products);
    } else {
        console.log("error")
    }
}

/**
 * Shows the products on the page.
 *
 * @param {Array} products - The array of products to be displayed.
 */
function showProducts(products) {
    const productCard = document.getElementById("productCard");
    productCard.innerHTML = "";

    products.forEach((product) => {
        const productDiv = document.createElement("div");
        productDiv.className = "index-product-card";

        const productLink = document.createElement("a");
        productLink.className = "index-nav-product";
        productLink.href = "/products/" + product.id;

        const productSection = document.createElement("section");
        productSection.className = "product-card-section";

        const productImage = document.createElement("img");
        productImage.className = "index-product-card-image";
        productImage.src = product.image;
        productImage.alt = product.name;

        productSection.appendChild(productImage);

        if (product.newProduct) {
            const newProductContainer = document.createElement("section");
            newProductContainer.className = "index-product-card-new-container";

            const newProductBox = document.createElement("div");
            newProductBox.className = "index-product-card-new-box";

            const newProductText = document.createElement("p");
            newProductText.textContent = "New";

            newProductBox.appendChild(newProductText);
            newProductContainer.appendChild(newProductBox);
            productSection.appendChild(newProductContainer);
        } else {
            const whiteContainer = document.createElement("div");
            whiteContainer.className = "index-product-card-white-container";

            const whiteContainerText = document.createElement("p");
            whiteContainerText.textContent = "New";

            whiteContainer.appendChild(whiteContainerText);
            productSection.appendChild(whiteContainer);
        }

        const productName = document.createElement("h1");
        productName.className = "index-product-card-name";
        productName.textContent = product.name;

        const productDescription = document.createElement("p");
        productDescription.className = "index-product-card-short-description";
        productDescription.textContent = product.shortDescription;

        const learnMoreFlex = document.createElement("div");
        learnMoreFlex.className = "index-product-card-learn-more-flex";

        const learnMoreText = document.createElement("p");
        learnMoreText.className = "learn-more";
        learnMoreText.textContent = "Learn more";

        const arrowIcon = document.createElement("div");
        arrowIcon.innerHTML = '<i class="fa-solid fa-angle-right"></i>';

        const priceText = document.createElement("p");
        priceText.className = "price";
        priceText.textContent = "Price " + product.price + ",-";

        learnMoreFlex.appendChild(learnMoreText);
        learnMoreFlex.appendChild(arrowIcon);
        learnMoreFlex.appendChild(priceText);

        productSection.appendChild(productName);
        productSection.appendChild(productDescription);
        productSection.appendChild(learnMoreFlex);

        productLink.appendChild(productSection);
        productDiv.appendChild(productLink);
        productCard.appendChild(productDiv);
    });
}