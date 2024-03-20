// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        function success(response) {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        }

        function fail() {
            alert('자신이 쓴 글만 삭제할 수 있습니다.');
        }

        httpRequest('DELETE',`/api/articles/${id}`, null, success, fail);
    });
}

// 수정 기능
//const modifyButton = document.getElementById('modify-btn');
//
//if (modifyButton) {
//    modifyButton.addEventListener('click', event => {
//        let params = new URLSearchParams(location.search);
//        let id = params.get('id');
//
//        body = JSON.stringify({
//            title: document.getElementById('title').value,
//            content: document.getElementById('content').value
//        })
//
//        function success() {
//            alert('수정 완료되었습니다.');
//            location.replace(`/articles/${id}`);
//        }
//
//        function fail() {
//            alert('수정 실패했습니다.');
//            location.replace(`/articles/${id}`);
//        }
//
//        httpRequest('PUT',`/api/articles/${id}`, body, success, fail);
//    });
//}

// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    // 등록 버튼을 클릭하면 /api/articles로 요청을 보낸다
    createButton.addEventListener('click', event => {
        body = JSON.stringify({
            selectedGame: selectedGame,
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        });
        function success(response) {
            alert('등록 완료되었습니다.');
            location.replace('/articles');
        };
        function fail() {
            alert('등록 실패했습니다.');
            location.replace('/articles');
        };

        httpRequest('POST','/api/articles', body, success, fail)
    });
}


