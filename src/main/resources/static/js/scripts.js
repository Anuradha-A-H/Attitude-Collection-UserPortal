/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

function addToCart(productId, productName, productPrice, productImage, productQuantity) {
    // Retrieve existing cart items from local storage
//    localStorage.clear();
    var cartItems = JSON.parse(localStorage.getItem('cart')) || [];

    // Add the new product to the cart
    cartItems.push({ id : productId, name: productName, price: productPrice , image: productImage, quantity : productQuantity});

    // Store the updated cart items in local storage
    localStorage.setItem('cart', JSON.stringify(cartItems));

    // Update the cart badge count (optional)
    updateCartBadge(cartItems.length);
}

function updateCartBadge(count) {
    document.querySelector('.cart-badge').innerText = count;
}


function deleteItemFromLocalStorage(itemId) {
    // Retrieve items from localStorage
    var items = JSON.parse(localStorage.getItem('cart')) || [];

    // Find the index of the item with the matching ID
    var index = items.findIndex(function(item) {
        return item.id === itemId;
    });

    // If item found, remove it from the array
    if (index !== -1) {
        items.splice(index, 1);
    }

    // Save the updated array back to localStorage
    localStorage.setItem('cart', JSON.stringify(items));
}


function totalcount(){

var items = JSON.parse(localStorage.getItem('cart')) || [];
var len = items.length;
console.log(len);
document.querySelector('.cart-badge').innerText = len;
}


function redirectToCartList() {
    window.location.href = 'cartlist.html';
}




