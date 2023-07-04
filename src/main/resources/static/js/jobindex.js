// Code JavaScript pour récupérer l'image et définir l'icône de la page
fetch('/files/DyshaJobs_Logo')
    .then(response => response.blob())
    .then(blob => {
        const url = URL.createObjectURL(blob);
        const favicon = document.getElementById('favicon');
        favicon.href = url;
    });

document.querySelector('.menu-toggle').addEventListener('click', function() {
    document.querySelector('.toolbar ul').classList.toggle('open');
});
