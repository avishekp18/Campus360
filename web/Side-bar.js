function toggleSidebar() {
    const sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("active");
}

// Close sidebar when clicking outside or on a link
document.addEventListener("click", function (event) {
    const sidebar = document.getElementById("sidebar");
    const menuButton = document.querySelector(".menu-button");
    if (!sidebar.contains(event.target) && !menuButton.contains(event.target)) {
        sidebar.classList.remove("active");
    }
});

document.addEventListener("DOMContentLoaded", function () {
    // Make a GET request to fetch user info
    fetch('UserInfoServlet')
        .then(response => {
            if (!response.ok) {
                throw new Error("User not logged in");
            }
            return response.json();
        })
        .then(data => {
            // Check if the response data is valid
            if (data && data.userName) {
                // Set the user name in the login button
//                document.getElementById('loginButton').innerText = data.userName;
//                document.getElementById('loginButton').textContent ="<i class='fas fa-user'></i>" +data.userName;
                  document.getElementById('loginButton').innerHTML = `<i class='fas fa-user'></i>${data.userName}`;
//                  document.getElementById('loginButton').innerHTML = `${data.userName} <i class="fas fa-chevron-down"></i>`;
                
                // Optionally, set the user email or other information
                // document.getElementById('user-Name').innerText = data.userEmail;

                // Add an event listener to the login button to redirect when clicked
                document.getElementById('loginButton').addEventListener('click', function () {
//                    window.location.href = 'dashboard.html'; // Redirect to dashboard
                    window.location.href = 'Profile.html'; // Redirect to dashboard
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // Optionally handle the error, e.g., redirect to login page
            // window.location.href = 'log.html';
        });
});
