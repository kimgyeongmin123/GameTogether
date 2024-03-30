    const AllSearchButton = document.getElementById("AllSearch-btn");

    if (AllSearchButton) {
        AllSearchButton.addEventListener("click", () => {
            const input = document.getElementById("AllSearchInput").value;


            window.location.href = `/articles/search/${input}`;

        });
    }