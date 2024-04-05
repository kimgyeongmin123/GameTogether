const chatButton = document.getElementById('chat-Btn');

if (chatButton) {
    chatButton.addEventListener('click', event => {

        body = JSON.stringify({
            articleId: document.getElementById('article-id').value,
            author: document.getElementById('article-author').value,
            selectedGame: document.getElementById('article-selectedGame').value
        });

        function success(response) {
            response.text().then(text => {
                const data = JSON.parse(text);
                window.open("/chatRoom/" + data.id, "_blank", "width=600, height=400, location=no");
            }).catch(error => {
                console.error('Failed to read response body:', error);
            });
        }

        function fail() {
            alert('내가 쓴 글은 채팅을 할 수 없습니다.');
        }

        httpRequest('POST',`/api/ChatRoom`, body, success, fail);
    });
}