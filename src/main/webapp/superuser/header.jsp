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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                        aria-expanded="false">My profile <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="fa fa-user-o fw"></i> My account</a></li>
                        <li><a href="#"><i class="fa fa-envelope-o fw"></i> My inbox</a></li>
                        <li><a href="#"><i class="fa fa-question-circle-o fw"></i> Help</a></li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <form action="/superuser/logout-super" method="POST">
                                <div style="left: 200px;">
                                    <i class="fa fa-sign-out"></i><button class="btn btn-danger " style="outline: 0mm;">Logout</button>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#"><i class="fa fa-comments"></i><span>23</span></a></li>
                <li><a href="#"><i class="fa fa-bell-o"></i><span>98</span></a></li>
                <li><a href="#"><i data-show="show-side-navigation1" class="fa fa-bars show-side-btn"></i></a>
                </li>
            </ul>
        </div>
    </div>
</nav>