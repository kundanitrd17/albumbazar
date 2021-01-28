
// Check auth to google
function checkGoogleAuth() {

    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/secured/branch/is-google-auth-allowed";

    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("fine");
            console.log(this);
            // $('.fancy-cards .fancy-card a .associationSelection').remove();
        } else if (this.readyState === 4 && this.status === 403) {
            console.log("Not fine");
            var selectBtn = document.querySelectorAll('.associationSelection');
            selectBtn.forEach(ele => {
                ele.remove();
            });

            document.querySelectorAll('.caption').forEach(parent => {
                console.log("adding");
                var signinBtn = document.createElement('a');
                // signinBtn.target = "_blank";
                signinBtn.type = "button";
                signinBtn.className = "btn btn-info button";
                signinBtn.href = "/googlesignin";
                signinBtn.innerText = "SignIn";

                parent.append(signinBtn);
                console.log(parent);
            })
        }
    }

    xhr.send(null);

}


// Controlling orders 
// State management variables
var allSizes = [];
var allProductCategories = [];
var allCovers = [];
var allPapers = [];

var selectedProductSize = null;
var availablePapers = [];
var availableCovers = []




document.addEventListener('DOMContentLoaded', function () {

    loadAssociationPriceView();

    checkGoogleAuth();



    // Select a particular company
    $(".associationSelection").click(function () {

        selectedProductSize = null;
        availablePapers = [];
        availableCovers = [];



        // Clean the table for re-use
        $("#myModal form #albumType").empty()
        $("#myModal form #albumSize").empty()
        $("#myModal form #coverPageQuality").empty()
        $("#myModal form #coverPrice").val(null)
        $("#myModal form #test-body").empty()



        var companyId = $(this).siblings("input").val();
        var companyName = $(this).siblings(".title").text();
        $("#myModal .modal-header .modal-title").text(companyName);
        $("#myModal form #selectedAssociationId").val(companyId);

        const URI = "http://localhost:8080/api/product/company/" + companyId;

        $.ajax({
            url: URI,
            success: function (result) {
                console.log(result);
                allSizes = result["sizes"]
                allProductCategories = result["productCategories"];
                allCovers = result["covers"];
                allPapers = result["papers"];

                availableCovers = [];

                availablePapers = [];


                allProductCategories.forEach(element => {
                    var node = document.createElement('option');
                    node.value = element;
                    node.innerHTML = element;
                    document.querySelector("#myModal form #albumType").appendChild(node);
                });


                allSizes.forEach(element => {
                    var node = document.createElement('option');
                    node.value = element;
                    node.innerHTML = element;
                    document.querySelector("#myModal form #albumSize").appendChild(node);
                });

                selectedProductSize = $("#myModal form #albumSize option:selected").val();
                console.log(selectedProductSize);

                paperRow = 0;
                changeCoverAndPaperOptions(selectedProductSize);

            }
        });

    })



    $('#albumSize').on('change', () => {
        const selectedSize = $("#albumSize option:selected").val();
        console.log("Selected Option Text: " + selectedSize);
        selectedProductSize = selectedSize;
        changeCoverAndPaperOptions(selectedProductSize);
    });


    $("#myModal form #coverPageQuality").on('change', setCoverPriceInInput);

});




function changeCoverAndPaperOptions(selectedProductSize) {
    availableCovers = [];
    allCovers.forEach(cover => {
        if (cover["coverSize"] === selectedProductSize) {
            const data = { "id": cover["id"], "name": cover["coverName"], "price": cover["coverPrice"] };
            availableCovers.push(data);
        }
    });

    availablePapers = [];
    allPapers.forEach(paper => {
        if (paper["paperSize"] === selectedProductSize) {
            const data = { "id": paper["id"], "name": paper["paperQuality"], "price": paper["paperPrice"] };
            availablePapers.push(data);
        }
    });

    addCoverOptions();

    while (paperRow-- > 0) {
        document.getElementById('PaperRow' + paperRow).remove();
    }
    paperRow = 0;
    appendPaperRow();
}



function setCoverPriceInInput() {
    const id = $("#myModal form #coverPageQuality option:selected").val();
    console.log(id);

    availableCovers.forEach(cover => {
        // console.log(parseInt(cover["id"]), parseInt(id));
        if (parseInt(cover["id"]) === parseInt(id)) {
            $("#myModal form #coverPrice").val(cover["price"]);
        }
    })

}

function addCoverOptions() {
    $("#myModal form #coverPageQuality").empty()
    // $("#myModal form #coverPrice").empty()
    availableCovers.forEach(cover => {
        // console.log(cover);
        var node = document.createElement('option');
        // var priceNode = document.createElement('option');

        node.value = cover["id"];
        node.innerHTML = cover["name"];

        // priceNode.innerHTML = cover["price"]

        document.querySelector("#myModal form #coverPageQuality").appendChild(node);
        // document.querySelector("#myModal form #coverPrice").appendChild(priceNode);
    });

    setCoverPriceInInput();

}

// $('#myModal form .sheet-paper-type').on('change', setPaperPriceOnChange);

function setPaperPriceOnChange(id) {
    console.log(id);
    const paperId = $("#myModal form #sheetType" + id + " option:selected").val();
    console.log(paperId);

    availablePapers.forEach(paper => {
        if (parseInt(paper["id"]) === parseInt(paperId)) {
            console.log(paper);
            const sheetPriceInput = document.querySelector("#PaperRow" + id + " td input[name='sheetPrice']");
            sheetPriceInput.value = paper["price"];
        }
    })

}


function addPaperOptions() {
    $('#sheetType' + (paperRow - 1)).empty();
    const row = document.getElementById('sheetType' + (paperRow - 1));


    availablePapers.forEach(paper => {
        // console.log(paper);
        var option = document.createElement('option');
        option.value = paper["id"];
        option.innerHTML = paper["name"];
        row.append(option);
    })

    setPaperPriceOnChange((paperRow - 1));
}

// Add row
var paperRow = 0;
$(document).on("click", "#add-row", appendPaperRow);

function appendPaperRow() {
    if (paperRow > 4) {
        return false;
    }

    var new_row = '<tr id="PaperRow' + paperRow + '"><td> <select onchange="setPaperPriceOnChange(' + paperRow + ')" class="form-control" name="paperId" id="sheetType' + paperRow + '"  style="width: 160px;"><option value="">Paper Type</option></select></td><td><input name="numberOfSheet" value="" type="number" class="form-control input-md"  placeholder=""  style="width: 80px;" required /></td><td><input name="sheetPrice" type="number" class="form-control" placeholder=""  style="width: 80px;" disabled/></td><td><input id="add-row" class="btn btn-primary" type="button" value="+" /></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

    $('#test-body').append(new_row);

    ++paperRow;
    addPaperOptions();
    return false;
}

// Remove criterion
$(document).on("click", ".delete-row", function () {
    //  alert("deleting row#"+row);
    if (paperRow > 1) {
        $(this).closest('tr').remove();
        paperRow--;
    }
    return false;
});










// Loading menu



// Price view
function loadAssociationPriceView() {
    // Select a particular company
    $(".associationViewPrice").click(function () {
        $("#paperDetails tbody").empty()
        $("#coverDetails tbody").empty()
        // $('#largeModal').modal('show');

        var associationId = $(this).siblings("input").val();
        var associationName = $(this).siblings(".title").text();
        console.log(associationId, associationName);
        $("#largeModal #myModalLabel").text(associationName);
        $('#largeModal #selected_association_id').val(associationId);


        const URI = "http://localhost:8080/api/product/company/" + associationId;

        $.ajax({
            url: URI,
            success: function (result) {
                console.log(result);
                allProductCategories = result["productCategories"];
                allCovers = result["covers"];
                allPapers = result["papers"];

                console.log(allCovers, allPapers, allProductCategories);

                // fill-in cover table 
                var coverTable = document.querySelector("#coverDetails tbody");
                allCovers.forEach(element => {
                    var row = document.createElement('tr');
                    var coverName = document.createElement('td');
                    coverName.innerHTML = element.coverName;

                    var coverSize = document.createElement('td');
                    coverSize.innerHTML = element.coverSize;

                    var coverPrice = document.createElement('td');
                    coverPrice.innerHTML = element.coverPrice;

                    // <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;" class="fa fa-trash"></i></td>


                    row.appendChild(coverName);
                    row.appendChild(coverSize);
                    row.appendChild(coverPrice);

                    coverTable.appendChild(row);

                    // coverTable.append('<tr><td>' + element.coverName + '</td><td>' + element.coverSize + '</td><td>' + element.coverPrice + '</td> </tr>');


                });

                // fill-in Paper Table
                var paperTable = document.querySelector("#paperDetails tbody");

                allPapers.forEach(element => {

                    var row = document.createElement('tr');
                    var paperQuality = document.createElement('td');
                    paperQuality.innerHTML = element.paperQuality;

                    var paperSize = document.createElement('td');
                    paperSize.innerHTML = element.paperSize;

                    var paperPrice = document.createElement('td');
                    paperPrice.innerHTML = element.paperPrice;

                    // <td><i style="color: rgb(236, 34, 34); font-size: 20px; cursor: pointer;" class="fa fa-trash"></i></td>



                    row.appendChild(paperQuality);
                    row.appendChild(paperSize);
                    row.appendChild(paperPrice);

                    paperTable.appendChild(row);


                });

            }
        });

    })
}