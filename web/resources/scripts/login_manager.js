/*
	Authur 		: Chandika Jayawardena
	Version		: Initial verison(1.0)
	Create Date	: 27 June 2017
	Module 		: scripts
	Descript	: This modue contains the client side functionalites of the login module

*/

var loginManager = (function () {

    //Private methods and variables


    return {
        //Public methods and variables

        //Login form validation method
        validateLogin: function () {
            document.getElementById("idLoginForm").submit();
        },

        //Page event bindings
        bindPageEvents: function(){
        	setInterval(loginManager.setDateTime,1000);

        	$('.btn-login').on('click',function(){
				return loginManager.validateLogin();
	    	});

			$('.lnk-sys').on('click',function(){
    			$('.tle-infor-panel .close-pop').click();
    			$('.system-infor-panel').fadeIn('slow');
    			$('.sys-down-arrow').show();
    		});

    		$('.system-infor-panel .close-pop').on('click',function(){
    			$('.system-infor-panel').fadeOut('fast');
    			$('.sys-down-arrow').hide();
    		});

    		$('.lnk-about').on('click',function(){
    			$('.system-infor-panel .close-pop').click();
    			$('.tle-infor-panel').fadeIn('slow');
    			$('.tle-down-arrow').show();
    		});

    		$('.tle-infor-panel .close-pop').on('click',function(){
    			$('.tle-infor-panel').fadeOut('fast');
    			$('.tle-down-arrow').hide();
    		});	

    		$('.do-nothing').on('click',function(e){
    			e.preventDefault();
    		});

        },

        //Brand animation
        setBrandAnimation: function(){
			setTimeout(function(){
				$('.brand').addClass('eff-movetop');
				$('.login-box').addClass('eff-fadeinscale');
	    	},1200);
        },

        //Populate date time widget
        setDateTime: function(){
        	var time = new Date(),
			hours = time.getHours(),
			minutes = time.getMinutes();
			seconds = time.getSeconds();
			date = time.getDate();

    		var weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
    		var month = ["January","February","March","April","May","June","July","August","September","October","November","December"];

    		ampm = 'AM';
    		if(hours>=12){ ampm = 'PM'; }

    		function harold(standIn) {
				if (standIn < 10) {
					standIn = '0' + standIn
				}
				return standIn;
			}

    		var _html = harold(hours) + ":" + harold(minutes)+ ":" + harold(seconds)+" "+ampm+"<br />"+weekday[time.getDay()]+", "+harold(date)+" "+month[time.getMonth()];
			$('.sys-time').html(_html);
        },

        //Show or hide error message
        setErrorMSG: function(msg){
			$('.msg-panel').html(msg);
		}
	};

})();


$(document).ready(function(){
	loginManager.setBrandAnimation();
	loginManager.bindPageEvents();
});