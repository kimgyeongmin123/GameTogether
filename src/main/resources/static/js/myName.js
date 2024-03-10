var myName;
document.addEventListener('DOMContentLoaded', function() {

    function success(response) {
        response.text().then(text => {
                console.log('text 출력.', text);
                const data = JSON.parse(text);
                console.log('로그인한 유저 아이디 : ', data.username);
                myName = data.username;
            }).catch(error => {
                console.error('Failed to read response body:', error);
            });
        }

        function fail() {
            alert('내가 쓴 목록을 조회실패했습니다.');
            location.replace('/articles');
        }

        httpRequest('GET',`/api/userinfo`, null, success, fail);

});