const chatButton = document.getElementById('chat-Btn');

if (chatButton) {
    chatButton.addEventListener('click', event => {

    console.log('articleId', document.getElementById('article-id').value);

        body = JSON.stringify({
            articleId: document.getElementById('article-id').value,
            author: document.getElementById('article-author').value,
            selectedGame: document.getElementById('article-selectedGame').value
        });

        function success(response) {
            response.text().then(text => {
                console.log('text 출력.', text);
                const data = JSON.parse(text);
                console.log('이 번호의 채팅방으로 갈거임.', data.id);
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