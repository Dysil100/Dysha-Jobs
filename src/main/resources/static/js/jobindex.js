// Code JavaScript pour récupérer l'image et définir l'icône de la page
// Créer une balise <link> pour l'icône
var iconLink = document.createElement('link');
iconLink.rel = 'icon';
iconLink.href = '/files/DyshaJobs_Logo';
iconLink.type = 'image/*';
document.head.appendChild(iconLink);


document.querySelector('.menu-toggle').addEventListener('click', function() {
    document.querySelector('.toolbar ul').classList.toggle('open');
});

