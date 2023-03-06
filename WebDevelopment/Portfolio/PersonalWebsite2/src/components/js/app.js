var slideIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > x.length) {slideIndex = 1}
  x[slideIndex-1].style.display = "block";
  setTimeout(carousel, 2000); // Change image every 2 seconds
}

function linkedinRedirect() {
    window.location.href = "https://www.linkedin.com/in/riley-moore-659bb9169/";
}

function githubRedirect() {
    window.location.href = "https://github.com/RileyMoore01/Portfolio";
}