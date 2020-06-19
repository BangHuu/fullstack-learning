$(document).ready(function () {
    $('#product-feature').owlCarousel({
        loop:true, // chạy l
        // margin:10, // khoảng cách 2 sp
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
                nav:false
            },
            200:{
                items:3,
                nav:false
            }
        }
    })
});
var buttons = document.getElementsByClassName('tablinks');
var contents = document.getElementsByClassName('tabcontent');
function showContent(id){
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    var content = document.getElementById(id);
    content.style.display = 'block';
}
for (var i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener("click", function(){
        var id = this.textContent;
        for (var i = 0; i < buttons.length; i++) {
            buttons[i].classList.remove("active");
        }
        this.className += " active";
        showContent(id);
    });
}
showContent('Bánh');