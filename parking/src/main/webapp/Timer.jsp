<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Ticket Details</title>
</head>

<p id="demo"></p>

<script>
//Place here the total of seconds you receive on your PHP code. ie: var initialTime = <? echo $remaining; ?>;
var initialTime = 30;

var seconds = initialTime;
function timer() {
    var days   = Math.floor(seconds/24/60/60);
    var hoursLeft   = Math.floor((seconds) - (days*86400));
    var hours       = Math.floor(hoursLeft/3600);
    var minutesLeft = Math.floor((hoursLeft) - (hours*3600));
    var minutes     = Math.floor(minutesLeft/60);
    var remainingSeconds = seconds % 60;
    if (remainingSeconds < 10) {
        remainingSeconds = "0" + remainingSeconds; 
    }
    document.getElementById('demo').innerHTML = days + "day " + hours + "hrs " + minutes + "minute " + remainingSeconds+ "seconds";
    if (seconds == 15) {
        //clearInterval(countdownTimer);
        window.alert("You have only 15 Seconds Leftout!You need to renew your Ticket ");
        //document.getElementById('demo').innerHTML = "Completed";
       // seconds--;
    } if(seconds == 0){
    	 window.alert("Your Time is UP ");
    	clearInterval(countdownTimer);
    	
    }else {
        seconds--;
    	//clearInterval(countdownTimer);
    }
}
var countdownTimer = setInterval('timer()', 1000);





</script>

</body>
</html>