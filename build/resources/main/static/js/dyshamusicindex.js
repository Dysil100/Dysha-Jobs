
function playAudio(thumbnailContainer) {
    var audio = thumbnailContainer.querySelector('audio');
    var allAudio = document.getElementsByTagName('audio');
    var elementsByClassName = document.getElementsByClassName("playing");
    for (let i = 0; i < elementsByClassName.length; i++) {
       elementsByClassName[i].classList.remove('playing');
    }
    // Pause tous les éléments audio en cours
    for (var i = 0; i < allAudio.length; i++) {
        if (allAudio[i] !== audio) {
            stopAudio(allAudio[i], thumbnailContainer)
        }
    }

    if(audio.paused === false){
        stopAudio(audio, thumbnailContainer)
    }else {
        playDyshaAudio(audio, thumbnailContainer)
    }
}

function playDyshaAudio(element, thumbnailContainer) {
    thumbnailContainer.classList.remove('hovered');
    // Supprimer la classe "hovered" de l'élément
    element.play()
// Lorsque la musique est en cours de lecture
    thumbnailContainer.classList.add('playing');

}

function stopAudio(element, thumbnailContainer) {
    element.pause();
// Lorsque la musique n'est pas en cours de lecture
    thumbnailContainer.classList.remove('playing');

}

function startAudio(element) {
    element.classList.add('playing');
}
