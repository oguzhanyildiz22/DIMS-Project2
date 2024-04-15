/**
 *
 */

$(document).ready(function() {
    $('.table .btn').on('click', function(event) {
        event.preventDefault();

        let href = $(this).attr('href');

        $.get(href, function(adviser, status) {
            $('#IdEdit').val(adviser.id); // ID alanına ID'yi gizlice yerleştir
            $('#nameEdit').val(adviser.name);
            $('#departmentEdit').val(adviser.department);
        });

        $('#updateAdviserModalCenter').modal('show');
    });
});