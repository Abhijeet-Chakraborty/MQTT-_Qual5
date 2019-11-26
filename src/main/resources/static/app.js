$(document).ready(function(){
	connect();
})
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}

function connect() {
    var socket = new SockJS('/websocket-conn');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/websocket', function (greeting) {
        	console.log("&&&&&&&&&&"+JSON.parse(greeting.body))
            showGreeting(greeting.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function startConnection() {
    stompClient.send("/app/startConnection", {}, JSON.stringify({'ipAddress': $("#ipaddress").val(),
    	'port': $("#port").val(),
    	'topic': $("#topic").val()}));
}

function stopConnection() {
    stompClient.send("/app/stopConnection", {});
}

function showGreeting(message) {
    $("#deviceinfo").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#connect-mqtt" ).click(function() { startConnection(); });
    $( "#disconnect-mqtt" ).click(function() { stopConnection(); });
});