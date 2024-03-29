var myName;
var myNickName;
var myAgeGroup
document.addEventListener('DOMContentLoaded', function() {

    const token = searchParam('token')

    if(token){
        localStorage.setItem("access_token", token)
    }

    function searchParam(key){
        return new URLSearchParams(location.search).get(key);
    }

    function success(response) {
        response.text().then(text => {
                console.log('text 출력.', text);
                const data = JSON.parse(text);
                console.log('로그인한 유저 아이디 : ', data.username);
                myName = data.username;
                myNickName = data.nickname;
                myAgeGroup = data.agegroup;
                console.log('로그인한 유저 닉네임 : ', data.nickname);
                console.log('로그인한 유저 나이대 : ', data.agegroup);

                document.getElementById("myNameSpan").textContent = myNickName;

                if(myAgeGroup === null){
                    window.location.href = '/additional_info';
                }
            }).catch(error => {
                console.error('Failed to read response body:', error);
            });
        }

        function fail() {
            alert('정보를 불러올 수 없습니다.');
            location.replace('/login');
        }

        httpRequest('GET',`/api/OAuth2userinfo`, null, success, fail);

});