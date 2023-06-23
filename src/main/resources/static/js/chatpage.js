function updateMessages() {
    const sender = document.getElementById('sender').value;
    const receiver = document.getElementById('receiver').value;
    const chatBody = document.querySelector('.chat-body');

    setInterval(() => {
        fetch(`/messages/${sender}/${receiver}`)
            .then(response => response.json())
            .then(messages => {
                chatBody.innerHTML = '';
                messages.forEach(message => {
                    const chatMessage = document.createElement('div');
                    chatMessage.className = message.sender === sender ? 'chat-message sent' : 'chat-message';
                    chatMessage.innerHTML = `
            <span>${message.sujet || ''}</span><br>
            <p>${message.message}</p>
            <span>${message.createdAt}</span>
          `;
                    chatBody.appendChild(chatMessage);
                });
            })
            .catch(error => {
                console.error('Error fetching messages:', error);
            });
    }, 2000);
}

// Appeler la fonction updateMessages lorsque la page est chargée
window.addEventListener('load', updateMessages);
