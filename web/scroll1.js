// // Smooth scrolling for anchor links manually (optional, for specific events)
// document.querySelectorAll('a[href^="#"]').forEach(anchor => {
//     anchor.addEventListener('click', function (e) {
//         e.preventDefault();

//         document.querySelector(this.getAttribute('href')).scrollIntoView({
//             behavior: 'smooth',
//             block: 'start'
//         });
//     });
// });

// // Scroll to Top Button Functionality
// const scrollToTopBtn = document.createElement('button');
// scrollToTopBtn.innerText = "↑";
// scrollToTopBtn.classList.add('scroll-to-top');
// document.body.appendChild(scrollToTopBtn);

// // Show/Hide the Scroll to Top Button based on scroll position
// window.addEventListener('scroll', () => {
//     if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
//         scrollToTopBtn.style.display = "block"; // Make button visible
//     } else {
//         scrollToTopBtn.style.display = "none"; // Hide button
//     }
// });

// // Scroll to the top when the button is clicked
// scrollToTopBtn.addEventListener('click', () => {
//     window.scrollTo({ top: 0, behavior: 'smooth' });
// });

// // Scrollbar Styling for cross-browser compatibility
// const style = document.createElement('style');
// style.innerHTML = `
//   ::-webkit-scrollbar { width: 10px; }
//   ::-webkit-scrollbar-thumb { background: #888; border-radius: 10px; }
//   ::-webkit-scrollbar-thumb:hover { background: #555; }
//   scrollbar-width: thin;
//   scrollbar-color: #888 #f1f1f1;
// `;
// document.head.appendChild(style);
        // Smooth scrolling for anchor links manually (optional, for specific events)
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();

                document.querySelector(this.getAttribute('href')).scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            });
        });

        // Scroll to Top Button Functionality
        const scrollToTopBtn = document.getElementById('scrollToTopBtn');

        // Show/Hide the Scroll to Top Button based on scroll position
        window.addEventListener('scroll', () => {
            if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
                scrollToTopBtn.style.display = "block"; // Make button visible
            } else {
                scrollToTopBtn.style.display = "none"; // Hide button
            }
        });

        // Scroll to the top when the button is clicked
        scrollToTopBtn.addEventListener('click', () => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });

        // Scrollbar Styling for cross-browser compatibility
        const style = document.createElement('style');
        style.innerHTML = `
          ::-webkit-scrollbar { width: 10px; }
          ::-webkit-scrollbar-thumb { background: #888; border-radius: 10px; }
          ::-webkit-scrollbar-thumb:hover { background: #555; }
          scrollbar-width: thin;
          scrollbar-color: #888 #f1f1f1;
        `;
        document.head.appendChild(style);

        // Page Load - Scroll to top and clear hash from URL
        window.onload = function() {
            window.scrollTo(0, 0);
        };

        // Clear the hash if it's present in the URL
        if (window.location.hash) {
            window.location.hash = "";
        }