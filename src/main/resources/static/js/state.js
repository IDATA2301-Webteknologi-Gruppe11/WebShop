const state = {
    products: []
};

const storedStateString = localStorage.getItem('state');
if (storedStateString) {
    const storedState = JSON.parse(storedStateString);
    state.products = storedState.products;
}

window.state = state;