var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

const paper_form_data = {}

const cover_form_data = {}

document.addEventListener('DOMContentLoaded', () => {

    // Setting headers and tokens for csrf token
    header = $("meta[name='_csrf_header']").attr("content");
    token = $("meta[name='_csrf']").attr("content");

    addNewCoverRow();
    addNewPaperRow();


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
var paper_row = 0;
$(document).on("click", "#addPaper", addNewPaperRow);
function addNewPaperRow() {
    var new_row = `
    <tr id="paperRow${paper_row}">
    <td>
      <input name='paperQuality' value='' type='text' class='form-control' placeholder="Paper Type" />
    </td>
    <td>
      <input name='paperSize' value='' type='text' class='form-control input-md'
        placeholder="Paper Size" />
    </td>

    <td>
      <input name='paperPrice' value='' type='number' class='form-control input-md'
        placeholder="Paper Price" />
    </td>

    <td>
      <input name='GST' value='' type='number' class='form-control input-md' placeholder="GST" />
    </td>

    <td>
      <input class='delete-paper-row btn btn-danger' type='button' value='X' />
    </td>
  </tr>
    `;


    $('#paperList').append(new_row);
    paper_row++;
    return false;
}

// adding form data on submit paper_form_data
document.getElementById('paperForm').addEventListener('submit', function (e) {
    e.preventDefault();

    if (paper_form_data.associationId == null) {
        alert("select an association");
        return false;
    }

    $('.loader').modal('show');

    try {
        const paperDetails = [];
        try {
            for (let index = 0; index < paper_row; ++index) {
                const paperRow = document.getElementById('paperRow' + index);
                console.log(paperRow);
                const paperQuality = paperRow.querySelector('input[name="paperQuality"]').value;
                const paperSize = paperRow.querySelector('input[name="paperSize"]').value;
                const paperPrice = paperRow.querySelector('input[name="paperPrice"]').value;
                const GST = paperRow.querySelector('input[name="GST"]').value;
                console.log(paperQuality, paperSize, paperPrice, GST);

                const paperDetail = {
                    "paperQuality": paperQuality,
                    "paperSize": paperSize,
                    "paperPrice": paperPrice,
                    "GST": GST
                };

                paperDetails.push(paperDetail);

            }

        } catch (error) {
            console.log(error);
        }
        const selectedAssociationId = paper_form_data.associationId;
        paperDetails.forEach(paper =>
            uploadPaperInfo(selectedAssociationId, paper)
        );

        while (--paper_row >= 0) {
            document.getElementById('paperRow' + paper_row).remove();
        }
        paper_row = 0;
        addNewPaperRow();

        // paper_form_data["paperDetails"] = paperDetails;

        // submitPaperForm();
    } catch (e) {
        console.log(e);
        alert("Unable to add papers... Check details...!");
    }



    $('.loader').modal('hide');


});

// Sending paper data to the server

function uploadPaperInfo(associationId, paper) {

    if (paper === null) {
        return false;
    }

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/superuser/product/paper/add";
    xhr.open('POST', url, true)
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.response);

        }
    }

    console.log(paper);
    const paperForm = new FormData();
    paperForm.append("associationId", associationId);
    paperForm.append("paperQuality", paper.paperQuality);
    paperForm.append("paperSize", paper.paperSize);
    paperForm.append("paperPrice", paper.paperPrice);
    // paperForm.append("uploadImageFile", paper.image);
    paperForm.append("GST", paper.GST);

    xhr.send(paperForm);

}



// function submitPaperForm() {
//     if (paper_form_data.paperDetails == null || paper_form_data.paperDetails.length < 0) {
//         alert("fill info");
//         return false;
//     }

//     console.log(JSON.stringify(paper_form_data));
//     console.log("Submitting form");

//     // Make API call
//     var xhr = new XMLHttpRequest();

//     // Replace 1 with current order id
//     var url = 'http://localhost:8080/superuser/product/paper/add';
//     xhr.open("POST", url, true);
//     xhr.setRequestHeader('Content-type', 'application/json');
//     xhr.setRequestHeader(header, token);
//     xhr.onreadystatechange = function () { // Call a function when the state changes.
//         if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
//             console.log(this.response);
//             while (--paper_row >= 0) {
//                 document.getElementById('paperRow' + paper_row).remove();
//             }
//             paper_row = 0;
//             addNewPaperRow();
//         }
//     }
//     console.log(JSON.stringify(paper_form_data));
//     xhr.send(JSON.stringify(paper_form_data));

// }

// Add cover row
var cover_row = 0;
$(document).on("click", "#addCover", addNewCoverRow);
function addNewCoverRow() {
    var new_row = `
        <tr id="coverRow${cover_row}">
            <td>
                <div class="image-upload text-center">
                    <label for="file-input${cover_row}">
                    <img src="https://icon-library.net/images/upload-photo-icon/upload-photo-icon-21.jpg"
                        style=" height: 30px; width: 30px;" />
                    </label>

                    <input id="file-input${cover_row}" name="image" type="file" style="display: none;" />
                </div>
            </td>
            <td>
                <input name='coverName' value='' type='text' class='form-control' placeholder="Cover Type" />
            </td>
            <td>
                <input name='coverSize' value='' type='text' class='form-control input-md'
                    placeholder="Cover Size" />
            </td>

            <td>
                <input name='coverPrice' value='' type='number' class='form-control input-md'
                    placeholder="Cover Price" />
            </td>

            <td>
                <input name='GST' value='' type='number' class='form-control input-md' placeholder="GST" />
            <td>
                <input class='delete-cover-row btn btn-danger' type='button' value='X' />
            </td>
        </tr>
        `;

    $('#coverList').append(new_row);
    cover_row++;
    return false;
}

// Remove criterion
$(document).on("click", ".delete-cover-row", function () {
    //  alert("deleting row#"+row);
    if (cover_row > 1) {
        $(this).closest('tr').remove();
        cover_row--;
    }
    return false;
});
// Remove criterion
$(document).on("click", ".delete-paper-row", function () {
    //  alert("deleting row#"+row);
    if (paper_row > 1) {
        $(this).closest('tr').remove();
        paper_row--;
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
    $('.loader').modal('show');

    try {
        const coverDetails = [];
        for (let index = 0; index < cover_row; ++index) {
            const coverRow = document.getElementById('coverRow' + index);
            // console.log(coverRow);
            const coverName = coverRow.querySelector('input[name="coverName"]').value;
            const coverSize = coverRow.querySelector('input[name="coverSize"]').value;
            const coverPrice = coverRow.querySelector('input[name="coverPrice"]').value;
            const GST = coverRow.querySelector('input[name="GST"]').value;
            const image = coverRow.querySelector('input[name="image"]').files[0];

            const coverDetail = {
                "coverName": coverName,
                "coverSize": coverSize,
                "coverPrice": coverPrice,
                "image": image,
                "GST": GST
            };

            coverDetails.push(coverDetail);
        }
        // cover_form_data["coverDetails"] = coverDetails;
        // submitCoverForm();

        const selectedAssociationId = cover_form_data.associationId;
        coverDetails.forEach(cover => uploadCoverInfo(selectedAssociationId, cover));

        while (--cover_row >= 0) {
            document.getElementById('coverRow' + cover_row).remove();
        }
        cover_row = 0;
        addNewCoverRow();
    } catch (e) {
        alert("check info...!");
    }


    $('.loader').modal('hide');

});


function uploadCoverInfo(associationId, cover) {

    if (cover === null) {
        return false;
    }

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/superuser/product/cover/add";
    xhr.open('POST', url, true)
    xhr.setRequestHeader(header, token);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.response);

        }
    }

    console.log(cover);
    const coverForm = new FormData();
    coverForm.append("associationId", associationId);
    coverForm.append("coverName", cover.coverName);
    coverForm.append("coverSize", cover.coverSize);
    coverForm.append("coverPrice", cover.coverPrice);
    coverForm.append("uploadImageFile", cover.image);
    coverForm.append("GST", cover.GST);

    xhr.send(coverForm);

}


