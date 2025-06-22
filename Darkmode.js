document.addEventListener('DOMContentLoaded', async function () {
    try {
        // Fetch the courses from the servlet
        const response = await fetch('GetCoursesServlet');

        if (!response.ok) {
            throw new Error('Network response was not ok: ' + response.statusText);
        }

        // Parse the JSON response
        const courses = await response.json();

        // Get the table body element to display courses
        const courseItemsDiv = document.getElementById('courseItems');
        courseItemsDiv.innerHTML = ''; // Clear previous content

        // Create table structure if no courses
        const table = document.createElement('table');
        table.classList.add('table', 'table-striped'); // Bootstrap classes for styling
        const thead = document.createElement('thead');
        const tbody = document.createElement('tbody');

        // Check if there are courses
        if (courses.length === 0) {
            courseItemsDiv.innerHTML = '<p>No courses available</p>';
        } else {
            // Add headers
            const headerRow = document.createElement('tr');
            const courseNameHeader = document.createElement('th');
            courseNameHeader.textContent = 'Course Name';
            const instructorNameHeader = document.createElement('th');
            instructorNameHeader.textContent = 'Instructor Name';
            const serialNoHeader = document.createElement('th');
            serialNoHeader.textContent = 'Serial No.';
            headerRow.appendChild(serialNoHeader);
            headerRow.appendChild(courseNameHeader);
            headerRow.appendChild(instructorNameHeader);
            thead.appendChild(headerRow);

            // Loop through the courses and add rows to the table
            courses.forEach((course, index) => {
                const row = document.createElement('tr');

                // Create serial number cell (index + 1 for 1-based numbering)
                const serialNoCell = document.createElement('td');
                serialNoCell.textContent = index + 1;  // Serial number starts from 1
                row.appendChild(serialNoCell);

                // Create course name cell
                const courseNameCell = document.createElement('td');
                courseNameCell.textContent = course.course_name;
                row.appendChild(courseNameCell);

                // Create instructor name cell
                const instructorNameCell = document.createElement('td');
                instructorNameCell.textContent = course.instructor_name;
                row.appendChild(instructorNameCell);

                tbody.appendChild(row);
            });

            // Append the header and body to the table
            table.appendChild(thead);
            table.appendChild(tbody);

            // Add the table to the container
            courseItemsDiv.appendChild(table);
        }
    } catch (error) {
        console.error('Error fetching courses:', error);
        const courseItemsDiv = document.getElementById('courseItems');
        courseItemsDiv.innerHTML = '<p>Error fetching courses. Please try again later.</p>';
    }
});

// Toggle the visibility of the courses list
document.getElementById('toggleCoursesBtn').addEventListener('click', function () {
    const courseItemsDiv = document.getElementById('courseItems');
    
    // Check if the course list is currently visible or hidden
    if (courseItemsDiv.style.display === 'none' || courseItemsDiv.style.display === '') {
        // Show the course list
        courseItemsDiv.style.display = 'block';
        this.textContent = 'Hide Courses'; // Change button text to "Hide Courses"
    } else {
        // Hide the course list
        courseItemsDiv.style.display = 'none';
        this.textContent = 'Show Courses'; // Change button text to "Show Courses"
    }
});

