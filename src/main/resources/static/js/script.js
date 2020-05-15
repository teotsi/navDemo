const coords = {};

let srcMenu = document.querySelector("#src-menu");
let source = document.querySelector("#source");

let dstMenu = document.querySelector("#dst-menu");
let destination = document.querySelector("#destination");

function createOptions(locations, menu, label) {
    locations.forEach((l) => {
        let newLocationA = document.createElement("a");
        newLocationA.href = "#";
        newLocationA.className = "dropdown-item";
        newLocationA.innerText = l.name;
        newLocationA.addEventListener("click", evt => {
            label.value = newLocationA.innerText;
        });
        menu.appendChild(newLocationA);

    });
}

axios.get('/poi')
    .then(function (response) {
        let locations = response.data;
        locations.forEach((l) => {
            coords[l.name] = [l.lat, l.lon];
        });
        createOptions(locations, srcMenu, source);
        createOptions(locations, dstMenu, destination);
        console.log(coords)
    });

function submitForm(event) {
    let srcCoords = coords[source.value];
    let destCoords = coords[destination.value];
    console.log(srcCoords);
    console.log(destCoords);

    axios.post('/nav', {
        srcLat: srcCoords[0],
        srcLon: srcCoords[1],
        destLat: destCoords[0],
        destLon: destCoords[1]
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
                let h4 = document.createElement("h4");
                h4.innerText = "Instruction: ";

                let paragraph = document.createElement("p");
                paragraph.classList.add('point-details');
                paragraph.innerHTML += `Sign: ${instruction.sign} <br> 
                                       name: ${instruction.name} <br>
                                       distance: ${instruction.distance} <br>
                                       time(ms): ${instruction.time} <br>
                                       angle: ${instruction.angle} <br>
                                       <span> points: ${JSON.stringify(instruction.points,
                    null, 4)}</span>`;
                container.appendChild(h4);
                container.appendChild(paragraph);
            });
            let grid = document.querySelector(".grid-wrapper");
            grid.appendChild(container);
        })
        .catch(function (error) {
            console.log(error);
        });

    event.preventDefault();

}