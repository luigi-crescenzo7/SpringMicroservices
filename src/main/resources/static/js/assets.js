function fetch_assets() {
    const pTag = document.getElementById('data');
    fetch('https://localhost:8443/api/contract/all')
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            pTag.textContent = JSON.stringify(data);
        }).catch((error) => console.error(error))
}