<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/stack.png">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> HeapOverflow </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.likePost .fa-thumbs-up {
    transition: transform 0.3s, color 0.3s;
}

.likePost.liked .fa-thumbs-up {
    transform: scale(1.5);
    color: red;
}

.commentBox {
    margin-top: 10px;
    margin-bottom: 10px;
}

.submitComment {
    background-color: #4CAF50; /* Green background */
    color: white; /* White text color */
    border: none; /* Remove borders */
    padding: 10px 20px; /* Padding */
    text-align: center; /* Center text */
    text-decoration: none; /* Remove underline */
    display: inline-block; /* Display as block */
    font-size: 16px; /* Increase font size */
    margin: 4px 2px; /* Add margin */
    cursor: pointer; /* Pointer cursor on hover */
    border-radius: 4px; /* Rounded corners */
}

.submitComment:hover {
    background-color: #45a049; /* Darker green on hover */
}

.submitComment:focus {
    outline: none; /* Remove outline on focus */
}

.submitComment .fa {
    margin-right: 5px; /* Margin for icon */
}
</style>

<script type="text/javascript">
$(document).ready(function(){
    $.ajaxSetup({ cache: false }); // Avoids caching!
    
    $(document).on("click",".menu", function(event) {
        $('#content').load($(this).attr('id'));
        event.preventDefault();
    });
    
    $(document).on("submit","form", function(event) {
        $('#content').load($(this).attr('action'),$(this).serialize());
        event.preventDefault();
    });
    
    /* Like post */
	$(document).on("click", ".likePost", function(event){
	    var likeButton = $(this);
	    var post = likeButton.closest('.w3-container'); // Find the closest ancestor div with class w3-container
	    var postId = post.attr("id"); // Retrieve postId from the id attribute of the post div
	    $.post("LikePost", { id: postId }, function(response) {
	        $("#content").load("ViewFeedLogged.jsp");
	        likeButton.addClass("liked");
	        setTimeout(function() {
	            likeButton.removeClass("liked");
	        }, 300);
	    });
	    event.preventDefault();
	});

    /* Comment on post */
/* Comment on post */
$(document).on("click", ".commentPost", function(event) {
    var commentButton = $(this);
    var tweet = commentButton.closest('.w3-card');
    
    if (tweet.find(".commentBox").length === 0) {
        var commentBox = $("<textarea>")
            .addClass("commentBox w3-input w3-border w3-margin-bottom")
            .attr("placeholder", "Write your comment...")
            .css({
                "height": "100px",   // Fixed height
                "resize": "none"     // Disable resizing
            });
        
        var submitButton = $("<button>")
            .addClass("submitComment w3-button w3-small w3-theme-d1")
            .html('<i class="fa fa-paper-plane"></i> Submit');
        
        tweet.append(commentBox).append(submitButton);
    } else {
        tweet.find(".commentBox, .submitComment").toggle();
    }
    
    event.preventDefault();
});

	    
	    /* Handle comment submission */
	    $(document).on("click",".submitComment",function(event){
	        var submitButton = $(this);
	        var tweet = submitButton.closest('.w3-card');
	        var commentBox = tweet.find(".commentBox");
	        var comment = commentBox.val();
	        
	        if(comment.trim() !== "") {
	            $.post("CommentPost", { id: tweet.attr("id"), comment: comment }, function(response) {
	                commentBox.val('');
	                $("#content").load("ViewFeedLogged.jsp");
	            });
	        }
	        
	        event.preventDefault();
	    });
	    
	    
	    /* Delete post */
		$(document).on("click", ".delPost", function(event){
		    var tweet = $(this).closest('.w3-container'); // Find the closest container with post data
		    var postId = tweet.attr("id"); // Get the post ID from the container
		    
		    // Perform post deletion
		    $.post("DelPost", { id: postId }, function(response) {
		        $("#content").load("ViewOwnFeed.jsp"); // Reload timeline after deletion
		    });
		    
		    event.preventDefault();
		});



		$(document).on("click", "#addPost", function(event) {
		    // Gather data from input fields
		    var description = $("#postContent").text().trim();
		    var url = $("#postUrl").val().trim();
		    var programmingLanguage = $("#programmingLanguage").val();
		    var professionalField = $("#professionalField").val();

		    // Validate content
		    if (description === "") {
		        alert("Post content cannot be empty.");
		        return;
		    }

		    // Validate other fields
		    if (url === "") {
		        alert("URL cannot be empty.");
		        return;
		    }

		    if (programmingLanguage === "") {
		        alert("Please select a programming language.");
		        return;
		    }

		    if (professionalField === "") {
		        alert("Please select a professional field.");
		        return;
		    }

		    // Prepare data for POST request
		    var postData = {
		        description: description,
		        url: url,
		        programmingLanguage: programmingLanguage,
		        professionalField: professionalField
		    };

		    // Send POST request
		    $.post("AddPost", postData)
		        .done(function(response) {
		            // Handle success response
		            console.log("Post added successfully:", response);
		            
		            // Reload timeline or perform any other action
		            $("#content").load("ViewFeedLogged.jsp");
		        })
		        .fail(function(jqXHR, textStatus, errorThrown) {
		            // Handle error response
		            console.error("Error adding post:", textStatus, errorThrown);
		            alert("Failed to add post. Please try again.");
		        });

		    // Prevent default form submission
		    event.preventDefault();
		});
    /* Delete tweet */
    $(document).on("click",".delTweet",function(event){
        var tweet = $(this).parent();
        $.post("DelTweet", { id: tweet.attr("id") }, function(response) {
            $("#content").load("GetOwnTimeline");                
        });
        event.preventDefault();
    });

    /* Follow user */
    $(document).on("click",".followUser",function(event){
        var user = $(this).parent();
        $.post("FollowUser", { id: user.attr("id") }, function(response) { 
            $("#content").load("GetFollowedUsers");
            $("#lcolumn").load("GetNotFollowedUsers");
        });
        event.preventDefault();
    });

    /* UnFollow user */
    $(document).on("click",".unfollowUser",function(event) {
        var user = $(this).parent();
        $.post("UnFollowUser", { id: user.attr("id") }, function(response) {
            $("#content").load("GetFollowedUsers");
            $("#lcolumn").load("GetNotFollowedUsers");
        });
        event.preventDefault();
    });
});
</script>
</head>

<body>
    <!-- Begin Navigation -->
    <div class="w3-bar w3-blue" id="navigation">
        <jsp:include page="${menu}" />
    </div>
    <!-- End Navigation -->
 
    <!-- Begin Content -->
    <div class="w3-container w3-card-4 w3-padding-24" id="content">
        <jsp:include page="${content}" />
    </div>
    <!-- End Content -->
    
    <script>
        function stack() {
            var x = document.getElementById("stack");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else { 
                x.className = x.className.replace(" w3-show", "");
            }
        }
    </script>
</body>
</html>
