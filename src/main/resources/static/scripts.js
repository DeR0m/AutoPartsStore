$(function(){
    var equal_height = 0;
    $(".row div").each(function(){
        if ($(this).height() > equal_height) { equal_height = $(this).height(); }
    });
    $(".row div").height(equal_height);
});