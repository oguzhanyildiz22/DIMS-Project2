/**
 *
 */

$(document).ready(function() {
    $('.update-button').on('click', function(event) {
        event.preventDefault();

        let adviserId = $(this).attr('id');

        $.get(`/adviser/update/${adviserId}`, function(adviser) {
            $('#IdEdit').val(adviser.id);
            $('#nameEdit').val(adviser.name);
            $('#departmentEdit').val(adviser.department);

            $('#updateAdviserModalCenter').modal('show');
        }).fail(function() {
            console.error("Error loading adviser data");
        });
    });
});



