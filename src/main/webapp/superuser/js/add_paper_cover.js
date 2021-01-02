var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

const paper_form_data = {}

const cover_form_data = {}

document.addEventListener('DOMContentLoaded', () => {

    // Setting headers and tokens for csrf token
    header = $("meta[name='_csrf_header']").attr("content");
    token = $("meta[name='_csrf']").attr("content");


});

// Change association id on change of dropdown 
$('#Association').on('change', function () {
    const selectedAssociationId = $("#Association option:selected").val();
    console.log("Selected Option Text: " + selectedAssociationId);
    paper_form_data["associationId"] = selectedAssociationId;
    cover_form_data["associationId"] = selectedAssociationId;
    console.log(selectedAssociationId);
});


// Add paper row
var paper_row = 1;
$(document).on("click", "#addPaper", addNewPaperRow);
function addNewPaperRow() {
    var new_row = '<tr id="paperRow' + paper_row + '"><td><input name="paperQuality' + '" type="text" placeholder="Paper Type" class="form-control" /></td><td><input name="paperSize' + '" type="text" class="form-control" placeholder="Paper Size" /></td><td><input name="paperPrice' + '" type="number" class="form-control" placeholder="Paper Price" /></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

    $('#paperList').append(new_row);
    paper_row++;
    return false;
}

// adding form data on submit paper_form_data
document.getElementById('paperForm').addEventListener('submit', function (e) {
    e.preventDefault();
    console.log("hi");
    if (paper_form_data.associationId == null) {
        alert("select an association");
        return false;
    }


    const paperDetails = [];
    for (let index = 0; index < paper_row; ++index) {
        const paperRow = document.getElementById('paperRow' + index);
        console.log(paperRow);
        const paperQuality = paperRow.querySelector('input[name="paperQuality"]').value;
        const paperSize = paperRow.querySelector('input[name="paperSize"]').value;
        const paperPrice = paperRow.querySelector('input[name="paperPrice"]').value;
        console.log(paperQuality, paperSize, paperPrice);

        const paperDetail = {
            "paperQuality": paperQuality,
            "paperSize": paperSize,
            "paperPrice": paperPrice
        };

        paperDetails.push(paperDetail);

    }

    paper_form_data["paperDetails"] = paperDetails;

    submitPaperForm();


});
// Sending paper data to the server
function submitPaperForm() {
    if (paper_form_data.paperDetails == null || paper_form_data.paperDetails.length < 0) {
        alert("fill info");
        return false;
    }

    console.log(JSON.stringify(paper_form_data));
    console.log("Submitting form");

    // Make API call
    var xhr = new XMLHttpRequest();

    // Replace 1 with current order id
    var url = 'http://localhost:8080/superuser/product/paper/add';
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);
    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            console.log(this.response);
            while (--paper_row >= 0) {
                document.getElementById('paperRow' + paper_row).remove();
            }
            paper_row = 0;
            addNewPaperRow();
        }
    }
    xhr.send(JSON.stringify(paper_form_data));

}

// Add cover row
var cover_row = 1;
$(document).on("click", "#addCover", addNewCoverRow);
function addNewCoverRow() {
    var new_row = '<tr id="coverRow' + cover_row + '"><td><input name="coverName' + '" type="text" placeholder="Cover Type" class="form-control" /></td><td><input name="coverSize' + '" type="text" class="form-control" placeholder="Cover Size" /></td><td><input name="coverPrice' + '" type="number" class="form-control" placeholder="Cover Price" /></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

    $('#coverList').append(new_row);
    cover_row++;
    return false;
}

// Remove criterion
$(document).on("click", ".delete-row", function () {
    //  alert("deleting row#"+row);
    if (cover_row > 1) {
        $(this).closest('tr').remove();
        cover_row--;
    }
    return false;
});


// adding form data on submit paper_form_data
document.getElementById('coverForm').addEventListener('submit', function (e) {
    e.preventDefault();
    if (cover_form_data.associationId == null) {
        alert("select an association");
        return false;
    }



    const coverDetails = [];
    for (let index = 0; index < cover_row; ++index) {
        const coverRow = document.getElementById('coverRow' + index);
        console.log(coverRow);
        const coverName = coverRow.querySelector('input[name="coverName"]').value;
        const coverSize = coverRow.querySelector('input[name="coverSize"]').value;
        const coverPrice = coverRow.querySelector('input[name="coverPrice"]').value;

        const coverDetail = {
            "coverName": coverName,
            "coverSize": coverSize,
            "coverPrice": coverPrice
        };

        coverDetails.push(coverDetail);
    }
    cover_form_data["coverDetails"] = coverDetails;
    submitCoverForm();


});
// Sending paper data to the server
function submitCoverForm() {

    if (cover_form_data.coverDetails == null || cover_form_data.coverDetails.length < 0) {
        alert("fill info");
        return false;
    }

    console.log(JSON.stringify(cover_form_data));
    console.log("Submitting form");

    // Make API call
    var xhr = new XMLHttpRequest();

    // Replace 1 with current order id
    var url = 'http://localhost:8080/superuser/product/cover/add';
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);
    xhr.onreadystatechange = function () { // Call a function when the state changes.
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {

            console.log(this.response);
            while (--cover_row >= 0) {
                document.getElementById('coverRow' + cover_row).remove();
            }
            cover_row = 0;
            addNewCoverRow();
        }
    }
    xhr.send(JSON.stringify(cover_form_data));

}



