    // Code JavaScript pour récupérer l'image et définir l'icône de la page
    fetch('/files/DyshaJobs_Logo')
    .then(response => response.blob())
    .then(blob => {
    const url = URL.createObjectURL(blob);
    const favicon = document.getElementById('favicon');
    favicon.href = url;
});

    // Afficher ou masquer le menu déroulant en fonction de l'état de la case à cocher
    document.getElementById("menu-checkbox").addEventListener("change", function() {
        var menu = this.parentNode.nextSibling;
        if (this.checked) {
            menu.style.display = "flex";
        } else {
            menu.style.display = "none";
        }
    });
