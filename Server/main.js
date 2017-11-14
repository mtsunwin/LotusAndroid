var http = require("http").createServer()
var HashMap = require("hashmap")
var uuidv1 = require('uuid/v1');
var io = require("socket.io")(http)
var userLists = new HashMap()
io.on("connection", function(socket) {
	var id = uuidv1()
	userLists.set(id, socket)
	console.log("so luong dang ket noi: " + userLists.count())
	socket.on("disconnect", function() {
		userLists.delete(id)
		console.log("so luong dang ket noi con lai: " + userLists.count())
	})
	socket.on("message", function(data) {
		console.log(data);
		userLists.forEach(function(value, key) {
			if(key != id){
				value.emit("message", data)
				console.log("da gui")
			}
		})
	})
	var list = ""
	userLists.forEach(function(value, key) {
		list += key + ","
	})
	userLists.forEach(function(value, key) {
		value.emit("list", list.replace(key + ",", ""))
	})
})
http.listen(4567, function() {
	console.log("4567")
})