# Intruder Analyser

Intruder Analyser is a combination of a Portswigger Burp Suite extension and a React-based SPA, designed to help you to analyze Burp Intruder outputs more effectively. Plugin opens websocket on Burps side and feed it with intruder req/rsp messages. React then read these messages and presenting it to analyse.  

[<img src="https://github.com/MaCrek22/Intruder-Analyser/blob/main/screenshot.png" width="600" height="300"/>]


### 🚀 How to Use

1.  Load the "Intruder Analyser" Burp extension in Burp Suite.
2.  Open the `index.html` file in your browser (located at `/src/main/resources/ui/index.html`).
3.  Run your Intruder job in Burp Suite.
4.  Work and enjoy the streamlined analysis in the React app!


### 🎥 Demo

The video is self-explanatory:

[<img src="https://img.youtube.com/vi/bdMoQGFuLrc/hqdefault.jpg" width="600" height="300"/>](https://www.youtube.com/embed/bdMoQGFuLrc)


### ⚠️ Disclaimer

* This plugin is currently in a working PoC stage.
* As the Burp Montoya API does not provide all necessary functions, I'm still struggling with proper extraction of intruder payloads.
* I'm not sure about response time values - not properly tested
* been tested on **Windows** with **Firefox** only


### 📅 Future Works (One Day, Maybe)

* Add payload values
* Implement more filters (e.g., "payload reflected," "encoded payload reflected").
* Improve the UX when working with the histogram.
* Implement diff analysis on multiple responses (I've already tested some approaches with LLMs).


### 🤝 Contributing

Take it, use it, improve it, it's up to you.


### ☕ Support

If you find this tool helpful, feel free to [buy me a coffee](https://coindrop.to/macrek)

