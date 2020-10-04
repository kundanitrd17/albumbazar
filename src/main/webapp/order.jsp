<!DOCTYPE html>
<html lang="en">

<head>


  <title>Album Bazaar</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <style type="">
    html{
  font-family: 'open sans';
}
h1{
  margin-top: 30px;
  margin-bottom: 15px;
}

.fancy-cards{
  text-align: center;
  margin: 20px auto;
}
.fancy-cards .fancy-card{
  display: inline-block;
  position: relative;
  top: 40px;
  left: 20px;
  box-sizing: border-box;
}

.fancy-card .top,
.fancy-card .middle,
.fancy-card .bottom{
  position: relative;
  border-radius: 3px;
  overflow: hidden;
  width: 200px;
  height: 250px;
  transition: transform 300ms linear-out;
}
.fancy-card .top{
  z-index: 3;
  transform: scale(1.0);
  transition: transform 300ms cubic-bezier(0.22, 0.61, 0.36, 1);
 
  background-size: cover;
  background-position: center;
  box-shadow: 0px 1px 3px rgba(25,25,25,0.30);
}
.fancy-card .middle{
  position: absolute;
  background: #aaa;
  top: 0px;
  z-index: 2;
  transform: rotate(0deg);
  transition: transform 250ms cubic-bezier(0.68, -0.55, 0.27, 1.55);
}
.fancy-card .bottom{
  position: absolute;
  background: #ccc;
  top: 0px;
  z-index: 1;
  transform: rotate(0deg);
  transition: transform 250ms cubic-bezier(0.68, -0.55, 0.27, 1.55);
}

.fancy-card .caption{
  overflow: hidden;
  background: rgba(255,255,255,0.75);
  padding: 15px 10px;
  position: absolute;
  bottom: 0px;
  left: 0px;
  width: 100%;
}
.fancy-card .caption .title{
  color: #222;
  margin: 0px 0px 15px 0px;
  font-size: 1.4rem;
}
.fancy-card .caption .button{
  display: inline-block;
  color: #333;
  text-decoration: none;
  border: solid 1px #555;
  padding: 7px 13px;
  background-color: transparent;
  transition: all 300ms ease-in;
}


/*hovering*/
.fancy-card:hover .top{
  transform: scale(1.05);
}
.fancy-card:hover .middle{
 transform: rotate(-7deg);
 box-shadow: 1px 1px 2px rgba(74, 74, 74, 0.35);
}
.fancy-card:hover .bottom{
  transform: rotate(7deg);
  box-shadow: 1px 1px 2px rgba(113, 113, 113, 0.35);
}

.fancy-card:hover .button{
  background: rgba(0,0,0,0.8);
  color: #fff;
  border: 0px;
}

input
{
  max-width: 200px;
}
table tr th
{
  text-align: center;
}

table tr th h6
{

font-size: 13px; 
}

 </style>
</head>

<body>

  <div class="container">
    <div class="row">

      <div class="col-md-4 col-lg-4 col-xl-4">
        <div class="fancy-cards">
          <!-- <h1>Microinteraction on active</h1>
  <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
          <div class="fancy-card">
            <div class="top" id="t">
              <div class="caption">
                <h3 class="title">Company Name</h3>
                <input type="text" name="associationId" value="1" hidden>
                <a type="button" id="" class="btn btn-primary button associationSelection" data-toggle="modal"
                  data-target="#myModal">
                  Select Company
                </a>
              </div>
            </div>
            <div class="middle" id="m"></div>
            <div class="bottom" id="b"></div>
          </div>
        </div>
      </div>


      <div class="col-md-4 col-lg-4 col-xl-4">
        <div class="fancy-cards">
          <!-- <h1>Microinteraction on active</h1>
  <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
          <div class="fancy-card">
            <div class="top" id="t">
              <div class="caption">
                <h3 class="title">accha Name</h3>
                <input type="text" name="associationId" value="2" hidden>
                <a type="button" id="" class="btn btn-primary button associationSelection" data-toggle="modal"
                  data-target="#myModal">
                  Select Company
                </a>
              </div>
            </div>
            <div class="middle" id="m"></div>
            <div class="bottom" id="b"></div>
          </div>
        </div>
      </div>


      <div class="col-md-4 col-lg-4 col-xl-4">
        <div class="fancy-cards">
          <!-- <h1>Microinteraction on active</h1>
  <p>It triggers a subtle micro-interaction (scales and adjusts) when the user interacts with it.</p>-->
          <div class="fancy-card">
            <div class="top" id="t">
              <div class="caption">
                <h3 class="title">Company Name</h3>
                <input type="text" name="associationId" value="3" hidden>
                <a type="button" id="associationSelection" class="btn btn-primary button associationSelection"
                  data-toggle="modal" data-target="#myModal">
                  Select Company
                </a>
              </div>
            </div>
            <div class="middle" id="m"></div>
            <div class="bottom" id="b"></div>
          </div>
        </div>
      </div>

    </div>
    <!-- Button to Open the Modal -->




    <!-- The Modal -->
    <div class="modal fade" id="myModal">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">

          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">Company Name</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">

            <form class="" action="order" method="POST">
              <input type="text" name="selectedAssociation" id="selectedAssociationId">
              <h4>Select Album</h4>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <div class="container">
                <div class="row">

                  <select name="albumType" id="albumType" class="form-control col-md-4 col-xl-4 col-lg-4">
                  </select>

                  <select id="albumSize" name="albumSize" class="form-control col-md-4 col-xl-4 col-lg-4">

                  </select>
                </div>
                <hr>
                <h4>Cover Page</h4>
                <div class="row">


                  <select name="pageQuality" id="coverPageQuality" class="form-control col-md-4 col-xl-4 col-lg-4">
                    <option value="gold">gold</option>
                  </select>

                  <select name="pagePrice" id="coverPrice" class="form-control col-4">
                    <option value="">Price</option>
                  </select>
                </div>

                <hr>
                <h4>Photo Pages</h4>
                <div class="row table-responsive">

                  <table>
                    <tr>
                      <th>
                        <h6>Type</h6>
                      </th>

                      <th>
                        <h6> sheet</h6>
                      </th>
                      <th>
                        <h6>Price</h6>
                      </th>

                    </tr>
                    <tbody id="test-body">
                      <tr id="row0">
                        <td>
                          <select id="sheetType" name="sheetType" class="form-control " style="width: 160px;">
                            <option value="paper type1">Paper Type</option>
                          </select>
                        </td>

                        <td>
                          <input name="sheetRange" id="pr" type="number" class="form-control input-md" placeholder=""
                            style="width: 80px;" />
                        </td>

                        <td>
                          <input name="sheetPrice" type="number" class="form-control input-md" placeholder=""
                            style="width: 80px;" disabled />
                        </td>
                        <td><input id="add-row" class="btn btn-primary" type="button" value="+" /></td>
                      </tr>



                    </tbody>
                    <tfoot>
                      <tr>
                        <td colspan="2">
                          <input type="file" name="" value="" class="btn btn-warning" style="margin-top:20px; ">
                        </td>
                      </tr>

                      <tr>
                        <td colspan="4">
                          <textarea cols="60" rows="5" placeholder="description"></textarea>
                        </td>
                      </tr>
                    </tfoot>
                  </table>
                </div>

              </div>


          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button type="submit" class="btn btn-success">submit</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

          </div>
          </form>
        </div>
      </div>
    </div>

  </div>

  <script type="text/javascript">

    $(document).ready(function () {
      $(".fancy-card #t").css("background-image", "url('https://static-1.gumroad.com/res/gumroad/assets/collections/food_and_cooking_thumb-34fb9ef316a7b01177529839891c3a03.jpg')");
    });
  </script>

  <script type="text/javascript">

    // State management variables
    var allSizes = [];
    var allProductCategories = [];
    var allCovers = [];
    var allPapers = [];

    // Select a particular company
    $(".associationSelection").click(function () {

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

          $("#myModal form #albumType").empty()
          allProductCategories.forEach(element => {
            var node = document.createElement('option');
            node.value = element;
            node.innerHTML = element;
            document.querySelector("#myModal form #albumType").appendChild(node);
          });

          $("#myModal form #albumSize").empty()
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





  </script>

</body>

</html>