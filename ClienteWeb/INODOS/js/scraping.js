const puppeteer = require("puppeteer");

async function run () {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();

    await page.goto('https://ca.eltiempo.es/calidad-aire/valencia');
    //await page.type('#inputSearch', 'Gandia');
    //await page.screenshot({path: 'pruebaCaptura.jpg', fullPage: true});

    const meds = await page.evaluate(()=> {
        const elements = document.querySelectorAll('[data-title="NO2"] p span');
        const mediciones = [];
        for (let element of elements) {
            mediciones.push(element.innerHTML)
        }
        return mediciones;
    })

    for (let valor of meds) {
        
    }

    console.log(meds[9]);

    await browser.close();
    
    return meds[9];

}
run();