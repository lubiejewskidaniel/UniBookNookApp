<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>

<%
    // Retrieve the logged-in user from the session
    User admin = (User) session.getAttribute("user");

    // Check if the user is an admin; if not, redirect to the login page
    if (admin == null || !"ADMIN".equalsIgnoreCase(admin.getRole())) {
        response.sendRedirect("LoginServlet");
        return;
    }

    // Get the admin's username to display in the dashboard
    String adminName = admin.getUsername();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/BookNook/css/adminDashboard.css">
</head>

<body>
    <div class="container mt-5">
        <h1 class="text-center">Admin Dashboard</h1>
        
        <!-- Navigation bar -->
        <nav class="nav nav-pills nav-justified mt-4">
            <a class="nav-link active disabled" href="#" data-page="AdminDashboardServlet">Dashboard</a>
            <a class="nav-link" href="#" data-page="ViewBooksAccessoriesServlet">Add Books & Accessories</a>
            <a class="nav-link" href="#" data-page="ViewUsersServlet">View Users</a>
            <a class="nav-link" href="AdminOrdersServlet">View All Orders</a>
            <a class="nav-link text-danger" href="LogoutServlet">Logout</a>
        </nav>

        <!-- Admin greeting message -->
        <div class="mt-4">
            <h3>Welcome, <%= adminName %>!</h3>
            <p>Select an option from the navigation above to manage the system.</p>
        </div>

        <!-- Container for dynamically loaded content -->
        <div id="content-section" class="mt-4">
            <p></p>
        </div>
    </div>

<!-- jQuery for handling AJAX requests -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>       
<script>
document.addEventListener("DOMContentLoaded", function () {
    const navLinks = document.querySelectorAll('.nav-link'); // Select all navigation links
    const navBar = document.querySelector('.nav-pills'); // Select navigation container
    const contentSection = document.getElementById('content-section'); // Select content section where AJAX content will be loaded
    let activeLink = document.querySelector('.nav-link.active'); // Get currently active navigation link

    // Function to highlight the active navigation link
    function updateHighlight(target) {
        navBar.style.setProperty('--selected-width', target.offsetWidth + 'px'); // Update width of the active highlight
        navBar.style.setProperty('--selected-left', target.offsetLeft + 'px'); // Update position of the active highlight
    }

    // Initialize navigation highlighting when the page loads
    if (activeLink) {
        updateHighlight(activeLink);
    }

    // Handle navigation clicks
    navLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();  // Prevent default navigation behavior

            // Remove active class from all links and add it to the clicked one
            navLinks.forEach(item => item.classList.remove('active'));
            link.classList.add('active');
            updateHighlight(link);

            // Get the page URL from data attribute
            const page = link.getAttribute('data-page');
            if (page) {
                // Fetch the page content via AJAX
                fetch(page)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Failed to load page'); // Handle error if response is not ok
                        }
                        return response.text();
                    })
                    .then(html => {
                        contentSection.innerHTML = html; // Load received HTML content into the content section
                        initEditUser(); // Initialize edit user functionality after content is loaded
                    })
                    .catch(error => {
                        contentSection.innerHTML = '<p class="text-danger">Error loading content.</p>'; // Show error message in case of failure
                    });
            } else {
                window.location.href = link.href; // Redirect for non-AJAX links
            }
        });
    });

    // Function to handle user edit functionality after page loads
    function initEditUser() {
        console.log("Edit user script initialized.");

        // Handle edit button click event
        $(document).on('click', '.edit-btn', function(event) {
            event.preventDefault(); // Prevent default link behavior
            let userId = $(this).data('id'); // Get user ID from data attribute
            console.log("Clicked Edit for User ID:", userId);

            // Toggle the edit form section visibility
            if ($('#editSection').is(':visible')) {
                $('#editSection').slideUp(); // Hide section if already visible
            } else {
                loadEditForm(userId); // Load the edit form dynamically
            }
        });

        // Function to load the user edit form dynamically via AJAX
        function loadEditForm(userId) {
            $.ajax({
                url: '<%= request.getContextPath() %>/EditUserServlet', // Servlet handling the request
                type: 'GET',
                data: { id: userId }, // Pass user ID as parameter
                beforeSend: function() {
                    $('#editSection').html('<p>Loading...</p>').slideDown(); // Show loading message while fetching data
                },
                success: function(response) {
                    if (response.trim().length > 0) {
                        $('#editSection').html(response).slideDown(); // Display the received form

                        // Scroll smoothly to the edit form section
                        $('html, body').animate({
                            scrollTop: $("#editSection").offset().top
                        }, 500);
                    } else {
                        console.error("Empty response received.");
                        alert('Failed to load the edit form. Empty response.');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("AJAX Error:", error);
                    alert('Error loading form: ' + xhr.status + ' ' + xhr.statusText); // Show alert in case of an error
                }
            });
        }

        // Close edit form when the close button is clicked
        $(document).on('click', '#closeEditSection', function() {
            $('#editSection').slideUp(); // Hide edit section when close button is clicked
        });
    }

    // Initialize the edit user functionality when the page loads
    initEditUser();
});
</script>
</body>
</html>