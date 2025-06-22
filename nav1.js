const window ="window.location.href='log.html'"

const nav1 = `<nav class='navbar navbar-expand-lg navbar-light'>
        <div class='container-fluid'>
            <div class='d-flex align-items-center'>
                <button class='btn menu-button' onclick='toggleSidebar()'>
                    <i class='fas fa-bars'></i> <!-- Font Awesome SVG Icon -->
                </button>
                <a href='index.html' class='navbar-brand'>
                    <img src='asets/Campus360 .png' alt='Logo' width='400' height='50'>
                </a>
            </div>
    <div class='collapse navbar-collapse' id='navbarNav'>
        <ul class='navbar-nav ms-auto'>
            <li class='nav-item'><a href='index.html' class='nav-link'>Home</a></li>
            <li class='nav-item dropdown'>
                <a href='#' class='nav-link dropdown-toggle' id='navbarDropdown' role='button'
                    data-bs-toggle='dropdown' aria-expanded='false'>Services</a>
                <ul class='dropdown-menu' aria-labelledby='navbarDropdown'>
                    <li><a class='dropdown-item' href='createAccount.html'>Register Student</a></li>
                    <li><a class='dropdown-item' href='addcourse.html'>Manage Courses</a></li>
                    <li><a class='dropdown-item' href='attandance.html'>Attendance</a></li>
                    <li><a class='dropdown-item' href='thankyou.html'>Grades</a></li>
                    <li><a class='dropdown-item' href='thankyou.html'>Reports</a></li>
                </ul>
            </li>
            <li class='nav-item'><a href='about-us.html' class='nav-link'>About</a></li>

            <li class='nav-item'><a href='contactus.html' class='nav-link'>Contact</a></li>
            <li class='nav-item'>
                <button id='loginButton' class='btn btn-primary' onclick=${window}><i class='fas fa-user'></i>Log In</button>
            </li>
        </ul>
    </div>
</div>
</nav>

<div class='sidebar' id='sidebar'>
        <ul class='list-unstyled'>
            <li><img src='asets/KIIT-Full-Logo-Left.png' alt='Logo' style='width: 225px; height: 28px; margin-bottom: 20px;'></li>
            <li onclick='toggleSidebar()'><a href='#'><i class='fas fa-times'></i> Close</a></li>
            <li><a href='index.html' onclick='toggleSidebar()'><i class='fas fa-home'></i> Home</a></li>
            <li><a href='Features.html' onclick='toggleSidebar()'><i class='fas fa-calendar-check'></i> Attendance</a></li>
            <li><a href='Features.html' onclick='toggleSidebar()'><i class='fas fa-graduation-cap'></i> Grades</a></li>
            <li><a href='Features.html' onclick='toggleSidebar()'><i class='fas fa-chart-bar'></i> Reports</a></li>
            <li><a href='about-us.html' onclick='toggleSidebar()'><i class='fas fa-info-circle'></i> About</a></li>
            <li><a href='contactus.html' onclick='toggleSidebar()'><i class='fas fa-phone-alt'></i> Contact</a></li>
            <li><a href='log.html' onclick='toggleSidebar()'><i class='fas fa-user'></i> Log In</a></li>
        </ul>
    </div>`

export  default nav1 ;
    