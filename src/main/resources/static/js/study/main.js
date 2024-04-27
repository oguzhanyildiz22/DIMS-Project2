/**
 *
 */
$(document).ready(function() {
    $('.update-button').on('click', function(event) {
        event.preventDefault();

        let studyId = $(this).attr('id');

        $.get(`/study/update/${studyId}`, function(study) {
            $('#IdEdit').val(study.id);
            $('#titleEdit').val(study.title);
            $('#descriptionEdit').val(study.description);

            $('#updateStudyModalCenter').modal('show');
        }).fail(function() {
            console.error("Error loading study data");
        });
    });
});

$(document).ready(function() {
    $('#modals-container').load('modals/study/modals-study.html');
});

function Delete(id) {
    console.log(id);
    let ret = confirm(`${id} will be deleted!`);
    if (!ret) return;
    $.ajax({
        url: `/study/delete/${id}`,
        type: "POST",
        success: function() {
            window.location.href = '/study';
        },
        error: function(xhr, status, error) {
            console.log(`ERROR`);
        }
    });
}
