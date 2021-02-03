<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
        integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
        crossorigin="anonymous" />

    <style type="text/css">
        .profile-pic {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }

        .profile-pic:hover img {
            opacity: 0.5;

        }

        .profile-pic:hover .edit {
            display: block;

        }

        .profile-pic .edit i {
            transform: scale(1.8);
        }

        .edit {
            position: absolute;
            right: 0;
            top: 0;
            display: none;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            text-align: center;
        }

        .edit .icon {
            color: #000;
        }
    </style>
</head>

<body>

    <input id="csrfInput" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Your Orders</h1>


        <div class="profile-pic">
            <img src="./img/carasoul1.jpg" alt="Avatar" class="image"
                style="height: 80px; width: 80px; border-radius: 50%;">

            <div class="edit">
                <div class="icon" title="Edit Profile">
                    <i class="fa fa-camera"></i>
                </div>
            </div>
        </div>

    </div>


    <script>


        document.querySelector('.profile-pic').addEventListener('click', openFileDialog);

        function openFileDialog(accept) {  // this function must be called from  a user
            // activation event (ie an onclick event)

            console.log(accept);
            // Create an input element
            var inputElement = document.createElement("input");
            inputElement.type = "file";
            // inputElement.hidden = true; 
            // document.querySelector(".profile-pic").append(inputElement);
            // Set accept to the file types you want the user to select. 
            // Include both the file extension and the mime type
            inputElement.accept = accept;

            // set onchange event to call selectedFiles when user has selected file
            inputElement.addEventListener("change", () => {
                console.log("selected files to upload");
                // Append the selected files and keep them hidden

                console.log(inputElement);

                const file = inputElement.files[0];

                changeProfilePic(file);

            });

            // dispatch a click event to open the file dialog
            inputElement.dispatchEvent(new MouseEvent("click"));
        }

        function changeProfilePic(photo) {

            //if the file isn't a image nothing happens.
            //you are free to implement a fallback
            if (!photo || !photo.type.match(/image.*/)) return;

            console.log(photo);

            const formData = new FormData();
            formData.append("profile_photo", photo);

            const xhr = new XMLHttpRequest();
            const url = "/test/profile";

            xhr.open('PUT', url, true);

            const csrfEle = document.getElementById("csrfInput");
            console.log(csrfEle.name, csrfEle.value);
            formData.append(csrfEle.name, csrfEle.value);


            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    console.log(this.response);
                }
            }

            xhr.send(formData);


        }
    </script>

</body>


</html>