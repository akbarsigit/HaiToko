$(document).ready(function(){{
	$("#logoutId").on("click", function(e){
		e.preventDefault();
		document.logoutForm.submit();
	});
	dropDownMenu()
}});
	
function dropDownMenu(){
	
	$(".navbar .dropdown").hover(function(){
		$(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
	}, function(){
		$(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp();	
	});
	
	$(".dropdown > a").click(function(){
		location.href=this.href;
	});
}

$(document).ready(function(){
			if(navigator.geolocation){
				navigator.geolocation.getCurrentPosition(onSuccess, onError);
			}else{
				alert("Your browser is not support for geolocation API");
			}
			
		});
	
		function onError(error){
			alert(error.message);
		}
	
	
		function onSuccess(position){
			var  apiKey = "4644fa98c58f8eb00826d23a7b4b6e2d";
			var {latitude, longitude} = position.coords;
			let api = `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&units=metric&appid=${apiKey}`;
			fetch(api).then(response => response.json()).then(result => weatherDetails(result));
		}
	
	
		function weatherDetails(info){
			var city = info.name;
			var country = info.sys.country;
			var {desc, id} = info.weather[0];
			
			console.log(info);
		
			var {temp, feels_like, humidity} = info.main;
	
			document.getElementById("weather").innerHTML = "Today temprature at " + info.name +" is "+ Math.floor(temp) + " °C &nbsp|&nbsp With today weather is " + info.weather[0].main;
		   
		}

