const client = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/ws',
    debug: function (str) {
        console.log(str);
    },
    onConnect: () => {
        console.log("연결")
        client.subscribe('thing/product/hi', message =>
            console.log(`Received: ${message.body}`)
        );
    },
});

client.activate();
