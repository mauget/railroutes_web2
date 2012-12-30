
// To cloners: change APP.urlStem to your public URL

    var APP = APP || {};

    APP.urlStem = 'http://myjavaneo4j.herokuapp.com';
	
	APP.drawMap = function() {
    	
        var mapOptions = {
          center: new google.maps.LatLng(39.0997, -94.5783),
          zoom: 4,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        APP.map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	};
	
      
    $(document).ready(function(){
    	APP.drawMap();
    	
    	// Draw route1
    	$('#buttonRoute').click(function() {
    		// Would rather use a template here, but we does the best with what we have  ..
    	    // http://myjavaneo4j.herokuapp.com/Controller?nodeA=68214&nodeB=40126 
    		var finalUrl = APP.urlStem+'/Controller?nodeA='+$('#fromId').val()+'&nodeB='+$('#toId').val();
    		
    		APP.georssLayer = new google.maps.KmlLayer(finalUrl);
    		APP.georssLayer.setMap(APP.map);
    		
    		console.log(APP.georssLayer.getUrl());
    	});

    	// Clear -- redraw unadorned map
    	$('#buttonReset').click(function() {
    		APP.drawMap();
    	});
    	
    });
