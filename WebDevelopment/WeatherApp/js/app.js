async function getWeatherByZip() {
    let weatherKey = 'fed200574f31448d3c4ef74409fc60bf';
    var zipCode = document.getElementById("zipcode").value;

    if (!zipCode) {  // If the zip code has a falsy value 
        alert("Not a valid zip code");
    } else {  // If the zip code has a truthy value 
        const response = await fetch(
            `https://api.openweathermap.org/data/2.5/weather?zip=${zipCode},us&appid=${weatherKey}&units=imperial`
        )
        const data = await response.json()
        console.log(data)
        moveIndexPage();
    }
}

function moveIndexPage() {
    
    // Get the elements on the page
    var text = document.getElementById("input-page");
    var header = document.getElementById("enter-heading");
    var result = document.getElementById("result-page");

    // Start animation on the properties
    text.style.transform = "translateX(200%)";
    header.style.transform = "translateX(1000px)";
    result.style.display = "block";
    result.style.transform = "translateX(100px)";
}