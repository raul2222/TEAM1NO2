const puppeteer = require('puppeteer');

function iniciarScraping() {
    console.log("Empezamos el Scraping");
    
    const browser = await puppeteer.launch();
    
    const page = await browser.newPage();
    
    await page.goto('https://www.amazon.es');
}
