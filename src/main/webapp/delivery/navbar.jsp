<!-- Navbar Section -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- Image and text -->
    <a class="navbar-brand" href="/delivery">
        <img src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" width="30" height="30"
            class="d-inline-block align-top" alt="">
        AlbumBazaar
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
        aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">

        <ul class="navbar-nav mr-auto">
            <li class="nav-item"></li>
        </ul>

        <ul class="navbar-nav justify-content-end mt-2 mt-lg-0">
            <li class="nav-item mx-2">
                <a class="nav-link active" href="/delivery">Newly Arrived</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link active" href="/delivery/undelivered">Under-Process</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link active" href="/delivery/delivered">Delivered</a>
            </li>
            <li class="nav-item mx-2">
                <form action="/delivery/logout" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button class="btn nav-link active" type="submit">logout</button>
                </form>

            </li>
        </ul>

    </div>
</nav>
<!-- End of Navbar section -->