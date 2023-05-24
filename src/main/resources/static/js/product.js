async function sendProductDataRequest() {
    console.log("Loading product data...");
    const response = await fetch("/api/products/getAll");
    const productJson = await response.json();

    // showProducts(productJson);
}


