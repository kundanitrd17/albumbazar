<style>
    .custom-file-input {
        color: transparent;
        width: 45px;
        margin-left: 10px;
    }

    .custom-file-input::-webkit-file-upload-button {
        visibility: hidden;
    }

    .custom-file-input::before {
        content: 'Edit';
        color: black;
        display: inline-block;
        background: -webkit-linear-gradient(top, #f9f9f9, #e3e3e3);
        border: 1px solid #999;
        border-radius: 3px;
        padding: 0px 10px;

        outline: none;
        white-space: nowrap;
        -webkit-user-select: none;
        cursor: pointer;
        text-shadow: 1px 1px #fff;
        font-weight: 700;
        font-size: 10pt;
    }

    .custom-file-input:hover::before {
        border-color: black;
    }

    .custom-file-input:active {
        outline: 0;
    }

    .custom-file-input:active::before {
        background: -webkit-linear-gradient(top, #e3e3e3, #f9f9f9);
    }
</style>

<script>

    function loadProfilePhoto() {
        const xhr = new XMLHttpRequest();

        xhr.open('GET', 'http://localhost:8080/api/secured/association/dp', true);

        xhr.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById('associationProfilePhoto').src = this.response;
            }
        }

        xhr.send(null);
    }

    window.onload = function () {

        loadProfilePhoto();

        document.getElementById('ChangeProfilePhotoInput').addEventListener('change', () => {

            var form = document.getElementById("ChangeProfilePhotoForm");

            var submitBtn = document.createElement("button");
            submitBtn.innerHTML = "Change";
            submitBtn.type = "submit";
            submitBtn.className = "btn btn-success";
            form.appendChild(submitBtn);
            document.getElementById('ChangeProfilePhotoInput').style.display = "none";

        })


    };
</script>
<aside class="side-nav" id="show-side-navigation1">
    <!-- <i class="fa fa-bars close-aside hidden-sm hidden-md hidden-lg" data-close="show-side-navigation1"></i> -->
    <div class="heading row">
        <img id="associationProfilePhoto"
            src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907008/medium/1501685726/enhance"
            alt="" style="height: 100px; width: 100px; object-fit: fill;">

        <div class="info col">
            <h3><a href="/association">Album Bazar</a></h3>
            <p>Lorem ipsum dolor sit amet consectetur.</p>
        </div>

        <form class="col" id="ChangeProfilePhotoForm" action="/association/dp/change" method="POST"
            enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input name="photo" id="ChangeProfilePhotoInput" type="file" class="custom-file-input">
        </form>
    </div>


    <div class="search">
        <input type="text" placeholder="Type here"><i class="fa fa-search"></i>
    </div>

    <ul class="categories">

        <li><i class="fa fa-support fa-fw"></i><a href="#"> Order</a>
            <ul class="side-nav-dropdown">
                <li><a href="/association/order-list/new-arrived-order">Newly Arrived Orders</a></li>
                <li><a href="/association/order-list/processing">Order Under Process</a></li>
                <li><a href="/association/order-list/ready-to-deliver">Ready to Deliver</a></li>
                <li><a href="/association/order-list/completed">Completed Order</a></li>
            </ul>
        </li>

        <p>Example:</p>
        <li><i class="fa fa-envelope-open-o fa-fw"></i><a href="#"> Messages <span class="num dang">56</span></a>
        </li>
        <li><i class="fa fa-wrench fa-fw"></i><a href="#"> Settings <span class="num prim">6</span></a>
            <ul class="side-nav-dropdown">
                <li><a href="#">Lorem ipsum</a></li>
                <li><a href="#">ipsum dolor</a></li>
                <li><a href="#">dolor ipsum</a></li>
                <li><a href="#">amet consectetur</a></li>
                <li><a href="#">ipsum dolor sit</a></li>
            </ul>
        </li>
        <li><i class="fa fa-laptop fa-fw"></i><a href="#"> About UI &amp; UX <span class="num succ">43</span></a>
        </li>
        <li><i class="fa fa-comments-o fa-fw"></i><a href="#"> Something else</a></li>
    </ul>
</aside>