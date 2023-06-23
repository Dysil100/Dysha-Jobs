function displayImages(event) {
    var previewContainer = document.getElementById("image-preview");
    previewContainer.innerHTML = ""; // Effacer les images précédemment affichées

    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        var reader = new FileReader();

        reader.onload = function(e) {
            var img = document.createElement("img");
            img.src = e.target.result;
            img.classList.add("preview-image");
            previewContainer.appendChild(img);
        }

        reader.readAsDataURL(file);
    }
}

/*
uodatecities
 */
function updateCities(villeId, regionId) {
    const region = document.getElementById(regionId).value;
    const citiesSelect = document.getElementById(villeId);

    // Efface les options existantes
    citiesSelect.innerHTML = '';

    // Appel AJAX pour récupérer les villes de la région sélectionnée
    fetch('/villes?region=' + encodeURIComponent(region))
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erreur lors de la récupération des villes');
            }
        })
        .then(cities => {
            // Ajoute les options des villes dans le select
            for (const city of cities) {
                const option = document.createElement('option');
                option.value = city;
                option.textContent = city;
                citiesSelect.appendChild(option);
            }
        })
        .catch(error => {
            console.error(error);
        });
}
