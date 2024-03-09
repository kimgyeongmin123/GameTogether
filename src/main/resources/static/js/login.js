const loginButton = document.getElementById("login-btn");

if(loginButton){
    loginButton.addEventListener("click", (event) => {
        fetch("/login", {
            method : "POST",
            headers : {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email:document.getElementById("floatingInput").value,
                password:document.getElementById("floatingPassword").value,
            }),
        }).then(response => {
            if(!response.ok){
                throw new Error("로그인 실패");
            }
            return response.text();
        })
        .then(token => {
            localStorage.setItem("access_token", token);
            window.location.href = "/articles";
        })
        .catch(error => {
            console.error("로그인 에러 : ", error);
            alert("아이디와 비밀번호를 확인해주세요.");
        });
    });
}