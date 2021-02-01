<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <i class="fa fa-align-right"></i>
            </button>
            <a class="navbar-brand" href="#">my<span class="main-color">Dashboard</span></a>
        </div>
        <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <form action="/superuser/logout-super" method="POST">
                        <div style="left: 200px;">
                            <button class="btn  "
                                style="outline: 0mm; margin-top: 10px; margin-right: 15px;">Logout</button>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </li>
                <li><a href="#"><i data-show="show-side-navigation1" class="fa fa-bars show-side-btn"
                            style="margin-top: 15px; "></i></a>
                </li>
            </ul>
        </div>
    </div>
</nav>