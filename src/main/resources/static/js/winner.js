fetch("/winner")
    .then(response => response.json())
    .then(candidate => {

        document.getElementById("winner").innerHTML = `
            <h3>${candidate.candidateName}</h3>
            <p><strong>Party:</strong> ${candidate.party}</p>
            <p><strong>Total Votes:</strong> ${candidate.votes}</p>
        `;

    })
    .catch(error => {
        document.getElementById("winner").innerHTML =
            "Unable to load winner.";
        console.error(error);
    });