
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/bg.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
	
	
	
	
    
});




/////properties data expand in ipad

$(".dashbord-item").on("click",".propertiesReadMore.closed",function(){
var data = $(this).closest(".propInfo").find(".propDesc").html();
$(this).html("Less <i class='fa fa-chevron-up'></i>").removeClass("closed").addClass("open");
$(this).closest(".dashbord-item").find(".propInfoDataIpad").html(data).promise().done(function(){
        $(this).slideDown();
    });
});

$(".dashbord-item").on("click",".propertiesReadMore.open",function(){
$(this).html("More <i class='fa fa-chevron-down'></i>").removeClass("open").addClass("closed");
$(this).closest(".dashbord-item").find(".propInfoDataIpad").slideUp();
});