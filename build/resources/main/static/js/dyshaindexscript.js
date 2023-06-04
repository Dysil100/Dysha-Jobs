function uploadVideo() {
    window.location.href = "/dyshatube/upload";
}

function goToHomePage() {
    window.location.href = "/dyshatube";
}

function goToMyAccount() {
    window.location.href = "/dyshatube/myaccount";
}
function goToTendances() {
    window.location.href = "/dyshatube/tendances";
}
function goToAbonements() {
    window.location.href = "/dyshatube/abonements";
}
function goToBibliotheke() {
    window.location.href = "/dyshatube/bibliotheke";
}function goTohistorique() {
    window.location.href = "/dyshatube/historique";
}

function playVideo(element) {
    var video = element.querySelector('.video-preview');
    video.currentTime = 0; // Définit le point de départ de l'extrait (en secondes)
    video.style.opacity = 1; // Affiche la vidéo
    video.play();
}

function pauseVideo(element) {
    var video = element.querySelector('.video-preview');
    video.style.opacity = 0; // Masque la vidéo
    video.pause();
}
