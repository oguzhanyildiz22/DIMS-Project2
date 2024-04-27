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

$(document).ready(function() {
    $('#modals-container').load('modals/adviser/modals-adviser.html');
});

function Delete(id) {
    console.log(id);
    let ret = confirm(`${id} will be deleted!`);
    if (!ret) return;
    $.ajax({
        url: `/adviser/delete/${id}`,
        type: "POST",
        success: function() {
            window.location.href = '/adviser';
        },
        error: function(xhr, status, error) {
            console.log(`ERROR`);
        }
    });
}



