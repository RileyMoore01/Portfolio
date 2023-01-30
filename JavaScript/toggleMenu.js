//This program is a toggle menu function I wrote at work
//Makes a custom API call to store session data
//Uses logic on the local roaming data as well

import { getUrl } from "@pcca/pcca-web-ui";

//----------------------------------------------------------------------
//                Fetch to toggle sever data                          --
//----------------------------------------------------------------------
export function MenuToggledFetch() {
    let apiUrl = getUrl('api/Menu/SetMenuState');

    var data = { MenuIsOpen: false };
    if (window.sessionStorage.getItem('class') === 'menu') {
        data = { MenuIsOpen: false };
    } else {
        data = { MenuIsOpen: true };
    }

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    })
        .then((result) => {
            console.log('Success:', result);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}


//----------------------------------------------------------------------
//                      OnLoad Function Call                          --
//----------------------------------------------------------------------
document.addEventListener("DOMContentLoaded", () => {

    var menuContent = document.getElementById('menu-content');
    var bodyContent = document.getElementById('body-content');
    var headerLogo = document.getElementById('headerLogo');
    var footerBar = document.getElementById('footer-bar');

    if (window.sessionStorage.getItem('class') === 'menu')  //Menu is closed
    {
        headerLogo.classList.add("headerLogo-closed-onload");
        headerLogo.classList.remove("headerLogo-closed");
        headerLogo.classList.remove("headerLogo-open");
        headerLogo.classList.remove("headerLogo-open-onload");

        menuContent.classList.add('menu-content-closed-onload');
        menuContent.classList.remove('menu-content-closed');
        menuContent.classList.remove('menu-content-open');

        bodyContent.classList.add('body-content-closed');
        bodyContent.classList.remove('body-content-open');
        bodyContent.classList.remove('body-content');
        bodyContent.classList.remove('no-menu-body-content');

        footerBar.classList.add('footer-bar-closed');
        footerBar.classList.remove('footer-bar-open');
        footerBar.classList.remove('footer-bar');
    } 
    else    //Menu is open
    {
        headerLogo.classList.add("headerLogo-open-onload");
        headerLogo.classList.remove("headerLogo-closed");
        headerLogo.classList.remove("headerLogo-open");
        headerLogo.classList.remove("headerLogo-closed-onload");

        menuContent.classList.remove('menu-content-closed-onload');
        menuContent.classList.remove('menu-content-open');
        menuContent.classList.remove('menu-content-closed');

        bodyContent.classList.add('body-content-open');
        bodyContent.classList.remove('no-menu-body-content');
        bodyContent.classList.remove('body-content-closed');
        bodyContent.classList.remove('body-content');

        footerbar.classList.add('footer-bar-open');
        footerBar.classList.remove('footer-bar-closed');
        footerBar.classList.remove('footer-bar');
    }
});

//----------------------------------------------------------------------
//              Function to open || close the menu                    --
//----------------------------------------------------------------------
export function toggleMenu() {

    //MenuToggledFetch();

    var menuContent = document.getElementById('menu-content');
    var bodyContent = document.getElementById('body-content');
    var headerLogo = document.getElementById('headerLogo');
    var footerBar = document.getElementById('footer-bar');

    if (window.sessionStorage.getItem('class') === null) {  //Menu is closed
        window.sessionStorage.setItem('class', 'menu');

        headerLogo.classList.remove('headerLogo-closed-onload');
        headerLogo.classList.remove('headerLogo-open-onload');
        headerLogo.classList.remove('headerLogo-open');
        headerLogo.classList.add('headerLogo-closed');

        menuContent.classList.remove('menu-content-open');
        menuContent.classList.remove('menu-content-closed-onload');
        menuContent.classList.add('menu-content-closed');

        bodyContent.classList.remove('body-content');
        bodyContent.classList.remove('body-content-open');
        bodyContent.classList.remove('body-content-closed');
        bodyContent.classList.add('no-menu-body-content');

        footerBar.classList.remove('footer-bar-open');
        footerBar.classList.remove('footer-bar');
        footerBar.classList.add('footer-bar-closed');
    }

    else if (window.sessionStorage.getItem('class') === 'menu') {    //Menu is open
        window.sessionStorage.removeItem('class');

        headerLogo.classList.remove('headerLogo-open-onload');
        headerLogo.classList.remove('headerLogo-closed-onload');
        headerLogo.classList.remove('headerLogo-closed');
        headerLogo.classList.add('headerLogo-open');

        menuContent.classList.remove('menu-content-closed');
        menuContent.classList.remove('menu-content-closed-onload');
        menuContent.classList.add('menu-content-open');
        
        bodyContent.classList.remove('no-menu-body-content');
        bodyContent.classList.remove('body-content-closed');
        bodyContent.classList.remove('body-content-open');
        bodyContent.classList.add('body-content');

        footerBar.classList.remove('footer-bar-closed');
        footerBar.classList.remove('footer-bar');
        footerBar.classList.add('footer-bar-open');
    }
}

// note for furture Riley
//----------------------------------
// Use this trigger menu to check if the button id exisit
// If it does then set the hamburgerMenu icon to the toggleMenu() function

export function TriggerMenu() {
    var hamburgerIcon = document.getElementById('hamburgerIcon');
    if (hamburgerIcon === null) {

    }
    else {
        toggleMenu();
    }
}
