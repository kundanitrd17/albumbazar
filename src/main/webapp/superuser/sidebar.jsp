<aside class="side-nav" id="show-side-navigation1">
    <i class="fa fa-bars close-aside hidden-sm hidden-md hidden-lg" data-close="show-side-navigation1"></i>
    <div class="heading">
        <img src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148907008/medium/1501685726/enhance"
            alt="">
        <div class="info">
            <h3><a href="#">Album Bazar</a></h3>
            <p>Lorem ipsum dolor sit amet consectetur.</p>
        </div>
    </div>
    <div class="search">
        <input type="text" placeholder="Type here"><i class="fa fa-search"></i>
    </div>
    <ul class="categories">
        <li><i class="fa fa-home fa-fw" aria-hidden="true"></i><a href="#"> Branch</a>
            <ul class="side-nav-dropdown">
                <li><a href="/superuser/add-branch">Add Branch</a></li>
                <li><a href="/superuser/list-branch">All Branch</a></li>
            </ul>
        </li>
        <li><i class="fa fa-support fa-fw"></i><a href="#"> Order</a>
            <ul class="side-nav-dropdown">
                <li><a href="/order-list?payment=false">Order Unpaid</a></li>
                <li><a href="/order-list?status=pending">Order Pending</a></li>
                <li><a href="/order-list?status=process">Order Under Process</a></li>
                <li><a href="/order-list">Completed Order</a></li>
            </ul>
        </li>
        <li><i class="fa fa-envelope fa-fw"></i><a href="#"> Employees</a>
            <ul class="side-nav-dropdown">
                <li><a href="/employee/add">Add Employee</a></li>
                <li><a href="/employee">View Employees</a></li>
            </ul>
        </li>
        <li><i class="fa fa-users fa-fw"></i><a href="#"> Our Association</a>
            <ul class="side-nav-dropdown">
                <li><a href="/superuser/add-association">Add Association</a></li>
                <li><a href="/association">Views Association List </a></li>
                <li><a href="/product/add">Add Associated Products </a></li>
                <li><a href="/association/all_association_product_view">View Associated Products </a></li>
            </ul>
        </li>
        <li><i class="fa fa-bolt fa-fw"></i><a href="#"> Transaction</a>
            <ul class="side-nav-dropdown">
                <li><a href="/transaction/income">All Income</a></li>
                <li><a href="/transaction/expense">All Expense</a></li>
                <li><a href="/transaction">All Transaction</a></li>
            </ul>
        </li>
        <li><i class="fa fa-user"></i><a href="#"> Customer</a>
            <ul class="side-nav-dropdown">
                <li><a href="/customer">All Customer</a></li>
                <li><a href="/customer/discounted">Discounted Customer</a></li>
                <li><a href="/customer/blocked">Blocked Customer</a></li>
            </ul>
        </li>
        <p>Example:</p>
        <li><i class="fa fa-envelope-open-o fa-fw"></i><a href="#"> Messages <span class="num dang">56</span></a></li>
        <li><i class="fa fa-wrench fa-fw"></i><a href="#"> Settings <span class="num prim">6</span></a>
            <ul class="side-nav-dropdown">
                <li><a href="#">Lorem ipsum</a></li>
                <li><a href="#">ipsum dolor</a></li>
                <li><a href="#">dolor ipsum</a></li>
                <li><a href="#">amet consectetur</a></li>
                <li><a href="#">ipsum dolor sit</a></li>
            </ul>
        </li>
        <li><i class="fa fa-laptop fa-fw"></i><a href="#"> About UI &amp; UX <span class="num succ">43</span></a></li>
        <li><i class="fa fa-comments-o fa-fw"></i><a href="#"> Something else</a></li>
    </ul>
</aside>
<section id="contents">
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
                                        <i class="fa fa-sign-out"></i><button style="outline: 0mm;">Logout</button>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                            </li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-comments"></i><span>23</span></a></li>
                    <li><a href="#"><i class="fa fa-bell-o"></i><span>98</span></a></li>
                    <li><a href="#"><i data-show="show-side-navigation1" class="fa fa-bars show-side-btn"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>