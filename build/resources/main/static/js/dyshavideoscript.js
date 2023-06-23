function subscribeTo(userId) {
    // Effectuer une requête AJAX pour s'abonner à l'utilisateur avec l'ID donné
    // Vous pouvez utiliser une bibliothèque JavaScript comme jQuery ou Axios pour effectuer la requête AJAX
    // Par exemple, en utilisant Axios :
    axios.post('/subscribevideo/' + userId + '/subscribe')
        .then(function(response) {
            // Gérer la réponse de la requête si nécessaire
            console.log(response);
        })
        .catch(function(error) {
            // Gérer les erreurs de la requête si nécessaire
            console.error(error);
        });
}

function likeVideo(videoId) {
    // Effectuer une requête AJAX pour aimer la vidéo avec l'ID donné
    // Vous pouvez utiliser une bibliothèque JavaScript comme jQuery ou Axios pour effectuer la requête AJAX
    // Par exemple, en utilisant jQuery :
    $.post('/likevideo/' + videoId + '/like', function(response) {
        // Gérer la réponse de la requête si nécessaire
        console.log(response);
    })
        .fail(function(error) {
            // Gérer les erreurs de la requête si nécessaire
            console.error(error);
        });
}
