$(function () {

    var form = layui.form;
    //常规用法

    $('.add-btn').click(function () {
        $('.addlists').append($('.newadd1').clone().first().addClass());
        form.render();
    });
    $('.addlists').on("click",".btn-del",function () {
        $(this).parent().parent().remove();
    });


});