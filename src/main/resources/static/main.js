/**
 *
 */

$('document').ready(function () {
    $('.table .btn').on('click',function (event) {

        event.preventDefault();

        let href = $(this).attr('href');

        $.get(href,function (adviser,status) {
            $('#nameEdit').val(adviser.name);
            $('#departmentEdit').val(adviser.department);
        });

        $('#updateAdviserModalCenter').modal();
        
    });
});