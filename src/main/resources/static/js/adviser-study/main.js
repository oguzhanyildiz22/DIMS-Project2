/**
 *
 */

$(document).on("click", ".delete-button", function () {
    let adviserStudyId = $(this).attr('id');
    $("#deleteIdEdit").val(adviserStudyId);

    let adviserName = $(this).closest('tr').find('td:nth-child(1)').text();
    let titleName = $(this).closest('tr').find('td:nth-child(4)').text();
    $("#deleteNameEdit").val(adviserName);
    $("#deleteTitleEdit").val(titleName);

});

$(document).ready(function() {
    $('#modals-container').load('modals/adviser-study/modals-adviser-study.html');
});