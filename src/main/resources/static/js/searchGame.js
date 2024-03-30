    const searchButton = document.getElementById("search-btn");

    if (searchButton) {
        searchButton.addEventListener("click", () => {
            const selectedGame = document.getElementById("inputGroupSelect04").value;

            if (selectedGame === 'all') {
                window.location.href = '/articles';
            } else {
                window.location.href = `/articles/list/${selectedGame}`;
            }
        });
    }