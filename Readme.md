# Intruder Analyser

Intruder Analyser is a combination of a Portswigger Burp Suite extension and a React-based SPA designed to help you analyze Burp Intruder outputs more effectively.

![Intruder Analyser Screenshot](https://img.youtube.com/vi/bdMoQGFuLrcre/0.jpg)

## 🚀 How to Use

1.  Load the "Intruder Analyser" Burp extension in Burp Suite.
2.  Open the `index.html` file in your browser (located at `/src/main/resources/ui/index.html`).
3.  Run your Intruder job in Burp Suite.
4.  Work and enjoy the streamlined analysis in the React app!

## 🎥 Demo

The video is self-explanatory:
[![Watch the demo video](https://img.youtube.com/vi/bdMoQGFuLrcre/0.jpg)](https://www.youtube.com/watch?v=bdMoQGFuLrcre)

---

## ⚠️ Disclaimer

* This plugin is currently in a working PoC stage.
* As the Burp Montoya API does not provide all necessary functions, I'm still struggling with proper extraction of intruder payloads.
* I'm not sure about response time values - not properly tested
* Tbeen tested on **Windows** with **Firefox** only

---

## 📅 Future Works (One Day, Maybe)

* Add payload values
* Implement more filters (e.g., "payload reflected," "encoded payload reflected").
* Improve the UX when working with the histogram.
* Implement diff analysis on multiple responses (I've already tested some approaches with LLMs).

---

## 🤝 Contributing

**Everything is welcome!** I will be glad if someone more skilled in programming takes this idea and helps boost it to its maximum potential. :)

---

## ☕ Support
If you find this tool helpful, feel free to [buy me a coffee](https://coindrop.to/macrek)