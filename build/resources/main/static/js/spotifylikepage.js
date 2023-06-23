// Funktion zum Abspielen/Pause des Musikstücks
function playpauseTrack() {
    var audio = document.getElementById("audio");
    if (audio.paused) {
        audio.play();
        document.querySelector(".playpause-track i").className = "fa fa-pause-circle fa-5x";
    } else {
        audio.pause();
        document.querySelector(".playpause-track i").className = "fa fa-play-circle fa-5x";
    }
}

function setVolume() {
    var volumeSlider = document.querySelector('.volume_slider');
    var audioPlayer = document.getElementById('audio');

    var volumeValue = volumeSlider.value / 100;
    audioPlayer.volume = volumeValue;
}

function seekTo() {
    var seekSlider = document.querySelector('.seek_slider');
    var audioPlayer = document.getElementById('audio');

    var seekValue = seekSlider.value;
    var duration = audioPlayer.duration;
    var currentTime = (seekValue / 100) * duration;
    audioPlayer.currentTime = currentTime;
}
