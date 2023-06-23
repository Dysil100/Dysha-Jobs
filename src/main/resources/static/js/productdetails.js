document.addEventListener("DOMContentLoaded", function() {
    var carousel = document.querySelector("#carouselExample");
    var carouselItems = carousel.querySelectorAll(".carousel-item");
    var carouselPrev = carousel.querySelector(".carousel-control-prev");
    var carouselNext = carousel.querySelector(".carousel-control-next");
    var currentIndex = 0;

    // Afficher le premier élément actif
    carouselItems[currentIndex].classList.add("active");

    // Fonction pour passer à l'élément précédent
    function goToPrevItem() {
        carouselItems[currentIndex].classList.remove("active");
        currentIndex = (currentIndex - 1 + carouselItems.length) % carouselItems.length;
        carouselItems[currentIndex].classList.add("active");
    }

    // Fonction pour passer à l'élément suivant
    function goToNextItem() {
        carouselItems[currentIndex].classList.remove("active");
        currentIndex = (currentIndex + 1) % carouselItems.length;
        carouselItems[currentIndex].classList.add("active");
    }

    // Gérer les événements de clic sur les boutons de navigation
    carouselPrev.addEventListener("click", goToPrevItem);
    carouselNext.addEventListener("click", goToNextItem);
});


// Récupérer toutes les images du carrousel
var images = document.querySelectorAll('.carousel-item');

// Ajouter un gestionnaire d'événement de clic à chaque image
images.forEach(function(image) {
    image.addEventListener('click', function() {
        // Récupérer l'URL de l'image en cliquant sur celle-ci
        var imageUrl = this.getAttribute('src');

        // Créer une fenêtre modale pour afficher l'image en grand
        var modal = document.createElement('div');
        modal.classList.add('modal');

        var modalContent = document.createElement('div');
        modalContent.classList.add('modal-content');

        var modalImage = document.createElement('img');
        modalImage.setAttribute('src', imageUrl);

        modalContent.appendChild(modalImage);
        modal.appendChild(modalContent);

        // Ajouter la fenêtre modale à la page
        document.body.appendChild(modal);

        // Fermer la fenêtre modale lorsque l'utilisateur clique en dehors de l'image
        modal.addEventListener('click', function(event) {
            if (event.target === modal) {
                document.body.removeChild(modal);
            }
        });
    });
});
