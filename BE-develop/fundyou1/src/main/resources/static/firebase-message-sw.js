// resources/static/firebase-message-sw.js
 importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
 importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');
 // Initialize Firebase
 var config = { apiKey: "AIzaSyAI5wWWXhm691_0daTF1MfEtimXpAsa39I", authDomain: "fundyou-3808d.firebaseapp.com", projectId: "fundyou-3808d", storageBucket: "fundyou-3808d.appspot.com", messagingSenderId: "215863555712", appId: "1:215863555712:web:3fe9ab09d8def9e1a55865", measurementId: "G-8YRV0TMMQ9" }; firebase.initializeApp(config); const messaging = firebase.messaging(); messaging.setBackgroundMessageHandler(function (payload) { const title = "Hello World"; const options = { body: payload.data.status }; return self.registration.showNotification(title, options); });​


