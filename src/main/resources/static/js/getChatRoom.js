document.addEventListener('DOMContentLoaded', function() {
            function success(response) {
                response.json().then(chatRoomList => {

                    chatRoomList.forEach(function(chatRoom) {

                        var displayName = (myName === chatRoom.author) ? chatRoom.username : chatRoom.author;

                        $("#chatRoomList").append(
                                "<div class='room'><div class='game' onclick='myChat("+chatRoom.id+")'>"+chatRoom.selectedGame+"</div><div class='other'>"+displayName+"</div></div>"
                        );

                        function myChat() {
                            window.open("/chatRoom/" + chatRoom.articleId, "_blank", "width=600, height=400, location=no");
                        }
                    });

                    }).catch(error => {
                        console.error('Failed to parse JSON response:', error);
                    });
            }

            function fail() {
                alert('채팅방을 조회실패했습니다.');
                location.replace('/articles');
            }

            httpRequest('GET',`/api/ChatRoom`, null, success, fail);
    });

    function myChat(chatRoomId) {
            window.open("/chatRoom/" + chatRoomId, "_blank", "width=600, height=400, location=no");
        }