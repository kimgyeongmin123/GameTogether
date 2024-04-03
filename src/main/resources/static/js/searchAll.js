    const AllSearchButton = document.getElementById("AllSearch-btn");

    if (AllSearchButton) {
        AllSearchButton.addEventListener("click", () => {
            const input = document.getElementById("AllSearchInput").value;

            if (input.trim() === "") {
                alert("검색어를 입력하세요.");
                return;
            }

            window.location.href = `/articles/search/${input}`;

        });
    }