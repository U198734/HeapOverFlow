<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#iterator').load('PostsController');
 });
</script>

<br><br><br>
<div class="w3-container w3-card-4 w3-round-large w3-white w3-margin-bottom">
    <h6 class="w3-opacity w3-padding-16"> Got something to share? Create a post </h6>
    
    <!-- Post Content (Text) -->
    <div class="w3-container">
        <p id="postContent" contenteditable="true" class="w3-input w3-border w3-margin-bottom w3-round-large"
            style="min-height: 100px;" placeholder="Write your post content..."></p>
    </div>
    
    <!-- URL Input -->
    <div class="w3-container">
        <input id="postUrl" type="text" class="w3-input w3-border w3-margin-bottom w3-round-large"
            placeholder="URL">
    </div>
    
    <!-- Programming Language Toggle List -->
    <div class="w3-container">
        <label for="programmingLanguage">Programming Language:</label>
        <select id="programmingLanguage" class="w3-select w3-border w3-round-large" style="padding-left: 10px;">
            <option value="">Select language</option>
            <option value="Java">Java</option>
            <option value="Python">Python</option>
            <option value="JavaScript">JavaScript</option>
            <option value="C++">C++</option>
        </select>
    </div>
    
    <!-- Professional Field Toggle List -->
    <div class="w3-container">
        <label for="professionalField">Professional Field:</label>
        <select id="professionalField" class="w3-select w3-border w3-round-large" style="padding-left: 10px;">
            <option value="">Select field</option>
            <option value="Software Development">Software Development</option>
            <option value="Data Science">Data Science</option>
            <option value="Web Development">Web Development</option>
            <option value="Machine Learning">Machine Learning</option>
        </select>
    </div>
    
    <!-- Submit Button -->
    <div class="w3-container w3-center w3-margin-top">
        <button id="addPost" type="button" class="w3-button w3-orange w3-round-large w3-padding-large w3-margin-bottom">
            <i class="fa fa-pencil"></i> &nbsp; Post
        </button>
    </div>
</div>



<div id="iterator">
</div>