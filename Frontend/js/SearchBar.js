$(document).ready(function() {
    $("#search-icon").click(function() {
        $("#search-form").toggle();
    });

    $("#search-form").submit(function(event) {
        event.preventDefault();
        const searchQuery = $("#search-input").val();
        // Perform the search action with the searchQuery
    });
});