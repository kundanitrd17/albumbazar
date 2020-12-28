
document.addEventListener('DOMContentLoaded', function () {
    $('.carousel').carousel({
        interval: 5000
    })
});

// State management variables
var allSizes = [];
var allProductCategories = [];
var allCovers = [];
var allPapers = [];

// Select a particular company
$(".associationSelection").click(function () {
    // Clean the table for re-use
    $("#myModal form #albumType").empty()
    $("#myModal form #albumSize").empty()


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

            $("#myModal form #coverPageQuality").empty()
            $("#myModal form #coverPrice").empty()
            allCovers.forEach(element => {
                var node = document.createElement('option');
                var priceNode = document.createElement('option');

                node.value = element["coverName"];
                node.innerHTML = element["coverName"];

                priceNode.value = element["coverPrice"]
                priceNode.innerHTML = element["coverPrice"]

                document.querySelector("#myModal form #coverPageQuality").appendChild(node);
                document.querySelector("#myModal form #coverPrice").appendChild(priceNode);
            });

            $("#myModal form #sheetType").empty()
            allPapers.forEach(element => {
                var node = document.createElement('option');
                node.value = element["paperQuality"];
                node.innerHTML = element["paperQuality"];
                document.querySelector("#myModal form #sheetType").appendChild(node);
            })

        }
    });

})

// Add row
var row = 1;
$(document).on("click", "#add-row", function () {
    console.log(allCovers);
    if (row > 4) {
        return false;
    }
    var new_row = '<tr id="row' + row + '"><td> <select name="sheetType" class="form-control" style="width: 160px;"><option value="">Paper Type</option></select></td><td><input name="sheetRange" value="" type="number" class="form-control input-md"  placeholder=""  style="width: 80px;"  /></td><td><input name="sheetPrice"' + row + '" type="number" class="form-control" placeholder=""  style="width: 80px;" disabled/></td><td><input class="delete-row btn btn-danger" type="button" value="X" /></td></tr>';

    $('#test-body').append(new_row);
    row++;
    return false;
});

// Remove criterion
$(document).on("click", ".delete-row", function () {
    //  alert("deleting row#"+row);
    if (row > 1) {
        $(this).closest('tr').remove();
        row--;
    }
    return false;
});

// copy to clipboard
document.getElementById('ButtonToCopyReferralCode').addEventListener('click', event => {
    let element = document.getElementById("customer_referral_code");
    var range, selection, worked;

    if (document.body.createTextRange) {
        range = document.body.createTextRange();
        range.moveToElementText(element);
        range.select();
    } else if (window.getSelection) {
        selection = window.getSelection();
        range = document.createRange();
        range.selectNodeContents(element);
        selection.removeAllRanges();
        selection.addRange(range);
    }
    document.execCommand('copy');

    console.log("copied");

});



