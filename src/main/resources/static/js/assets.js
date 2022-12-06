/*fetch("./js/config.json")
    .then((response) => response.json())
    .then((data) => console.log(data))
    .catch((error) => console.error(error))*/

function fetch_assets() {
    const windowUrl = window.location.host;
    let url = 'http://localhost:8087/api/contract/all';
    if (!windowUrl.includes('localhost')) {
        url = 'http://host.minikube.internal:8087/api/contract/all'
    }
    const pTag = document.getElementById('data');
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            pTag.textContent = JSON.stringify(data);
        }).catch((error) => console.error(error))
}