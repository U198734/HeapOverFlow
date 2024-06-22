<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#iterator').load('PostsController');
 });
</script>


<div class="w3-container w3-card w3-round w3-white w3-section">
	<h6 class="w3-opacity"> ${user.name}, what are you thinking? </h6>
	<p id="tweetContent" contenteditable="true" class="w3-border w3-padding"> </p>
	<button id="addTweet" type="button" class="w3-button w3-theme w3-section"><i class="fa fa-pencil"></i> &nbsp;Post</button> 
</div>

 
<div id="iterator">
</div>