function requestQuote (data) {
    localStorage.setItem('query-type', data);
}

function getQuote () {
    localStorage.getItem('query-type');
    console.log("testing the get function");
}

function toggleMenu() {
    var quries = document.getElementById("quries");
    var aboutUs = document.getElementById("about-us-home");
    
    if (localStorage.getItem("toggle") == "true")
    {
        quries.style.display = "block";
        aboutUs.style.display = "block";

        localStorage.removeItem("toggle")
    }
    else
    {
        quries.style.display = "none";
        aboutUs.style.display = "none";

        localStorage.setItem("toggle", "true");
    }

}