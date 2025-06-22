//        // Fetch User Data when the page loads
//        document.addEventListener("DOMContentLoaded", function () {
//            fetchUserInfo();
//        });
//
//        function fetchUserInfo() {
//            fetch('AllAttendanceServlet')  // Make sure the endpoint is correct
//                .then(response => {
//                    if (!response.ok) throw new Error("Failed to fetch user info");
//                    return response.json();
//                })
//                .then(data => {
//                    // Update the profile information with the data from the response
//                    if (data.status === "success") {
//                        document.getElementById('userName').textContent = data.fullName || "No name available";
//                        document.getElementById('userEmail').textContent = data.email || "No email available";
//                        document.getElementById('userGender').textContent = data.gender || "No gender available";
//                        document.getElementById('userDob').textContent = data.dob || "No date of birth available";
//                        document.getElementById('userTelephone').textContent = data.telephone || "No telephone available";
//                    } else {
//                        // If there's an error, display the message from the response
//                        document.getElementById('userName').textContent = data.message || "Error loading profile.";
//                        document.getElementById('userEmail').textContent = "Error loading email.";
//                        document.getElementById('userGender').textContent = "Error loading gender.";
//                        document.getElementById('userDob').textContent = "Error loading date of birth.";
//                        document.getElementById('userTelephone').textContent = "Error loading telephone.";
//                    }
//                })
//                .catch(error => {
//                    console.error("Error:", error.message);
//                    // Handle fetch error
//                    document.getElementById('userName').textContent = "Error fetching user data.";
//                    document.getElementById('userEmail').textContent = "Error fetching user data.";
//                    document.getElementById('userGender').textContent = "Error fetching user data.";
//                    document.getElementById('userDob').textContent = "Error fetching user data.";
//                    document.getElementById('userTelephone').textContent = "Error fetching user data.";
//                });
//        }

document.addEventListener("DOMContentLoaded", function () {
    fetchUserInfo();
});

function fetchUserInfo() {
    fetch('AllAttendanceServlet') // Adjust the endpoint to your backend
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch user info");
            return response.json();
        })
        .then(data => {
            if (data.status === "success") {
                // Update text content
                document.getElementById('userName').textContent = data.fullName || "No name available";
                document.getElementById('userEmail').textContent = data.email || "No email available";
                document.getElementById('userGender').textContent = data.gender || "No gender available";
                document.getElementById('userDob').textContent = data.dob || "No date of birth available";
                document.getElementById('userTelephone').textContent = data.telephone || "No telephone available";

                // Set profile image based on gender
//                const profileImage = document.getElementById('profileImage');
//                if (data.gender === "Male") {
//                    profileImage.src = "https://via.placeholder.com/150?text=Male"; // Example placeholder
//                } else if (data.gender === "Female") {
//                    profileImage.src = "https://via.placeholder.com/150?text=Female"; // Example placeholder
//                } else {
//                    profileImage.src = "https://via.placeholder.com/150?text=Default"; // Default image for unspecified gender
//                }
                    const profileIcon = document.getElementById('profileIcon');

if (data.gender === "Male") {
    profileIcon.className = "fas fa-male fa-6x text-primary"; // Male icon
} else if (data.gender === "Female") {
profileIcon.className = '<img src="asets/woman(1).png" alt="Female Icon">';

} else {
    profileIcon.className = "fas fa-user-circle fa-6x text-secondary"; // Default icon
}


            } else {
                // Handle error response
                document.getElementById('userName').textContent = data.message || "Error loading profile.";
                document.getElementById('userEmail').textContent = "Error loading email.";
                document.getElementById('userGender').textContent = "Error loading gender.";
                document.getElementById('userDob').textContent = "Error loading date of birth.";
                document.getElementById('userTelephone').textContent = "Error loading telephone.";
                document.getElementById('profileImage').src = "https://via.placeholder.com/150?text=Error"; // Error image
            }
        })
        .catch(error => {
            console.error("Error:", error.message);
            // Handle fetch error
            document.getElementById('userName').textContent = "Error fetching user data.";
            document.getElementById('userEmail').textContent = "Error fetching user data.";
            document.getElementById('userGender').textContent = "Error fetching user data.";
            document.getElementById('userDob').textContent = "Error fetching user data.";
            document.getElementById('userTelephone').textContent = "Error fetching user data.";
            document.getElementById('profileImage').src = "https://via.placeholder.com/150?text=Error"; // Error image
        });
}
