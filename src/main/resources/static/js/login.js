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
                password:document.getElementByDi("floatingPassword").value,
            }),
        }).then(()=>{
            location.replace("/articles");
        });
    });
}