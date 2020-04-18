const coords = {
    Victoria: [
        23.73014830000,
        37.99269130000
    ],
    TheBigBadWolf: [
        23.73160050000,
        37.99219550000
    ],
    motoPlace: [
        23.74046130000,
        37.99007600000
    ]
};


let srcPlaces = document.querySelectorAll("#src-menu a");
let source = document.querySelector("#source");
srcPlaces.forEach(place => {
    place.addEventListener("click", evt => {
        source.value = place.innerHTML;
    })
});


let dstPlaces = document.querySelectorAll("#dst-menu a");
let destination = document.querySelector("#destination");
dstPlaces.forEach(place => {
    place.addEventListener("click", evt => {
        console.log(place.innerHTML);
        destination.value = place.innerHTML;
    })
});


function submitForm(event) {
    let srcCoords = coords[source.value];
    let destCoords = coords[destination.value];
    console.log(srcCoords);
    console.log(destCoords);

    axios.post('/nav', {
        srcLat: srcCoords[1],
        srcLon: srcCoords[0],
        destLat: destCoords[1],
        destLon: destCoords[0]
    })
        .then(function (response) {
            let data = response.data;
            let container = document.createElement("div");
            container.className = "main-container";
            container.style.gridRow = 5;
            let h3 = document.createElement("h3");
            h3.innerText = "Details";
            container.appendChild(h3);
            let detailParagraph = document.createElement("p");
            detailParagraph.innerText = `Distance(m): ${data.distance}, time(ms): ${data.time}`;
            container.appendChild(detailParagraph);

            let instH3 = document.createElement("h3");
            instH3.innerText = "Instructions";
            container.appendChild(instH3);

            data.instructions.forEach(instruction => {
                let paragraph = document.createElement("p");
                paragraph.innerText = JSON.stringify(instruction);
                container.appendChild(paragraph);
            });
            let grid = document.querySelector(".grid-wrapper");
            grid.appendChild(container);
            console.log(response);
        });

    event.preventDefault();

}