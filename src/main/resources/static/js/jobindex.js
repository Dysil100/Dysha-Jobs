// Code JavaScript pour récupérer l'image et définir l'icône de la page
fetch('/dysha_files/DyshaJobs_Logo')
    .then(response => response.blob())
    .then(blob => {
        const url = URL.createObjectURL(blob);
        const favicon = document.getElementById('favicon');
        favicon.href = url;
    });

document.querySelector('.menu-toggle').addEventListener('click', function() {
    document.querySelector('.toolbar ul').classList.toggle('open');
});

// Récupérer la référence vers l'élément du bouton de menu
var menuToggle = document.querySelector('.menu-toggle');

// Récupérer la référence vers l'élément de la liste du menu
var menuList = document.querySelector('nav ul');

// Ajouter un gestionnaire d'événement pour le clic sur le bouton de menu
menuToggle.addEventListener('click', function() {
    // Ajouter ou supprimer la classe 'active' pour afficher ou masquer le menu déroulant
    menuList.classList.toggle('active');
});

